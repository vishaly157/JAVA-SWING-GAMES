package data;

/**
 * Brick Game
 * 
 * @author vyadav
 * 
 */

import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;


public class BrickGame1 extends Engine{
	Random rr=new Random();			//Random number generator
	private int shx=400,			//player ship x position
			shy=535,				//player ship y position
			bcx=400,				//ball x position
			bcy=400,				//ball y position
			incx=1,					//variation in x position for x
			incy=1,					//variation in x position for y
			p=0,					//Power-Up helper
			px=450,					//Power-Up x position
			py=0,					//Power-Up y position
			shl=150,				//player ship length
			sc=0;					//Score
	
	private ArrayList<Point> blks = new ArrayList<Point>();				//List to store blocks info
	private ArrayList<Integer> hits = new ArrayList<Integer>();			//List to store hits on blocks info
	
	/**
	 * Initializer for Game
	 * 
	 * @throws IOException
	 */
	public BrickGame1()throws IOException{
		super(930,645,900,600,"BrickGame1");
		//initialize blocks
		int x,y=0;
		for(int i=1;i<=9;i++){
			x=0;
			for(int j=1;j<=9;j++){
				blks.add(new Point(x+10,y+10));
				x+=100;
				hits.add(rr.nextInt(3)+3);
			}
			y+=50;
		}
		Game_Over=false;
	}
	
	@Override
	public Graphics updategr(Graphics g){
		g.drawImage(back, 0, 0, 900, 600, panel);
		for(Point co : blks){
//			g.drawRect(co.x,co.y, 80, 20);
//			g.drawString("hit "+hits.get(blks.indexOf(co))+" times", co.x+10, co.y+15);
			switch(hits.get(blks.indexOf(co))){
				case 0: g.drawImage(l0, co.x, co.y, 80, 20, panel);
				break;
				case 1: g.drawImage(l1, co.x, co.y, 80, 20, panel);
				break;
				case 2: g.drawImage(l2, co.x, co.y, 80, 20, panel);
				break;
				case 3: g.drawImage(l3, co.x, co.y, 80, 20, panel);
				break;
				case 4: g.drawImage(l4, co.x, co.y, 80, 20, panel);
				break;
				case 5: g.drawImage(l5, co.x, co.y, 80, 20, panel);
				break;
				default: g.drawRect(co.x,co.y, 80, 20);
				g.drawString("hit "+hits.get(blks.indexOf(co))+" times", co.x+10, co.y+15);
				break;
			}
		}
		//player ship
		if(Action_right && shx<890-shl)	shx+=10;
		if(Action_left && shx>10)		shx-=10;
		g.drawImage(beam, shx, shy, shl, 25, panel);
		//player ball
		g.drawOval(bcx-15, bcy-15, 30, 30);
		g.drawOval(bcx, bcy, 2, 2);
		powerup(g);
		bcx+=incx;
		bcy+=incy;
		return g;
	}
	
	/**
	 * Power-Up modifier
	 * 
	 * @param g Graphics
	 */
	private void powerup(Graphics g){
		int pw=bounce();
		
			//speedup power
		if(p==0)	p=pw;
		if(pw!=0)System.out.println(p+"	"+pw);
		if(p==4){
			g.drawOval(px, py, 20, 20);
			g.drawString("V", px+7, py+15);
			py+=1;
		}
		if(p==5){
			g.drawOval(px, py, 20, 20);
			g.drawString("A", px+7, py+15);
			py+=1;
		}
		if(p==6){
			g.drawOval(px, py, 20, 20);
			g.drawString("<>", px+7, py+15);
			py+=1;
		}
			//slowdown power
		else if(p==7){
			g.drawOval(px, py, 20, 20);
			g.drawString("><", px+7, py+15);
			py+=1;
		}
		else if(p==8){
			g.drawOval(px, py, 20, 20);
			g.drawString("!X!", px+7, py+15);
			py+=1;
		}
		else	py+=1;
		if(py>=585){
			p=0;
			py=0;
		}
		if(catchpow()){
//			System.out.println("got power");
			switch(p){
			case 4: System.out.println("slowdown !!!disabled");
//					if(incx>0)	incx=1;
//					else	incx=-1;
					break;
			case 5: System.out.println("speedup !!!disabled");
//					if(incx>0)	incx=2;
//					else	incx=-2;
					break;
			case 6: System.out.println("extend");
					if(shl!=250)
					shl+=50;
				break;
			case 7: System.out.println("shrink");
					if(shl!=50)
					shl-= 50;
				break;
			case 8 : System.out.println("ship destroyed");
					shy=800;
				break;
			default : System.out.println("un def power");
					break;
			}
			p=0;
			py=0;
		}
	}
	
	/**
	 * catch power validate
	 * 
	 * @return boolean
	 */
	private boolean catchpow(){
		return py>=490 && px>=shx && px<=shx+shl;
	}
	
