/*
Author: Philip Harlow
Class: cs452
Teacher: Misurda
Program: This program represents a graphics library that can set pixels in the terminal to a particular color. this allows us to draw shapes and move the shapes around the canvas
like in a small video game. the graphics library will use a driver program to read input and keypresses allowing the user to move the shapes around.
*/

#include <fcntl.h>
#include <sys/types.h>
#include <sys/ioctl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <unistd.h>
#include <termios.h>
#include <sys/select.h>
#include <sys/time.h>
#include <linux/fb.h>
#include "iso_font.h"

struct fb_var_screeninfo vinfo;
struct fb_fix_screeninfo finfo;
long int screensize;
typedef short color_t;
struct timespec timeX, timeRem;
void * map;
struct termios old_term;
int filedesc;
static int xGlobal, yGlobal;
int draw_char(int x, int y, char c, color_t color);
void clear_screen();


/*
This function will initiliaze our graphics library and prepare the memory and screen for writing into it.
*/
void init_graphics() {

    //we first open the framebuffer that we will use to store our pixels in to show on the screen.
    filedesc = open("/dev/fb0", O_RDWR);
    if(filedesc < 0) {
        //checking to make sure we opened the framebuffer, if not we need to exit
        write(2, "We failed opening the frame buffer\n", 35);
        return;
    }

    if(ioctl(filedesc, FBIOGET_VSCREENINFO, &vinfo) < 0) {
        write(2, "Error reading variable info\n", 28);
        return;
    }

    if(ioctl(filedesc, FBIOGET_FSCREENINFO, &finfo) < 0) {
        write(2, "Error reading fixed information\n", 31);
        return;
    }

    //set the size of the render area
    screensize = vinfo.yres_virtual * finfo.line_length;

    vinfo.grayscale = 0;
    vinfo.bits_per_pixel = 16;
    ioctl(filedesc, FBIOPUT_VSCREENINFO, &vinfo);
	ioctl(filedesc, FBIOGET_VSCREENINFO, &vinfo);

    //we mmap here so we dont have to read and write into the framebuffer every time, which would require more context switches. instead the mmap creates a copy into our memory
    //and allows us to change the memory within our address space
    map = mmap(0, screensize, PROT_READ | PROT_WRITE, MAP_SHARED, filedesc, (off_t)0);
    if(map == MAP_FAILED) {
        write(2, "failed to mmap framebuffer\n", 26);
        return;
    }

    if(ioctl(0, TCGETS, &old_term) < 0) {
        write(2, "failed to get the termios struct\n", 33);
        return;
    }

    old_term.c_lflag &= ~(ICANON|ECHO);
    if(ioctl(0, TCSETS, &old_term) < 0) {
        write(2, "Failed to set new terminal settings\n", 36);
        return;
    }

}

/*
This function will clean up our library, it closes the frame buffer, frees up the mmap memory, restores the terminal settings
*/
void exit_graphics() {
    //restore the terminal settings to enable echo
    old_term.c_lflag |= (ICANON|ECHO);
    if(ioctl(0, TCSETS, &old_term) < 0) {
        write(2, "Failed to set back old terminal settings\n", 41);
        return;
    }
    clear_screen();
    if(munmap(map, screensize) < 0) {
        write(2, "Faled to unmap\n", 15);
        return;
    }

    if(close(filedesc) < 0) {
        write(2, "Failed to close filedesc\n", 25);
        return;
    }

}

//This function will clear the screen of any pixels by writing an ANSI escape code to the stdout of the terminal
void clear_screen() {
    if(write(1, "\033[2J", 4) < 0) {
        write(2, "failed writting to stdout with the clearscreen escape sequence\n", 63);
        return;
    }
}

/*
This function will read in 1 character at a time, however we first use the select() call to check if the file descriptor we want to read from is ready with input
otherwise we can suspend(block) this from running. once we have the character to read we save it into a char variable and return that.
*/
char getkey() {
    char buf;
    fd_set readfds;
    FD_ZERO(&readfds);
    FD_SET(0, &readfds);
    int retval = select(1, &readfds, NULL, NULL, NULL);
    if(FD_ISSET(0, &readfds)) {
        if(read(0, &buf, 1) <= 0) {
            write(2, "Failed reading input from user after the select() call\n", 55);
            return;
        }
        else {
            return buf;
        }
    }
}

//This function will sleep for the desired number of milliseconds
void sleep_ms(long ms) {
    timeX.tv_nsec = ms * 1000000;
    timeX.tv_sec = 0;
    nanosleep(timeX, &timeRem);
}

//This function actually manipulates our address in the mmap and changes the value in that memory address to the desire color
void draw_pixel(int x, int y, color_t color) {
    int offset = (x+vinfo.xoffset) * (vinfo.bits_per_pixel/8) + (y+vinfo.xoffset) * finfo.line_length;
    *((unsigned short*)(map + offset)) = color;
}

//Using some a algorithm we can draw a rectangle with a given start point and given width and height of the rectangle.
void draw_rect(int x1, int y1, int width, int height, color_t c) {
    int i = x1;
    int x2 = x1 + width;
    int j = y1;
    int y2 = y1 + height;
    while(i <= x2) {
        j = y1;
        while(j <= y2) {
            draw_pixel(i, j, c);
            j++;
        }
        i++;
    }
}

//This function takes a pointer to a string of text and iterates through that string passing each character to the draw_char() function.
void draw_text(int x, int y, const char *text, color_t color) {
    char c;
    int i = 0;
    int xTemp = x;
    yGlobal = y;

    while((c = *text) != '\n') {
        text++;
        xTemp = draw_char(xTemp, y, c, color);
    }
}

/*recieves a single character and finds the starting index in iso_font[] using that char
we then proceed to check each bit of the index, if the bit is turned on we draw a pixel
the iso_font[] index is a 8x16 bit size. so we need to check 16 rows and 8 columns
*/
int draw_char(int x, int y, char c, color_t color) {
    int index = 0;
    int i;
    int tempX = x;
    int tempY = y;
    unsigned char offset;
    while(index < 16) {
        i = 0;
        offset = iso_font[(c * 16 + index)]; //saving the 8bit integer to check if bits are on or off
        index++;
        tempX = x;
        while(i < 8) { //check all of one row first then drop down a row and repeat
            if(offset & 0x01) {
                draw_pixel(tempX, tempY, color);
            }
            offset = offset >> 1;
            tempX++;
            i++;
        }
        tempY++;
    }
    return tempX;

}