package sf;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimatedSprite {
	
	private boolean stop;
	private ArrayList<BufferedImage> animation = new ArrayList<BufferedImage>();
	private int currentFrame = 0;
	private int frameCount = 0;
	private int frameDelay;
	private int length;
	private boolean reverse;

	public AnimatedSprite(SpriteSheet s, int frameDelay, int frameDuration, int[][] coords) {
		for(int i=0;i<coords.length;i++) {
			addSprite(s.getSprite(coords[i][0], coords[i][1], coords[i][2], coords[i][3], 4));
		}
		this.frameDelay = frameDelay;
		stop = true;
		length = animation.size();
	}
	private void addSprite(BufferedImage i) {
		animation.add(i);
	}
	public void start() {
		
		stop = false;
	
	}
	public void stop() {
		
		stop = true;
	}
	public void restart() {
		stop = true;
	}
	public void reverse() {
		reverse = true;
	}
	public void update(int frames) {
		int framespersprite = 60/length;
		
		if(!stop) {
			frameCount++;
			
			
			if(frameCount > frameDelay) {
				
				currentFrame = (frameCount-frameDelay)/framespersprite;
				if(currentFrame == animation.size()) {
					currentFrame = 0;
					frameCount = frameDelay+1;
				}
			}
		}
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
