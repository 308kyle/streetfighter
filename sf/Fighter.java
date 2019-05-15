package sf;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Fighter {
	private SpriteSheet s;
	AnimatedSprite ryuIdle;
	AnimatedSprite ryuWalk;
	AnimatedSprite ryuPunch;
	AnimatedSprite ryuPunch2;
	AnimatedSprite ryuJump;
	AnimatedSprite ryuHit;
	AnimatedSprite ryuHad;
	AnimatedSprite hadouken;
	AnimatedSprite ryuKO;
	AnimatedSprite ryuV1;
	AnimatedSprite ryuFHit;
	AnimatedSprite ryuKnock;
	AnimatedSprite ryuCrouch;
	AnimatedSprite ryuBlock;
	AnimatedSprite ryuCBlock;
	AnimatedSprite current;
	
	private MutableInt x;
	private MutableInt y;
	
	private int direction = 1;
	
	private int grav = 5;
	
	public Fighter(String filename) {
		
		s = new SpriteSheet(filename);
		
		Sprite i1 = new Sprite(6, 18, 6+43-1, 18+81-1, 0, 0, s);
		Sprite i2 = new Sprite(55, 19, 55+43-1, 19+80-1, 0, 0, s);
		Sprite i3 = new Sprite(105, 18, 105+43-1, 18+81-1, 0, 0, s);
		Sprite i4 = new Sprite(154, 17, 154+43-1, 17+82-1, 0, 0, s);
		ryuIdle = new AnimatedSprite(0, 60, true, true, new Sprite[] {i1, i2, i3, i4});
		
		Sprite w1 = new Sprite(205, 24, 205+43-1, 24+75-1, 5, 0, s);
		Sprite w2 = new Sprite(252, 19, 252+43-1, 19+80-1, 5, 0, s);
		Sprite w3 = new Sprite(301, 18, 301+43-1, 18+81-1, 5, 0, s);
		Sprite w4 = new Sprite(351, 19, 351+43-1, 19+80-1, 5, 0, s);
		Sprite w5 = new Sprite(401, 19, 401+43-1, 19+80-1, 5, 0, s);
		ryuWalk = new AnimatedSprite(0, 60, true, true, new Sprite[] {w1, w2, w3, w4, w5});
		
		Sprite p11 = new Sprite(3, 134, 3+43-1, 134+81-1, 0, 0, s);
		Sprite p12 = new Sprite(52, 134, 52+57-1, 134+81-1, 0, 0, s);
		p12.addBox(new Rectangle(30, 10, 31+1, 14+1));
		Sprite p13 = new Sprite(117, 134, 117+43-1, 134+81-1, 0, 0, s);
		ryuPunch = new AnimatedSprite(2, 15, false, false, new Sprite[] {p11, p12, p13});
			
		Sprite p21 = new Sprite(170, 134, 170+43-1, 134+81-1, 0, 0, s);
		Sprite p22 = new Sprite(218, 130, 218+51-1, 130+85-1, 0, 0, s);
		Sprite p23 = new Sprite(274, 130, 274+72-1, 130+85-1, 0, 0, s);
		p23.addBox(new Rectangle(41, 12, 35+1, 14+1));
		Sprite p24 = new Sprite(353, 130, 353+51-1, 130+85-1, 0, 0, s);
		Sprite p25 = new Sprite(411, 134, 411+43-1, 134+81-1, 0, 0, s);
		ryuPunch2 = new AnimatedSprite(5, 35, false, false, new Sprite[] {p21, p22, p23, p24, p25});
		
		Sprite j1 = new Sprite(452, 24, 452+43-1, 24+75-1, 0, -4, s);
		Sprite j2 = new Sprite(503, 9, 503+33-1, 9+90-1, 0, -4, s);
		Sprite j3 = new Sprite(545, 17, 545+29-1, 17+78-1, 0, -4, s);
		Sprite j4 = new Sprite(582, 19, 582+31-1, 19+67-1, 0, 0, s);
		Sprite j5 = new Sprite(619, 17, 619+29-1, 17+78-1, 0, 4, s);
		Sprite j6 = new Sprite(656, 9, 656+33-1, 9+90-1, 0, 4, s);
		Sprite j7 = new Sprite(696, 24, 696+43-1, 24+75-1, 0, 4, s);
		ryuJump = new AnimatedSprite(5, 49, false, false, new Sprite[] {j1, j2, j3, j4, j5, j6, j7});
		
		Sprite h1 = new Sprite(5, 760, 5+44-1, 760+77-1, 0, 0, s);
		Sprite h2 = new Sprite(53, 761, 53+48-1, 761+76-1, 0, 0, s);
		Sprite h3 = new Sprite(106, 771, 106+50-1, 771+66-1, 0, 0, s);
		Sprite h4 = new Sprite(163, 754, 163+44-1, 754+83-1, 0, 0, s);
		ryuHit = new AnimatedSprite(0, 28, false, false, new Sprite[] {h1, h2, h3, h4});
		
		Sprite had1 = new Sprite(4, 636, 5+4-1, 636+83-1, 0, 0, s);
		Sprite had2 = new Sprite(60, 642, 60+67-1, 642+77-1, 0, 0, s);
		Sprite had3 = new Sprite(131, 643, 131+67-1, 643+76-1, 0, 0, s);
		Sprite had4 = new Sprite(202, 648, 202+92-1, 648+71-1, 0, 0, s);
		Sprite had5 = new Sprite(299, 648, 299+71-1, 648+71-1, 0, 0, s);
		ryuHad = new AnimatedSprite(5, 35, false, false, new Sprite[] {had1, had2, had3, had4, had5});
		
		Sprite fh1 = new Sprite(217, 757, 217+49-1, 757+80-1, 0, 0, s);
		Sprite fh2 = new Sprite(271, 756, 271+53-1, 756+80-1, 0, 0, s);
		Sprite fh3 = new Sprite(328, 753, 328+59-1, 753+84-1, 0, 0, s);
		Sprite fh4 = new Sprite(391, 754, 391+44-1, 754+83-1, 0, 0, s);
		ryuFHit = new AnimatedSprite(0, 28, false, false, new Sprite[] {fh1, fh2, fh3, fh4});
		
		Sprite v1 = new Sprite(6, 887, 6+44-1, 887+76-1, 0, 0, s);
		Sprite v2 = new Sprite(57, 875, 57+44-1, 875+88-1, 0, 0, s);
		Sprite v3 = new Sprite(108, 853, 108+44-1, 853+110-1, 0, 0, s);
		ryuV1 = new AnimatedSprite(0, 60, false, false, new Sprite[] {v1, v2, v3});
		
		Sprite ko1 = new Sprite(1165, 781, 1165+46-1, 781+60-1, 0, 0, s);
		Sprite ko2 = new Sprite(1218, 789, 1218+73-1, 789+43-1, 0, 0, s);
		Sprite ko3 = new Sprite(1295, 806, 1295+75-1, 806+31-1, 0, 0, s);
		Sprite ko4 = new Sprite(1373, 789, 1373+73-1, 789+43-1, 0, 0, s);
		Sprite ko5 = new Sprite(1450, 806, 1450+75-1, 806+31-1, 0, 0, s);
		ryuKO = new AnimatedSprite(3, 35, false, false, new Sprite[] {ko1, ko2, ko3, ko4, ko5});
		
		Sprite k1 = new Sprite(511, 767, 511+48-1, 767+65-1, 0, 0, s);
		Sprite k2 = new Sprite(568, 760, 568+44-1, 760+72-1, 0, 0, s);
		Sprite k3 = new Sprite(618, 783, 618+73-1, 783+54-1, 0, 0, s);
		Sprite k4 = new Sprite(696, 806, 696+75-1, 806+31-1, 0, 0, s);
		Sprite k5 = new Sprite(774, 771, 774+53-1, 774+66-1, 0, 0, s);
		Sprite k6 = new Sprite(831, 734, 831+53-1, 734+103-1, 0, 0, s);
		Sprite k7 = new Sprite(889, 765, 889+56-1, 765+72-1, 0, 0, s);
		Sprite k8 = new Sprite(949, 742, 949+42-1, 742+95-1, 0, 0, s);
		ryuKnock = new AnimatedSprite(0, 56, false, false, new Sprite[] {k1, k2, k3, k4, k5, k6, k7, k8});
		
		Sprite b1 = new Sprite(1211, 16, 1211+44-1, 16+84-1, 0, 0, s);
		ryuBlock = new AnimatedSprite(0, 40, false, false, new Sprite[] {b1});
		
		Sprite b2 = new Sprite(1260, 38, 1260+44-1, 38+62-1, 0, 0, s);
		ryuCBlock = new AnimatedSprite(0, 40, false, false, new Sprite[] {b2});
		
		Sprite c1 = new Sprite(1160, 44, 1160+44-1, 44+56-1, 0, 0, s);
		ryuCrouch = new AnimatedSprite(0, 56, true, true, new Sprite[] {c1});
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
