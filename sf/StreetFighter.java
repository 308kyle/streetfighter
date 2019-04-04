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
		Screen main = new Screen();
		this.add(main);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.pack();
		this.setVisible(true);
		//main game loop
		int fps = 30;
		int mpf = 1000/fps;
		long current = System.currentTimeMillis();
		long target = current + mpf;
		while(true) {
			current = System.currentTimeMillis();
			
			if(current<target) {
				return;
			}
			
			
		}
	}
	public static void main(String[] str) {
		new StreetFighter();
	}
}