	/**
	 * Game Over reset helper
	 * 
	 */
	private void turns(){
		Game_Over=true;
		JOptionPane.showMessageDialog(null, "Game over\nYour Score: "+sc);
		int x,y=0;
		blks.clear();
		hits.clear();
		for(int i=1;i<=9;i++){
			x=0;
			for(int j=1;j<=9;j++){
				blks.add(new Point(x+10,y+10));
				x+=100;
				hits.add(rr.nextInt(3)+3);
			}
			y+=50;
		}
		shy=535;
		shx=400;
		bcx=400;
		bcy=400;
		incx=1;
		incy=1;
		p=0;
		py=0;
		shl=150;
		sc=0;
		bcy=500;
		frames_count=0;
		Action_up=false;
		Action_down=false;
		Action_left=false;
		Action_right=false;
		Game_Over=false;
}
	
	/**
	 * Bounce Modifier
	 * 
	 * @return integer for power Up modifier
	 */
	private int bounce(){
		int ret=0;
		//from screen borders
		//x from 0 to 900
		//y from 0 to 600
		//radius -> 15
		//bat start -> shx end -> shx+150
		try{
		if(incy>0){
			//going down
			//screen edge reaction start;
			if(bcy>=585)	//bottom edge reached
				turns();
			else if(bcx>=885)	//right edge
				incx*=-1;
			else if(bcx<=15)		//left edge
				incx*=-1;
			//screen edge reaction end;
			//bat top start reaction start;
			else if(bcy>=shy-15 && bcx>=shx && bcx<=shx+shl)
				incy*=-1;
			//bat top start reaction end;
			//block reaction begin
			else
			for(Point co : blks){
				if(bcx+15==co.x && incx>0 && bcy-15<co.y+50 && bcy+15>co.y){
					//left edge collision
					ret=rr.nextInt(9)+1;
					sc+=5;
					incx*=-1;
					if		(hits.get(blks.indexOf(co))==0)	blks.set(blks.indexOf(co), new Point(1000, 1000));
					else	hits.set(blks.indexOf(co), hits.get(blks.indexOf(co))-1);
				}
				else if(bcx-15==co.x+80 && incx<0 && bcy-15<co.y+50 && bcy+15>co.y){
					//right edge collision
					sc+=5;
					ret=rr.nextInt(9)+1;
					incx*=-1;
					if		(hits.get(blks.indexOf(co))==0)	blks.set(blks.indexOf(co), new Point(1000, 1000));
					else	hits.set(blks.indexOf(co), hits.get(blks.indexOf(co))-1);
				}
				else if(bcy+15==co.y && bcx+15>co.x && bcx-15<co.x+80){
					//top edge collision
					sc+=5;
					ret=rr.nextInt(9)+1;
					incy*=-1;
					if		(hits.get(blks.indexOf(co))==0)	blks.set(blks.indexOf(co), new Point(1000, 1000));
					else	hits.set(blks.indexOf(co), hits.get(blks.indexOf(co))-1);
				}
			}
			//block reaction end
		}
		else if(incy<0){
			//going up
			//screen edge reaction start;
			if(bcy<=15)		//top edge reached
				incy*=-1;
			else if(bcx>=885)	//right edge
				incx*=-1;
			else if(bcx<=15)		//left edge
				incx*=-1;
			//screen edge reaction end;
			//block reaction start;
			else
			for(Point co : blks){
				if(bcx+15==co.x && bcy-15<co.y+50 && bcy+15>co.y){
					//left edge collision
					sc+=5;
					ret=rr.nextInt(9)+1;
					incx*=-1;
					if		(hits.get(blks.indexOf(co))==0)	blks.set(blks.indexOf(co), new Point(1000, 1000));
					else	hits.set(blks.indexOf(co), hits.get(blks.indexOf(co))-1);
				}
				else if(bcx-15==co.x+80 && bcy-15<co.y+50 && bcy+15>co.y){
					//right edge collision
					sc+=5;
					ret=rr.nextInt(9)+1;
					incx*=-1;
					if		(hits.get(blks.indexOf(co))==0)	blks.set(blks.indexOf(co), new Point(1000, 1000));
					else	hits.set(blks.indexOf(co), hits.get(blks.indexOf(co))-1);
				}
				else if(bcy-15==co.y+30 && bcx+15>co.x && bcx-15<co.x+80){
					//bottom edge collision
					sc+=5;
					ret=rr.nextInt(9)+1;
					incy*=-1;
					if		(hits.get(blks.indexOf(co))==0)	blks.set(blks.indexOf(co), new Point(1000, 1000));
					else	hits.set(blks.indexOf(co), hits.get(blks.indexOf(co))-1);
				}
			}
			//block reaction end;
		}
		}catch(Exception e){
			//ignored
		}
		return ret;
	}
	
	/**
	 * Main Method 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
			try {
				new BrickGame1();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
