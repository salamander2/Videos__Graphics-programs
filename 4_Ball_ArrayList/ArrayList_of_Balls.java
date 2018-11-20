package balls_arraylist;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import hsa2.GraphicsConsole;
/* This program shows how to take the TwoBalls bouncing program and modify it
 * so that it uses an ** Arraylist ** ... where you can add and delete balls at will. 
 */
public class ArrayList_of_Balls {

	public static void main(String[] args) {
		new ArrayList_of_Balls();
	}


	final static int SCRW=800;
	final static int SCRH=600;
	final static int SLEEP = 25;
	GraphicsConsole gc = new GraphicsConsole(SCRW,SCRH);

	ArrayList<Ball> ballList = new ArrayList<Ball>();

	//constructor
	ArrayList_of_Balls(){
		setup();

		while(gc.getKeyCode() != 'Q') {
			for (Ball ball : ballList) {
				moveBall(ball);
			}

			addBalls();
			deleteBalls();

			drawGraphics();
			gc.sleep(SLEEP);
		}
		gc.drawString("Game Over", 100,100);
	}

	void setup(){
		gc.setAntiAlias(true);
		gc.setLocationRelativeTo(null);	
		gc.setTitle("Two Ball Collision --> Balls in an arraylist");
		gc.setBackgroundColor(Color.BLACK);
		gc.enableMouse();
		for (int i = 0; i < 6; i++) {
			Ball b = new Ball();
			if (i%2==0) b.clr = Color.CYAN;
			else b.clr = Color.YELLOW;

			ballList.add(b);
		}
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

	void deleteBalls() {

		if (gc.getMouseButton(2)) { //right button
			if (gc.getMouseClick() > 0) {
				Point p = new Point(gc.getMouseX(), gc.getMouseY());
				for(int i=0; i< ballList.size(); i++) {
					Ball b = ballList.get(i);
					if (b.contains(p)) {	//clicked on a ball
						ballList.remove(i);
						i--;
					}
				}
			}
		}
	}

	boolean readyToAdd = true;
	void addBalls() {		
		if (readyToAdd && gc.isKeyDown(32)) {
			ballList.add(new Ball());
			readyToAdd = false;
		}

		if (!gc.isKeyDown(32)) {
			readyToAdd = true;
		}
	}

	void drawGraphics(){
		//sync. is needed to stop flickering
		synchronized(gc){
			gc.clear();
			for (Ball b : ballList) {
				gc.setColor(b.clr);
				gc.fillOval(b.x, b.y, b.width, b.height);
			}
		}
	}


}
