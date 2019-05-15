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

public class StreetFighter extends JFrame {
	Fighter a = new Fighter(true,100,800);
	Fighter b = new Fighter(false,800,800);
	
//	Queue<Integer> inputs = new LinkedList<Integer>();
	ArrayList<Integer> pressed = new ArrayList<Integer>();
	ArrayList<Integer> ai = new ArrayList<Integer>();
	Map<Integer, AnimatedSprite> map = new HashMap<Integer, AnimatedSprite>();
	Map<Integer, AnimatedSprite> mapR = new HashMap<Integer, AnimatedSprite>();
//	Set<Integer> pressed = new HashSet<Integer>();
	
	MutableInt timer = new MutableInt(99);
	
	boolean cancellable;

	public StreetFighter() {
		super("Street Fighter");

		map.put(KeyEvent.VK_LEFT, a.ryuWalk);
		//map.put(KeyEvent.VK_DOWN, a.ryuWalk);
		map.put(KeyEvent.VK_RIGHT, a.ryuWalk);
		map.put(KeyEvent.VK_W, a.ryuJump);
		map.put(KeyEvent.VK_A, a.ryuWalk);
		map.put(KeyEvent.VK_S, a.ryuCrouch);
		map.put(KeyEvent.VK_D, a.ryuWalk);
		map.put(KeyEvent.VK_J, a.ryuPunch);
		map.put(KeyEvent.VK_K, a.ryuPunch2);
		map.put(KeyEvent.VK_L, a.ryuBlock);
		
		
		mapR.put(KeyEvent.VK_LEFT, a.ryuWalkR);
		//map.put(KeyEvent.VK_DOWN, a.ryuWalk);
		mapR.put(KeyEvent.VK_RIGHT, a.ryuWalkR);
		mapR.put(KeyEvent.VK_W, a.ryuJumpR);
		mapR.put(KeyEvent.VK_A, a.ryuWalkR);
		mapR.put(KeyEvent.VK_S, a.ryuCrouchR);
		mapR.put(KeyEvent.VK_D, a.ryuWalkR);
		mapR.put(KeyEvent.VK_J, a.ryuPunchR);
		mapR.put(KeyEvent.VK_K, a.ryuPunch2R);
		mapR.put(KeyEvent.VK_L, a.ryuBlockR);
		
		this.addKeyListener(new KeyListener() {	
			public synchronized void keyPressed(KeyEvent e) {
		
				int key = e.getKeyCode();

				if(!pressed.contains(new Integer(key))) {
					pressed.add(key);
				}
				if(pressed.size()>1) {
								
					return;
				}
				
				if(map.get(key)!=null && cancellable) {
					a.current = mapR.get(key);
					a.current.start();
				}
				if(key == KeyEvent.VK_R && timer.getInt()==0) {
					new StreetFighter();
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
		Screen s = new Screen(a, b, frames, timer);
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

		while(timer.getInt()>0) {
			long current = System.currentTimeMillis();
			if(current>=target_time) {
				if(frames.getInt()==60) {
					frames.setInt(0);
					timer.setInt(timer.getInt()-1);
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
