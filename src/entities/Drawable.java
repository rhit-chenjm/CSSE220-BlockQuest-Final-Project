package entities;

import java.awt.Graphics2D;

/**
 * Ensures that all classes implementing Drawable have the draw() method.
 * 
 * @author Katie Henthorn
 */
public interface Drawable {

	public void drawOn(Graphics2D g2);
	
}
