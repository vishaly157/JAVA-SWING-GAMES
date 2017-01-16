package data;

/**
 * Launcher for Shooter Game
 * 
 * @author vyadav
 * 
 */


import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class GameLauncher extends Image_data{

	public ShooterGame1 slot=null;		//Save Slot
	private int Armour=200,		//player health
			Cannons=1,			//player cannons
			Damage=1,			//damage done by player
			Rateoffire=40,		//rate of fire of player
			Lazer=0,			//NOT IMPLEMENTED
			Shld=0,				//NOT IMPLEMENTED
			Resc,				//NOT IMPLEMENTED
			Level=1;			//Level
	
	private static int rlevel,	//Returned level
					rkills;		//Returned no of kills
	private String saves="D:/JAVA_GAME/vyadav/Saves/";		//Save location

	public static void setRlevel(int rlevel) {
		GameLauncher.rlevel = rlevel;
	}

	public static void setRkills(int rkills) {
		GameLauncher.rkills = rkills;
	}

	/**
	 * Constructor initializing window size and components
	 * 
	 */
	public GameLauncher(){
		super("launcher");
		JFrame main = new JFrame("Game Launcher");
		main.setLayout(null);
		main.setBounds(100, 100, 720, 500);
		main.setResizable(false);
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane jtp = new JTabbedPane();
		jtp.setBounds(10, 10, 690, 450);
		jtp.setBorder(BorderFactory.createEtchedBorder());
		JPanel jp1 = new JPanel(null);
		jp1.setBounds(0, 0, 690, 450);
		designsht(jp1);
	    JPanel jp2 = new JPanel(null);
	    jp2.setBounds(0, 0, 690, 450);
	    sec(jp2);
        jtp.addTab("Shooter Game", jp1);
        jtp.addTab("Anim", jp2);
		main.add(jtp);
		
		if(!(new File(saves)).exists())
			new File(saves).mkdirs();
	}
	
	void sec(JPanel rr){
		JButton begin = new JButton("PLAY");
		begin.setBounds(540, 10, 120, 150);
		begin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		rr.add(begin);
	}
	
	/**
	 * Design the user interface
	 * 
	 * @param rr java swing Panel
	 */
	void designsht(JPanel rr){
		JLabel ship = new JLabel("");
		ship.setBounds(10, 10, 500, 150);
		ship.setBorder(BorderFactory.createEtchedBorder());
		ship.setIcon(new ImageIcon(ply.getScaledInstance(500, 150, Image.SCALE_SMOOTH)));
		JLabel ii = new JLabel("Player Ship");
		ii.setBounds(200, 160, 150, 25);
		JLabel pr1 = new JLabel("ARMOUR");
		pr1.setBounds(10, 210, 150, 25);
		JLabel pr2 = new JLabel("CANNONS");
		pr2.setBounds(10, 240, 150, 25);
		JLabel pr3 = new JLabel("DAMAGE");
		pr3.setBounds(10, 270, 150, 25);
		JLabel pr4 = new JLabel("RATE OF FIRE");
		pr4.setBounds(10, 300, 150, 25);
		JLabel pr5 = new JLabel("LAZER SP");
		pr5.setBounds(10, 330, 150, 25);
		JLabel pr6 = new JLabel("SHIELD SP");
		pr6.setBounds(10, 360, 150, 25);
		JLabel pr7 = new JLabel("RESOURCES");
		pr7.setBounds(10, 390, 150, 25);

		final JLabel pi1 = new JLabel(""+Armour);
		pi1.setBounds(200, 210, 175, 25);
		pi1.setBorder(BorderFactory.createEtchedBorder());
		JButton upr1 = new JButton("Upgrade");
		upr1.setBounds(400, 210, 100, 25);
		upr1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Armour = Integer.parseInt(JOptionPane.showInputDialog("Armour (50 to 50000): "));
				if(Armour<50 | Armour>50000)	Armour=300;
				pi1.setText(""+Armour);
			}
		});
		
		final JLabel pi2 = new JLabel(""+Cannons);
		pi2.setBounds(200, 240, 175, 25);
		pi2.setBorder(BorderFactory.createEtchedBorder());
		JButton upr2 = new JButton("Upgrade");
		upr2.setBounds(400, 240, 100, 25);
		upr2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cannons = Integer.parseInt(JOptionPane.showInputDialog("Cannons (1 to 5): "));
				if(Cannons<1 | Cannons>5)	Cannons=1;
				pi2.setText(""+Cannons);
			}
		});
		
		final JLabel pi3 = new JLabel(""+Damage);
		pi3.setBounds(200, 270, 175, 25);
		pi3.setBorder(BorderFactory.createEtchedBorder());
		JButton upr3 = new JButton("Upgrade");
		upr3.setBounds(400, 270, 100, 25);
		upr3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Damage = Integer.parseInt(JOptionPane.showInputDialog("Damage (1 to 5000): "));
				if(Damage<1 | Damage>5000)	Damage=1;
				pi3.setText(""+Damage);
			}
		});
		
		final JLabel pi4 = new JLabel(""+Rateoffire);
		pi4.setBounds(200, 300, 175, 25);
		pi4.setBorder(BorderFactory.createEtchedBorder());
		JButton upr4 = new JButton("Upgrade");
		upr4.setBounds(400, 300, 100, 25);
		upr4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Rateoffire = Integer.parseInt(JOptionPane.showInputDialog("Rate of Fire \nmin at 200, max at 4: "));
				if(Rateoffire<4 | Rateoffire>200)	Rateoffire=50;
				pi4.setText(""+Rateoffire);
			}
		});
		
		final JLabel pi5 = new JLabel(""+Lazer);
		pi5.setBounds(200, 330, 175, 25);
		pi5.setBorder(BorderFactory.createEtchedBorder());
		JButton upr5 = new JButton("Upgrade");
		upr5.setBounds(400, 330, 100, 25);
		upr5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Lazer = Integer.parseInt(JOptionPane.showInputDialog("Lazer : "));
				pi5.setText(""+Lazer);
			}
		});
		
		final JLabel pi6 = new JLabel(""+Shld);
		pi6.setBounds(200, 360, 175, 25);
		pi6.setBorder(BorderFactory.createEtchedBorder());
		JButton upr6 = new JButton("Upgrade");
		upr6.setBounds(400, 360, 100, 25);
		upr6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Shld = Integer.parseInt(JOptionPane.showInputDialog("Shield : "));
				pi6.setText(""+Shld);
			}
		});
		
		JLabel pi7 = new JLabel(""+Resc);
		pi7.setBounds(200, 390, 175, 25);
		pi7.setBorder(BorderFactory.createEtchedBorder());
		
		JButton upr7 = new JButton("Load");
		upr7.setBounds(400, 390, 100, 25);
		upr7.addActionListener(shooter_load_progress);
		
		JButton upr8 = new JButton("Save");
		upr8.setBounds(510, 390, 100, 25);
		upr8.addActionListener(shooter_save_progress);
		
		JButton begin = new JButton("PLAY");
		begin.setBounds(540, 10, 120, 150);
		begin.addActionListener((e)->slot=new ShooterGame1(Armour, Cannons, Damage, Rateoffire, Lazer, Shld, Level));
		
		SpinnerModel spm = new SpinnerNumberModel(1, 1, 16, 1);
		JSpinner le = new JSpinner(spm);
		le.setBounds(540, 210, 120, 30);
		le.addChangeListener((e) -> {
				Level=(int) ((JSpinner)e.getSource()).getValue();
			}
		);
		JLabel lea = new JLabel("Set Level");
		lea.setBounds(542, 240, 120, 30);
		lea.setBorder(BorderFactory.createEtchedBorder());
		
		rr.add(lea);
		rr.add(le);
		rr.add(begin);
		rr.add(ship);
		rr.add(ii);
		rr.add(upr1);
		rr.add(upr2);
		rr.add(upr3);
		rr.add(upr4);
		rr.add(upr5);
		rr.add(upr6);
		rr.add(upr7);
		rr.add(upr8);
		rr.add(pr1);
		rr.add(pr2);
		rr.add(pr3);
		rr.add(pr4);
		rr.add(pr5);
		rr.add(pr6);
		rr.add(pr7);
		rr.add(pi1);
		rr.add(pi2);
		rr.add(pi3);
		rr.add(pi4);
		rr.add(pi5);
		rr.add(pi6);
		rr.add(pi7);
	}
	
	/**
	 * Action Listener for saving
	 * 
	 */
	ActionListener shooter_save_progress = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int ch = JOptionPane.showConfirmDialog(null, "Are you sure you\nwant to save?", "Save Prompt", JOptionPane.YES_NO_OPTION);
			if(ch==JOptionPane.NO_OPTION)	return;
			Properties save = new Properties();
			save.setProperty("Armour_Value", Integer.toString(Armour));
			save.setProperty("Cannons_Value", Integer.toString(Cannons));
			save.setProperty("Damage_Value", Integer.toString(Damage));
			save.setProperty("RateOfFire_Value", Integer.toString(Rateoffire));
			save.setProperty("Rescource_Value", Integer.toString(Resc));
			
			String ffnme = saves+"shooter.savefile";
			File sv = new File(ffnme);
			if(sv.exists())	sv.delete();
			PrintWriter pw = null;
			try {
				sv.createNewFile();
				pw = new PrintWriter(sv);
				save.store(pw, "Shooter Game Save File");
				System.out.println("Saving Done");
			} catch (IOException e1) {
				System.out.println(e1+" Save Error");
				e1.printStackTrace();
			}finally{
				pw.close();
			}
			
		}
	};
	
	/**
	 * Action Listener for loading
	 * 
	 */
	ActionListener shooter_load_progress = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Properties save = new Properties();
			String ffnme = saves+"shooter.savefile";
			File sv = new File(ffnme);
			BufferedReader br=null;
			if(sv.exists()){
			try {
				br = Files.newBufferedReader(Paths.get(ffnme));
					save.load(br);
					Armour=Integer.parseInt(save.getProperty("Armour_Value"));
					Cannons=Integer.parseInt(save.getProperty("Cannons_Value"));
					Damage=Integer.parseInt(save.getProperty("Damage_Value"));
					Rateoffire=Integer.parseInt(save.getProperty("RateOfFire_Value"));
					Resc=Integer.parseInt(save.getProperty("Rescource_Value"));
				System.out.println("Loading Done");
			} catch (IOException e1) {
				System.out.println(e1+" load Error");
			}finally{
				try {
					br.close();
				} catch (IOException e1) {
					System.out.println(e1+" Closing Error");
				}
			}
		}else
			System.out.println("Save First");
		}
	};
	
	/**
	 * 
	 * 
	 */
	ActionListener shootersave = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PrintWriter pw=null;
			try {
				String ffnme = saves+"shooter.save";
				File dirs = new File(saves);
				File save = new File(ffnme);
				if(save.exists())
					save.delete();
				int ch = JOptionPane.showConfirmDialog(null, "No Save Found\nCreate New Save?", "Save Not Found", JOptionPane.YES_NO_OPTION);
				if(ch== JOptionPane.YES_OPTION){
					dirs.mkdirs();
					save.createNewFile();
					pw = new PrintWriter(save);
					pw.println("Armour_Val "+Armour*2);
					pw.println("Cannons_Val "+Cannons*2);
					pw.println("Damage_Val "+Damage*2);
					pw.println("Rateoffire_Val "+Rateoffire*2);
					pw.println("Lazer_Val "+Lazer);
					pw.println("Shield_Val "+Shld);
					pw.println("Resc_Val "+Resc);
					
				}
				else 
					System.out.println("Continuing without save");
			} catch (IOException e1) {
				System.out.println(e1);
			}finally{
				pw.close();
			}
		}
	};
	
	/**
	 * Action Listener for saving method 2
	 * 
	 */
	ActionListener shooterload = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String ffnme = saves+"shooter.save";
			File save = new File(ffnme);
			Scanner scx;
			try {
				scx = new Scanner(save);
			while(scx.hasNext()){
				String s=scx.next();
				if(s.equals("Armour_Val"))
					System.out.println("Armour "+scx.nextInt());
				else if(s.equals("Cannons_Val"))
					System.out.println("Cannons "+scx.nextInt());
				else if(s.equals("Damage_Val"))
					System.out.println("Damage "+scx.nextInt());
				else if(s.equals("Rateoffire_Val"))
					System.out.println("Rate of fire "+scx.nextInt());
				else if(s.equals("Lazer_Val"))
					System.out.println("Lazer "+scx.nextInt());
				else if(s.equals("Shield_Val"))
					System.out.println("Shld "+scx.nextInt());
				else if(s.equals("Resc_Val"))
					System.out.println("Resources "+scx.nextInt());
				else
					System.out.println("----------------------------");
			}
			scx.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	
	/**
	 * notify returns
	 * 
	 */
	static void inotify(){
		System.out.println(rkills+"\n"+rlevel);
		
	}
	
	/**
	 * Main Method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {		
			@Override
			public void run() {
				new GameLauncher();
			}
		});
	}
	
}
