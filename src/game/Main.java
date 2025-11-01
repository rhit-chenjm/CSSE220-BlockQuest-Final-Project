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
 * This framework stores the state of the game. The framework 
 * then repeatedly does these 3 things in one "tick" of the clock:
 * 
 * 1. Input events from the human change the game state. 
 * 2. Things move/spawn/die according to the game state.
 * 3. The component draws the current game state.
 * 
 * 
 * This design functions, but it makes use of type predicated code:
 * this means that the code checks what type objects and then decides what to do
 * based upon that. This is a terrible and unacceptable design strategy that
 * will cause you to be penalized heavily!
 * 
 * However, the logic in the GameComponent.handleCollisions() has to be dealt with
 * somewhere! To solve this problem we will find a way to remove instanceof 
 * everywhere by using inheritance via creating two Abstract classes
 * 
 * 
 * In this exercise, we develop each of these ideas.
 * TODO #1  Create AbstractPlatform
 * TODO #2  Create AbstractDrop
 * TODO #3  Re-work GameComponent to use lists of these abstract classes
 *          in order to handle the logic instead of using instanceof
 *
 * @author Jason Yoder, Buffalo Hewner, Matt Boutell, Mark Hays
 * and their colleagues.
 *
 */
public class Main {

	public static final int DELAY=10;
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
	    final JFrame frame = new JFrame("Moving Object — Key Listener");
	    GamePanel panel = new GamePanel();
		frame.setSize(500, 500);
        frame.setContentPane(panel);   // add our game panel
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
//		JButton button = new JButton("Click me");
//		frame.add(button, BorderLayout.SOUTH);
		GameAdvanceListener advanceListener = new GameAdvanceListener(panel.getGameComponent());
		
		Timer timer = new Timer(DELAY, advanceListener);
		timer.start();
		

		


//		button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				component.toggleBoxDirection();
//			}
			
		
	


	}
}
