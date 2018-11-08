/**
* Philip Harlow
* CS 337
* This is the javascript for our paint.html.
* this is what allows us to draw on our canvas and 
* select different modes and colors
*/
"use strict";
(function () {
  let canvas, context, colorPicker, colorPickerContext;
  let paint = false;
  let colorPickerGradient1, colorPickerGradient2;
  let curColor, curSize, curMode;
  let penCanvas, penCanvasContext;

  window.onload = function() {
    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");
    colorPicker = document.getElementById("color-canvas");
    colorPickerContext = colorPicker.getContext("2d");
    penCanvas = document.getElementById("pen-canvas");
    penCanvasContext = penCanvas.getContext("2d");
    curColor = "black";
    curSize = "1";
    curMode = "pen";
    createGradient();
    createPenSize();
    let penPlus = document.getElementById("plus");
    penPlus.onclick = increaseSize;
    let penMinus = document.getElementById("minus");
    penMinus.onclick = decreaseSize;
    canvas.addEventListener("mousemove", eventMouseMove, false);
    canvas.addEventListener("mousedown", eventMouseDown, false);
    canvas.addEventListener("mouseup", eventMouseUp, false);
    colorPicker.addEventListener("mousedown", getColorClick, false);
    let clear = document.getElementById("clear");
    clear.onclick = init;
    let pen = document.getElementById("pen");
    pen.onclick = setMode;
    let circles = document.getElementById("circles");
    circles.onclick = setMode;
    let squares = document.getElementById("squares");
    squares.onclick = setMode;
    let lines = document.getElementById("lines");
    lines.onclick = setMode;
  };
/**
* This function will return the specific color at a given
* pixel in our color picker gradient
*/
  function getColorClick(event) {
    let mouseX = event.pageX - this.offsetLeft;
    let mouseY = event.pageY - this.offsetTop;
    let pixel = colorPickerContext.getImageData(mouseX, mouseY, 1, 1).data;
    curColor = "rgb(" + pixel[0] + ", " + pixel[1] + ", " + pixel[2] + ")";
  }
/**
* This function will set what mode we want to draw in
*/
  function setMode() {
    if(this.id == "pen") {
      curMode = "pen";
    }
    else if(this.id == "circles") {
      curMode = "circles";
    }
    if(this.id == "squares") {
      curMode = "squares";
    }
    if(this.id == "lines") {
      curMode = "lines";
    }
  }
/**
* This function will increase the size of our pen
*/
  function increaseSize() {
    let temp = parseInt(curSize);
    temp++;
    curSize = temp + "";
    createPenSize();
  }
/**
* This function will decrease the size of our pen
*/
  function decreaseSize() {
    let temp = parseInt(curSize);
    if(temp == 1) {
      return;
    }
    temp--;
    curSize = temp + "";
    createPenSize();
  }
/**
* This function draws our pixel size into our small canvas
* where we can adjust the pen size
*/
  function createPenSize() {
    penCanvasContext.clearRect(0, 0, 50, 50);
    penCanvasContext.beginPath();
    let temp = parseInt(curSize);
    penCanvasContext.arc(25, 25, 1, 0, 2 * Math.PI);
    penCanvasContext.lineWidth = temp;
    penCanvasContext.stroke();
  }
/**
* This function creates our color picker gradient
*/
  function createGradient() {
    colorPickerGradient1 = colorPickerContext.createLinearGradient(0, 0, 100, 0);
    colorPickerGradient1.addColorStop(0, "rgb(255, 0, 0)");
    colorPickerGradient1.addColorStop(0.15, "rgb(255, 0, 255)");
    colorPickerGradient1.addColorStop(0.33, "rgb(0, 0, 255)");
    colorPickerGradient1.addColorStop(0.49, "rgb(0, 255, 255)");
    colorPickerGradient1.addColorStop(0.67, "rgb(0, 255, 0)");
    colorPickerGradient1.addColorStop(0.84, "rgb(255, 255, 0)");
    colorPickerGradient1.addColorStop(1, "rgb(255, 0, 0)");
    colorPickerContext.fillStyle = colorPickerGradient1;
    colorPickerContext.fillRect(0, 0, 100, 100);
    colorPickerGradient2 = colorPickerContext.createLinearGradient(0, 0, 0, 100);
    colorPickerGradient2.addColorStop(0, "rgb(255, 255, 255, 1)");
    colorPickerGradient2.addColorStop(0.5, "rgb(255, 255, 255, 0)");
    colorPickerGradient2.addColorStop(0.5, "rgb(0, 0, 0, 0)");
    colorPickerGradient2.addColorStop(1, "rgb(0, 0, 0, 1)");
    colorPickerContext.fillStyle = colorPickerGradient2;
    colorPickerContext.fillRect(0, 0, 100, 100);
  }
/**
* This function checks when we click down on the mouse
* and adds our click to the array
*/
  function eventMouseDown(e) {
    let mouseX = e.pageX - this.offsetLeft;
    let mouseY = e.pageY - this.offsetTop;
    paint = true;
    addClick(mouseX, mouseY);
    redraw();
  }
/**
* This function checks when we let go of the click
*/
  function eventMouseUp() {
    paint = false;
  }
/**
* This function check when our mouse moves
*/
  function eventMouseMove(e) {
    if(paint){
      addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop, true);
      redraw();
    }
  }

  //These are the arrays we use to store our drawing info
  let clickX = new Array();
  let clickY = new Array();
  let clickDrag = new Array();
  let clickSize = new Array();
  let clickMode = new Array();
  let clickColor = new Array();
/**
* This function will clear out our arrays giving us a fresh
* canvas to work on
*/
  function init() {
    clickX = new Array();
    clickY = new Array();
    clickDrag = new Array();
    clickSize = new Array();
    clickMode = new Array();
    clickColor = new Array();
    context.clearRect(0, 0, context.canvas.width, context.canvas.height); // Clears the canvas
  }
/**
* This function is where we add our clicks
* to our arrays for the draw method to draw
*/
  function addClick(x, y, dragging)
  {
    clickX.push(x);
    clickY.push(y);
    clickDrag.push(dragging);
    clickSize.push(curSize);
    clickMode.push(curMode);
    clickColor.push(curColor);
  }
/**
* This function is what actually draws onto the canvas
* we do so by looping through our arrays and determining what kind
* of pixels to draw
*/
  function redraw() {
  context.clearRect(0, 0, context.canvas.width, context.canvas.height); // Clears the canvas
  context.lineJoin = "round";
  
  for(let i=0; i < clickX.length; i++) {
    context.beginPath();
    if(clickMode[i] == "lines") {
      context.moveTo(0, 0);
      context.lineTo(clickX[i], clickY[i]);
    }
    else if(clickMode[i] == "circles") {
      context.arc(clickX[i], clickY[i], 40, 0, 2 * Math.PI);
    }
    else if(clickMode[i] == "squares") {
      context.rect(clickX[i] - 25, clickY[i] - 25, 50, 50);
    }
    else if(clickDrag[i] && i){
      context.moveTo(clickX[i-1], clickY[i-1]);
      context.lineTo(clickX[i], clickY[i]);
     }
     else{
       context.moveTo(clickX[i]-1, clickY[i]);
       context.lineTo(clickX[i], clickY[i]);
     }
     context.closePath();
     context.strokeStyle = clickColor[i];
     context.lineWidth = clickSize[i];
     context.stroke();
  }
}


})();
