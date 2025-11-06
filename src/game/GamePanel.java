package game;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import hudScore.HudModel;
import hudScore.HudViewer;

/**
 * player controls, some formatting
 */
public class GamePanel extends JPanel {
	private final HudModel hudModel = new HudModel();
	private final HudViewer hudView = new HudViewer();
	
	
    private final GameComponent canvas = new GameComponent(hudView, hudModel);
    public GamePanel() {
    	JPanel layered = new JPanel();
        layered.setLayout(new OverlayLayout(layered));
        layered.setOpaque(false);  // Make layered panel transparent
        
    
        canvas.setOpaque(false);    
        // view
        layered.add(hudView); 
    	layered.add(canvas);
        layered.add(canvas);

    	hudView.setOpaque(false);         // Transparent so no gray background
    	hudView.setAlignmentX(0f);        // Left edge
    	hudView.setAlignmentY(0f);        // Top edge
    	layered.add(hudView);             // Add after canvas → goes on top
       
    	// PANEL LAYOUT
    	this.setLayout(new BorderLayout());
    	this.add(layered, BorderLayout.CENTER);
//    	this.setBackground(canvas.getBackground());
    	
    
    	
    	
    	
    	

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
                    	
                    	// canvas.setPlayerXSpeed(0);
                    	// canvas.setPlayerYSpeed(0);
                    	// break;
                }}});
//=======
//            	System.out.println(e.getKeyCode());
//            	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    canvas.setPlayerXSpeed(-10);
//                    canvas.setPlayerYSpeed(0);
//            	} else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//                	canvas.setPlayerXSpeed(10);
//                	canvas.setPlayerYSpeed(0);
//            	} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
//                	canvas.setPlayerXSpeed(0);
//                	canvas.setPlayerYSpeed(10);
//            	}else if(e.getKeyCode() == KeyEvent.VK_UP){
//                	canvas.setPlayerXSpeed(0);
//                	canvas.setPlayerYSpeed(-10);
//            	} else {
//            		canvas.setPlayerXSpeed(0);
//            		canvas.setAlignmentY(0);
//            		
//            	}
//            	e.setKeyCode(0);               
		setFocusable(true);          // must be focusable to get keys
		requestFocusInWindow();      // ask for focus

            }
    
    public GameComponent getGameComponent() {
    	return canvas;
    }
    
    //attempting to fix hud
    private void tick() {
    	hudModel.setScore(5);
    	hudView.refresh(hudModel);
    	canvas.repaint();
    }

}
