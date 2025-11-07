package hudScore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class HudPanel extends JPanel {
	
	private String hudText = "Hello world!";
	private int textX = 50;
	private int textY = 100;
	private int storedHealth = 999;
	private int storedScore = 999;
	
	public HudPanel() {
		setPreferredSize(new Dimension(100, 200));
		setOpaque(false);
	}
	
	public void drawHud(Graphics2D g2) {
		super.paintComponent(g2);
		g2.setColor(Color.GREEN);
		g2.setFont(new Font("Arial", Font.BOLD, 20));
		g2.drawString(hudText, textX, textY);
	}
	
	public void setHudText(int health, int score) {
		this.hudText = "health: "+health+" | score: "+score;
		repaint();
	}
	
}
