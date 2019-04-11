package sf;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimatedSprite {
	private boolean stop;
	private ArrayList<BufferedImage> animation = new ArrayList<BufferedImage>();
	
	public AnimatedSprite(SpriteSheet s, int num, int[][] coords) {
		for(int i=0;i<coords.length;i++) {
			addSprite(s.getSprite(coords[i][0], coords[i][1], coords[i][2], coords[i][3], 1));
		}
		addSprite(s.getSprite(0, 0, 60, 90, 1));
		
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
		
	}
	public BufferedImage update(int frames) {
		if(!stop) {
			System.out.println(frames);
			int frame = (int) ((float)frames/60 * (animation.size()-1)); 
			return animation.get(frame);
		}
		return null;
	}
}
