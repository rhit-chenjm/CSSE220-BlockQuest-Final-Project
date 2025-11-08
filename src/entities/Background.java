package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background implements Drawable {

	// Background image
    private BufferedImage image;
    private boolean imageLoaded = false;
    private int levelID;
    
    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;
    
    public Background(int levelID) {
    	this.levelID = levelID;
    }
	
	@Override
	public void drawOn(Graphics2D g2) {
		switch (levelID) {
		case 1:

			try {
				// Percopo Background
				this.image = ImageIO.read(Platform.class.getResource("Level1Background.jpg"));
	            imageLoaded = (image != null);
	        } catch (IOException | IllegalArgumentException ex) {
	            imageLoaded = false; 
	        }
			
			break;
			
		case 2:
			
			try {
				// Fall Background
				this.image = ImageIO.read(Platform.class.getResource("FallLevelBackground.jpg"));
	            imageLoaded = (image != null);
	        } catch (IOException | IllegalArgumentException ex) {
	            imageLoaded = false; 
	        }
			
			break;
			
			
		case 3:
			
			try {
				// Winter Background
				this.image = ImageIO.read(Platform.class.getResource("WinterLevelBackground.jpg"));
	            imageLoaded = (image != null);
	        } catch (IOException | IllegalArgumentException ex) {
	            imageLoaded = false; 
	        }
			
			break;
			
			
		case 4:
			
			try {
				// Winter Background
				this.image = ImageIO.read(Platform.class.getResource("Level4Background.jpg"));
	            imageLoaded = (image != null);
	        } catch (IOException | IllegalArgumentException ex) {
	            imageLoaded = false; 
	        }
			
			break;
			
		case 6:
			
			try {
				// Winter Background
				this.image = ImageIO.read(Platform.class.getResource("GameOver.jpg"));
	            imageLoaded = (image != null);
	        } catch (IOException | IllegalArgumentException ex) {
	            imageLoaded = false; 
	        }
			
			break;
			
		default:
			throw new IllegalArgumentException("No level declared.");
		}

		if (imageLoaded) {
						
			// 500 x 500 panel
    		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
    	} else {
    	    g2.setColor(Color.PINK);
    		g2.fill(new Rectangle(0, 0, WIDTH, HEIGHT));
    	}
	}
}
