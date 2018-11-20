package graphics;

import hsa2.GraphicsConsole;
import java.awt.Color;


public class Fading {

	public static void main(String args[]) {
		new Fading();

	}
	GraphicsConsole gc = new GraphicsConsole (800,600, "Spots");

	Fading() {
		gc.setAntiAlias(true);
		gc.setLocationRelativeTo(null); //center window
		//                gc.setBackgroundColor(Color.GRAY);
		gc.setBackgroundColor(new Color(40,40,40,10));
		gc.clear();
		gc.setColor(Color.RED);

		int size = 40;
		int sleepTime = 5;

		while(true) {
			gc.clear();                 
			int rx = (int) ((Math.random()* 750));
			int ry = (int) ((Math.random()* 550));
			gc.fillOval (rx, ry, size, size);

			gc.sleep(sleepTime);

			int r = (int) (Math.random()* 256);
			int g = (int) (Math.random()* 256);
			int b = (int) (Math.random()* 256);
			//gc.setColor(new Color(r,g,b));
			gc.setColor(new Color(Color.HSBtoRGB((float)Math.random(), 1.0f, 1.0f)));

		}
	}
}

