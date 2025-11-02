package game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import blocks.Collectable;
import blocks.Enemy;
import blocks.AbstractBlock;
import blocks.Player;
import entities.Platform;

/**
 * stores objects and handles some interactions between them
 */
public class GameComponent extends JComponent {
	//state of the game
	private int numTicks;

	// creating test fields
	private List<AbstractBlock> enemies = new ArrayList<>();
	private Player player;
	private List<Platform> platforms = new ArrayList<>();
	private Platform testPlatform;
	private Platform lowTestPlatform;
	private List<Collectable> collectables = new ArrayList<>();
	private Collectable testHighCollectable;
	private Collectable testLowCollectable;
	
	// holds things to be placed on the screen
	public GameComponent() {
		
		this.testPlatform = new Platform(30, 200, 200, 20);
		this.lowTestPlatform = new Platform(300, 400, 300, 20);
		this.platforms.add(this.testPlatform);
		this.platforms.add(this.lowTestPlatform);
		this.testLowCollectable = new Collectable(300, 100, 0, 0, this);
		this.testHighCollectable = new Collectable (100, 80, 0, 0, this);
		this.collectables.add(this.testLowCollectable);
		this.collectables.add(this.testHighCollectable);
		this.player =  new Player(10, 0, this);
		this.enemies.add(new Enemy(200, 100, 5, 0, this));
		this.enemies.add(new Enemy(30,  100, 0, 5, this));
		this.enemies.add(new Enemy(130, 150, 0, 5, this));
		this.enemies.add(new Enemy(230, 200, 0, 5, this));

	}

	public void drawScreen() {
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		for (AbstractBlock enemy : this.enemies) {
			enemy.drawOn(g2);
		}
		this.player.drawOn(g2);
		for (Platform platform : this.platforms) {
			platform.drawOn(g2);
		}
		for (Collectable collectable : this.collectables) {
			collectable.drawOn(g2);
		}
	}

	public void updateState() {
		updateEnemies();
		handleCollisions();
		updatePlayer();
		this.numTicks++;
	}

	private void handleCollisions() {
		List<GameObject> allObjects = new ArrayList<>();
		
		// Prevents enemies from falling through platforms
		for (AbstractBlock e: enemies) {
			boolean eChangedGravity = false;
			for (Platform p: platforms) {
				if (!eChangedGravity) {
					if (e.overlaps(p)) {
						e.collideWithPlatform(p);
						e.gravity = 0;
						eChangedGravity = true;
					}
					else e.gravity = 1;
				}
			}
		}
		// Prevents player from falling through platforms
		boolean pChangedGravity = false;
		for (entities.Platform p: platforms) {
			if (!pChangedGravity) {
				if (player.overlaps(p)){
					player.collideWithPlatform(p);
					player.gravity = 0;
					pChangedGravity = true;
				}
				else player.gravity = 1;
			}
		}
		
		// handles player/enemy collision
//		this.player.checkForEnemyCollision(enemies);

		// removes objects when certain conditions are met
		List<GameObject> shouldRemove = new ArrayList<>();
		
		for(GameObject object: allObjects){
			if(object.shouldRemove()){
				shouldRemove.add(object);
			}
		}

	}

	private void updateEnemies() {
		for (AbstractBlock enemy : this.enemies) {
			enemy.update();
		}
	}
	private void updatePlayer() {
		this.player.update();
	}
		
	public void togglePlayerDirection() {
		this.player.reverseDirection();
	}
	
	public void setPlayerXSpeed(double c) {
		this.player.setXSpeed(c);
	}
	
	public void setIsCollidingWithPlatform(boolean b1) {
		this.player.setTouchingPlatform(b1);
		System.out.println(b1);
		}
	
	public void setPlayerYSpeed(int i) {
		this.player.addYPos(-4);
		this.player.setYSpeed(i);
	}
	public void addPlayerYSpeed(double i) {
		this.player.addYSpeed(i);
	}
}
