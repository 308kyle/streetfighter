package sf;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Fighter {

	private SpriteSheet s = new SpriteSheet("ryu transparent.png");

	AnimatedSprite ryuIdle;
	AnimatedSprite ryuWalkL;
	AnimatedSprite ryuWalk;
	AnimatedSprite ryuPunch;
	AnimatedSprite ryuPunch2;
	AnimatedSprite ryuJump;
	AnimatedSprite ryuHit;
	AnimatedSprite ryuKO;
	AnimatedSprite ryuV1;
	AnimatedSprite ryuFHit;
	AnimatedSprite ryuKnock;
	AnimatedSprite current;
	AnimatedSprite ryuBlock;
	AnimatedSprite ryuCBlock;
	AnimatedSprite ryuCrouch;

	AnimatedSprite ryuCKick;

	AnimatedSprite ryuJKick;
	AnimatedSprite ryuFJump;


	AnimatedSprite ryuIdleR;
	AnimatedSprite ryuWalkLR;
	AnimatedSprite ryuWalkR;
	AnimatedSprite ryuPunchR;
	AnimatedSprite ryuPunch2R;
	AnimatedSprite ryuJumpR;
	AnimatedSprite ryuHitR;
	AnimatedSprite ryuKOR;
	AnimatedSprite ryuV1R;
	AnimatedSprite ryuFHitR;
	AnimatedSprite ryuKnockR;
	AnimatedSprite ryuBlockR;
	AnimatedSprite ryuCBlockR;
	AnimatedSprite ryuCrouchR;

	AnimatedSprite ryuCKickR;

	AnimatedSprite ryuJKickR;
	AnimatedSprite ryuFJumpR;


	private MutableInt x;
	private MutableInt y;

	private int direction = 1;

	private boolean human;

	private int hp = 100;
	Sprite v3;
	public Fighter(boolean human, int x2, int y2) {
		

		this.human = human;
		
		Sprite i1 = new Sprite(6, 18, 6+43-1, 18+81-1, 0, 0, s);
		Sprite i2 = new Sprite(55, 19, 55+43-1, 19+80-1, 0, 0, s);
		Sprite i3 = new Sprite(105, 18, 105+43-1, 18+81-1, 0, 0, s);
		Sprite i4 = new Sprite(154, 17, 154+43-1, 17+82-1, 0, 0, s);
		i1.createBox();
		i2.createBox();
		i3.createBox();
		i4.createBox();
		i1.createBox();
		i2.createBox();
		i3.createBox();
		i4.createBox();
		ryuIdle = new AnimatedSprite(0, 60, true, true, new Sprite[] {i1,i2,i3,i4});
		Sprite[] clone = Sprite.clones(new Sprite[] {i1,i2,i3,i4});
		ryuIdleR = new AnimatedSprite(0, 60, true, true, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}
		
	

		Sprite w1 = new Sprite(205, 24, 205+43-1, 24+75-1, 5, 0, s);
		Sprite w2 = new Sprite(252, 19, 252+43-1, 19+80-1, 5, 0, s);
		Sprite w3 = new Sprite(301, 18, 301+43-1, 18+81-1, 5, 0, s);
		Sprite w4 = new Sprite(351, 19, 351+43-1, 19+80-1, 5, 0, s);
		Sprite w5 = new Sprite(401, 19, 401+43-1, 19+80-1, 5, 0, s);
		w1.createBox();
		w2.createBox();
		w3.createBox();
		w4.createBox();
		w5.createBox();
		w1.createBox();
		w2.createBox();
		w3.createBox();
		w4.createBox();
		w5.createBox();
		ryuWalk = new AnimatedSprite(0, 30, true, true, new Sprite[] {w1, w2, w3, w4, w5});
		clone = Sprite.clones(new Sprite[] {w1, w2, w3, w4, w5});
		ryuWalkR = new AnimatedSprite(0, 30, true, true, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}
		
		
		ryuWalkL = new AnimatedSprite(0, 30, true, true, new Sprite[] {w1, w2, w3, w4, w5});
		clone = Sprite.clones(new Sprite[] {w1, w2, w3, w4, w5});
		ryuWalkLR = new AnimatedSprite(0, 30, true, true, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}
		ryuWalkL.reverse();
		ryuWalkLR.reverse();
		
    
		Sprite p11 = new Sprite(3, 134, 3+43-1, 134+81-1, 0, 0, s);
		Sprite p12 = new Sprite(52, 134, 52+57-1, 134+81-1, 0, 0, s);
		//p12.addBox(new Rectangle(30, 10, 31+1, 14+1));
		Sprite p13 = new Sprite(117, 134, 117+43-1, 134+81-1, 0, 0, s);
		p11.createBox();
		p12.createBox();
		p13.createBox();
		p11.createBox();
		p12.createBox();
		p13.createBox();
		ryuPunch = new AnimatedSprite(2, 15, false, false, new Sprite[] {p11, p12, p13});
		clone = Sprite.clones(new Sprite[] {p11, p12, p13});
		ryuPunchR = new AnimatedSprite(2, 15, false, false, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}

		Sprite p21 = new Sprite(170, 134, 170+43-1, 134+81-1, 0, 0, s);
		Sprite p22 = new Sprite(218, 130, 218+51-1, 130+85-1, 0, 0, s);
		Sprite p23 = new Sprite(274, 130, 274+72-1, 130+85-1, 0, 0, s);
		//p23.addBox(new Rectangle(41, 12, 35+1, 14+1));
		Sprite p24 = new Sprite(353, 130, 353+51-1, 130+85-1, 0, 0, s);
		Sprite p25 = new Sprite(411, 134, 411+43-1, 134+81-1, 0, 0, s);
		p25.createBox();
		p21.createBox();
		p22.createBox();
		p23.createBox();
		p24.createBox();
		p25.createBox();
		p21.createBox();
		p22.createBox();
		p23.createBox();
		p24.createBox();
		ryuPunch2 = new AnimatedSprite(5, 35, false, false, new Sprite[] {p21, p22, p23, p24, p25});
		clone = Sprite.clones(new Sprite[] {p21, p22, p23, p24, p25});
		ryuPunch2R = new AnimatedSprite(5, 35, false, false, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}
		
		Sprite j1 = new Sprite(452, 24, 452+43-1, 24+75-1, 0, -20, s);
		Sprite j2 = new Sprite(503, 9, 503+33-1, 9+90-1, 0, -16, s);
		Sprite j3 = new Sprite(545, 17, 545+29-1, 17+78-1, 0, -4, s);
		Sprite j4 = new Sprite(582, 19, 582+31-1, 19+67-1, 0, 4, s);
		Sprite j5 = new Sprite(619, 17, 619+29-1, 17+78-1, 0, 16, s);
		Sprite j6 = new Sprite(656, 9, 656+33-1, 9+90-1, 0, 20, s);
		Sprite j7 = new Sprite(696, 24, 696+43-1, 24+75-1, 0, 0, s);
		j1.createBox();
		j2.createBox();
		j3.createBox();
		j4.createBox();
		j5.createBox();
		j6.createBox();
		j7.createBox();
		j1.createBox();
		j2.createBox();
		j3.createBox();
		j4.createBox();
		j5.createBox();
		j6.createBox();
		j7.createBox();
		ryuJump = new AnimatedSprite(5, 60, false, false, new Sprite[] {j1, j2, j3, j4, j5, j6, j7});
		clone = Sprite.clones(new Sprite[] {j1, j2, j3, j4, j5, j6, j7});
		ryuJumpR = new AnimatedSprite(5, 60, false, false, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}

		Sprite h1 = new Sprite(5, 760, 5+44-1, 760+77-1, 0, 0, s);
		Sprite h2 = new Sprite(53, 761, 53+48-1, 761+76-1, 0, 0, s);
		Sprite h3 = new Sprite(106, 771, 106+50-1, 771+66-1, 0, 0, s);
		Sprite h4 = new Sprite(163, 754, 163+44-1, 754+83-1, 0, 0, s);
		h1.createBox();
		h2.createBox();
		h3.createBox();
		h4.createBox();
		h1.createBox();
		h2.createBox();
		h3.createBox();
		h4.createBox();
		ryuHit = new AnimatedSprite(0, 28, false, true, new Sprite[] {h1, h2, h3, h4});
		clone = Sprite.clones(new Sprite[] {h1, h2, h3, h4});
		ryuHitR = new AnimatedSprite(0, 28, false, true, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}

		Sprite fh1 = new Sprite(217, 757, 217+49-1, 757+80-1, 0, 0, s);
		Sprite fh2 = new Sprite(271, 756, 271+53-1, 756+80-1, 0, 0, s);
		Sprite fh3 = new Sprite(328, 753, 328+59-1, 753+84-1, 0, 0, s);
		Sprite fh4 = new Sprite(391, 754, 391+44-1, 754+83-1, 0, 0, s);
		fh1.createBox();
		fh2.createBox();
		fh3.createBox();
		fh4.createBox();
		fh1.createBox();
		fh2.createBox();
		fh3.createBox();
		fh4.createBox();
		ryuFHit = new AnimatedSprite(0, 28, false, false, new Sprite[] {fh1, fh2, fh3, fh4});
		clone = Sprite.clones(new Sprite[] {fh1, fh2, fh3, fh4});
		ryuFHitR = new AnimatedSprite(0, 28, false, false, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}

		Sprite v1 = new Sprite(6, 887, 6+44-1, 887+76-1, 0, 0, s);
		Sprite v2 = new Sprite(57, 875, 57+44-1, 875+88-1, 0, 0, s);
		v3 = new Sprite(108, 853, 108+44-1, 853+110-1, 0, 0, s);
		ryuV1 = new AnimatedSprite(0, 27, false, false, new Sprite[] {v1, v2, v3});
		clone = Sprite.clones(new Sprite[] {v1, v2, v3});
		ryuV1R = new AnimatedSprite(0, 27, false, false, clone);

		Sprite ko1 = new Sprite(1165, 781, 1165+46-1, 781+60-1, 0, 0, s);
		Sprite ko2 = new Sprite(1218, 789, 1218+73-1, 789+43-1, 0, 0, s);
		Sprite ko3 = new Sprite(1295, 806, 1295+75-1, 806+31-1, 0, 0, s);
		Sprite ko4 = new Sprite(1373, 789, 1373+73-1, 789+43-1, 0, 0, s);
		Sprite ko5 = new Sprite(1450, 806, 1450+75-1, 806+31-1, 0, 0, s);
		ko5.createBox();
		ko1.createBox();
		ko2.createBox();
		ko3.createBox();
		ko4.createBox();
		ko5.createBox();
		ko1.createBox();
		ko2.createBox();
		ko3.createBox();
		ko4.createBox();
		ryuKO = new AnimatedSprite(3, 35, false, false, new Sprite[] {ko1, ko2, ko3, ko4, ko5});
		ryuKOR = new AnimatedSprite(3, 35, false, false, Sprite.clones(new Sprite[] {ko1, ko2, ko3, ko4, ko5}));

		Sprite k1 = new Sprite(511, 767, 511+48-1, 767+65-1, 0, 0, s);
		Sprite k2 = new Sprite(568, 760, 568+44-1, 760+72-1, 0, 0, s);
		Sprite k3 = new Sprite(618, 783, 618+73-1, 783+54-1, 0, 0, s);
		Sprite k4 = new Sprite(696, 806, 696+75-1, 806+31-1, 0, 0, s);
		Sprite k5 = new Sprite(774, 771, 774+53-1, 774+66-1, 0, 0, s);
		Sprite k6 = new Sprite(831, 734, 831+53-1, 734+103-1, 0, 0, s);
		Sprite k7 = new Sprite(889, 765, 889+56-1, 765+72-1, 0, 0, s);
		Sprite k8 = new Sprite(949, 742, 949+42-1, 742+95-1, 0, 0, s);
		k1.createBox();
		k2.createBox();
		k3.createBox();
		k4.createBox();
		k5.createBox();
		k6.createBox();
		k7.createBox();
		k8.createBox();
		k1.createBox();
		k2.createBox();
		k3.createBox();
		k4.createBox();
		k5.createBox();
		k6.createBox();
		k7.createBox();
		k8.createBox();
		ryuKnock = new AnimatedSprite(0, 56, false, false, new Sprite[] {k1, k2, k3, k4, k5, k6, k7, k8});
		clone = Sprite.clones(new Sprite[] {k1, k2, k3, k4, k5, k6, k7, k8});
		ryuKnockR = new AnimatedSprite(0, 56, false, false, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}
		
		Sprite b1 = new Sprite(1211, 16, 1211+44-1, 16+84-1, 0, 0, s);
		b1.createBox();
		b1.createBox();
		ryuBlock = new AnimatedSprite(0, 40, false, false, new Sprite[] {b1});
		clone = Sprite.clones(new Sprite[] {b1});
		ryuBlockR = new AnimatedSprite(0, 40, false, false, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}

		Sprite b2 = new Sprite(1260, 38, 1260+44-1, 38+62-1, 0, 0, s);
		b2.createBox();
		b2.createBox();
		ryuCBlock = new AnimatedSprite(0, 40, false, false, new Sprite[] {b2});
		ryuCBlockR = new AnimatedSprite(0, 40, false, false, Sprite.clones(new Sprite[] {b2}));

		Sprite c1 = new Sprite(1160, 44, 1160+44-1, 44+56-1, 0, 0, s);
		c1.createBox();
		c1.createBox();
		ryuCrouch = new AnimatedSprite(0, 56, true, true, new Sprite[] {c1});
		clone = Sprite.clones(new Sprite[] {c1});
		ryuCrouchR = new AnimatedSprite(0, 56, true, true, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}

		Sprite ck1 = new Sprite(660, 418, 660+50-1, 418+57-1, 0, 0, s);
		Sprite ck2 = new Sprite(714, 417, 714+72-1, 417+58-1, 0, 0, s);
		ck1.createBox();
		ck2.createBox();
		ck1.createBox();
		ck2.createBox();
		ryuCKick = new AnimatedSprite(0, 15, false, false, new Sprite[] {ck1, ck2, ck1});
		clone = Sprite.clones(new Sprite[] {ck1, ck2, ck1});
		ryuCKickR = new AnimatedSprite(0, 15, false, false, clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}
		
		
		Sprite f1 = new Sprite(747,24,789,98,5,-20,s);
		Sprite f2 = new Sprite(795,9,827,98,5,-16,s);
		Sprite f3 = new Sprite(835,40,895,76,5,-4,s);
		Sprite f4 = new Sprite(902,24,932,90,5,0,s);
		Sprite f5 = new Sprite(942,36,1013,74,5,4,s);
		Sprite f6 = new Sprite(1021,25,1063,98,5,16,s);
		Sprite f7 = new Sprite(1071,9,1103,98,5,20,s);
		f1.createBox();
		f2.createBox();
		f3.createBox();
		f4.createBox();
		f5.createBox();
		f6.createBox();
		f7.createBox();
		f1.createBox();
		f2.createBox();
		f3.createBox();
		f4.createBox();
		f5.createBox();
		f6.createBox();
		f7.createBox();
		ryuFJump = new AnimatedSprite(5,60,false,false,new Sprite[] {f1,f2,f3,f4,f5,f6,f7});
		clone = Sprite.clones(new Sprite[] {f1,f2,f3,f4,f5,f6,f7});
		ryuFJumpR = new AnimatedSprite(5,60,false,false,clone);
		for(int i=0;i<clone.length;i++) {
			clone[i].createBox();
			clone[i].createBox();
		}
		
		
		x = new MutableInt(x2);
		y = new MutableInt(y2);

		current = ryuIdle;

	}
	
	public SpriteSheet getSheet() {
		return s;
	}
	public int gethp() {
		return hp;
	}
	public void sethp(int newhp) {
		if(newhp>=0) {
			hp = newhp;
		}
	}

	public void draw(Graphics g) {
		if(direction==1) {
			
			g.drawImage(current.getSprite(), x.getInt()-150, y.getInt()-current.getSprite().getHeight(), null);
			
			g.drawRect(current.getSSprite().box.get(0).x, current.getSSprite().box.get(0).y, current.getSSprite().box.get(0).width, current.getSSprite().box.get(0).height);

		} else {
			g.drawImage(current.getSprite(), x.getInt()-current.getSprite().getWidth(), y.getInt()-current.getSprite().getHeight(), null);
			g.drawRect(current.getSSprite().box.get(1).x, current.getSSprite().box.get(1).y, current.getSSprite().box.get(1).width, current.getSSprite().box.get(1).height);
			
		}
		
		
		g.setFont(new Font( "", Font.BOLD, 36));
		g.setColor(Color.RED);
		if(human) {
			g.drawString("Player", x.getInt()+25-150, y.getInt()-current.getSprite().getHeight()-10);
		}else {
			g.drawString("CPU", x.getInt()+25-150, y.getInt()-current.getSprite().getHeight()-10);
		}
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
	public boolean human() {
		return human;
	}
	public void setDirection(int x) {
		direction = (int) Math.signum(x-this.x.getInt());
	}
	public int getDirection() {
		return direction;
	}

}
