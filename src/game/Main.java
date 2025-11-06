package game;
import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * main method, runs the program
 */
public class Main {

	public static final int DELAY=10;
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Moving Object — Key Listener");
	    GamePanel panel = new GamePanel();
		frame.setSize(1000, 800);
        frame.setContentPane(panel);   // add our game panel
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

		GameAdvanceListener advanceListener = new GameAdvanceListener(panel, panel.getGameComponent());

		
		Timer timer = new Timer(DELAY, advanceListener);
		timer.start();
		//:D
	}
	
}
