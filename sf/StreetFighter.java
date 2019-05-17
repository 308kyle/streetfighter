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
	ArrayList<Integer> pressed = new ArrayList<Integer>();
	ArrayList<Integer> ai = new ArrayList<Integer>();
	Map<Integer, AnimatedSprite> map = new HashMap<Integer, AnimatedSprite>();
	Map<Integer, AnimatedSprite> mapR = new HashMap<Integer, AnimatedSprite>();
	int[] keys = {KeyEvent.VK_A,KeyEvent.VK_S,KeyEvent.VK_D};


	AnimatedSprite[] moves = {a.ryuCrouch,a.ryuIdle,a.ryuPunch,a.ryuPunch2,a.ryuBlock,a.ryuCKick};
	AnimatedSprite[] movesR = {a.ryuCrouchR,a.ryuIdleR,a.ryuPunchR,a.ryuPunch2R,a.ryuBlockR,a.ryuCKickR};


	MutableInt timer = new MutableInt(99);

	int lastFrame = 0;
	int count = 0;

	boolean cancellable;
	boolean cancellable2;
	static boolean winner = false;
	boolean hit;

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
		if(a.gethp()<=0) {
			winner = true;

		}else if(b.gethp()<=0){
			winner = true;
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
				if(a.current!=a.ryuJump&&a.current!=a.ryuFJump&&b.current!=a.ryuJumpR&&b.current!=a.ryuFJumpR) {
					if(a.current==a.ryuPunch||a.current==a.ryuPunch2||a.current==a.ryuCKick) {
						if(b.current==a.ryuPunchR||a.current==a.ryuPunch2R||b.current==a.ryuCKickR&&!hit) {
							a.current.reset();
							a.current = a.ryuFHit;
							a.current.start();
							a.sethp(a.gethp()-10);

							b.current.reset();
							b.current = b.ryuFHitR;
							b.current.start();
							b.sethp(b.gethp()-10);
							hit = true;
						}
						else if(b.current!=a.ryuBlockR&&!hit){
							b.current.reset();
							b.current = a.ryuHitR;
							b.current.start();
							b.sethp(b.gethp()-10);
							hit = true;
						}

					} 
					else if(b.current==a.ryuPunchR||b.current==a.ryuPunch2R||b.current==a.ryuCKickR) {
						if((a.current!=a.ryuBlock)&&!hit) {
							a.current.reset();
							a.current = a.ryuFHit;
							a.current.start();
							a.sethp(a.gethp()-10);
							hit=true;
						}

					} 
					else {
						hit = false;
					}
				}
			}
		}
		else if(a.getDirection()==-1) {
			if(a.current.getSSprite().box.get(1).intersects(b.current.getSSprite().box.get(0))) {
				if(a.current!=a.ryuJumpR&&a.current!=a.ryuFJumpR&&b.current!=a.ryuJump&&b.current!=a.ryuFJump) {

					if(a.current==a.ryuPunchR||a.current==a.ryuPunch2R||a.current == a.ryuCKickR) {
						if(b.current==b.ryuPunch||b.current==b.ryuPunch2||b.current==b.ryuCKick&&!hit) {
							a.current.reset();
							a.current = a.ryuFHitR;
							a.current.start();
							a.sethp(a.gethp()-10);

							b.current.reset();
							b.current = b.ryuFHit;
							b.current.start();
							b.sethp(b.gethp()-10);
							hit = true;
						}

						else if(b.current!=b.ryuBlock&&!hit) {
							a.current.reset();
							a.current = a.ryuFHitR;
							a.current.start();
							a.sethp(a.gethp()-10);
							hit = true;
						}
						else {
							hit = false;
						}
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
				int k = (int) (Math.random()*movesR.length);
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

		}
	}
	public void gameLoop(Screen s, MutableInt frames) {

		final int fps = 60;
		final int targetMillis = 1000/fps;
		long last_time = System.currentTimeMillis();
		long target_time = last_time + targetMillis;

		while(timer.getInt()>0 && !winner) {
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
				win();
				s.repaint();
				frames.setInt(frames.getInt()+1);
			}

		}
		a.current.reset();
		b.current.reset();
		int g = Math.max(a.gethp(), b.gethp());
		if(g==b.gethp()) {
			if(a.getDirection()==1) {
				b.current = b.ryuV1R;
				a.current = a.ryuKO;
			} else {
				a.current = a.ryuKOR;
				b.current = b.ryuV1;
			}
		} 
		else if(g==a.gethp()){
			if(a.getDirection()==-1) {
				a.current = a.ryuV1R;
				b.current = b.ryuKO;
			} else {
				b.current = b.ryuKOR;
				a.current = a.ryuV1R;
			}
		}
		a.current.start();
		b.current.start();
	}


	public static void main(String[] str) {
		new StreetFighter();
	}
}
