package sf;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Stage {
	SpriteSheet Stage;
	BufferedImage[] S = new BufferedImage[3];
	double uwu = 4.2;
	public Stage() {
		Stage = new SpriteSheet("ree.png");
		S[0] = Stage.getSprite( 9, 8, 511, 204, uwu);
		S[1] = Stage.getSprite( 56, 200, 464, 216, uwu);
		S[2] = Stage.getSprite( 42, 651, 465, 150, uwu);
	}
	public void draw(Graphics g) {
		g.drawImage(S[0], 0, 0, null);
		g.drawImage(S[1], 0, 0, null);
		g.drawImage(S[2], 0, (int)(66*uwu), null);
	}
}
