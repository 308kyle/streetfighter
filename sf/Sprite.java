package sf;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	private SpriteSheet s;
	private BufferedImage img;
	
	public Sprite(int x, int y, int x1, int y1, int dx, int dy, SpriteSheet s) {
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		
		this.s = s;
		
		w = x1 - x + 1;
		h = y1 - y + 1;
		this.dx = dx;
		this.dy = dy;
		img = s.getSprite(x, y, w, h, 4);		
	}
	public static Sprite clone(Sprite s) {
		Sprite c = new Sprite(s.x(),s.y(),s.x1(),s.y1(), s.dx(), s.dy(), s.ss());
		return c;
	}
	public static Sprite[] clones(Sprite[] s) {
		Sprite[] c = new Sprite[s.length];
		for(int i=0;i<s.length;i++) {
			c[i] = Sprite.clone(s[i]);
		}
		for(int i=0;i<s.length;i++) {
			c[i].setSprite(SpriteSheet.MirrorImage(c[i].getSprite()));
		}
		return c;
	}
	public static Sprite[] clones2(Sprite[] s) {
		Sprite[] c = new Sprite[s.length];
		for(int i=0;i<s.length;i++) {
			c[i] = Sprite.clone(s[i]);
		}
		
		return c;
	}
	public BufferedImage getSprite() {
		return img;
	}
	public void setSprite(BufferedImage b) {
		img = b;
	}
	
	public static BufferedImage copyImage(BufferedImage source){
	    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics2D g = b.createGraphics();
	    //Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	
	public int dx() {
		return dx;
	}
	public int dy() {
		return dy;
	}
	public int x() {
		return x;
	}
	public int y() {
		return y;
	}
	public int x1() {
		return x1;
	}
	public int y1() {
		return y1;
	}
	public SpriteSheet ss() {
		return s;
	}
	public void addBox(Rectangle b) {
		box.add(b);
	}
	public void createBox() {
		box.add(new Rectangle(w, h));
	}
	
}
