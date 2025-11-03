package blocks;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameComponent;

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
