package blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Platform;
import game.GameComponent;
import game.GameObject;
/**
 * A Player moves by user input and collides with platforms
 * 
 * A Player will interact with an Enemy or a Collectable and produce some effect
 */
public class Player extends GameObject {
	
	public int gravity = 3;
	public boolean isHoldingDown = true;
	public static final int SIZE = 80;
	private static final int STARTING_DX = 0;
	private static final int STARTING_DY = 0;
	
	private static final int BOX_SIZE = 90;
	private static final int BOX_X = 10;
	private static final int BOX_Y = 100;
	private boolean isTouchingPlatform;
	private Rectangle r1;
	private int lives;
	private boolean isInvincible;
	private boolean isFacingRight = true;
	private int score = 0;
	
    private BufferedImage image;
    private boolean imageLoaded = false;

	public Player(int width, int height, int lives, GameComponent gameComponent) {
		super(gameComponent, BOX_X, BOX_Y, STARTING_DX, STARTING_DY, SIZE, SIZE);
		this.isInvincible = false;
		isTouchingPlatform = false;
		r1 = new Rectangle((int) super.x, (int) super.y, SIZE, SIZE);
		this.lives = lives; 		
		try {
            image = ImageIO.read(Enemy.class.getResource("student.png"));
            this.imageLoaded = (image != null);
        } catch (IOException | IllegalArgumentException ex) {
            this.imageLoaded = false; 
        }
	}


	

	// handles movement, offscreen interactions, platform interactions, etc.
	@Override
	public void update() {
		if ( isOffScreen() == 1 ) {
			this.xVelocity /= -2;
			this.x = 0;
		} else if(isOffScreen() == 2) {
			this.xVelocity /= -2;
			this.x = super.gameComponent.getWidth()-super.width;
		} else if(isOffScreen() == 3) {
			this.yVelocity /= -2;
			this.y = 0;
		} else if(isOffScreen() == 4) {
			this.yVelocity /= -2;
			this.y = super.gameComponent.getHeight()-super.height;

		}
		if(isTouchingPlatform) {
			if (xVelocity >= 0) {
				xVelocity -= 0.01;
			} else {
				xVelocity += 0.01;
			}
		}

		this.yVelocity += 0.05 * gravity;
		super.update();

		
	}

	
	// methods for changing fields of Player
	public void setXSpeed(double c1) {
		super.xVelocity = c1;
	}
	public void setYSpeed(double c1) {
		super.yVelocity = c1;
	}
	public void addYSpeed(double c1) {
		super.yVelocity += c1;
	}
	public double getYSpeed() {
		return super.yVelocity;
	}
	public void addYPos(double c1) {
		super.y += c1;
	}
	public int getScore() {
		return this.score;
	}
	public int getLives() {
		return this.lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void subtractLife() {
		if(!this.isInvincible) {
			this.lives -= 1;
		}
	}
	public void collideWithEnemy(Enemy e) {
		if(super.overlapsGameObject(e)) {
			subtractLife();
			this.isInvincible = true;
		}
	}
	public void checkForEnemyCollision(List<Enemy> enemies) {
		for (Enemy e1 : enemies) {
			collideWithEnemy(e1);
		}
	}
	@Override
	public void collideWithPlatform( Platform other) {
		if(other.collidesWith(r1)) {
			isTouchingPlatform = true;
		}else {
			isTouchingPlatform = false;
		}
		if(isTouchingPlatform = true) {
			this.gravity = 0;
			if (this.yVelocity > 0) this.yVelocity = 0;
		} else {
			this.y -= 1;
			this.yVelocity = 5;
			this.gravity = 1;
		}

	}
	public boolean isTouchingPlatform() {
		return isTouchingPlatform;
	}
	public void setTouchingPlatform(boolean b1) {
		isTouchingPlatform = b1;
	}
	public void setIsInvincible(boolean b1) {
		this.isInvincible = b1;
	}
	public boolean getInvincibility() {
		return this.isInvincible;
	}
	public int returnLives() {
		return this.lives;
	}
	
	public void collideWithCollectable(Collectable c) {
		if(super.overlapsGameObject(c)) {
			if (isHoldingDown) {
				if (c.getType() == "coin") this.score ++;
				if (c.getType() == "dollar_bill") this.score += 5;
				c.health = 0;
				System.out.println(this.score);
			}
		}
	}
	public void checkForCollectableCollision(List<Collectable> collectables) {
		for (Collectable c1 : collectables) {
			collideWithCollectable(c1);
		}
	}
	
	
	@Override
	public void drawOn(Graphics2D g) {

		if (this.imageLoaded) {

			// Don't swap directions if velocity is tiny.
			
			// If moving right, face right.
			if (this.xVelocity > 0.01) {
				this.isFacingRight = true;
			}
			
			// If moving left, face left.
			if (this.xVelocity < -0.01) {
				this.isFacingRight = false;
			}

			if (this.isFacingRight) {
				// Draw facing right.
				g.drawImage(this.image, ((int) getBoundingBox().x), ((int) getBoundingBox().y), 
						BOX_SIZE, BOX_SIZE, null);
			} else {
				// Draw facing left.
				g.drawImage(this.image, ((int) getBoundingBox().x + BOX_SIZE), ((int) getBoundingBox().y), 
						-BOX_SIZE, BOX_SIZE, null);
			}

		} else {
			g.setColor(new Color(0, 255, 0));
			g.fill(this.getBoundingBox());
		}

	}

	@Override
	public void onRemove() {   
		//do nothing
	}
	public void setX(int x) {
		super.setX(x);
	}

	public void setY(int y) {
		super.setY(y);
	}
	public void checkForPlatformCollision(List<Platform> platforms) {
		boolean pChangedGravity = false;
		for (entities.Platform p: platforms) {
			if (!pChangedGravity) {
				if (this.overlaps(p)){
					this.collideWithPlatform(p);
					this.gravity = 0;
					pChangedGravity = true;
				}
				else this.gravity = 3;
			}
		}
		
	}
}
