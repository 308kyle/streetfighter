package sf;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;


import javax.swing.JFrame;

public class StreetFighter extends JFrame {
	Fighter a = new Fighter(true,300,800);
	Fighter b = new Fighter(false,1500,800);
	public static boolean noWinner = true;
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
	public void collisions() {
		AnimatedSprite ac = a.current;
		AnimatedSprite bc = b.current;

		//facing right
		ac.getSSprite().box.get(0).setLocation(a.getX().getInt()-150,
				a.getY().getInt()-a.current.getSprite().getHeight());
		bc.getSSprite().box.get(0).setLocation(b.getX().getInt()-150,
				b.getY().getInt()-b.current.getSprite().getHeight());

		//facing left
		ac.getSSprite().box.get(1).setLocation(a.getX().getInt()-a.current.getSprite().getWidth(),
				a.getY().getInt()-a.current.getSprite().getHeight());
		bc.getSSprite().box.get(1).setLocation(b.getX().getInt()-b.current.getSprite().getWidth(),
				b.getY().getInt()-b.current.getSprite().getHeight());

		if(a.getDirection()==1) {
			if(ac.getSSprite().box.get(0).intersects(bc.getSSprite().box.get(1))) {
				if(ac==a.ryuWalk) {
					if(bc==b.ryuIdleR||bc==b.ryuBlockR||bc==b.ryuCBlockR||bc==b.ryuCrouchR) {
						a.setX((a.getX().getInt()-150)-ac.getSSprite().dx());
					}
					if(bc==b.ryuWalkR) {
						a.setX((a.getX().getInt()-150)-ac.getSSprite().dx());
						b.setX((b.getX().getInt()-b.current.getSprite().getWidth())+bc.getSSprite().dx());
					}
					if(bc==b.ryuPunchR||bc==b.ryuPunch2R) {
						ac.reset();
						ac = a.ryuFHit;
						ac.start();
						a.sethp(a.gethp()-10);
					}
					if(bc==b.ryuCKickR) {
						ac.reset();
						ac = a.ryuHit;
						ac.start();
						a.sethp(a.gethp()-10);
					}
				}
				if(ac==a.ryuIdle) {
					if(bc==b.ryuWalkR) {
						b.setX((b.getX().getInt()-b.current.getSprite().getWidth())+bc.getSSprite().dx());
					}
					if(bc==b.ryuPunchR||bc==b.ryuPunch2R) {
						ac.reset();
						ac = a.ryuFHit;
						ac.start();
						a.sethp(a.gethp()-10);
					}
					if(bc==b.ryuCKickR) {
						ac.reset();
						ac = a.ryuHit;
						ac.start();
						a.sethp(a.gethp()-10);
					}
				}
				if(ac==a.ryuBlock) {
					if(bc==b.ryuWalkR) {
						b.setX((b.getX().getInt()-b.current.getSprite().getWidth())+bc.getSSprite().dx());
					}
					if(bc==b.ryuCKickR) {
						ac.reset();
						ac = a.ryuHit;
						ac.start();
						a.sethp(a.gethp()-10);
					}
				}
				if(ac==a.ryuCBlock) {
					if(bc==b.ryuWalkR) {
						b.setX((b.getX().getInt()-b.current.getSprite().getWidth())+bc.getSSprite().dx());
					}
				}
				if(ac==a.ryuPunch||ac==a.ryuPunch2) {
					if(bc==b.ryuWalkR||bc==b.ryuIdleR) {
						bc.reset();
						bc = b.ryuFHitR;
						bc.start();
						b.sethp(b.gethp()-10);
					}
					if(bc==b.ryuHitR||bc==b.ryuFHitR) {
						bc.reset();
						bc = b.ryuFHitR;
						bc.start();
						b.sethp(b.gethp()-10);
					}
					if(bc==b.ryuPunchR||bc==b.ryuPunch2R) {
						bc.reset();
						ac.reset();
						ac = a.ryuFHit;
						bc = b.ryuFHitR;
						bc.start();
						ac.start();
						b.sethp(b.gethp()-10);
						a.sethp(a.gethp()-10);
					}
				}
				if(ac==a.ryuCKick) {
					if(bc==b.ryuWalkR||bc==b.ryuIdleR) {
						bc.reset();
						bc = b.ryuHitR;
						bc.start();
						b.sethp(b.gethp()-10);
					}
					if(bc==b.ryuHitR||bc==b.ryuFHitR) {
						bc.reset();
						bc = b.ryuFHitR;
						bc.start();
						b.sethp(b.gethp()-10);
					}
				}
				if(ac==a.ryuCrouch) {
					if(bc==b.ryuWalkR) {
						b.setX((b.getX().getInt()-b.current.getSprite().getWidth())+bc.getSSprite().dx());
					}
					if(bc==b.ryuCKickR) {
						ac.reset();
						ac = a.ryuHit;
						ac.start();
						a.sethp(a.gethp()-10);
					}
				}
			}
		}
		if(a.getDirection()==-1) {
			if(ac.getSSprite().box.get(1).intersects(bc.getSSprite().box.get(0))) {
				if(ac==a.ryuWalkR) {
					if(bc==b.ryuIdle||bc==b.ryuBlock||bc==b.ryuCBlock||bc==b.ryuCrouch) {
						a.setX((a.getX().getInt()-a.current.getSprite().getWidth())+ac.getSSprite().dx());
					}
					if(bc==b.ryuWalk) {
						a.setX((a.getX().getInt()-a.current.getSprite().getWidth())+ac.getSSprite().dx());
						b.setX((b.getX().getInt()-150)-bc.getSSprite().dx());
					}
					if(bc==b.ryuPunch||bc==b.ryuPunch2) {
						ac.reset();
						ac = a.ryuFHitR;
						ac.start();
						a.sethp(a.gethp()-10);
					}
					if(bc==b.ryuCKick) {
						ac.reset();
						ac = a.ryuHitR;
						ac.start();
						a.sethp(a.gethp()-10);
					}
				}
				if(ac==a.ryuIdleR) {
					if(bc==b.ryuWalk) {
						b.setX((b.getX().getInt()-150)-bc.getSSprite().dx());
					}
					if(bc==b.ryuPunch||bc==b.ryuPunch2) {
						ac.reset();
						ac = a.ryuFHitR;
						ac.start();
						a.sethp(a.gethp()-10);
					}
					if(bc==b.ryuCKick) {
						ac.reset();
						ac = a.ryuHitR;
						ac.start();
						a.sethp(a.gethp()-10);
					}
				}
				if(ac==a.ryuBlockR) {
					if(bc==b.ryuWalk) {
						b.setX((b.getX().getInt()-150)-bc.getSSprite().dx());
					}
					if(bc==b.ryuCKick) {
						ac.reset();
						ac = a.ryuHitR;
						ac.start();
						a.sethp(a.gethp()-10);
					}
				}
				if(ac==a.ryuCBlockR) {
					if(bc==b.ryuWalk) {
						b.setX((b.getX().getInt()-150)+bc.getSSprite().dx());
					}
				}
				if(ac==a.ryuPunchR||ac==a.ryuPunch2R) {
					if(bc==b.ryuWalk||bc==b.ryuIdle) {
						bc.reset();
						bc = b.ryuFHit;
						bc.start();
						b.sethp(b.gethp()-10);
					}
					if(bc==b.ryuHit||bc==b.ryuFHit) {
						bc.reset();
						bc = b.ryuFHit;
						bc.start();
						b.sethp(b.gethp()-10);
					}
					if(bc==b.ryuPunch||bc==b.ryuPunch2) {
						bc.reset();
						ac.reset();
						ac = a.ryuFHitR;
						bc = b.ryuFHit;
						bc.start();
						ac.start();
						b.sethp(b.gethp()-10);
						a.sethp(a.gethp()-10);
					}
				}
				if(ac==a.ryuCKickR) {
					if(bc==b.ryuWalk||bc==b.ryuIdle) {
						bc.reset();
						bc = b.ryuHit;
						bc.start();
						b.sethp(b.gethp()-10);
					}
					if(bc==b.ryuHit||bc==b.ryuFHit) {
						bc.reset();
						bc = b.ryuFHit;
						bc.start();
						b.sethp(b.gethp()-10);
					}
				}
				if(ac==a.ryuCrouchR) {
					if(bc==b.ryuWalk) {
						b.setX((b.getX().getInt()-150)-bc.getSSprite().dx());
					}
					if(bc==b.ryuCKick) {
						ac.reset();
						ac = a.ryuHitR;
						ac.start();
						a.sethp(a.gethp()-10);
					}
				}
			}
		}
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

		while(timer.getInt()>0 || noWinner) {
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
				collisions();

				s.repaint();
				frames.setInt(frames.getInt()+1);
			}
		}
	}
	public static void main(String[] str) {
		new StreetFighter();
	}
}
