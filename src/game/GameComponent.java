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

import entities.Background;
import entities.Level;
//import drops.AbstractDrop;
//import drops.DamagingDrop;
//import drops.HealingDrop;
//import drops.InvincibilityDrop;
import entities.Platform;
//import blocks.Entity;
import blocks.Enemy;
import blocks.Goose;
import blocks.Player;

import blocks.Collectable;
import blocks.DollarBill;
import blocks.AbstractBlock;
import blocks.Coin;
import blocks.Player;
import entities.Platform;

/**
 * stores objects and handles some interactions between them
 */
public class GameComponent extends JComponent {
	//state of the game
	private int numTicks;
	private int compareTicks;
	private List<Enemy> enemies;
	private Player player;
	private List<Platform> platforms;
	private List<Collectable> collectables;
	
	
	// holds things to be placed on the screen
	private Background background1;
	
	private int currentLevel = 1;
	private Level level;

	public GameComponent() {
		
		this.level = new Level(currentLevel, this);
		setLevel(currentLevel);


	}
	
	public void setLevel(int levelNumber) {
		this.currentLevel = levelNumber;
		this.level = new Level(levelNumber, this);
		
		this.player = level.getPlayer();
		this.enemies = level.getEnemies();
		this.platforms = level.getPlatforms();
		this.collectables = level.getCollectables();
		
		repaint();
	}

	public void drawScreen() {
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		level.drawOn(g2);
		
	}

	public void updateState() {
		updateEnemies();
		handleCollisions();
		updatePlayer();
		handleInvincibilityframes();
		this.numTicks++;
	}
	public void handleInvincibilityframes() {
		if(!this.player.getInvincibility()) {
			this.compareTicks++;
		}
		else{
			if(this.numTicks- this.compareTicks > 120) {
				this.player.setIsInvincible(false);
				this.compareTicks = this.numTicks;
			}
		}
	}
	private void handleCollisions() {
		List<GameObject> allObjects = new ArrayList<>();
		allObjects.addAll(collectables);
		
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
		
		// Player and Collectable interaction
		this.player.checkForCollectableCollision(collectables);
		
		// handles player/enemy collision
		this.player.checkForEnemyCollision(enemies);

		// removes objects when certain conditions are met
		List<GameObject> shouldRemove = new ArrayList<>();
		
		for(GameObject object: allObjects){
			if(object.shouldRemove()){
				shouldRemove.add(object);
				// moves deleted object offscreen, may only work with collectables
				object.x = -500;
				object.y = -500;
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

	public void playerCanCollect(boolean b) {
		this.player.isHoldingDown = b;
	}
} 
