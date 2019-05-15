package sf;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimatedSprite implements Cloneable {

	private Sprite[] animation;
	private int currentFrame = 0;
	private int lastFrame = 0;
	private int frameCount = 0;
	private int frameDelay;
	private int frameDuration;
	private int length;
	
	private boolean cancel;
	private boolean stop;
	private boolean reverse;
	private boolean repeat;

	public AnimatedSprite(int frameDelay, int frameDuration, boolean repeat, boolean cancel, Sprite[] a) {
		animation = a;

		this.frameDelay = frameDelay;
		this.frameDuration = frameDuration;
		this.repeat = repeat;
		this.cancel = cancel;
		
		length = animation.length;
		stop = true;
	}
	public static AnimatedSprite copy(AnimatedSprite a) {
		try {
			AnimatedSprite copy = (AnimatedSprite) a.clone();
			
			Sprite[] temp = new Sprite[a.sprites().length];
			Sprite[] c = a.sprites().clone();
			for(int i=0;i<a.sprites().length;i++) {
				temp[i].setSprite(SpriteSheet.MirrorImage(c[i].getSprite()));
			}
		
			return copy;
		} catch (CloneNotSupportedException e) {
			System.out.println("Reee");
		}
		return a;
		
	}
	
	
	public void start() {
		stop = false;
	}
	public boolean stopped() {
		return stop;
	}
	public Sprite[] sprites() {
		return animation;
	}
	public int getDuration() {
		return frameDuration;
	}
	public void restart() {
		stop = true;
	}
	public void reverse() {
		reverse = true;
	}
	public boolean update(MutableInt x, MutableInt y) {

		int framespersprite = frameDuration/length;
		if(!stop) {
			frameCount++;
			
			if(frameCount >= frameDelay) {	
				
				currentFrame = (frameCount-frameDelay)/framespersprite;
				
				if(currentFrame == animation.length) {
					
					if(!repeat) {
						
						this.reset();
						return true;
					}
					currentFrame = 0;
					frameCount = frameDelay+1;
				}
				if(!reverse) {
					x.setInt(x.getInt()+animation[currentFrame].dx());
					y.setInt(y.getInt()+animation[currentFrame].dy());
				} else {
					x.setInt(x.getInt()-animation[currentFrame].dx());
					y.setInt(y.getInt()-animation[currentFrame].dy());
				}
			}
		}
		return cancel;
	}
	
	public void reset() {
		stop = true;
		reverse = false;
		frameCount = 0;
		currentFrame = 0;
		
	}
	public BufferedImage getSprite() {
		if(!reverse)
			return animation[currentFrame].getSprite();
		return animation[animation.length-1-currentFrame].getSprite();
	}
	
}
