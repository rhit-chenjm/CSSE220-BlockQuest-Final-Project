package blocks;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;

public class Coin extends Collectable {

	public Coin(int x, int y, int xVelocity, int yVelocity, GameComponent gameComponent) {
		super(x, y, xVelocity, yVelocity, gameComponent);
		
		try {
            this.image = ImageIO.read(Enemy.class.getResource("coin.png"));
            this.imageLoaded = (image != null);
        } catch (IOException | IllegalArgumentException ex) {
            this.imageLoaded = false; 
        }
	}

}
