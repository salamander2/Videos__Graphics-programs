package balls_array;

import java.awt.Color;
import java.awt.Point;

import hsa2.GraphicsConsole;

public class Array_of_Balls {
	/* This is the TwoBalls.java program that has been modified to use an array
	 * so that as many balls as one wishes can be added.
	 * I also show how to make the balls get "deleted" - i.e. disappear so that they are
	 * not shown.
	 */
	
	public static void main(String[] args) {
		new Array_of_Balls();
	}
	
	final static int NUMB = 20;	//number of balls
	final static int SCRW=800;
	final static int SCRH=600;
	final static int SLEEP = 20;
	GraphicsConsole gc = new GraphicsConsole(SCRW,SCRH);
	
	Ball[] balls = new Ball[NUMB];
	
	//constructor
	Array_of_Balls(){
		setup();
		
		while(gc.getKeyCode() != 'Q') {
			
			for (Ball squid : balls) {
				moveBall(squid);
			}			
			
			if (gc.getMouseClick() > 0) {
				Point p = new Point(gc.getMouseX(), gc.getMouseY());
				for (Ball b: balls) {
					if (b.contains(p)) {	//clicked on a ball
						b.isVisible = false;
					}
				}
			}
			
			drawGraphics();
			
			boolean gameWon = true;
			for (Ball b : balls) {
				if (b.isVisible) gameWon = false;
			}
			
			if (gameWon) break;
			
			
			gc.sleep(SLEEP);
		}
		gc.drawString("Game Over", 100,100);
	}
	
	void setup(){
		gc.setAntiAlias(true);
		gc.setLocationRelativeTo(null);	
		gc.setTitle("Two Ball Collision");
		gc.setBackgroundColor(Color.BLACK);
		gc.enableMouse();
		
		for (int i = 0; i < NUMB; i++) {
			balls[i] = new Ball();
			if (i > NUMB/2) balls[i].clr = Color.CYAN;
		}

		balls[2].clr = Color.YELLOW;
		balls[0].width += 10;
		balls[0].height += 10;
	}
	
	void moveBall(Ball b){
		b.x = b.x + b.vx;
		b.y = b.y + b.vy;
		
		//bounce off edges
		if (b.x < 0) b.vx = -b.vx;
		if (b.y < 0) b.vy = -b.vy;
		if (b.x > SCRW-b.width) b.vx = -b.vx;
		if (b.y > SCRH-b.height) b.vy = -b.vy;
		
	}
	void drawGraphics(){
		//sync. is needed to stop flickering
		synchronized(gc){
			gc.clear();
			for(Ball nemo : balls) {
				if (!nemo.isVisible) continue;
				gc.setColor(nemo.clr);
				gc.fillOval(nemo.x, nemo.y, nemo.width, nemo.height);
			}

		}
	}
	
	
}
