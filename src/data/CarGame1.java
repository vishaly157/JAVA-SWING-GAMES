package data;

/**
 * Car Game
 * 
 * @author vyadav
 * 
 */

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JOptionPane;

public class CarGame1 extends Engine{
	private int x=10,		//x position for car
			y=290;			//y position for car
	private int y1=0,		//y position of wall 1
			y2=190,			//y position of wall 2
			y3=380,			//y position of wall 3
			inc=5,			//car speed
			o1,				//Gap width modifier between wall 1
			o2,				//Gap width modifier between wall 2
			o3,				//Gap width modifier between wall 3
			sc=0,			//Score
			n=7,			//FPS limiter for game
			yr1=0;			//Background Updater
	
	/**
	 * Game initializer
	 * 
	 * @throws IOException
	 */
	public CarGame1()throws IOException{
		super(535,545,500,500,"CarGame1");
	}
	
	@Override
	public Graphics updategr(Graphics g){
		//show score and actions
		g.drawString("Score "+sc, 10, 10);
		//
		//for obstacles
		//width of 10,	vertical gap of 190, horizontal gap of 150
		if(yr1==50)	yr1=0;
		for(int i=0;i<=500;i+=50){
			g.drawImage(road, 0, i+yr1, 500, 50, panel);
		}
		int xe1=rr.nextInt(201)+1;	//rand.nextInt((max - min) + 1) + min;
		if(frames_count%n==0){y1+=inc;	y2+=inc;	y3+=inc;	yr1+=inc;}
		if(frames_count%3000==0 && n!=1)	n-=1;
		if(y1==550){o1=xe1;	y1=10; sc+=inc;}
		if(y2==550){o2=xe1;	y2=10; sc+=inc*2;}
		if(y3==550){o3=xe1;	y3=10; sc+=inc;}
		g.drawImage(wall,10, y1, o1, 10,panel);		g.drawImage(wall,o1+150, y1, 350-o1, 10,panel);
		g.drawImage(cwall,10, y2, o2, 10,panel);		g.drawImage(cwall,o2+120, y2, 380-o2, 10,panel);
		g.drawImage(wall,10, y3, o3, 10,panel);		g.drawImage(wall,o3+150, y3, 350-o3, 10,panel);
		
		//for rectangle (player)
		if(Action_up && y>=60)	y-=8/4;
		if(Action_down && y<=390)	y+=8/4;
		if(Action_right && x<=390)	x+=8/2;
		if(Action_left && x>=10)	x-=8/2;
		//g.drawRect(x, y, 65, 90);
		if(Action_up){	
			g.drawImage(carn, x, y, 65, 120, panel);	
			}
		else if(Action_down){	
			g.drawImage(cars, x, y, 65, 100, panel);
			}
		else{		
			g.drawImage(car, x, y, 65, 100, panel);
			}
		g.drawImage(head, 0, 0, 500, 60, panel);
		collision();
		g.drawString("Score "+sc, 10, 15);
		//draw over
		return g;
	}
	
	/**
	 * Game Over reset helper
	 * 
	 */
	private void turns(){
			Game_Over=true;
			JOptionPane.showMessageDialog(null, "Game over\nYour Score: "+sc);
			sc=0;
			frames_count=0;
			n=7;
			Action_up=false;
			Action_down=false;
			Action_left=false;
			Action_right=false;
			Game_Over=false;
	}
	
	/**
	 * Collision check
	 * 
	 */
	private void collision(){
		int pu=y+5,pd=y+90,plf=x+5,prt=x+55;
		int y1u=y1,y1d=y1+10,y2u=y2,y2d=y2+10,y3u=y3,y3d=y3+10,ulf=0,urt=0;
		try{
		if(frames_count>500){
			if(y1d>=pu && y1u<=pd){
				ulf=o1;	urt=o1+150;
				if(plf<=ulf | prt>=urt){
					turns();
				}
			}
			else if(y2d>=pu && y2u<=pd){
				ulf=o2;	urt=o2+120;
				if(plf<=ulf | prt>=urt){
					turns();
				}
			}
			else if(y3d>=pu && y3u<=pd){
				ulf=o3;	urt=o3+150;
				if(plf<=ulf | prt>=urt){
					turns();
				}
			}
		}
		else{
			sc=0;
		}
		}catch(Exception ex){
			System.out.println(""+ex);
		}
	}
	
	/**
	 * Main Method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new CarGame1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
