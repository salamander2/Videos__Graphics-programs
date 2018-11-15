package graphics;

import java.awt.Color;
import java.awt.Rectangle;

public class Ball extends Rectangle{
	
	/*
	Since ball is a child of Rectangle, it inherits all of the 
	rectangle properties. 
	In particular, it now has int x, y, width, height
	*/
	
	//Each ball also has all of these properties
	int vx = 3;		//speed x
	int vy = 2;		//speed y
	
	Color clr = Color.RED;	//ball colour
	
	Ball(int w){	//w is the screen width
		w -= 100;
		
		x = (int)(Math.random() * w) + 50;
		y = (int)(Math.random() * 100) +1;
		width = height = 40;
	}
	
}
