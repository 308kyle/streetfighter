package sf;

import java.awt.Graphics;

public class Fighter {
	private SpriteSheet s;
	AnimatedSprite ryuIdle;
	AnimatedSprite ryuWalk;
	AnimatedSprite ryuPunch;
	AnimatedSprite ryuPunch2;
	AnimatedSprite ryuJump;
	
	AnimatedSprite current;
	
	private MutableInt x;
	private MutableInt y;
	
	private int direction = 1;
	
	private int grav = 5;
	
	public Fighter(String filename) {
		
		s = new SpriteSheet(filename);
		
		ryuIdle = new AnimatedSprite(s, 0, 60, true, true, new int[4], new int[4], new int[][] {{6, 18, 43, 81},
																			{55, 19, 43, 80},
																			{105, 18, 43, 81},
																			{154, 17, 43, 82}});
		
		ryuWalk = new AnimatedSprite(s, 0, 60, true, true, new int[] {5,5,5,5,5}, new int[5], new int[][] {{205, 24, 43, 75},
																			{252, 19, 43, 80},
																			{301, 18, 43, 81},
																			{351, 19, 43, 80},
																			{401, 19, 43, 80}});
		
		ryuPunch = new AnimatedSprite(s, 2, 15, false, false, new int[3], new int[3], new int[][] {{3, 134, 43, 81},
																			{52, 134, 57, 81},
																			{117, 134, 43, 81}});
			
		ryuPunch2 = new AnimatedSprite(s, 5, 35, false, false, new int [5], new int[5], new int[][] {{170, 134, 43, 81},
																			{218, 130, 51, 85},
																			{274, 130, 72, 85},
																			{353, 130, 51, 85},
																			{411, 134, 43, 81}});
		ryuJump = new AnimatedSprite(s, 0, 45, false, false, new int [7], new int[] {-10,-10,-10,0,10,10,10}, new int[][] {{452, 24, 43, 75},
																			{503, 9, 33, 90},
																			{545, 17, 29, 78},
																			{582, 19, 31, 67},
																			{619, 17, 29, 78},
																			{656, 9, 33, 90},
																			{696, 24, 43, 75}});
		if(filename.equals("ryu good transparent.png")) {
			
		}

		x = new MutableInt(100);
		y = new MutableInt(600);
		

		current = ryuIdle;
		
	}
	public SpriteSheet getSheet() {
		return s;
	}
	public void draw(Graphics g, int frames) {

		g.drawImage(current.getSprite(), x.getInt(), y.getInt()-current.getSprite().getHeight(), null);
		

	}
	
	public MutableInt getX() {
		return x;
	}
	public MutableInt getY() {
		return y;
	}
	
	public void setX(int a) {
		x.setInt(a);
	}
	public void setY(int a) {
		y.setInt(a);
	}

}
