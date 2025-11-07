package game;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import hudScore.HudPanel;
/**
 * player controls, some formatting
 */
public class GamePanel extends JPanel {
	private HudPanel hudPanel = new HudPanel();
	private int storedLives = 0;
	private int storedScore = 0;
	
    private final GameComponent canvas = new GameComponent(hudPanel);
    
    public GamePanel() {

    	JPanel layered = new JPanel();
        layered.setLayout(new OverlayLayout(layered));
        layered.setOpaque(false);  // Make layered panel transparent
        
        canvas.setOpaque(false);    
        // view
    	layered.add(canvas);

    	hudPanel.setOpaque(false);         // Transparent so no gray background
    	hudPanel.setAlignmentX(0f);        // Left edge
    	hudPanel.setAlignmentY(0f);        // Top edge
    	layered.add(hudPanel);             // Add after canvas → goes on top
       
    	// PANEL LAYOUT
    	this.setLayout(new BorderLayout());
    	this.add(layered, BorderLayout.CENTER);
    	this.setBackground(canvas.getBackground());
    
		this.addKeyListener(new KeyAdapter() {
			
            @Override
            public void keyPressed(KeyEvent e) {
            	// player controls by switch case
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: 
                        canvas.setPlayerXSpeed(-2);
                        canvas.playerCanCollect(false);
                        break;
                    case KeyEvent.VK_RIGHT: 
                    	canvas.setPlayerXSpeed(2);
                    	canvas.playerCanCollect(false);
                    	break;
                    case KeyEvent.VK_UP: 
                    	canvas.setPlayerYSpeed(-2);
                    	canvas.playerCanCollect(false);
                    	break;
                    case KeyEvent.VK_DOWN:
                    	canvas.playerCanCollect(true);
                    	break;
                    default:
                    	// No key is matched.
                    	throw new InputMismatchException("Wrong key!");
                }}});            
		setFocusable(true);          // must be focusable to get keys
		requestFocusInWindow();      // ask for focus

            }
    
    public GameComponent getGameComponent() {
    	return canvas;
    }
    public void updateHud() {
    	int currentLives = canvas.getPlayer().returnLives();
    	int currentScore = canvas.getPlayer().getScore();
    	// below if statement improves game speed
    	if (currentLives != this.storedLives || currentScore != this.storedScore){
    		this.storedLives = currentLives;
    		this.storedScore = currentScore;
    		hudPanel.setHudText(this.storedLives, this.storedScore);
    	}    
    }
}
