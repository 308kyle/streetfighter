package sf;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Sprite {
	private ArrayList<Rectangle> box;
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
		img = s.getSprite(x, y, w, h, 4);		
	}
	public BufferedImage getSprite() {
		return img;
	}
	public int dx() {
		return dx;
	}
	public int dy() {
		return dy;
	}
	public void createBox() {
		box.add(new Rectangle(w, h));
	}
}
