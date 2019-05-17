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

		
		two.draw(g);
		one.draw(g);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 36));
		g.setColor(Color.yellow);
		g.drawString(""+timer.getInt(), (d.width/2)-25, 90);
		

		g.setColor(Color.MAGENTA);
		g.drawString("Player", 53, 30);
		g.drawString("CPU", 1550-73, 30);
		
		g.setColor(Color.GREEN);
		g.drawRect(50, 50, 600, 50);
		g.drawRect(950, 50, 600, 50);
		g.fillRect(50, 50, (int)(600*one.gethp()/100), 50);
		g.fillRect(1550-(int)(600*one.gethp()/100), 50, (int)(600*one.gethp()/100), 50);
		

	}
}
