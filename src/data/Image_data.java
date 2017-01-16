package data;

/**
 * Image Store
 * 
 * @author vyadav
 * 
 */

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image_data {
    Image car;				//Car
    Image carn;				//Car with NOS
    Image cars;				//car tail-light on
    Image wall;				//brick wall
    Image cwall;			//concrete wall
    Image road;				//road texture
    Image head;				//header
    Image beam;				//iron beam
    Image l0;				//brick shade 1
    Image l1;				//brick shade 2
    Image l2;				//brick shade 3
    Image l3;				//brick shade 4
    Image l4;				//brick shade 5
    Image l5;				//brick shade 6
    Image back;				//background image
    Image spl1;				//Space 1
    Image spl5;				//Space 2
    Image spl10;			//Space 3
    Image spl13;			//Space 4
    Image ply;				//player ship
    Image h;				//laser shot
    Image sh1_5;			//Space Ship 1
    Image sh5_10;			//Space Ship 2
    Image sh10_15;			//Space Ship 3
    Image sh15_20;			//Space Ship 4
    Image sh20_25;			//Space Ship 5
    Image sh25_30;			//Space Ship 6
    Image sh30_40;			//Space Ship 7
    Image sh40_50;			//Space Ship 8
    Image sh11;				//Space Ship 9
    Image sh12;				//Space Ship 10
    Image sh13;				//Space Ship 11
    Image sh14;				//Space Ship 12
    Image sh15;				//Space Ship 13
    Image sh16;				//Space Ship 14
    Image iboss;			//NOT INITALIZED HERE
    Image b1;				//Big SpaceShip 1
    Image b2;				//Big SpaceShip 2
    Image b3;				//Big SpaceShip 3
    Image b4;				//Big SpaceShip 4
    Image b5;				//Big SpaceShip 5
    Image b6;				//Big SpaceShip 6
    Image b7;				//Big SpaceShip 7
    Image b8;				//Big SpaceShip 8
    Image b9;				//Big SpaceShip 9
    Image b10;				//Big SpaceShip 10
    Image b11;				//Big SpaceShip 11
    Image b12;				//Big SpaceShip 12
    Image b13;				//Big SpaceShip 13
    Image b14;				//Big SpaceShip 14
    Image b15;				//Big SpaceShip 15
    Image b16;				//Big SpaceShip 16
    Image sh;				//laser shot
    Image ensh;				//missile 1
    Image mm;				//missile 2
    
    String path = System.getProperty("user.dir")+File.separator+"assets"+File.separator;				//Path where game assets are stored
    
    /**
     * registers Images for games
     * 
     * @param i String having qualified name for game
     */
    protected Image_data(String i) {
        
        try {
            if (i.equalsIgnoreCase("CarGame1") | i=="launcher") {
                this.car = ImageIO.read(new File(String.valueOf(path) + "car.png"));
                this.carn = ImageIO.read(new File(String.valueOf(path) + "carn.gif"));
                this.cars = ImageIO.read(new File(String.valueOf(path) + "cars.png"));
                this.wall = ImageIO.read(new File(String.valueOf(path) + "wall.jpg"));
                this.cwall = ImageIO.read(new File(String.valueOf(path) + "conc.jpg"));
                this.road = ImageIO.read(new File(String.valueOf(path) + "6.jpg"));
                this.head = ImageIO.read(new File(String.valueOf(path) + "hed.jpg"));
                System.out.println("Success");
            }
            if (i.equalsIgnoreCase("BrickGame1") | i=="launcher") {
                this.beam = ImageIO.read(new File(String.valueOf(path) + "board.jpg"));
                this.l0 = ImageIO.read(new File(String.valueOf(path) + "l0.jpg"));
                this.l1 = ImageIO.read(new File(String.valueOf(path) + "l1.jpg"));
                this.l2 = ImageIO.read(new File(String.valueOf(path) + "l2.jpg"));
                this.l3 = ImageIO.read(new File(String.valueOf(path) + "l3.jpg"));
                this.l4 = ImageIO.read(new File(String.valueOf(path) + "l4.jpg"));
                this.l5 = ImageIO.read(new File(String.valueOf(path) + "l5.jpg"));
                this.back = ImageIO.read(new File(String.valueOf(path) + "base.jpg"));
                System.out.println("Success");
            }
            if (i.equalsIgnoreCase("ShooterGame1") | i=="launcher") {
                this.sh1_5 = ImageIO.read(new File(String.valueOf(path) + "sph1.png"));
                this.sh5_10 = ImageIO.read(new File(String.valueOf(path) + "sph5.png"));
                this.sh10_15 = ImageIO.read(new File(String.valueOf(path) + "sph10.png"));
                this.sh15_20 = ImageIO.read(new File(String.valueOf(path) + "sph15.png"));
                this.sh20_25 = ImageIO.read(new File(String.valueOf(path) + "sph20.png"));
                this.sh25_30 = ImageIO.read(new File(String.valueOf(path) + "sph25.png"));
                this.sh30_40 = ImageIO.read(new File(String.valueOf(path) + "sph30.png"));
                this.sh40_50 = ImageIO.read(new File(String.valueOf(path) + "sph40.png"));
                this.sh11 = ImageIO.read(new File(String.valueOf(path) + "sh11.png"));
                this.sh12 = ImageIO.read(new File(String.valueOf(path) + "sh12.png"));
                this.sh13 = ImageIO.read(new File(String.valueOf(path) + "sh13.png"));
                this.sh14 = ImageIO.read(new File(String.valueOf(path) + "sh14.png"));
                this.sh15 = ImageIO.read(new File(String.valueOf(path) + "sh15.png"));
                this.sh16 = ImageIO.read(new File(String.valueOf(path) + "sh16.jpg"));
                this.ply = ImageIO.read(new File(String.valueOf(path) + "player.png"));
                this.sh = ImageIO.read(new File(String.valueOf(path) + "sh.png"));
                this.ensh = ImageIO.read(new File(String.valueOf(path) + "ensht.png"));
                this.mm = ImageIO.read(new File(String.valueOf(path) + "mis.png"));
                this.spl1 = ImageIO.read(new File(String.valueOf(path) + "spl1.jpg"));
                this.spl5 = ImageIO.read(new File(String.valueOf(path) + "spl5.jpg"));
                this.spl10 = ImageIO.read(new File(String.valueOf(path) + "spl10.jpg"));
                this.spl13 = ImageIO.read(new File(String.valueOf(path) + "spl13.jpg"));
                this.h = ImageIO.read(new File(String.valueOf(path) + "heal.png"));
                this.b1 = ImageIO.read(new File(String.valueOf(path) + "b1.png"));
                this.b2 = ImageIO.read(new File(String.valueOf(path) + "b2.png"));
                this.b3 = ImageIO.read(new File(String.valueOf(path) + "b3.png"));
                this.b4 = ImageIO.read(new File(String.valueOf(path) + "b4.png"));
                this.b5 = ImageIO.read(new File(String.valueOf(path) + "b5.png"));
                this.b6 = ImageIO.read(new File(String.valueOf(path) + "b6.png"));
                this.b7 = ImageIO.read(new File(String.valueOf(path) + "b7.png"));
                this.b8 = ImageIO.read(new File(String.valueOf(path) + "b8.png"));
                this.b9 = ImageIO.read(new File(String.valueOf(path) + "b9.png"));
                this.b10 = ImageIO.read(new File(String.valueOf(path) + "b10.png"));
                this.b11 = ImageIO.read(new File(String.valueOf(path) + "b11.png"));
                this.b12 = ImageIO.read(new File(String.valueOf(path) + "b12.png"));
                this.b13 = ImageIO.read(new File(String.valueOf(path) + "b13.png"));
                this.b14 = ImageIO.read(new File(String.valueOf(path) + "b14.png"));
                this.b15 = ImageIO.read(new File(String.valueOf(path) + "b15.png"));
                this.b16 = ImageIO.read(new File(String.valueOf(path) + "b16.png"));
                System.out.println("Success");
            }
        }
        catch (IOException e) {
            System.out.println("Image not found at " + path);
        }
    }
}
