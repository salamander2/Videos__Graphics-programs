// This is a template for an animation game using SWING.
// There are things that need fixing (see comments)

// Other classes have been added at the end of this file. This is a BAD THING. 
//They should be in separate files or else inner classes.  I've done it this way because it's easier for you to download.

/* Use WASD to move player. Use SPACE to fire laser. 
 * Game stops when all enemies are off the screen. */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

//This version handles multiple keys at the same time
//Wow! This is lightning fast, especially if you reduce the timer time.

public class AnimationGame implements ActionListener {

	public static void main(String[] args) {
		new AnimationGame();
	}

	static int panW = 800, panH = 500;
		
	JFrame window;
	JPanel panel;
	Player player;
	Timer timerPlayer, timerMain;

	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ArrayList<Laser> laserList = new ArrayList<Laser>();
	
	MyKL mainKL = new MyKL();	
	
	AnimationGame(){
		//make game objects
		player = new Player();
		for (int i = 0; i < Enemy.MAXENEMIES; i++) {
			enemyList.add(new Enemy()); //make them at random locations
		}
		
		//setup GUI
		window = new JFrame("Starting animation game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DrawingPanel();		
		window.add(panel);	// the panel will control the size
		window.pack();		//therefore we need pack
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		timerPlayer = new Timer(10, this);
		timerMain = new Timer (10, new mainAL()); 
		timerPlayer.start();
		timerMain.start();
	}

	/** This controls the game, except for player interaction. **/
	class mainAL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			moveEnemies();
			moveLasers();
			checkCollisions();
			checkWin();				
		}		
	}
	
	void moveEnemies() {
		for (Enemy enemy : enemyList) {
			enemy.y += enemy.speed;
		}	
		//remove them when they go off the bottom of the screen
		for (Enemy enemy : enemyList) {
			if (enemy.y > panH) {
				enemyList.remove(enemy);
				break;
			}
		}
	}
	
	void moveLasers() {
		for (Laser z: laserList) {
			z.y += z.speed;
		}	
		//remove them when they go off the top of the screen
		for (Laser z: laserList) {
			if (z.y < 0) {
				laserList.remove(z);
				break;
			}
		}
	}
	
	private long lastShotTime = System.currentTimeMillis();
	void shoot() {
		if (laserList.size() >= 7) return;
		
		//has more than SHOTDELAY time passed since last shot?
		if (System.currentTimeMillis() - lastShotTime < Laser.SHOTDELAY) return;
		lastShotTime = System.currentTimeMillis();
		
		laserList.add(new Laser(player));
	}
	
	void checkCollisions() {
		//see if enemy hits player:
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy en = enemyList.get(i);
			if (en.intersects(player)) {
				player.health -= en.dmg;
				enemyList.remove(i);
				i--;
			}				
		}
	}
	
	
	/* Purpose: determine if the game is over
	 * Called from: EnemyAL (which moves the enemy)
	 */
	void checkWin() {
		if (player.health <= 0) {
			//pop up message that the game is over
			System.out.println("game over!");  //TEMPORARY FIX
			panel.repaint(); //to update player health bar
			timerPlayer.stop();
			timerMain.stop();				
		}
		if (enemyList.size() == 0) {
			System.out.println("YOU WIN!");  //TEMPORARY FIX
			panel.repaint(); 
			timerPlayer.stop();
			timerMain.stop();
		}
	}
	
	//for Player timer
	@Override
	public void actionPerformed(ActionEvent e) {

		//get keys and move player
		if (mainKL.isKeyDown('A')  && player.x > 0) player.x-=player.speed;
		if (mainKL.isKeyDown('D')  && player.x+player.width < panW) player.x+=player.speed;
		if (mainKL.isKeyDown('W')  && player.y > 0) player.y-=player.speed;
		if (mainKL.isKeyDown('S')  && player.y+player.height < panH) player.y+=player.speed;
		
		//shoot
		if (mainKL.isKeyDown(' ')) shoot();
		
		panel.repaint();
	}
	
	private class DrawingPanel extends JPanel {
		
		DrawingPanel(){
			//this.setBackground(new Color(60,60,60));
			this.setPreferredSize(new Dimension(panW,panH));
			this.addKeyListener(mainKL); 
			this.setFocusable(true); // need something like this to get focus
						
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g; 
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						
			g.setColor(Color.ORANGE);
			g.fillRect(player.x, player.y, player.width, player.height);
			//** Draw health bar **			
			g.setColor(Color.GREEN);
			if (player.health < 45) g.setColor(Color.RED); 
			g.fillRect(player.x, player.y-10, player.width*player.health/100, 10);
			g.setColor(Color.GRAY);
			g.drawRect(player.x, player.y-10, player.width, 10);
			
			//draw lasers
			g.setColor(Laser.c);
			for (Laser z: laserList) {
				g.fillRect(z.x, z.y, z.width, z.height);
			}
			
			//enemies
			g2.setStroke(new BasicStroke(3));			
			g.setColor(Color.BLUE);
			for (Enemy e: enemyList) {
				g.drawOval(e.x, e.y, e.width, e.height);
			}
		}
	
	} //end of drawing panel
	
	class MyKL implements KeyListener {
		private boolean[] keys = new boolean[256]; //hopefully default=false
		
		boolean isKeyDown(int n) {
			return keys[n];
		}
		@Override
		public void keyPressed(KeyEvent e) {
			keys[e.getKeyCode()] = true;
		}		
		@Override
		public void keyReleased(KeyEvent e) {
			keys[e.getKeyCode()] = false;
		}
		@Override
		public void keyTyped(KeyEvent e) {} //slow!!!
	}
	
	
	
	static BufferedImage loadImage(String filename) {
		BufferedImage img = null;			
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "ERROR", JOptionPane.ERROR_MESSAGE);
		}		
		return img;
	}
}
    

class Enemy extends Rectangle {
	static final int MAXENEMIES = 50;
	int speed = 3;
	int dmg = 15; //damage

	//This is needed for the player class????
	Enemy(int x, int y, int w, int h) {		
		this.x = x; 
		this.y = y;
		width = w;
		height = h;
	}

	 // They start above the top of screen (-50 to -150)
	Enemy() {
		this.x = (int) (Math.random()*(AnimationGame.panW-15)) + 30; 
		this.y = (int) (Math.random()*200) - 250;
		width = height = 15;
		speed =  (int) (Math.random()*4)+1;
	}
}

class Player extends Enemy {

	int health = 100;
	
	Player(){
		this(AnimationGame.panW/2 , AnimationGame.panH-200 , 100, 60);
	}
	
	Player(int x, int y, int w, int h) {
		super(x, y, w, h);
		//change speed here: 
	}
}  


class Laser extends Rectangle {
	
	static int speed = -3;
	static Color c = Color.BLACK;
	static final int MAXSHOTS = 7;  //how many on screen
	static final int SHOTDELAY = 100;  //ms
	
	Laser(Player p) {
		x = p.x+p.width/2;
		y = p.y - 10;
		width = 2;
		height = 10;
	}
}

