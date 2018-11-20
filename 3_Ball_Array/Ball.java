package balls_array;

import java.awt.Color;
import java.awt.Rectangle;

public class Ball extends Rectangle {
	
	/* Note:
	   Each ball has all of the properties of its Rectangle Parent
	   In particular it has its own copy of
	   int x, int y, int width, int height.
	*/
	
	static int screenW = Array_of_Balls.SCRW;
	
	int vx = 3;
	int vy = 2;	//positive means that it starts by going down
	boolean isVisible = true;
	
	Color clr = Color.RED;
	
	Ball(){
		//make a random location for the ball to start near the top
		//of the screen
		x = (int)(Math.random()* (screenW -100)) +50;
		y = (int)(Math.random()* 100) +1;
		width = height = 30;
	}
	
}
