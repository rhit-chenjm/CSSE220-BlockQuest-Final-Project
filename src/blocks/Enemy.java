package blocks;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entities.Drawable;
import game.GameComponent;
import java.io.IOException;

/**
 * An Enemy moves around on screen, colliding with platforms
 *
 * An Enemy will produce some effect when interacting with a Player
 */
public class Enemy extends AbstractBlock implements Drawable {
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private int health = 1;
	private boolean bounced;

	private Rectangle boundingBox;
    protected BufferedImage image;
    protected boolean imageLoaded = false;
	
	public Enemy(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH, HEIGHT);
		
		this.boundingBox = new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public boolean willRemove() {
		return this.health <= 0;
	}

	public void collideWithBlock(AbstractBlock otherBlock ) {
		this.reverseDirection();
		this.update();
		bounced = true;
	}
	
	// handles movement, offscreen interactions, platform interactions, etc.
	@Override
	public void update() {
		if ( isOffScreen() == 1 ) {
			this.xVelocity = -this.xVelocity;
			this.x = 0;
		} else if(isOffScreen() == 2) {
			this.xVelocity = -this.xVelocity;
			this.x = super.gameComponent.getWidth()-super.width;
		} else if(isOffScreen() == 3) {
			this.yVelocity = -this.yVelocity;
			this.y = 0;
		} else if(isOffScreen() == 4) {
			this.yVelocity = -this.yVelocity;
			this.y = super.gameComponent.getHeight()-super.height;
		}
		this.yVelocity += 0.05*gravity;

		super.update();

		bounced = false;
	}
	
//	public boolean checkForCollision(Player player) {
//		return true;
//	}
	
	
	@Override
	public void drawOn(Graphics2D g2) {
		
		if (this.imageLoaded) {
			
			if (this.xVelocity >= 0) {
			// Face right
			g2.drawImage(this.image, ((int) getBoundingBox().x), ((int) getBoundingBox().y), WIDTH, HEIGHT, null);
			} else {
			g2.drawImage(this.image, ((int) getBoundingBox().x + WIDTH), ((int) getBoundingBox().y), -WIDTH, HEIGHT, null);
			}
    	} else {
    		g2.setColor(new Color(255, 70, 0));			
    		g2.fill(new Rectangle2D.Double(getBoundingBox().x, getBoundingBox().y, this.getWidth(), this.getHeight()));    	}
	}
	
	public void subtractLife(Player player) {
		player.subtractLife();
	}

	@Override
	public void onRemove() {
		// not yet implemented
	}
	
	@Override
	public boolean shouldRemove() {
		return willRemove();
	}
}
