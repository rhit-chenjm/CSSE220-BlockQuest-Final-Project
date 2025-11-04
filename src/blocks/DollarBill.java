package blocks;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;

public class DollarBill extends Collectable {
	
	private static int WIDTH = 80;
	private static int HEIGHT = 80;
	
	public DollarBill(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, WIDTH, HEIGHT, gameComponent);
		this.width = WIDTH;
		this.height = HEIGHT;
	
		try {
            this.image = ImageIO.read(Enemy.class.getResource("dollar_bill.png"));
            this.imageLoaded = (image != null);
        } catch (IOException | IllegalArgumentException ex) {
            this.imageLoaded = false; 
        }
	}

}
