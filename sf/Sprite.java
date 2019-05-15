package sf;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Sprite {
	private ArrayList<Rectangle> box = new ArrayList<Rectangle>();
	private int dx;
	private int dy;
	private int x;
	private int y;
	private int x1;
	private int y1;
	private int w;
	private int h;
	private BufferedImage img;
	public Sprite( int x, int y, int x1, int y1, int dx, int dy, SpriteSheet s) {
		w = x1 - x + 1;
		h = y1 - y + 1;
		this.dx = dx;
		this.dy = dy;
		img = s.getSprite(x, y, w, h, 4);		
	}
	public BufferedImage getSprite() {
		return img;
	}
	public void setX(int newx) {
		x = newx;
	}
	public void setY(int newy) {
		y = newy;
	}
	public int dx() {
		return dx;
	}
	public int dy() {
		return dy;
	}
	public void addBox(Rectangle b) {
		box.add(b);
	}
	public void createBox() {
		box.add(new Rectangle(w, h));
	}
	
}
