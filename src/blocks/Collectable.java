package blocks;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.GameComponent;

/**
 * A Collectable is stationary, and will produce some effect when collected by a Player
 */
public class Collectable extends AbstractBlock {
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	private int health = 1;
//	private boolean bounced;
	
	public Collectable(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent, WIDTH, HEIGHT);
	}

	public boolean willRemove() {
		return this.health <= 0;
	}

	@Override
	public void update() {
		super.update();
	}

	public void drawOn(Graphics2D g) {
		g.setColor(new Color(120, 0, 120));			
		g.fill(new Rectangle2D.Double(getBoundingBox().x, getBoundingBox().y, this.getWidth(), this.getHeight()));
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
