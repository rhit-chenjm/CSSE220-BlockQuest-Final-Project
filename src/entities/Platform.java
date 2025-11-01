package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Creates a new Platform allowing entities to stand on and fall off.
 *
 * @author Katie Henthorn
 * 
 * @param x - the upper left corner
 * @param y - the upper left corner
 * @param width - width in pixels
 * @param height - height in pixels
 * 
 */
public class Platform implements Drawable, Collidable {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Rectangle boundingBox;
	
	public Platform(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.boundingBox = new Rectangle(x, y, width, height);
	}

	@Override
	public Rectangle getBoundingBox() {
		return this.boundingBox;
	}

	@Override
	public boolean collidesWith(Rectangle boundingBox) {
		return this.boundingBox.intersects(boundingBox);
	}

	@Override
	public void drawOn(Graphics2D g2) {
		// Temporary color. We can change these platforms to images later.
		g2.setColor(Color.PINK);
		g2.fill(new Rectangle(this.x, this.y, this.width, this.height));
	}
}
