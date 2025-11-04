package blocks;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;

/**
 * A Goose is a type of Enemy.
 * @param x - x-coordinate of top-left corner of image
 * @param y - y-coordinate of top-left corner of image
 * @param xVelocity - starting velocity in the x direction
 * @param yVelocity - starting velocity in the y direction
 * @param gameComponent - the JComponent drawing the Goose
 * 
 * @author Katie Henthorn
 */
public class Goose extends Enemy {

	public Goose(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent);
		
		try {
            this.image = ImageIO.read(Enemy.class.getResource("goose.png"));
            this.imageLoaded = (image != null);
        } catch (IOException | IllegalArgumentException ex) {
            this.imageLoaded = false; 
        }
	}
}
