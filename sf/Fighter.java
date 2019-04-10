package sf;

import java.awt.Graphics;

public class Fighter {
	private SpriteSheet s;

	private AnimatedSprite walk;
	private double x;
	private double y;
	private double velx;
	private double vely;
	private int grav = 5;
	
	public Fighter(double xpos, double ypos, double vx, double vy, String filename) {
		s = new SpriteSheet(filename);
		x = xpos;
		y = ypos;
		velx = vx;
		vely = vy;
	}
	public SpriteSheet getSheet() {
		return s;
	}
	public void draw(Graphics g) {
		g.drawImage(s.getSprite(0, 0, 60, 100), (int)x, (int)y, null);
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
	public void setX(double a) {
		x = a;
	}
	public void setY(double a) {
		y = a;
	}
	public void setVelx(double a) {
		velx = a;
	}
	public void setVely(double a) {
		vely = a;
	}
}