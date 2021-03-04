import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Font;

public class MoveTheMouse {

	private JFrame frame;
    public static final int MAX_Y = 600;
    public static final int MAX_X = 600;
    public static Boolean active = false;    

	public static void main(String[] args) {
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(active) {
				try {					
					Robot robot = new Robot();
					Random random = new Random();		        
				    robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));        
				} catch (AWTException e) {				
				}
				}	        				
			}
		}, 1000, 5000);	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoveTheMouse window = new MoveTheMouse();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MoveTheMouse() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 11));
		frame.setResizable(false);
		frame.setBounds(100, 100, 204, 136);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(50, 11, 30, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		chckbxNewCheckBox.setEnabled(false);
		
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {				  
				if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					if(!active) {
						active = true;
						chckbxNewCheckBox.setSelected(true);
					} else if(active) {						
							active = false;
							chckbxNewCheckBox.setSelected(false);
					}									
		        }				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Status:");
		lblNewLabel.setBounds(10, 15, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Start/Stop: STRG + S");
		lblNewLabel_1.setBounds(10, 41, 178, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
