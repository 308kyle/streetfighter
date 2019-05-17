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
	private boolean noWinner = true;
	//	Queue<Integer> inputs = new LinkedList<Integer>();
	ArrayList<Integer> pressed = new ArrayList<Integer>();
	ArrayList<Integer> ai = new ArrayList<Integer>();
	Map<Integer, AnimatedSprite> map = new HashMap<Integer, AnimatedSprite>();
	Map<Integer, AnimatedSprite> mapR = new HashMap<Integer, AnimatedSprite>();
	//	Set<Integer> pressed = new HashSet<Integer>();
	int[] keys = {KeyEvent.VK_A,KeyEvent.VK_S,KeyEvent.VK_D};


	AnimatedSprite[] moves = {a.ryuCrouch,a.ryuIdle,a.ryuPunch,a.ryuPunch2,a.ryuBlock,a.ryuCKick,a.ryuFJump};
	AnimatedSprite[] movesR = {a.ryuCrouchR,a.ryuIdleR,a.ryuPunchR,a.ryuPunch2R,a.ryuBlockR,a.ryuCKickR,a.ryuFJumpR};


	MutableInt timer = new MutableInt(99);

	int lastFrame = 0;
	int count = 0;

	boolean cancellable;
	boolean cancellable2;

	boolean canMove;

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
					if(cancellable) {
						for(int i=pressed.size()-1;i>0;i--) {
							if(pressed.get(i)==KeyEvent.VK_A) {
								if(pressed.get(i-1)==KeyEvent.VK_DOWN) {

									if(a.getDirection()==1) {

										a.current = a.ryuCKick;
										a.current.start();

									} else {		
										a.current = a.ryuCKickR;
										a.current.reverse2();
										a.current.start();

									}
								}
							}
							if(pressed.get(i)==KeyEvent.VK_UP) {
								if(pressed.get(i-1)==KeyEvent.VK_LEFT) {
									if(a.getDirection()==1) {
										a.current = a.ryuFJump;
										a.current.reverse2();
										a.current.start();

									} else {		
										a.current = a.ryuFJumpR;
										a.current.reverse2();
										a.current.start();

									}
								}
								if(pressed.get(i-1)==KeyEvent.VK_RIGHT) {
									if(a.getDirection()==1) {
										a.current = a.ryuFJump;
										a.current.start();

									} else {		
										a.current = a.ryuFJumpR;

										a.current.start();

									}

								}
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
	public void win() {
		if(a.gethp()==0) {
			if(a.getDirection()==1) {
				a.current.reset();
				cancellable = false;
				a.current = a.ryuKO;
				a.current.start();
				
				b.current.reset();
				cancellable2 = false;
				b.current = b.ryuV1R;
				b.current.start();
				
				if(b.current.getSSprite()==b.ryuV1R.sprites()[2]) {
					noWinner = false;
				}
			}else {
				a.current.reset();
				cancellable = false;
				a.current = a.ryuKOR;
				a.current.start();
				
				b.current.reset();
				cancellable2 = false;
				b.current = b.ryuV1;
				b.current.start();
				if(b.current.getSSprite()==b.v3) {
					noWinner = false;
				}
			}
		}else if(b.gethp()==0){
			if(b.getDirection()==1) {
				b.current.reset();
				cancellable2 = false;
				b.current = b.ryuKO;
				b.current.start();
				
				a.current.reset();
				cancellable = false;
				a.current = a.ryuV1R;
				a.current.start();
				if(a.current.getSSprite()==a.ryuV1R.sprites()[2]) {
					noWinner = false;
				}
			}else {
				b.current.reset();
				cancellable2 = false;
				b.current = b.ryuKOR;
				b.current.start();
				
				a.current.reset();
				cancellable = false;
				a.current = a.ryuV1;
				a.current.start();
				if(a.current.getSSprite()==a.v3) {
					noWinner = false;
				}
			}
		}
	}
	public void collisions() {	

		a.current.getSSprite().box.get(0).setLocation(a.getX().getInt()-150,
				a.getY().getInt()-a.current.getSprite().getHeight());



		b.current.getSSprite().box.get(0).setLocation(b.getX().getInt()-150,
				b.getY().getInt()-b.current.getSprite().getHeight());


		a.current.getSSprite().box.get(1).setLocation(a.getX().getInt()-a.current.getSprite().getWidth(),
				a.getY().getInt()-a.current.getSprite().getHeight());
		b.current.getSSprite().box.get(1).setLocation(b.getX().getInt()-b.current.getSprite().getWidth(),
				b.getY().getInt()-b.current.getSprite().getHeight());


		if(a.getDirection()==1) {
			if(a.current.getSSprite().box.get(0).intersects(b.current.getSSprite().box.get(1))) {
				if(a.current==a.ryuWalk) {
					if(b.current==b.ryuPunchR||b.current==b.ryuPunch2R) {

						a.current.reset();
						a.current = a.ryuFHit;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
					if(b.current==b.ryuCKickR) {
						a.current.reset();
						a.current = a.ryuHit;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
				}
				if(a.current==a.ryuIdle) {
					if(b.current==b.ryuPunchR||b.current==b.ryuPunch2R) {
						a.current.reset();
						a.current = a.ryuFHit;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
					if(b.current==b.ryuCKickR) {
						a.current.reset();
						a.current = a.ryuHit;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
				}
				if(a.current==a.ryuBlock) {

					if(b.current==b.ryuCKickR) {
						a.current.reset();
						a.current = a.ryuHit;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
				}
				if(a.current==a.ryuCBlock) {

				}
				if(a.current==a.ryuPunch||a.current==a.ryuPunch2) {
					if(b.current==b.ryuWalkR||b.current==b.ryuIdleR) {
						b.current.reset();
						b.current = b.ryuFHitR;
						b.current.start();
						b.sethp(b.gethp()-10);

					}
					if(b.current==b.ryuHitR||b.current==b.ryuFHitR) {
						b.current.reset();
						b.current = b.ryuFHitR;
						b.current.start();
						b.sethp(b.gethp()-10);
					}
					if(b.current==b.ryuPunchR||b.current==b.ryuPunch2R) {
						b.current.reset();
						a.current.reset();
						a.current = a.ryuFHit;
						b.current = b.ryuFHitR;
						b.current.start();
						a.current.start();
						b.sethp(b.gethp()-10);
						a.sethp(a.gethp()-10);
					}
				}
				if(a.current==a.ryuCKick) {
					if(b.current==b.ryuWalkR||b.current==b.ryuIdleR) {
						b.current.reset();
						b.current = b.ryuHitR;
						b.current.start();
						b.sethp(b.gethp()-10);
					}
					if(b.current==b.ryuHitR||b.current==b.ryuFHitR) {
						b.current.reset();
						b.current = b.ryuFHitR;
						b.current.start();
						b.sethp(b.gethp()-10);
					}
				}
				if(a.current==a.ryuCrouch) {

					if(b.current==b.ryuCKickR) {
						a.current.reset();
						a.current = a.ryuHit;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
				}
			}
		}
		if(a.getDirection()==-1) {
			if(a.current.getSSprite().box.get(1).intersects(b.current.getSSprite().box.get(0))) {
				if(a.current==a.ryuWalkR) {


					if(b.current==b.ryuPunch||b.current==b.ryuPunch2) {
						a.current.reset();
						a.current = a.ryuFHitR;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
					if(b.current==b.ryuCKick) {
						a.current.reset();
						a.current = a.ryuHitR;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
				}
				else if(a.current==a.ryuIdleR) {

					if(b.current==b.ryuPunch||b.current==b.ryuPunch2) {
						a.current.reset();
						a.current = a.ryuFHitR;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
					if(b.current==b.ryuCKick) {
						a.current.reset();
						a.current = a.ryuHitR;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
				}
				else if(a.current==a.ryuBlockR) {

					if(b.current==b.ryuCKick) {
						a.current.reset();
						a.current = a.ryuHitR;
						a.current.start();
						a.sethp(a.gethp()-10);
					}
				}
				else if(a.current==a.ryuCBlockR) {

				}
				else if(a.current==a.ryuPunchR||a.current==a.ryuPunch2R) {
					if(b.current==b.ryuWalk||b.current==b.ryuIdle) {
						b.current.reset();
						b.current = b.ryuFHit;
						b.current.start();
						b.sethp(b.gethp()-10);
					}
					if(b.current==b.ryuHit||b.current==b.ryuFHit) {
						b.current.reset();
						b.current = b.ryuFHit;
						b.current.start();
						b.sethp(b.gethp()-10);
					}
					if(b.current==b.ryuPunch||b.current==b.ryuPunch2) {
						b.current.reset();
						a.current.reset();
						a.current = a.ryuFHitR;
						b.current = b.ryuFHit;
						b.current.start();
						a.current.start();
						b.sethp(b.gethp()-10);
						a.sethp(a.gethp()-10);
					}
				}
				else if(a.current==a.ryuCKickR) {
					if(b.current==b.ryuWalk||b.current==b.ryuIdle) {
						b.current.reset();
						b.current = b.ryuHit;
						b.current.start();
						b.sethp(b.gethp()-10);
					}
					if(b.current==b.ryuHit||b.current==b.ryuFHit) {
						b.current.reset();
						b.current = b.ryuFHit;
						b.current.start();
						b.sethp(b.gethp()-10);
					}
				}
				else if(a.current==a.ryuCrouchR) {

					if(b.current==b.ryuCKick) {
						a.current.reset();
						a.current = a.ryuHitR;
						a.current.start();
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
		if(timer.getInt()<90) {
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
				int k = (int) (Math.random()*moves.length);
				if(cancellable2) {
					b.current.reset();
					if(a.getDirection() == -1) {
						b.current = moves[k]; 
					}
					else if(a.getDirection() == 1) {
						b.current = movesR[k]; 
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
				cancellable = a.current.update(a.getX(), a.getY(), canMove);
				cancellable2 = b.current.update(b.getX(), b.getY(), canMove);

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
