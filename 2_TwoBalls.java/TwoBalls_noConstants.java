package graphics;

/* 
This program is essentially the same as the TwoBalls.java program.
However, it does not use any constants.
This makes it really hard to change a value. 
For example, if you wanted to change the screen width from 800 to 900
and did a search and replace, you would also mess up the max hitpoints.
That's why we use constants. Look up "why are magic numbers bad?".
*/

import java.awt.Color;

import hsa2.GraphicsConsole;

public class TwoBalls_noConstants {

	public static void main(String[] args) {
		new TwoBalls_noConstants();
	}
	
	GraphicsConsole gc = new GraphicsConsole(800, 600);
	
	Ball b1,b2;
	
	TwoBalls_noConstants() {
		setup();
		
		//main loop
		while(gc.getKeyCode() != 'Q') {
			moveBall(b1);
			moveBall(b2);
			checkCollision();
			drawGraphics();
			gc.sleep(5);
		}
		
		gc.close();
	}
	
	void setup() {
		gc.setTitle("Two Ball Collision");
		gc.setAntiAlias(true);
		gc.setLocationRelativeTo(null);
		gc.setBackgroundColor(Color.BLACK);
		gc.clear();
		
		b1 = new Ball(800);
		b2 = new Ball(800);
		b2.clr = Color.YELLOW;
		
		//Example of problems with no constants:
		//More stuff ...
		
		int hitpoints = 700; //up to a max of 800
	
		//more stuff
		if (hitpoints > 800) hitpoints = 800;
	}

	void drawGraphics() {
		synchronized(gc) {
			//gc.clear();
			gc.setColor(b1.clr);
			gc.fillOval(b1.x,  b1.y,  b1.width, b1.height);
			gc.setColor(b2.clr);
			gc.fillOval(b2.x,  b2.y,  b2.width, b2.height);
		}
	}
	
	void moveBall(Ball b) {
	
		b.x += b.vx;
		b.y += b.vy;
		
		//bounce off walls
		if (b.x < 0) b.vx = -b.vx;				//left 
		if (b.y < 0) b.vy = -b.vy;				//top
		if (b.x > 800 - b.width) b.vx = -b.vx;	//right
		if (b.y > 600 - b.height) b.vy = -b.vy;	//bottom
	}
	
	
	void checkCollision() {
		if (b1.intersects(b2)) {
			gc.setBackgroundColor(Color.WHITE);
		}
	}
	
	
	
}












