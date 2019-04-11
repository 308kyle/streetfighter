package sf;

import java.awt.Graphics;

public class Fighter {
	private SpriteSheet s;
	private AnimatedSprite ryuIdle;
	private AnimatedSprite ryuWalk;
	private AnimatedSprite ryu;
	private int x;
	private int y;
	private int velx;
	private int vely;
	private int grav = 5;
		
	public Fighter(String filename) {
		
		s = new SpriteSheet(filename);
		ryuIdle = new AnimatedSprite(s, 4, new int[][] {{0, 10, 50, 90},
														{50, 10, 50, 90},
														{100, 10, 50, 90},
														{150, 10, 50, 90}});
		ryuWalk = new AnimatedSprite(s, 3, new int[][] {{200, 10, 40, 90},
														{250, 10, 40, 90},
														{300, 10, 40, 90},
														{350, 10, 40, 90},
														{400, 10, 40, 90}});
		
		if(filename.equals("ryu good transparent.png")) {
			
		}
		x = 100;
		y = 100;
		velx = 5;
		vely = 0;
	}
	public SpriteSheet getSheet() {
		return s;
	}
	public void draw(Graphics g, int frames) {
		g.drawImage(ryuWalk.update(frames, 60), 100, 100, null);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getVelx() {
		return velx;
	}
	public double getVely() {
		return vely;
	}
	public void setX(int a) {
		x = a;
	}
	public void setY(int a) {
		y = a;
	}
	public void setVelx(int a) {
		velx = a;
	}
	public void setVely(int a) {
		vely = a;
	}
}