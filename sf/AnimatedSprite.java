package sf;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimatedSprite {
	private boolean stop;
	private ArrayList<BufferedImage> animation = new ArrayList<BufferedImage>();
	private BufferedImage end;
	private BufferedImage start;
	
	public AnimatedSprite(SpriteSheet s, int num, int[][] coords) {
		for(int i=0;i<coords.length;i++) {
			addSprite(s.getSprite(coords[i][0], coords[i][1], coords[i][2], coords[i][3], 3));
		}
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
	public BufferedImage update(int frames, int duration) {
		if(!stop) {
			int f = duration/animation.size();
			int frame = frames/f;
			if(frame==animation.size()) {
				frame = animation.size()-1;
			}
			return animation.get(frame);
		}
		return null;
	}
}
