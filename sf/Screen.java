package sf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class Screen extends JPanel {
	Fighter one;
	Fighter two;
	int x = 0;
	int y = 0;
	public Screen(Fighter one) {
		super();
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(one.getSheet().getSprite(0, 0, 50, 100), x, y, null);
	}
}
