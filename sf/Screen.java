package sf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Screen extends JPanel {
	int x = 0;
	int y = 0;
	public Screen() {
		super();
		this.setBackground(Color.red);
		this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
	}
	public void move() {
		x+=5;
		y+=5;
	}
	public void paintComponent(Graphics g) {
		g.fillRect(x, y, SpriteSheet.spriteSize, SpriteSheet.spriteSize);
	}
}
