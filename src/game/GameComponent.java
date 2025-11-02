package game;
import java.awt.Color;
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

import entities.Platform;
import platforms.Entity;
import platforms.Collectable;
import platforms.Enemy;
import platforms.Player;

public class GameComponent extends JComponent {
	// Here is the game state. In a bigger game, this would live
	// in another class like Level.
	private int numTicks;

	
//	private List<AbstractDrop> drops = new ArrayList<>();
	private List<Entity> enemies = new ArrayList<>();
	
	//this gets stored in the list above but easier to access directly since there is one of them
	//than to have to look through an find it

	private Player player;

	
	private List<Platform> platforms = new ArrayList<>();
	private Platform testPlatform;
	private Platform lowTestPlatform;

	
	
	private static final double DAMAGE_DROPS_PERC = 0.8;
	private static final double HEALING_DROPS_PERC = 0.18;


	private List<Collectable> collectables = new ArrayList<>();
	private Collectable testHighCollectable;
	private Collectable testLowCollectable;
	
	public GameComponent() {
		
		this.testPlatform = new Platform(30, 200, 200, 20);
		this.lowTestPlatform = new Platform(300, 400, 300, 20);
		this.platforms.add(this.testPlatform);
		this.platforms.add(this.lowTestPlatform);
		
//		this.testHighCollectable = new Collectable 
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


		for (Entity enemy : this.enemies) {
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
		// Each is big enough to be in a helper method.
		updateRaindrops();
		updatePlatforms();
		handleCollisions();
		updatePlayer();
		this.numTicks++;
	}

	private void handleCollisions() {
		List<GameObject> allObjects = new ArrayList<>();
		
		
		for (Entity e: enemies) {
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
//		this.player.checkForEnemyCollision(enemies);

		
		
		
		
		
		
		List<GameObject> shouldRemove = new ArrayList<>();
		
		for(GameObject object: allObjects){
			if(object.shouldRemove()){
				shouldRemove.add(object);
			}
		}

	}

	private void updateRaindrops() {
//		double rand = Math.random();
//		if (rand < DAMAGE_DROPS_PERC) {
//			this.drops.add(new DamagingDrop(this.getWidth(), this));
//		} else if (rand < DAMAGE_DROPS_PERC + HEALING_DROPS_PERC) {
//			this.drops.add(new HealingDrop(this.getWidth(), this));
//		} else {
//			this.drops.add(new InvincibilityDrop(this.getWidth(), this));
//		}
//		for (AbstractDrop drop : this.drops) {
//			drop.update();
//		}
	}

	private void updatePlatforms() {
		for (Entity platform : this.enemies) {
			platform.update();
		}
	}
	private void updatePlayer() {
		this.player.update();
	}
		
	public void toggleBoxDirection() {
		this.player.reverseDirection();
	}
	
	public void setPlayerXSpeed(double c) {
		this.player.setXSpeed(c);
	}
	
	public void setIsCollidingWithPlatform(boolean b1) {
		this.player.setTouchingPlatform(b1);
		System.out.println(b1);
		}
	

	//e

	public void setPlayerYSpeed(int i) {
		// TODO Auto-generated method stub
		this.player.addYPos(-4);
		this.player.setYSpeed(i);
	}
	public void addPlayerYSpeed(double i) {
		this.player.addYSpeed(i);
	}
}
