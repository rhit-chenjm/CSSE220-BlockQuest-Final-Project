package drops;

import java.awt.Color;
import java.awt.geom.Rectangle2D.Double;

import entities.Platform;
import game.GameComponent;
import platforms.Entity;
/**
 *  This class represents a blue drop that when
 *  hitting a bouncing platform should add a raindrop to it
 * 
 */
public class DamagingDrop extends AbstractDrop{

	private static final double SIZE = 5.0;
	private static final Color COLOR = Color.BLUE;
	
	public DamagingDrop(Double box, GameComponent component) {
		super(box, component, COLOR, SIZE);
	}
	
	public DamagingDrop(int width, GameComponent component) {
		super(width, component, COLOR, SIZE);
	}

	public void collideWithPlatform( Entity other) {
		other.addDrop();
		markToRemove();
	}

	@Override
	public void collideWithPlatform(Platform platform) {
		// TODO Auto-generated method stub
		
	}

}
