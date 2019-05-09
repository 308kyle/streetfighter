package sf;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
	public BufferedImage getSprite(int x, int y, int width, int height, int scalar) {
		BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
		int scaledWidth = width*scalar;
		int scaledHeight = height*scalar;
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, sprite.getType());
 
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(sprite, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
		return outputImage;
	}
	public static BufferedImage MirrorImage(BufferedImage image) {
		
		BufferedImage mirrored = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D graphics = (Graphics2D)mirrored.getGraphics();
	    AffineTransform transform = new AffineTransform();
	    transform.setToScale(-1, 1);
	    transform.translate(-image.getWidth(), 0);
	    graphics.setTransform(transform);
	    graphics.drawImage(image, 0, 0, null);

//	    try {
//			ImageIO.write(mirrored, "jpg", new File("test-flipped.jpg"));
//		} catch (IOException e) {
//	
//			e.printStackTrace();
//		}
	    
	    return  mirrored;
	    
	}
	
}
