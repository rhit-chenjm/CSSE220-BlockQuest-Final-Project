package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import hudScore.HudPanel;


/**
 * main method, runs the program
 */
public class Main {

	public static final int DELAY=10;
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Goose Game");
		
		//New code
		frame.setLayout(new BorderLayout());
		HudPanel hudPanel = new HudPanel();
		hudPanel.setBackground(new Color(0,0,0,0));
		//New code
		
		
	    GamePanel panel = new GamePanel();
		frame.setSize(1000, 800);
//      frame.setContentPane(panel);   // add our game panel
		
		//New code for adding HUD to game (HudPanelTest)
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(400,300));
		panel.setBounds(0,0,1000,800);
		layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
		hudPanel.setBounds(0,0,100,200);
		layeredPane.add(hudPanel, JLayeredPane.PALETTE_LAYER);
		frame.add(layeredPane, BorderLayout.CENTER);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

		GameAdvanceListener advanceListener = new GameAdvanceListener(panel, panel.getGameComponent());

		
		Timer timer = new Timer(DELAY, advanceListener);
		timer.start();
		//:D
	}
	
}
