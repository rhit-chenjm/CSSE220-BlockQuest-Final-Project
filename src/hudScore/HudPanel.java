package hudScore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Creates a HUD that updates based on player fields
 */
public class HudPanel extends JPanel {
	
	private String hudText1 = "Hello world!";
	private String hudText2 = "";
	private int textX = 10;
	private int textY = 20;
	
	public HudPanel() {
		setPreferredSize(new Dimension(100, 200));
		setOpaque(false);
	}
	
	public void drawHud(Graphics2D g2) {
		super.paintComponent(g2);
		g2.setColor(Color.GREEN);
		g2.setFont(new Font("Arial", Font.BOLD, 20));
		g2.drawString(hudText1, textX, textY);
		g2.drawString(hudText2, textX, textY+25);
	}
	
	public void setHudText(int health, int score) {
		this.hudText1 = "health: "+health;
		this.hudText2 = "score : "+score;
		repaint();
	}
	
}
