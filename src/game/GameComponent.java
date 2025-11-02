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
import drops.AbstractDrop;
import drops.DamagingDrop;
import drops.HealingDrop;
import drops.InvincibilityDrop;
import entities.Platform;
import platforms.Entity;
import platforms.Enemy;
import platforms.Player;

public class GameComponent extends JComponent {
	// Here is the game state. In a bigger game, this would live
	// in another class like Level.
	private int numTicks;

	// There are two types of objects with 6 subtypes
	private List<Enemy> enemies = new ArrayList<>();
	
	//this gets stored in the list above but easier to access directly since there is one of them
	//than to have to look through an find it
	private Player player;

	
	private List<Platform> platforms = new ArrayList<>();
	private Platform testPlatform;

	
	
	private static final double DAMAGE_DROPS_PERC = 0.8;
	private static final double HEALING_DROPS_PERC = 0.18;


	public GameComponent() {
		
		this.testPlatform = new Platform(30, 200, 200, 20);
		this.platforms.add(this.testPlatform);
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
//		allObjects.addAll( this.platforms);
//		allObjects.addAll( this.entities);
		
		//drop and platform collisions
//		for(AbstractDrop r: drops){
//			for(Entity p: platforms){
//				if( !r.shouldRemove() && !p.shouldRemove()) {
//					if (r.overlaps(p)) {
//						r.collideWithPlatform(p);
//					}
//				}
//			}
//		}
//		
//		for( Entity p1: platforms){
//			for( Entity p2: platforms){
//				if (p1 != p2) {
//					if (p1.overlaps(p2)) {
//						p1.collideWithPlatform(p2);
//					}
//				}
//			}
//		}
		for (entities.Platform p: platforms) {
			for(Entity e: enemies) {
				if (e.overlaps(p)) {
					e.collideWithPlatform(p);
				}
				else e.gravity = 1;
			}
			if (player.overlaps(p)){
				player.collideWithPlatform(p);
			}
			else player.gravity = 1;
		}
		this.player.checkForEnemyCollision(enemies);

		
		
		
		
		
		
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
