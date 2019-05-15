package sf;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;


import javax.swing.JFrame;

public class StreetFighter extends JFrame {
	Fighter a = new Fighter(true,300,800);
	Fighter b = new Fighter(false,1500,800);

	//	Queue<Integer> inputs = new LinkedList<Integer>();
	ArrayList<Integer> pressed = new ArrayList<Integer>();
	ArrayList<Integer> ai = new ArrayList<Integer>();
	Map<Integer, AnimatedSprite> map = new HashMap<Integer, AnimatedSprite>();
	Map<Integer, AnimatedSprite> mapR = new HashMap<Integer, AnimatedSprite>();
	//	Set<Integer> pressed = new HashSet<Integer>();
	int[] keys = {KeyEvent.VK_A,KeyEvent.VK_S,KeyEvent.VK_D};


	MutableInt timer = new MutableInt(99);

	int lastFrame = 0;
	int count = 0;

	boolean cancellable;
	boolean cancellable2;

	public StreetFighter() {
		super("Street Fighter");

		map.put(KeyEvent.VK_LEFT, a.ryuWalkL);
		map.put(KeyEvent.VK_RIGHT, a.ryuWalk);
		map.put(KeyEvent.VK_UP, a.ryuJump);
		map.put(KeyEvent.VK_DOWN, a.ryuCrouch);
		map.put(KeyEvent.VK_A, a.ryuPunch);
		map.put(KeyEvent.VK_S, a.ryuPunch2);
		map.put(KeyEvent.VK_D, a.ryuBlock);


		mapR.put(KeyEvent.VK_LEFT, a.ryuWalkLR);
		mapR.put(KeyEvent.VK_RIGHT, a.ryuWalkR);
		mapR.put(KeyEvent.VK_UP, a.ryuJumpR);
		mapR.put(KeyEvent.VK_DOWN, a.ryuCrouchR);
		mapR.put(KeyEvent.VK_A, a.ryuPunchR);
		mapR.put(KeyEvent.VK_S, a.ryuPunch2R);
		mapR.put(KeyEvent.VK_D, a.ryuBlockR);

		this.addKeyListener(new KeyListener() {	
			public synchronized void keyPressed(KeyEvent e) {

				int key = e.getKeyCode();

				if(!pressed.contains(new Integer(key))) {
					pressed.add(key);
				}
				if(key == KeyEvent.VK_ESCAPE) {
					dispose();
					System.exit(0);
				}
				System.out.println(pressed.size());
				if(pressed.size()>1) {
					for(int i=pressed.size()-1;i>0;i--) {
						if(pressed.get(i)==KeyEvent.VK_A) {
							if(pressed.get(i-1)==KeyEvent.VK_DOWN) {
								a.current = a.ryuCKick;
								a.current.start();
								
							}
						}
					}
				}
				else {
					if(map.get(key)!=null && cancellable) {
						if(a.getDirection()==1) {
							a.current = map.get(key);
						} else {
							a.current = mapR.get(key);
						}
						a.current.start();
					}
				}

				if(key == KeyEvent.VK_R && timer.getInt()==0) {
					new StreetFighter();
				}

			}
			public synchronized void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				pressed.remove(new Integer(key));
				if(cancellable) {
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
	public void move() {
		if(b.current.stopped()) {
			if(a.getDirection()==-1) {
				b.current = b.ryuIdle;
			} else {
				b.current = b.ryuIdleR;
			}
			b.current.start();
		}
		if(timer.getInt()<97) {
			if(a.getX().getInt()-b.getX().getInt()<-200) {
				count++;
				if(count>45) {
					b.current = b.ryuWalkLR;
				}
			} else if(a.getX().getInt()-b.getX().getInt()>200) {
				count++;
				if(count>45) {
					b.current = b.ryuWalk;
				}
			} else {
				count = 0;
				int k = (int) (Math.random()*keys.length);
				if(cancellable2) {
					b.current.reset();
					if(a.getDirection() == -1) {
						b.current = map.get(keys[k]); 
					}
					else if(a.getDirection() == 1) {
						b.current = mapR.get(keys[k]); 
					}
				}
			}

			b.current.start();

		} else {
			b.current = b.ryuIdleR;
			b.current.start();
		}
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

				if(a.current.stopped()&&a.getDirection()==1) {
					a.current = a.ryuIdle;
					a.current.start();
				} else if(a.current.stopped()&&a.getDirection()==-1) {
					a.current = a.ryuIdleR;
					a.current.start();
				}

				cancellable = a.current.update(a.getX(), a.getY());
				cancellable2 = b.current.update(b.getX(), b.getY());

				a.setDirection(b.getX().getInt());
				b.setDirection(a.getX().getInt());
				move();

				s.repaint();
				frames.setInt(frames.getInt()+1);
			}
		}
	}
	public static void main(String[] str) {
		new StreetFighter();
	}
}
