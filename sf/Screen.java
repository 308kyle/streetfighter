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
	MutableInt frames;
	public Screen(Fighter one, MutableInt frames) {
		super();
		this.frames = frames;
		this.one = one;

		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		this.one = one;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		one.draw(g, frames.getInt());
	}
}
