package drawingImages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DrawAndScaleImage extends JFrame{

	public static void main(String[] args) {
		new DrawAndScaleImage();
	}
	
	//These could easily be BufferedImage
	Image imgFish, imgback;
	
	DrawAndScaleImage(){	
	
		this.setTitle("Drawing images");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//this.setLocationRelativeTo(null);
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		JPanel grpanel = new GrPanel();
		grpanel.setBackground(Color.BLUE);

		JLabel lblStatus = new JLabel("This fish has a white background (not transparent)");
		lblStatus.setBackground(Color.YELLOW);
		lblStatus.setOpaque(true);

		String filename = "underwater-photo.jpg";
		imgback = loadImageR1(filename);
		
		filename = "fish.jpeg";		
		imgFish = loadImageR1(filename);
		
		grpanel.add(lblStatus);
		this.add(lblStatus,BorderLayout.NORTH);
		this.add(grpanel,BorderLayout.CENTER);
		this.setVisible(true);
	}



	private class GrPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			
			
			//make the background image fill up the whole window
			if (imgback != null) g.drawImage(imgback, 0, 0, getWidth(), getHeight(), null);
			
			if (imgFish != null) {
				//it turns out that the fish image is 204x204
				int fishW = imgFish.getWidth(null);	
				int fishH = imgFish.getHeight(null);
				
				//draw the fish at the right middle:
				g.drawImage(imgFish, 380, 50, fishW, fishH, null);
			
				//flip the fish sideways:
				//if (image != null) g.drawImage(image, 100, 0, 0, 100,this); //does not work to flip the image
				g.drawImage(imgFish, 100, 0, 0, 100, //destination: (100,0), to (0,100). ie. x goes from 100 to 0 (so it's flipped right to left)
						0, 0, fishW/2,fishH, null); //only use the left half of the fish for the image
				
				//middle two fish. (1) upgright
				g.drawImage(imgFish, 200, 100, 300, 200,    0, 0, fishW, fishH, null); //y goes from 100 to 200 (half the size of the original)
				//(2) flipped vertically
				g.drawImage(imgFish, 200, 300, 300, 200,    0, 0, fishW, fishH, null); //y goes from 300 to 200 (half size and upside down)
			
			
				g.drawString(fishW + "," + fishH, 10,300);
			}
		}

	}

	/** use ImageIO.read() **/
	//This is the only method that requires try-catch, but it can also handle inputstreams
	static Image loadImage1(String filename) {
		Image img = null;
		
		try {
		    img = ImageIO.read(new File(filename));
		} catch (IOException e) {			
			JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return img;
	}
	
	/** use newImageIcon() and icon.getImage() **/
	Image loadImage2(String filename) {		
		ImageIcon icon = new ImageIcon(filename);
		Image img = icon.getImage();
		if (img == null) JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "ERROR", JOptionPane.ERROR_MESSAGE);
//		the image is never null. If the filename is not found it creates an empty image of size (-1,-1)
		return img;
	}
	
	/** use Toolkit.getImage() **/
	Image loadImage3(String filename) {
		Image img = Toolkit.getDefaultToolkit().getImage(filename);
		if (img == null) JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "ERROR", JOptionPane.ERROR_MESSAGE);		
		return img;
//		the image is never null. If the filename is not found it creates an empty image of size (-1,-1)
	}
	
	

	/* Filename must be relative (no / at beginning) */
	BufferedImage loadImageR1(String fn) {	

		//Only works with ImageIO.read. Does not work with imageIcon
		InputStream inputStr = DrawAndScaleImage.class.getClassLoader().getResourceAsStream(fn);
		
		//Works with both ImageIO.read and ImageIcon
		java.net.URL imageURL = DrawAndScaleImage.class.getClassLoader().getResource(fn);
		if (imageURL == null) {
			System.out.println("null URL from filname");
			System.exit(0);
		}
			
		BufferedImage image = null;
		
		/*** NOTE: any of the three following will work ***/
		
		//1. ImageIO.read inputstream
		try {
			image = ImageIO.read(inputStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
/*		//2. ImageIO.read URL
		try {
			image = ImageIO.read(imageURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//3. ImageIcon with URL
		ImageIcon icon = new ImageIcon(imageURL);
		image = icon.getImage();*/
	
		return image;
	}

	/* Requires a / at the beginning of the filename */
	Image loadImageR2(String fn) {
		ImageIcon icon = new ImageIcon(this.getClass().getResource(fn));
		Image image = icon.getImage();
		if (image == null) System.out.println("null image");
		return image;
	}
}
