package platforms;
import game.GameComponent;
import game.GameObject;
import entities.Platform;

/**
 * A Platform move around the screen and collects RainDrops.
 * 
 * A Platform can fill with Raindrops; when it does, it EXPLODES 
 * into a shower of RainDrops. 
 *
 */
public abstract class Entity extends GameObject {
	
	
	public int gravity = 0;

	public Entity(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent, int width, int height) {
		super(gameComponent,x,y,xVelocity,yVelocity, width,height);
	}

	public abstract void removeDrop();
	public abstract void addDrop();
	public abstract void makeInvinciple();
	
	public void isOnPlatform( Platform other ) {
		
	}
	
	public void collideWithPlatform( Platform other) {
		if (this.yVelocity > 0) this.yVelocity = 0;
		this.gravity = 0;
	}
	

}
