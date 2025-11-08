package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import game.GameComponent;

public class Button implements Collidable, Drawable {
	private int x;
	private int y;
	private int width;
	private int height;
	private String text;
	
	public Button(int x, int y, int width, int height, String text, GameComponent g) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
	}
	
	@Override
	public void drawOn(Graphics2D g2) {
    		g2.setColor(new Color(230, 0, 0));			
    		g2.fill(new Rectangle(getBoundingBox().x, getBoundingBox().y, this.width, this.height));  
    		
    		g2.setColor(new Color(255, 255, 255));
    		g2.draw(new Rectangle(getBoundingBox().x, getBoundingBox().y, this.width, this.height));  
    		
    		g2.setColor(new Color(255, 255, 255));
    		g2.setFont(new Font("Arial", Font.BOLD, 40));
    		g2.drawString(text, x + 40, y + 48);
    }

	@Override
	public boolean collidesWith(Rectangle boundingBox) {
		return this.getBoundingBox().intersects(boundingBox);
		
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(x, y, width, height);
	}

}
