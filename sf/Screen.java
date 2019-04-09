package sf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class Screen extends JPanel {
	SpriteSheet sheet = new SpriteSheet("SNES - Super Street Fighter 2 - Ryu.gif");
	int x = 0;
	int y = 0;
	public Screen() {
		super();
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
	}
	public void move() {
		x+=1;
		y+=1;
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(sheet.getSprite(0, 0, 50, 100), x, y, null);
		
	}
}
