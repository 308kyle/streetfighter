package sf;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class StreetFighter extends JFrame {
	
	public StreetFighter() {
		super("Street Fighter");
		
		this.addKeyListener(new KeyListener() {	
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key==KeyEvent.VK_ESCAPE) {
					dispose();
				}
			}
			public void keyReleased(KeyEvent e) {

			}
			public void keyTyped(KeyEvent e) {

			}
		});
		Screen s = new Screen();
		this.add(s);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.pack();
		this.setVisible(true);
		
		new Thread() {
			public void run() {
				gameLoop(s);
			}
		}.start();

	}	
	public void gameLoop(Screen s) {
		final int fps = 60;
		final int targetMillis = 1000/fps;
		long last_time = System.currentTimeMillis();
		long target_time = last_time + targetMillis;
		
		while(true) {
			long current = System.currentTimeMillis();
			if(current>=target_time) {
				last_time = current;
				target_time = target_time + targetMillis;
				s.move();
			}
			
		}
	}
		
	public static void main(String[] str) {
		new StreetFighter();
	}
}
