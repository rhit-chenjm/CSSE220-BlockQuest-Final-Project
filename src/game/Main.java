package game;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * main method, runs the program
 */
public class Main {

	public static final int DELAY=10;
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
	    final JFrame frame = new JFrame("Moving Object — Key Listener");
	    GamePanel panel = new GamePanel();
		frame.setSize(1000, 1000);
        frame.setContentPane(panel);   // add our game panel
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
//		JButton button = new JButton("Click me");
//		frame.add(button, BorderLayout.SOUTH);
		GameAdvanceListener advanceListener = new GameAdvanceListener(panel.getGameComponent());
		
		Timer timer = new Timer(DELAY, advanceListener);
		timer.start();
		//:D
		

//		button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				component.toggleBoxDirection();
//			}
			
	}
}
