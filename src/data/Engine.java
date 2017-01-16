package data;

/**
 * Abstract class for providing basic game features
 * 
 * @author vyadav
 * 
 */
	
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

abstract public class Engine extends Image_data{
	
	public boolean play=false;					//Playable?
	protected int frames_count=0;				//No of frames processed
	protected boolean Game_Over,				//Game Over?
					Action_up,					//Up pressed?
					Action_down,				//Down pressed?
					Action_left,				//Left pressed?
					Action_right,				//Right pressed?
					Action_space;				//Space pressed?
	protected Random rr= new Random();			//Random generator
	protected Run process = new Run();			//Custom Thread
	protected drawPan panel;					//Custom Panel
	protected JFrame mains;						//Main Window
	
	/**
	 * Constructor for simple draw
	 * 
	 */
	protected Engine(){
		this(535,540,500,500,null);
	}
	
	/**
	 * Action Key reset helper
	 * 
	 */
	protected void Reset_bools(){
		Game_Over=false;
		Action_up=false;
		Action_down=false;
		Action_left=false;
		Action_right=false;
		Action_space=false;
	}
	
	/**
	 * Constructor for window and component initialization
	 * 
	 * @param Frame_width
	 * @param Frame_height
	 * @param Panel_width
	 * @param Panel_height
	 * @param i
	 */
	protected Engine(int Frame_width,int Frame_height,int Panel_width,int Panel_height,String i){
		super(i);
		Reset_bools();
		mains = new JFrame("Draw");
		mains.setLayout(null);
		mains.setBounds(50, 200, Frame_width, Frame_height);
		mains.setResizable(false);
		mains.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new drawPan();
		panel.setBounds(10, 10, Panel_width, Panel_height);
		panel.setBorder(BorderFactory.createEtchedBorder());
					
		mains.add(panel);
		actions();
		mains.setVisible(true);
		process.start();
	}
	
	/**
	 * Key Binding implementation
	 * 
	 */
	protected void actions(){
	//always call super when overriding this method
	mains.addKeyListener(new KeyListener() {		
		@Override
		public void keyTyped(KeyEvent e) {
		}
			@Override
				public void keyReleased(KeyEvent e) {
				int kc = e.getKeyCode();
				switch(kc){
				case KeyEvent.VK_UP:
					Action_up=false;
					break;
				case KeyEvent.VK_DOWN:
					Action_down=false;
					break;
				case KeyEvent.VK_LEFT:	
					Action_left=false;
					break;
				case KeyEvent.VK_RIGHT:	
					Action_right=false;
					break;
				case KeyEvent.VK_SPACE:
					Action_space=false;
					break;
				case KeyEvent.VK_NUMPAD1:
//					num1end();
					break;
				case KeyEvent.VK_NUMPAD2:
//					num2end();
					break;
				case KeyEvent.VK_NUMPAD3:
//					num3end();
					break;
				case KeyEvent.VK_NUMPAD4:
//					num4end();
					break;
				case KeyEvent.VK_NUMPAD5:
//					num5end();
					break;
				case KeyEvent.VK_NUMPAD6:
//					num6end();
					break;
				default:					
					break;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int kc = e.getKeyCode();
				switch(kc){
				case KeyEvent.VK_UP:
					Action_up=true;
					break;
				case KeyEvent.VK_DOWN:
					Action_down=true;
					break;
				case KeyEvent.VK_LEFT:	
					Action_left=true;	
					break;
				case KeyEvent.VK_RIGHT:	
					Action_right=true;
					break;
				case KeyEvent.VK_SPACE:
					Action_space=true;
					break;
				case KeyEvent.VK_NUMPAD1:
//					num1start();
					break;
				case KeyEvent.VK_NUMPAD2:
//					num2start();
					break;
				case KeyEvent.VK_NUMPAD3:
//					num3start();
					break;
				case KeyEvent.VK_NUMPAD4:
//					num4start();
					break;
				case KeyEvent.VK_NUMPAD5:
//					num5start();
					break;
				case KeyEvent.VK_NUMPAD6:
//					num6start();
					break;
				default:					
					break;
				}
			}
		});		
	}
	
	/**
	 * Draw method to be overridden by extending class
	 * 
	 * @param g Graphics
	 * @return Graphics
	 */
	abstract protected Graphics updategr(Graphics g);

	/**
	 * Custom Panel for Game Display
	 * 
	 * @author vyadav
	 *
	 */
	class drawPan extends JPanel{
		private static final long serialVersionUID = 1L;
		public drawPan(){
			setPreferredSize(new Dimension(500, 500));
			setLayout(null);
			
		}
			
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			if(!Game_Over) g = updategr(g);
		}
	}
	
	/**
	 * Custom Thread for Game Clock
	 * 
	 * @author vyadav
	 *
	 */
	class Run extends Thread{
		@Override
		public void run(){
			while(true){
				try {
					panel.repaint();
					frames_count += 1;
//					System.out.println(frames_count);
					Thread.sleep(10);
				} catch (InterruptedException e) {
					System.out.println("Thread 'Run' has been interrupted !!\n"+"Applicaiton will close now..." );
					System.exit(0);
				}
			}
		}
	}
}
