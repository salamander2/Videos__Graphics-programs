package graphics;
/* This program demonstrates how to make a ball bounce on the screen.
It's a very rudimentary program and does not use a Ball object.
Therefore this program should not actually be used for making a ball bounce. 
Instead, go on to the second program "TwoBalls.java" which does it correctly.

I repeat DO NOT USE THIS PROGRAM as a basis for making balls move or bounce!
This is just a nice screensaver-like animation.
*/

import hsa2.GraphicsConsole;
import java.awt.Color;

public class Bounce1 {	
	
	//constants
	final static int WINW = 800;
	final static int WINH = 600;
	//global variables
	
	GraphicsConsole gc = new GraphicsConsole (WINW, WINH, "Coloured Ball Bounce");

	int ballx = 100;
	int bally = 100;
	int speedx = 35;	//** Normally this is set to 2 or 3 pixels.
	int speedy = speedx;	//also could be vx, vy
	int size = 40;

	public static void main(String[] args) {
		new Bounce1().runGame();
	}

	//constructor
	Bounce1() {		
		setup();
	}

	void setup() {
		gc.setAntiAlias(true);
		gc.setLocationRelativeTo(null); //centre window
    gc.setBackgroundColor(Color.WHITE);
    gc.clear();
		gc.setColor(Color.RED.darker());
	}
	
	void runGame() {
 	//main game loop
		while(true) {
			moveBall();
			drawGraphics();
			gc.sleep(5);	// set this to a lower number in order for the animation to go faster
		}				
	}

	void moveBall() {
		ballx += speedx;
		bally += speedy;
	
		//bounce off 4 walls
		if ((bally + size) > WINH) {
			speedy *=-1;
			gc.setColor(new Color(Color.HSBtoRGB((float)Math.random(), 1.0f, 1.0f)));
		}
		if ((ballx + size) > WINW) {
			speedx *=-1;
			gc.setColor(new Color(Color.HSBtoRGB((float)Math.random(), 1.0f, 1.0f)));
		}
		if (bally < 0) {
			speedy *=-1;
			gc.setColor(new Color(Color.HSBtoRGB((float)Math.random(), 1.0f, 1.0f)));
		}
		if (ballx < 0) {
			speedx *=-1;
			gc.setColor(new Color(Color.HSBtoRGB((float)Math.random(), 1.0f, 1.0f)));
		}
	}
	
	void drawGraphics() {
		synchronized(gc) {
			//gc.clear();
			gc.fillOval(ballx, bally, size, size);
		}
	}
}
