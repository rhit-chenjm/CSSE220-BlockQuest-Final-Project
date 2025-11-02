
package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

//import blocks.Enemy;
//import blocks.Entity;
import entities.Platform;
/**
 * moves objects within the game
 */
public abstract class GameObject {
	
	protected double x, y;
	protected double yVelocity;
	protected double xVelocity;
	private boolean shouldRemove;
	protected double width;
	protected double height;
	protected GameComponent gameComponent;
	private int gravity;
	private Rectangle boundingBox;

	
	
	public GameObject(GameComponent gameComponent, double x, double y, double dx, double dy, double width, double height) {
		this.x = x;
		this.y = y;
		this.xVelocity = dx;
		this.yVelocity = dy;
		this.gameComponent = gameComponent;
		this.width = width;
		this.height = height;
		this.boundingBox = new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
	
	public abstract void onRemove();
	public abstract void drawOn(Graphics2D g2);
	
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getWidth() {
		return this.width;
	}
	public double getHeight() {
		return this.height;
	}

	// updating position of objects
	public void update() {
		this.x += this.xVelocity;
		this.y += this.yVelocity;
	}

	public void reverseDirection() {
		this.xVelocity = -this.xVelocity;
		this.yVelocity = -this.yVelocity;
	}
	
	public boolean shouldRemove() {
		return this.shouldRemove;
	}
	
	public void markToRemove() {
		this.shouldRemove = true;
	}
	
	public Rectangle2D.Double getBoundingBox() {
		return new Rectangle2D.Double(this.x, this.y, getWidth(), getHeight() );
	}
	
	// handles some overlap/collision
	public boolean overlaps(Platform p) {
		return getBoundingBox().intersects(p.getBoundingBox());
	}
	public boolean overlapsGameObject(GameObject o) {
		return getBoundingBox().intersects(o.getBoundingBox());
	}
	
	public int isOffScreen() {
		boolean xLow = x <0;
		boolean xHigh = x + width > gameComponent.getWidth();
		boolean yLow = y <0;
		boolean yHigh = y + height > gameComponent.getHeight();
		if(xLow) {
			return 1;
		} else if(xHigh) {
			return 2;
		} else if(yLow) {
			return 3;
		} else if(yHigh) {
			return 4;
		} else {
			return 0;
		}
	}
	
	public boolean offBottom() {
		return y > gameComponent.getHeight();
	}
	
	public abstract void collideWithPlatform(Platform platform);




}
