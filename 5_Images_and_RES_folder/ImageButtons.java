package resizeImageIcon;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageButtons {
	public static void main(String[] args) {
		new ImageButtons();
	}
	
	
	int cardW = 200;
	int cardH = 160;
	String filename = "kittens.jpg";
	
	ImageButtons() {
		
		JFrame window = setupJFrame(900,800, "Kitten Buttons");
		window.setResizable(true);
		
		JPanel panel = new JPanel(new GridLayout(4,4,20,20));
		panel.setBackground(Color.BLUE);
		
		//Note: this program does not use a resource folder, so images 
		//		may not load outside of Eclipse.
		
		/****** This is where we scale a BufferedImage to fit onto a JButton as an ImageIcon ********/
		BufferedImage img = loadImage(filename);
		//scale the image. Does not output BufferedImage, only Image
		Image scaledImage = img.getScaledInstance(cardW, cardH, Image.SCALE_DEFAULT);
		//convert to an ImageIcon
		ImageIcon icon =  new ImageIcon(scaledImage);
		/********************************************************************************************/

		panel.add(new JButton(icon) {{ 
			// to add a different background for the first button
			this.setBackground( Color.RED);			
		}});
		
		//add the rest of the buttons
		for (int i = 2; i <= 16; i++) {
			panel.add(new JButton(icon));	
		}
		window.add(panel);
		window.pack();
		window.setVisible(true);
	}
	
	static BufferedImage loadImage(String fn) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(fn));
		} catch (IOException e) {
			System.out.println(e.toString());
			JOptionPane.showMessageDialog(null, "An image failed to load: " + fn , "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return img;
	}
	
	static JFrame setupJFrame(int x, int y, String s) {
		JFrame frame = new JFrame();
		frame.setTitle(s);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);	
		frame.setSize(x,y);
		frame.setLocationRelativeTo(null);		
		return frame;
	}

}
