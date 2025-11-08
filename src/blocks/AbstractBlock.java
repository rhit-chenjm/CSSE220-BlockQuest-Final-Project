package blocks;
import game.GameComponent;
import game.GameObject;

import java.util.List;

import entities.Platform;

/**
 * Abstract class for Collectable, Enemy, and Player
 * 
 * A Block can collide with platforms and may be affected by gravity
 */
public abstract class AbstractBlock extends GameObject {
	
	public int gravity = 0;

	public AbstractBlock(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent, int width, int height) {
		super(gameComponent,x,y,xVelocity,yVelocity, width,height);
	}

	public void isOnPlatform( Platform other ) {
		// handled elsewhere currently
	}
	
	public void collideWithPlatform( Platform other) {
		if (this.yVelocity > 0) this.yVelocity = 0;
	}

	public void checkForPlatformCollision(List<Platform> platforms) {
		boolean eChangedGravity = false;
		for (Platform p: platforms) {
			if (!eChangedGravity) {
				if (this.overlaps(p)) {
					this.collideWithPlatform(p);
					this.gravity = 0;
					eChangedGravity = true;
				}
				else this.gravity = 3;
			}
		}
	}
}
