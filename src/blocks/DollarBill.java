package blocks;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;

/**
 * A DollarBill is a type of Collectable, and it adds to the player's score.
 * @param x - x-coordinate of top-left corner of image
 * @param y - y-coordinate of top-left corner of image
 * @param xVelocity - starting velocity in the x direction
 * @param yVelocity - starting velocity in the y direction
 * @param gameComponent - the JComponent drawing the DollarBill
 * 
 * @author Katie Henthorn
 */
public class DollarBill extends Collectable {
	
	private static int WIDTH = 80;
	private static int HEIGHT = 80;
	
	public DollarBill(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, WIDTH, HEIGHT, gameComponent);
		this.width = WIDTH;
		this.height = HEIGHT;
		this.type = "dollar_bill";
	
		try {
            this.image = ImageIO.read(Enemy.class.getResource("dollar_bill.png"));
            this.imageLoaded = (image != null);
        } catch (IOException | IllegalArgumentException ex) {
            this.imageLoaded = false; 
        }
	}

}
