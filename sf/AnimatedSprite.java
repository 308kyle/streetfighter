package sf;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimatedSprite {

	private ArrayList<BufferedImage> animation = new ArrayList<BufferedImage>();
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

	private int[] deltax;
	private int[] deltay;

	public AnimatedSprite(SpriteSheet s, int frameDelay, int frameDuration, boolean repeat, boolean cancel, int[] deltax, int[] deltay, int[][] coords) {
		for(int i=0;i<coords.length;i++) {
			addSprite(s.getSprite(coords[i][0], coords[i][1], coords[i][2], coords[i][3], 4));
		}

		this.frameDelay = frameDelay;
		this.frameDuration = frameDuration;
		this.repeat = repeat;
		this.cancel = cancel;

		this.deltax = deltax;
		this.deltay = deltay;

		length = animation.size();
		stop = true;
	}
	private void addSprite(BufferedImage i) {
		animation.add(i);
	}
	public void start() {
		stop = false;
	}
	public boolean stopped() {
		return stop;
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
			
			if(frameCount > frameDelay) {	
				
				currentFrame = (frameCount-frameDelay)/framespersprite;
				
				if(currentFrame == animation.size()) {
					if(!repeat) {
						this.reset();
						return true;
					}
					currentFrame = 0;
					frameCount = frameDelay+1;
				}
				if(!reverse) {
					x.setInt(x.getInt()+deltax[currentFrame]);
					y.setInt(y.getInt()+deltay[currentFrame]);
				} else {
					x.setInt(x.getInt()-deltax[currentFrame]);
					y.setInt(y.getInt()-deltay[currentFrame]);
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
			return animation.get(currentFrame);
		return animation.get(animation.size()-1-currentFrame);
	}
	
}
