package game;
import java.awt.Dimension;
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
//import hudScore.HudModel;
import hudScore.HudPanel;
//import hudScore.HudViewer;
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

	
	private HudPanel hudPanel = new HudPanel();
	
	// holds things to be placed on the screen
	private Background background1;
	
	private int currentLevel = 1;
	private Level level;

	public GameComponent(HudPanel h1) {
		
		
		this.level = new Level(currentLevel, this, 3);
		setLevel(currentLevel, 3);
//		this.viewer = v1;
//		this.model = m1;
		this.hudPanel = h1;

	}
	
	public void setLevel(int levelNumber, int lives) {
		this.currentLevel = levelNumber;
		this.level = new Level(levelNumber, this, level.getPlayer().getLives());
		if(levelNumber > 0 && levelNumber < 4) {
			this.setPlayer(level.getPlayer());
			this.enemies = level.getEnemies();
			this.platforms = level.getPlatforms();
			this.collectables = level.getCollectables();
		}
		repaint();


	}

	public void drawScreen() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		level.drawOn(g2);
		hudPanel.drawHud(g2);
		
	}

	public void updateState() {
		updateText();
		updateEnemies();
		handleInvincibilityframes();
		handleGameOver(); 
		handlelevelChanges();
		handleHudChanges();
		if(this.level.getLevelNumber() < 5) {
			updatePlayer();
			handleCollisions();

		}
		
		this.numTicks++;
	}
    private void updateText() {
//    	this.model.setScore(player.getScore());
//    	this.viewer.refresh(model);
    }
	public void handleInvincibilityframes() {
		if(!this.getPlayer().getInvincibility()) {
			this.compareTicks++;
		}
		else{
			if(this.numTicks- this.compareTicks > 120) {
				this.getPlayer().setIsInvincible(false);
				this.compareTicks = this.numTicks;
			}
		}
	}
	private void handleCollisions() {
		List<GameObject> allObjects = new ArrayList<>();
		allObjects.addAll(collectables);
		
		// Prevents enemies from falling through platforms
		for (AbstractBlock e: enemies) {
			e.checkForPlatformCollision( platforms );
		}
		
		// Prevents player from falling through platforms
		this.getPlayer().checkForPlatformCollision( platforms );
		
		// Player and Collectable interaction
		this.getPlayer().checkForCollectableCollision(collectables);
		
		// handles player/enemy collision
		this.getPlayer().checkForEnemyCollision(enemies);

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
		this.getPlayer().update();
	}
		
	public void togglePlayerDirection() {
		this.getPlayer().reverseDirection();
	}
	
	public void setPlayerXSpeed(double c) {
		this.getPlayer().setXSpeed(c);
	}
	
	public void setIsCollidingWithPlatform(boolean b1) {
		this.getPlayer().setTouchingPlatform(b1);
		System.out.println(b1);
		}
	
	public void setPlayerYSpeed(int i) {
		this.getPlayer().addYPos(-4);
		this.getPlayer().setYSpeed(i);
	}
	public void addPlayerYSpeed(double i) {
		this.getPlayer().addYSpeed(i);
	}

	public void playerCanCollect(boolean b) {
		this.getPlayer().isHoldingDown = b;
	}
	public void handleGameOver() {
		if(this.getPlayer().returnLives() < 1) {
			setLevel(6, 0);
		}
	}
	public void handlelevelChanges() {
		boolean isReady = true;
		for(Collectable c : collectables) {
			if(c.isAlive()) {
				
				isReady = false;
			}
			
		}
		if(this.level.getLevelNumber() > 4 || this.level.getLevelNumber() < 1) {
			isReady = false;
		}

		if(isReady) {
			setLevel(level.getLevelNumber() + 1, this.level.getPlayer().getLives());
		}


	}

	public void handleHudChanges() {
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void pressButton() {
		setLevel(1, 3);
	}
	
	public int getLevel() {
		return this.level.getLevelNumber();
	}
} 
