package blocks;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import game.GameComponent;

/**
 * An Enemy moves around on screen, colliding with platforms
 *
 * An Enemy will produce some effect when interacting with a Player
 */
public class Enemy extends AbstractBlock {
	private static final int WIDTH = 20;
	private static final int HEIGHT = 20;
	private int health = 1;
	private boolean bounced;
	private Rectangle boundingBox;

	
	public Enemy(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH, HEIGHT);
	}

	public boolean willRemove() {
		return this.health <= 0;
	}

	public void collideWithBlock(AbstractBlock otherBlock ) {
		this.reverseDirection();
		this.update();
		bounced =true;
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

		bounced =false;
	}
	
//	public boolean checkForCollision(Player player) {
//		return true;
//	}
	
	
	public void drawOn(Graphics2D g) {
		g.setColor(new Color(255, 70, 0));			
		g.fill(new Rectangle2D.Double(getBoundingBox().x, getBoundingBox().y, this.getWidth(), this.getHeight()));
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
