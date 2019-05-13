package sf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Screen extends JPanel {
	Fighter one;
	Fighter two;
	Stage st;
	int x = 0;
	int y = 0;
	MutableInt frames;
	public Screen(Fighter one, MutableInt frames) {
		super();
		this.frames = frames;
		this.one = one;
		st = new Stage();
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		this.one = one;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		st.draw(g);
		one.draw(g, frames.getInt());
	}
}
