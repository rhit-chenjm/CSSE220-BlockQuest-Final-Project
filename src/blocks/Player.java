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
	
	public int gravity = 1;
	public static final int SIZE = 80;
	private static final int STARTING_DX = 0;
	private static final int STARTING_DY = 0;
	
	private static final int BOX_SIZE = 90;
	private static final int BOX_X = 10;
	private static final int BOX_Y = 100;
	private boolean isTouchingPlatform;
	private Rectangle r1;
	private int lives = 0;
	
	private Rectangle boundingBox;
    private BufferedImage image;
    private boolean imageLoaded = false;
	
	public Player(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(gameComponent,x, y, xVelocity, yVelocity, SIZE , SIZE);
		isTouchingPlatform = false;
		r1 = new Rectangle((int) super.x, (int) super.y, SIZE, SIZE);
		lives = 3;
		
		try {
            image = ImageIO.read(Enemy.class.getResource("goose.png"));
            this.imageLoaded = (image != null);
        } catch (IOException | IllegalArgumentException ex) {
            this.imageLoaded = false; 
        }
	}

	public Player(int width, int height, GameComponent gameComponent) {
		super(gameComponent, BOX_X, BOX_Y, STARTING_DX, STARTING_DY, SIZE, SIZE);
		isTouchingPlatform = false;
		r1 = new Rectangle((int) super.x, (int) super.y, SIZE, SIZE);
		lives = 3;
		
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
		if(isTouchingPlatform) {
			if(xVelocity > 0) {
				xVelocity -= 0.01;
			} else {
				xVelocity += 0.01;
			}
		}

		this.yVelocity += 0.05 * gravity;
		super.update();

		if(xVelocity > 0) {
			this.xVelocity -= 0.01;
		} else if(xVelocity < 0) {
			this.xVelocity += 0.01;
		}

		
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
	
	public void subtractLife() {
		this.lives -= 1;
	}
	public void collideWithEnemy(Enemy e) {
		if(super.overlapsGameObject(e)) {
			subtractLife();
			System.out.println("hit");
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
	
	
	@Override
	public void drawOn(Graphics2D g) {

		if (this.imageLoaded) {
			g.drawImage(this.image, ((int) getBoundingBox().x), ((int) getBoundingBox().y), BOX_SIZE, BOX_SIZE, null);
    	} else {
    		g.setColor(new Color(0, 255, 0));
    		g.fill( this.getBoundingBox() );
    	}
	}


	@Override
	public void onRemove() {   
		//do nothing
	}
	
	




}
