//package graphics;

/* This is source code for the video at https://www.youtube.com/watch?v=mZQSBVmIHPw */

import java.awt.Color;

import hsa2.GraphicsConsole;

public class TwoBalls {

	public static void main(String[] args) {
		new TwoBalls();
	}
	
	//constants
	final static int SCRW = 800;
	final static int SCRH = 600;
	final static int SLEEPTIME = 3; //ms
	
	//global variables
	GraphicsConsole gc = new GraphicsConsole(SCRW, SCRH); 
	Ball b1, b2;
		
	//constructor
	TwoBalls(){
		setup();
		
		//main loop!!!!
		while(gc.getKeyCode() != 'Q') {
			moveBall(b1);
			moveBall(b2);	
			
			if (b1.intersects(b2)) {
				gc.setBackgroundColor(Color.WHITE);
				int temp = b1.vx;
				b1.vx = b2.vx;
				b2.vx = temp;
			}
				
			drawGraphics();
			gc.sleep(SLEEPTIME);
		}
		gc.close(); //<<<==== close the window when Q is pressed
				
	}
	
	void setup(){
		gc.setTitle("Two Ball Collision");
		gc.setAntiAlias(true);
		gc.setLocationRelativeTo(null);
		gc.setBackgroundColor(Color.BLACK);
		gc.clear();
		
		b1 = new Ball(SCRW);
		b2 = new Ball(SCRW);
		b2.clr = Color.YELLOW;
		
		
	}
	
	void moveBall(Ball b){
		b.x = b.x + b.vx;
		b.y = b.y + b.vy;
		if (b.x < 0) b.vx = -b.vx;   //left
		if (b.y < 0) b.vy = -b.vy;	//top
		if (b.x > SCRW-b.width) b.vx = -b.vx; //right
		if (b.y > SCRH-b.height) b.vy = -b.vy; //bottom	
		
	}
			
	void drawGraphics(){
		synchronized(gc){
			gc.clear();
			gc.setColor(b1.clr);
			gc.fillOval(b1.x, b1.y, b1.width, b1.height);
			gc.setColor(b2.clr);
			gc.fillOval(b2.x, b2.y, b2.width, b2.height);
		}
	}

}



