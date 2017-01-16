package data;

/**
 * Shooter Game
 * Launch Using GameLauncher.java
 * 
 * @author vyadav
 *
 */

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

import javax.swing.JOptionPane;


class Shot {
    int x;
    int y;
    int p;
    
    /**
     * Constructor for Shot.
     * Used as data type
     * 
     * @param x
     * @param y
     * @param p
     */
    public Shot(int x, int y, int p) {
        this.x = x;
        this.y = y;
        this.p = p;
    }
}

public class ShooterGame1 extends Engine{
	private int 
			fire_delay,					//delay between player shots
			px=20,						//initial position of ship x coordinate
			py=50,						//initial position of ship x coordinate
			shx=px+150,					//shots position relative to ship x coordinate 
			shy=py+30,					//shots position relative to ship y coordinate
			ind3=-1,					//helper index
			en_cnt=0,					//enemy killed count
			health,						//player health
			level=0,					//game level(1-16)
			enmaxhl=1,					//maximum health for enemy
			enminhl=1,					//minimum health for enemy
			sdam,						//damage dealt by player shots
			enemy_freq=100,				//frequency of enemy appearing
			enfiredelay=50,				//delay between enemy shots
			killreq=-1,					//no. of kills required to level up
			bx=1110,					//x coordinate for boss position
			by=10,						//y coordinate for boss position
			bhel=10,					//health of stage boss
			bm=1,						//boss movement multiplier
			r1=0,						//helper for background shift
			ns,							//no. of player cannons
			tbh=-1,						//initially maximum boss health
			ht=10;						//initially maximum player health
	
	private LinkedList<Shot> shot = new LinkedList<Shot>();				//List to store player shots info
	private LinkedList<Point> enem = new LinkedList<Point>();			//List to store enemy position info
	private LinkedList<Integer> hlth = new LinkedList<Integer>();		//List to store enemy health info
	private LinkedList<Point> enshot = new LinkedList<Point>();			//List to store enemy shots info
	
	/**
	 * Initializer for Game and key Bindings
	 * 
	 * @param armour		Integer between 300 to 50000 inclusive
	 * @param canons		Integer between 1 to 5 inclusive
	 * @param damage		Integer between 1 to 5000
	 * @param rateoffire	Integer between 4 to 200 
	 * @param lazer			NOT IMPLEMENTED
	 * @param shld			NOT IMPLEMENTED
	 * @param Level			NOT IMPLEMENTED
	 */
    protected ShooterGame1(int armour,int canons,int damage,int rateoffire,int lazer,int shld,int Level) {
        super(1130, 745, 1100, 700, "ShooterGame1");
        health=armour;
        ht=armour; 
        ns=canons;
        sdam=damage;
        fire_delay= rateoffire;
        level=Level;
        orient();
        frames_count=0;
        mains.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent e) {
				GameLauncher.setRkills(en_cnt);
				GameLauncher.setRlevel(level);
				GameLauncher.inotify();
				process.stop();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
    }

    @Override
    protected Graphics updategr(Graphics g) {
        if (frames_count % 2 == 0) {
            ++r1;
        }
        int i = 0;
        while (i <= 1100) {
            if (r1 > 110) {
                r1 = 0;
            }
            if (level >= 1 && level < 5) {
                g.drawImage(spl1, i - r1, 0, 110, 600, panel);
            }
            if (level >= 5 && level < 10) {
                g.drawImage(spl5, i - r1, 0, 110, 600, panel);
            }
            if (level >= 10 && level < 13) {
                g.drawImage(spl10, i - r1, 0, 110, 600, panel);
            }
            if (level >= 13 && level <= 15) {
                g.drawImage(spl13, i - r1, 0, 110, 600, panel);
            }
            if (level >= 16) {
                g.drawImage(sh16, i - r1, 0, 110, 600, panel);
            }
            i += 110;
        }
        shx = px + 120;
        shy = py + 20;
        if (health <= 0) {
            turns();
        }
        g.drawLine(10, 600, 1090, 600);
        g.drawString("Level: " + level, 20, 625);
        g.drawString("Score: " + en_cnt, 20, 675);
        g.drawString("Ship Health: " + health, 120, 625);
        g.drawString("Kills to boss: " + killreq, 120, 675);
        g.drawString("Rate of Fire: " + (50 - fire_delay) * 2, 250, 625);
        g.drawString("Shot damage: " + sdam * 10, 250, 675);
        g.drawImage(ply, px, py, 120, 40, panel);
        g.drawImage(h, px, py + 50, health * 120 / ht, 1, panel);
        if (Action_space && frames_count % fire_delay == 0) {
            i = -2;
            while (i <= ns - 3) {
                shot.add(new Shot(shx, shy + i * 10, i));
                ++i;
            }
        }
        i = 0;
        while (i < shot.size()) {
            Shot r = shot.get(i);
            g.drawImage(sh, r.x, r.y, 20, 10, panel);
            r.x += 5;
            if (r.p == -2 && frames_count % 5 == 0) {
                r.y -= 2;
            }
            if (r.p == -1 && frames_count % 5 == 0) {
                --r.y;
            }
            if (r.p == 1 && frames_count % 5 == 0) {
                ++r.y;
            }
            if (r.p == 2 && frames_count % 5 == 0) {
                r.y += 2;
            }
            if (r.x >= 1100 | r.y < 10 | r.y > 580) {
                shot.remove(i);
            }
            ++i;
        }
        if (Action_up && py >= 10) {
            py -= 4;
        }
        if (Action_down && py <= 540) {
            py += 4;
        }
        if (Action_left && px >= 10) {
            px -= 2;
        }
        if (Action_right && px <= 520) {
            px += 2;
        }
        if (killreq <= 0) {
            boss(g);
        } else {
            enemy(g);
        }
        return g;
    }

