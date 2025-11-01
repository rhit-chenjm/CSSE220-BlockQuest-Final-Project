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
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT  -> canvas.toggleBoxDirection();
                    case KeyEvent.VK_RIGHT -> canvas.toggleBoxDirection();
                    case KeyEvent.VK_SPACE -> {
//                        if (canvas.timer.isRunning()) canvas.stop();
//                        else canvas.start();
                    }
                 // In GamePanel.keyPressed (add a new case)

                }
            }
        });
		setFocusable(true);          // must be focusable to get keys
		requestFocusInWindow();      // ask for focus

    }
    public GameComponent getGameComponent() {
    	return canvas;
    }

}