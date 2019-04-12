package sf;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class StreetFighter extends JFrame {
	Fighter a = new Fighter("ryu transparent.png");
	private boolean rightPressed = false;
	private boolean crouchPressed = false;
	private boolean leftPressed = false;
	
	public StreetFighter() {
		super("Street Fighter");

		this.addKeyListener(new KeyListener() {	
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key==KeyEvent.VK_ESCAPE) {
					dispose();
					System.exit(0);
				}
				if (key == KeyEvent.VK_W) {

				}
				if (key == KeyEvent.VK_A) {
					leftPressed = true;
				}
				if (key == KeyEvent.VK_S) {
					crouchPressed = true;
				}
				if (key == KeyEvent.VK_D) {
					rightPressed = true;
				}
				if (key == KeyEvent.VK_J) {

				}
				if (key == KeyEvent.VK_K) {

				}
				if (key == KeyEvent.VK_L) {

				}
			}
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_A) {
					leftPressed = false;
				}
				if (key == KeyEvent.VK_S) {
					crouchPressed = false;
				}
				if (key == KeyEvent.VK_D) {
					rightPressed = false;
				}
			}
			public void keyTyped(KeyEvent arg0) {
				
			}
		});
		int[] frames = new int[1];
		Screen s = new Screen(a, frames);
		this.add(s);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.pack();
		this.setVisible(true);
		new Thread() {
			public void run() {
				gameLoop(s, frames);
			}
		}.start();
	}	

	public void gameLoop(Screen s, int[] frames) {
		final int fps = 60;
		final int targetMillis = 1000/fps;
		long last_time = System.currentTimeMillis();
		long target_time = last_time + targetMillis;
		
		while(true) {
			long current = System.currentTimeMillis();
			if(current>=target_time) {
				if(frames[0]==60) {
					frames[0]=0;
				}
				last_time = current;
				target_time = target_time + targetMillis;
				s.repaint();
				frames[0]++;
			}
		}
	}

	public static void main(String[] str) {
		new StreetFighter();
	}
}
