package icon1;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ImageIcon_demo {

	public static void main(String[] args) {
		
		//Note: this program does not use a resource folder, so images 
		//		may not load outside of Eclipse.
		String filename = "android-128x128.png";
		ImageIcon icon = new ImageIcon(filename);

		JFrame window = setupJFrame(600,400, "Android Icon");
		JPanel panel = new JPanel();
		panel.add(new JButton("OK"));
		panel.add(new JButton(icon));
		panel.add(new JLabel("A button with text and icon:"));
		panel.add(new JButton("Android",icon));
		panel.add(new JLabel("The icon is always the size of the image"));
		window.add(panel);
		window.setVisible(true);
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
