package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private final GameComponent canvas = new GameComponent();
    public GamePanel() {
        this.setLayout(new BorderLayout(8,8));
//		this.setBackground(GameComponent.BG);
		this.add(canvas,BorderLayout.CENTER);
		this.addKeyListener(new KeyAdapter() {
			
            @Override
            public void keyPressed(KeyEvent e) {
            	
            	
            	
            	
            	System.out.println(e.getKeyCode());
            	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    canvas.setPlayerXSpeed(-10);
                    canvas.setPlayerYSpeed(0);
            	} else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                	canvas.setPlayerXSpeed(10);
                	canvas.setPlayerYSpeed(0);
            	} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                	canvas.setPlayerXSpeed(0);
                	canvas.setPlayerYSpeed(10);
            	}else if(e.getKeyCode() == KeyEvent.VK_UP){
                	canvas.setPlayerXSpeed(0);
                	canvas.setPlayerYSpeed(-10);
            	} else {
            		canvas.setPlayerXSpeed(0);
            		canvas.setAlignmentY(0);
            		
            	}
            	e.setKeyCode(0);
                 // In GamePanel.keyPressed (add a new case)

                
            }
        });
		setFocusable(true);          // must be focusable to get keys
		requestFocusInWindow();      // ask for focus

    }
    public GameComponent getGameComponent() {
    	return canvas;
    }

}