package sf;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.swing.JFrame;

public class StreetFighter extends JFrame {
	Fighter a = new Fighter("ryu transparent.png");

	
//	Queue<Integer> inputs = new LinkedList<Integer>();
	ArrayList<Integer> inputs = new ArrayList<Integer>();
	Set<Integer> pressed = new HashSet<Integer>();
	
	
	boolean cancellable;

	private boolean rightPressed = false;
	private boolean jumpPressed = false;
	private boolean crouchPressed = false;
	private boolean leftPressed = false;

	
	public StreetFighter() {
		super("Street Fighter");

		this.addKeyListener(new KeyListener() {	
			public synchronized void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				pressed.add(key);
				if(!inputs.contains(key)) {
					inputs.add(key);

				}
				
				
				
				
				AnimatedSprite next = a.ryuIdle;
				boolean t;
				

				if(pressed.contains(KeyEvent.VK_ESCAPE)) {
					dispose();
					System.exit(0);
				}
				if(cancellable) {
					if(pressed.contains(KeyEvent.VK_LEFT)) {
						next = a.ryuWalk;
						next.reverse();
					}
					else if(pressed.contains(KeyEvent.VK_RIGHT)) {
						next = a.ryuWalk;
					}
					else if(pressed.contains(KeyEvent.VK_UP)) {
						next = a.ryuJump;
					} 
					else {
						next = null;
					}
					if(next!=null) {
						a.current.reset();
						a.current = next;
						a.current.start();
					}
				}	
			
			}
			public synchronized void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				pressed.remove(key);
				
				inputs.remove(new Integer(key));
				if(cancellable)
					a.current.reset();

			}
			public void keyTyped(KeyEvent arg0) {

			}
		});
		MutableInt frames = new MutableInt(0);
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


	public void gameLoop(Screen s, MutableInt frames) {

		final int fps = 60;
		final int targetMillis = 1000/fps;
		long last_time = System.currentTimeMillis();
		long target_time = last_time + targetMillis;



		while(true) {
			long current = System.currentTimeMillis();
			if(current>=target_time) {
				if(frames.getInt()==60) {
					frames.setInt(0);
				}
				last_time = current;
				target_time = target_time + targetMillis;
				
				if(a.current.stopped()) {
					a.current = a.ryuIdle;
					a.current.start();
				}
				cancellable = a.current.update(a.getX(), a.getY());
				
				s.repaint();
				frames.setInt(frames.getInt()+1);
			}
		}
	}
	public static void main(String[] str) {
		new StreetFighter();
	}
}
