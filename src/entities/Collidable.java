package entities;

import java.awt.Rectangle;

/**
 * Ensures that all classes implementing Collidable have collision logic for their bounding boxes.
 * 
 * @author Katie Henthorn
 */
public interface Collidable {

	public Rectangle getBoundingBox(); // Return a new Rectangle that overlaps with the boundaries of an object.
	
	public boolean collidesWith(Rectangle boundingBox); // Checks if this bounding box overlaps with another.
	
}