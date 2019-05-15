package sf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	
	Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
	
	MutableInt timer;
	
	MutableInt frames;
	public Screen(Fighter one, Fighter two, MutableInt frames, MutableInt timer) {
		super();
		this.frames = frames;
		this.timer = timer;
		this.one = one;
		this.two = two;
		st = new Stage();
		this.setBackground(Color.white);
		this.setPreferredSize(d);
		this.one = one;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		st.draw(g);
		
		one.draw(g);
		two.draw(g);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 36));
		g.setColor(Color.yellow);
		g.drawString(""+timer.getInt(), d.width/2, 100);
		
	}
}
