/* This program demonstrates how to make a ball bounce on the screen.
   It's a very rudimentary program and does not use a Ball object.
   Therefore this program should not actually be used for makeing a ball bounce. 
   Instead, go on to the second program "TwoBalls.java" which does it correctly.

   I repeat DO NOT USE THIS PROGRAM as a basis for making balls move or bounce!
   
*/
 
import hsa2.GraphicsConsole;
import java.awt.Color;

public class Bounce1 {	
	GraphicsConsole gc = new GraphicsConsole (800,600, "Coloured Ball Bounce");

	//global variables
	int ballx = 100;
	int bally = 100;
	int speedx = 35;	//** Normally this is set to 2 or 3 pixels.
	int speedy = speedx;
	int size = 40;

	public static void main(String[] args) {
		new Bounce1();
	}

	//main program
	Bounce1() {		
		setup();

    //main game loop
		while(true) {
			moveAndDrawBall();
			//checkCollision();
			gc.sleep(5);
		}				
	}

	void setup() {
		gc.setAntiAlias(true);
		gc.setLocationRelativeTo(null); //centre window
		gc.setColor(Color.RED.darker());
	}

	void moveAndDrawBall() {
		synchronized(gc) {
			//c.clearRect(ballx, bally, size, size);
			ballx += speedx;
			bally += speedy;
			gc.fillOval(ballx, bally, size, size);
		}
		//bounce off 4 walls
		if ((bally + size) > gc.getDrawHeight()) {
			speedy *=-1;
			gc.setColor(new Color(Color.HSBtoRGB((float)Math.random(), 1.0f, 1.0f)));
		}
		if ((ballx + size) > gc.getDrawWidth()) {
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
}