    /**
     * Boss modifier
     * 
     * @param g		Graphics
     */
    private void boss(Graphics g) {
        if (tbh < 100) {
            tbh = bhel;
        }
        enem.clear();
        if (bhel > 0) {
            g.drawImage(iboss, bx, by, 400, 200, panel);
            System.out.println(String.valueOf(bhel) + " " + tbh);
            g.drawImage(h, bx, by + 210, bhel * 400 / tbh, 3, panel);
            if (bx >= 650) {
                --bx;
            }
            by += bm;
            if (by <= 10) {
                bm *= -1;
            }
            if (by + 200 >= 590) {
                bm *= -1;
            }
            if (frames_count % enfiredelay / 3 == 0) {
                enshot.add(new Point(bx, rr.nextInt(201) + by));
            }
            int i = 0;
            while (i < enshot.size()) {
                Point r = enshot.get(i);
                g.drawImage(ensh, r.x, r.y, 25, 15, panel);
                r.x -= 5;
                if (r.x <= -10) {
                    enshot.remove(i);
                }
                ++i;
            }
            damage();
            i = 0;
            while (i < shot.size()) {
                Shot s = shot.get(i);
                if (s.y > by && s.y < by + 200 && s.x >= bx) {
                    shot.remove();
                    bhel -= sdam;
                }
                ++i;
            }
        } else {
            tbh = -1;
            if (level <= 15) {
                ++level;
            }
            en_cnt += 100 * level;
            orient();
        }
    }
    
    /**
     * Value updater on level switch
     * 
     */
    private void orient() {
        tbh = -1;
        System.out.println("Orient Called");
        switch (level) {
            case 1: {
                set(1, 3, 250, 300, 30, 200);
                iboss = b1;
                break;
            }
            case 2: {
                set(3, 6, 150, 300, 30, 300);
                iboss = b2;
                break;
            }
            case 3: {
                set(6, 9, 150, 240, 50, 500);
                iboss = b3;
                break;
            }
            case 4: {
                set(9, 12, 150, 240, 50, 750);
                iboss = b4;
                break;
            }
            case 5: {
                set(3, 15, 150, 200, 50, 1000);
                iboss = b5;
                break;
            }
            case 6: {
                set(6, 25, 150, 200, 60, 3500);
                iboss = b6;
                break;
            }
            case 7: {
                set(9, 30, 150, 140, 60, 7000);
                iboss = b7;
                break;
            }
            case 8: {
                set(20, 25, 150, 140, 60, 10000);
                iboss = b8;
                break;
            }
            case 9: {
                set(25, 30, 150, 100, 60, 25000);
                iboss = b9;
                break;
            }
            case 10: {
                set(30, 50, 150, 40, 60, 50000);
                iboss = b10;
                break;
            }
            case 11: {
                set(50, 100, 150, 40, 30, 75000);
                iboss = b11;
                break;
            }
            case 12: {
                set(80, 150, 150, 36, 30, 200000);
                iboss = b12;
                break;
            }
            case 13: {
                set(300, 5000, 150, 24, 15, 400000);
                iboss = b13;
                break;
            }
            case 14: {
                set(5000, 10000, 150, 12, 10, 600000);
                iboss = b14;
                break;
            }
            case 15: {
                set(10000, 50000, 150, 8, 5, 1000000);
                iboss = b15;
                break;
            }
            case 16: {
                set(10000, 50000, 150, 8, 0, 5000000);
                iboss = b16;
                break;
            }
        }
    }

