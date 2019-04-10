package sf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.transform.Scale;

public class SpriteSheet {
	
	BufferedImage spriteSheet;
	
	public SpriteSheet(String fileName) {
		try {
			spriteSheet = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
//	public BufferedImage getSprite(int r, int c, int width, int height) {
//		return spriteSheet.getSubimage(c*height, r*width, width, height);
//	}
	public BufferedImage getSprite(int x, int y, int width, int height) {
		BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
		return null;
	
	}
}
