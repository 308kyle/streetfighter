package sf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	final static int spriteSize = 50;
	
	BufferedImage spriteSheet;
	
	public SpriteSheet(String fileName) {
		try {
			spriteSheet = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getSprite(int r, int c) {
		return spriteSheet.getSubimage(c*spriteSize, r*spriteSize, spriteSize, spriteSize);
	}
}