    /**
     * helper for orient()
     * 
     * @param enminhl		Integer
     * @param enmaxhl		Integer
     * @param enem_freq		Integer
     * @param enfiredly		Integer
     * @param killreq		Integer
     * @param bosshlth		Integer
     */
    private void set(int enminhl, int enmaxhl, int enem_freq, int enfiredly, int killreq, int bosshlth) {
        this.enminhl = enminhl;
        this.enmaxhl = enmaxhl;
        enemy_freq = enem_freq;
        enfiredelay = enfiredly;
        this.killreq = killreq;
        bhel = bosshlth;
    }

    /**
     * Enemy Modifier
     * 
     * @param g Graphics
     */
    private void enemy(Graphics g) {
        if (killreq > 0) {
            Point r;
            if (frames_count % enemy_freq == 0) {
                enem.add(new Point(1100, rr.nextInt(501) + 35));
                hlth.add(rr.nextInt(enmaxhl - enminhl + 1) + enminhl);
            }
            int i = 0;
            while (i < enem.size()) {
                r = enem.get(i);
                if (hlth.get(i) >= 1 && hlth.get(i) < 5) {
                    g.drawImage(sh1_5, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 5 && hlth.get(i) < 10) {
                    g.drawImage(sh5_10, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 10 && hlth.get(i) < 15) {
                    g.drawImage(sh10_15, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 15 && hlth.get(i) < 20) {
                    g.drawImage(sh15_20, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 20 && hlth.get(i) < 25) {
                    g.drawImage(sh20_25, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 25 && hlth.get(i) < 30) {
                    g.drawImage(sh25_30, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 30 && hlth.get(i) < 40) {
                    g.drawImage(sh30_40, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 40 && hlth.get(i) < 50) {
                    g.drawImage(sh40_50, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 50 && hlth.get(i) < 80) {
                    g.drawImage(sh11, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 80 && hlth.get(i) < 300) {
                    g.drawImage(sh12, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 300 && hlth.get(i) < 5000) {
                    g.drawImage(sh13, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 5000 && hlth.get(i) < 10000) {
                    g.drawImage(sh14, r.x, r.y, 70, 35, panel);
                }
                if (hlth.get(i) >= 10000 && hlth.get(i) < 50000) {
                    g.drawImage(sh15, r.x, r.y, 70, 35, panel);
                }
                g.drawImage(h, r.x, r.y + 45, hlth.get(i) * 70 / enmaxhl, 1, panel);
                --r.x;
                ++i;
            }
            if (frames_count % enfiredelay == 0 && enem.size() > 0) {
                Point p = enem.get(rr.nextInt(enem.size()));
                enshot.add(new Point(p.x, p.y));
            }
            i = 0;
            while (i < enshot.size()) {
                r = enshot.get(i);
                g.drawImage(ensh, r.x, r.y, 25, 15, panel);
                r.x -= 5;
                if (r.x <= -10) {
                    enshot.remove(i);
                }
                ++i;
            }
            damage();
        }
    }

    /**
     * Damager Modifier
     * 
     */
    private void damage() {
        int i = 0;
        while (i < enem.size()) {
            Point e = enem.get(i);
            for (Shot s : shot) {
                if (s.y <= e.y || s.y >= e.y + 25 || e.x <= px + 120 || s.x + 10 < e.x) continue;
                ind3 = shot.indexOf((Object)s);
                hlth.set(i, hlth.get(i) - sdam);
                if (hlth.get(i) > 0) continue;
                enem.remove(i);
                hlth.remove(i);
                en_cnt += level;
                --killreq;
            }
            if ((e.y > py && e.y < py + 40) | (e.y + 25 > py && e.y + 25 < py + 40) && e.x + 50 > px && e.x < px + 120) {
                health -= hlth.get(i).intValue();
                enem.remove(i);
                hlth.remove(i);
            }
            ++i;
        }
        if (ind3 >= 0) {
            shot.remove(ind3);
            ind3 = -1;
        }
        int k = 0;
        while (k < enshot.size()) {
            Point s = enshot.get(k);
            if ((s.y > py && s.y < py + 40) | (s.y + 25 > py && s.y + 25 < py + 40) && s.x + 50 > px && s.x < px + 120) {
                health -= 20 + level / 2;
                enshot.remove(k);
            }
            ++k;
        }
    }

    /**
     * Game Over reset helper
     * 
     */
	private void turns() {
        Game_Over = true;
        JOptionPane.showMessageDialog(null, "Game over\nTotal kills: " + en_cnt);
        px = 20;
        py = 50;
        ind3 = -1;
        en_cnt = 0;
        enem.clear();
        shot.clear();
        hlth.clear();
        Reset_bools();
        mains.dispose();
    }	
}
