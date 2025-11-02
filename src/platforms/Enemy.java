package platforms;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.GameComponent;

/**
 * A Platform move around the screen and collects RainDrops.
 * 
 * A Platform can fill with Raindrops; when it does, it EXPLODES 
 * into a shower of RainDrops. 
 *
 */
public class Enemy extends Entity {
	private static final int WIDTH = 20;
	private static final int HEIGHT = 20;
	private int health = 1;
	private boolean bounced;
	
	public Enemy(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH, HEIGHT);
	}

	public boolean willExplode() {
		return this.health <= 0;
	}

//	public void collideWithPlatform(Entity otherPlatform ) {
//		this.reverseDirection();
//		this.update();
//		bounced =true;
//	}

	@Override
	public void update() {
		super.update();
		if ( isOffScreen()  ) {
			this.reverseDirection();
		}
		bounced =false;
	}

	public void drawOn(Graphics2D g) {
		g.setColor(new Color(255, 70, 0));			
		g.fill(new Rectangle2D.Double(getBoundingBox().x, getBoundingBox().y, this.getWidth(), this.getHeight()));
	}

	

	@Override
	public void onRemove() {
		// leave empty
	}
	
	@Override
	public boolean shouldRemove() {
		return willExplode();
	}
	
	
	//Methods shared with UserControlledPlatform but not with GameObject
	@Override
	public void removeDrop() {
		// leave empty
	}
	
	@Override
	public void addDrop() {
		// leave empty
	}
	
	@Override
	public void makeInvinciple() {
		// leave empty
	}
	
	

}
