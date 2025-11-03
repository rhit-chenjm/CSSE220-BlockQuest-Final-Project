package blocks;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;

public class DollarBill extends Collectable {
	
	private static int width = 80;
	private static int height = 80;

	public DollarBill(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, width, height, gameComponent);
	
		try {
            this.image = ImageIO.read(Enemy.class.getResource("dollar_bill.png"));
            this.imageLoaded = (image != null);
        } catch (IOException | IllegalArgumentException ex) {
            this.imageLoaded = false; 
        }
	}

}
