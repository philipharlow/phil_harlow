/*
Author: Philip Harlow
Class: cs452
Teacher: Misurda
Program: This program will act as a controller for our graphics library, it allows us to move a rectangle around the screen, change colors of that rectangle, write text to the screen.
This is a framework for a small game that could be made. You can make something like a game of snake with this simple graphics library and driver or a simple 2d scroller game.
With more time I would love to make that.
*/
#include <stdio.h>

typedef unsigned short color_t;
unsigned short color;
int x2, y2;

void clear_screen();
void exit_graphics();
void init_graphics();
char getkey();
void sleep_ms(long ms);
void draw_info();

void draw_pixel(int x, int y, color_t color);
void draw_rect(int x1, int y1, int width, int height, color_t c);
void draw_info();

int main(int argc, char** argv)
{
	init_graphics();
	int i = 0;
	char key;
	char line[256];
	line[0] = '\0';
	int x = (640-20)/2;
	int y = (480-20)/2;
	color = 0xFFFF;
	//draw a blue rectangle
	
	draw_rect(0, 0, 79, 479, 0xE8A2); //red
	draw_rect(80, 0, 79, 479, 0xEEC2); //yellow
	draw_rect(160, 0, 79, 479, 0x1744); //green
	draw_rect(240, 0, 79, 479, 0x175C); //teal
	draw_rect(320, 0, 79, 479, 0x0A7E); //blue
	draw_rect(400, 0, 79, 479, 0x911B); //purple
	draw_rect(480, 0, 79, 479, 0xD91B); //pink
	draw_rect(560, 0, 79, 479, 0xD910); //magenta
	draw_info();
	draw_rect(x, y, 20, 20, color);
	
	do
	{
		x2 = x;
		y2 = y;
		key = getkey();
		if(key == 'w') {
			y-=10;
		}
		else if(key == 's') {
			y+=10;
		}
		else if(key == 'a') {
			x-=10;
		}
		else if(key == 'd') {
			x+=10;
		}
		else if(key == 't') { //this is the input to enable typing text to the screen
			i = 0;
			while((scanf("%c",&line[i])) == 1) {
				if(line[i] == '\n') {
					i++;
					break;
				}
				i++;
			}
			line[i] = '\0';
			//draw the text to the screen
			draw_text(x+25, y, line, color);

		}
		else if(key == 'c') {
			clear_screen();
			draw_info();
		}
		else if(key == 'p') { //if the user inputs 'p' they want to select a new color for the rectangle and text
			printf("type r=red\ny=yellow\ng=green\nt=teal\nb=blue\np=purple\nh=pink\nm=magenta\nq=quit\n");
			while((scanf("%c",&line[i])) == 1) {
				if(line[i] == 'r') {
					color = 0xE8A2;
					break;
				}
				else if(line[i] == 'y') {
					color = 0xEEC2;
					break;
				}
				else if(line[i] == 'g') {
					color = 0x1744;
					break;
				}
				else if(line[i] == 't') {
					color = 0x175C;
					break;
				}
				else if(line[i] == 'b') {
					color = 0x0A7E;
					break;
				}
				else if(line[i] == 'p') {
					color = 0x911B;
					break;
				}
				else if(line[i] == 'h') {
					color = 0xD91B;
					break;
				}
				else if(line[i] == 'm') {
					color = 0xD910;
					break;
				}
				else if(line[i] == 'q') {
					break;
				}
			}
			clear_screen();
			draw_info();
		}
		draw_rect(x2, y2, 20, 20, 0);
		draw_rect(x, y, 20, 20, color);
		sleep_ms(20);
	} while(key != 'q');

	exit_graphics();

	return 0;

}

//this will output some directions to the terminal for the user to follow
void draw_info() {
	printf("move the square around the screen with 'wasd', to quit the program press 'q'\n");
	printf("to clear the screen press 'c', to change color of square press 'p'\n");
	printf("if you would like to switch to text input press't'\n");
	printf("this mode will accept text until you press return\n");
	printf("at which point the text will be displayed to the screen and will return you\n");
	printf("to 'wasd' input mode.\n");
}


