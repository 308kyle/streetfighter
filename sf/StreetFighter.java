package sf;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.swing.JFrame;

//xd
public class StreetFighter extends JFrame {
	Fighter a = new Fighter("ryu transparent.png");

	
//	Queue<Integer> inputs = new LinkedList<Integer>();
	ArrayList<Integer> pressed = new ArrayList<Integer>();
	Map<Integer, AnimatedSprite> map = new HashMap<Integer, AnimatedSprite>();
//	Set<Integer> pressed = new HashSet<Integer>();
	
	AnimatedSprite next;
	
	boolean cancellable;

	
	public StreetFighter() {
		super("Street Fighter");

		map.put(KeyEvent.VK_LEFT, a.ryuWalk);
		//map.put(KeyEvent.VK_DOWN, a.ryuWalk);
		map.put(KeyEvent.VK_RIGHT, a.ryuWalk);
		map.put(KeyEvent.VK_UP, a.ryuJump);
		map.put(KeyEvent.VK_A, a.ryuPunch);
		map.put(KeyEvent.VK_S, a.ryuPunch2);
		
		this.addKeyListener(new KeyListener() {	
			public synchronized void keyPressed(KeyEvent e) {
		
				int key = e.getKeyCode();

				if(!pressed.contains(new Integer(key))) {
					pressed.add(key);
				}
				if(pressed.size()>1) {
					
					
					return;
				}

				
//				if(pressed.contains(KeyEvent.VK_ESCAPE)) {
//					dispose();
//					System.exit(0);
//				}
//
//				if(pressed.contains(KeyEvent.VK_LEFT)) {
//					next = a.ryuWalk;
//					next.reverse();
//				}
//				else if(pressed.contains(KeyEvent.VK_RIGHT)) {
//					next = a.ryuWalk;
//				}
//				else if(pressed.contains(KeyEvent.VK_UP)) {
//					next = a.ryuJump;
//				} 
				
				if(map.get(key)!=null&cancellable) {
					a.current.reset();
					a.current = map.get(key);
					a.current.start();
					
				}

			}
			public synchronized void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				
				if(pressed.remove(new Integer(key))&&cancellable) {
					a.current.reset();

				}

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

	public void move() {
		if(next!=null) {
			a.current.reset();
			a.current = next;
			a.current.start();
		} else {
			a.current = a.ryuIdle;
		}
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
				
				for(Integer i : pressed) {
					System.out.print(i);
				}
				System.out.println();
				
				s.repaint();
				frames.setInt(frames.getInt()+1);
			}
		}
	}
	public static void main(String[] str) {
		new StreetFighter();
	}
}
