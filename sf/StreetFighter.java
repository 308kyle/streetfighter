package sf;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class StreetFighter extends JFrame {
	Fighter a = new Fighter("ryu transparent.png");
	private boolean rightPressed = false;
	private boolean jumpPressed = false;
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
					jumpPressed = true;
				}
				if (key == KeyEvent.VK_A) {
					a.current = a.ryuWalk;
					leftPressed = true;
					a.current.reverse();
					a.current.start();
				
				}
				if (key == KeyEvent.VK_S) {
					crouchPressed = true;
				}
				if (key == KeyEvent.VK_D) {
					a.current = a.ryuWalk;
					rightPressed = true;
					a.current.start();

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
					a.current.reset();
					a.current = a.ryuIdle;
					a.current.start();
				}
				if (key == KeyEvent.VK_S) {
					crouchPressed = false;

				}
				if (key == KeyEvent.VK_D) {
					rightPressed = false;
					a.current.reset();
					a.current = a.ryuIdle;
					a.current.start();
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

	public void move() {
		int x = a.getX();
		int y = a.getY();
		int vx = a.getVelx();
		int vy = a.getVely();
		if(rightPressed) {
			if(!(x>Toolkit.getDefaultToolkit().getScreenSize().getWidth()-125)) {
				a.setX(x+vx);
			}
		}
		if(leftPressed) {
			if(!(x<0)) {
				a.setX(x-vx);
			}
		}
		if(jumpPressed) {
			if(a.getY()>240) {
				a.setY(a.getY()-a.getVely());
			}
			if(a.getY()<=240) {
				a.setY(a.getY()+a.getVely());
			}
		}
	}

	public void gameLoop(Screen s, int[] frames) {
		final int fps = 60;
		final int targetMillis = 1000/fps;
		long last_time = System.currentTimeMillis();
		long target_time = last_time + targetMillis;
		
		a.current.start();
		
		while(true) {
			long current = System.currentTimeMillis();
			if(current>=target_time) {
				if(frames[0]==60) {
					frames[0]=0;
				}
				last_time = current;
				target_time = target_time + targetMillis;
				move();
				a.current.update(frames[0]);
				s.repaint();
				frames[0]++;
			}
		}
	}

	public static void main(String[] str) {
		new StreetFighter();
	}
}
