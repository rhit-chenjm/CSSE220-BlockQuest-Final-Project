package game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import drops.AbstractDrop;
import drops.DamagingDrop;
import drops.HealingDrop;
import drops.InvincibilityDrop;
import entities.Platform;
import platforms.AbstractPlatform;
import platforms.BouncingPlatform;
import platforms.Player;

public class GameComponent extends JComponent {
	// Here is the game state. In a bigger game, this would live
	// in another class like Level.
	private int numTicks;

	// There are two types of objects with 6 subtypes
	private List<AbstractDrop> drops = new ArrayList<>();
	private List<AbstractPlatform> platforms = new ArrayList<>();
	
	//this gets stored in the list above but easier to access directly since there is one of them
	//than to have to look through an find it
	private Player player;

	private Platform testPlatform;
	
	private static final double DAMAGE_DROPS_PERC = 0.8;
	private static final double HEALING_DROPS_PERC = 0.18;


	public GameComponent() {

		this.testPlatform = new Platform(30, 200, 200, 20);
		
		this.player =  new Player(10, 0, this);
	
		
		this.platforms.add(new BouncingPlatform(200, 100, 5, 0, this));
		this.platforms.add(new BouncingPlatform(30,  100, 0, 5, this));
		this.platforms.add(new BouncingPlatform(130, 150, 0, 5, this));
		this.platforms.add(new BouncingPlatform(230, 200, 0, 5, this));
	}

	public void drawScreen() {
		this.repaint();
//		System.out.println("Tick " + this.numTicks);
//		System.out.println("There are " + this.drops.size() + " drops.");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (AbstractDrop drop : this.drops) {
			drop.drawOn(g2);
		}
		for (AbstractPlatform platform : this.platforms) {
			platform.drawOn(g2);
		}
		this.player.drawOn(g2);
		this.testPlatform.drawOn(g2);
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
		allObjects.addAll( this.drops);
		allObjects.addAll( this.platforms);
		
		//drop and platform collisions
		for(AbstractDrop r: drops){
			for(AbstractPlatform p: platforms){
				if( !r.shouldRemove() && !p.shouldRemove()) {
					if (r.overlaps(p)) {
						r.collideWithPlatform(p);
					}
				}
			}
		}
		
		for( AbstractPlatform p1: platforms){
			for( AbstractPlatform p2: platforms){
				if (p1 != p2) {
					if (p1.overlaps(p2)) {
						p1.collideWithPlatform(p2);
					}
				}
			}
		}
		
		
		List<GameObject> shouldRemove = new ArrayList<>();
		
		for(GameObject object: allObjects){
			if(object.shouldRemove()){
				shouldRemove.add(object);
			}
		}
		
		for(GameObject object: shouldRemove){
			this.drops.remove(object);
			this.platforms.remove(object);
			object.onRemove();
		}
	}

	private void updateRaindrops() {
		double rand = Math.random();
		if (rand < DAMAGE_DROPS_PERC) {
			this.drops.add(new DamagingDrop(this.getWidth(), this));
		} else if (rand < DAMAGE_DROPS_PERC + HEALING_DROPS_PERC) {
			this.drops.add(new HealingDrop(this.getWidth(), this));
		} else {
			this.drops.add(new InvincibilityDrop(this.getWidth(), this));
		}
		for (AbstractDrop drop : this.drops) {
			drop.update();
		}
	}

	private void updatePlatforms() {
		for (AbstractPlatform platform : this.platforms) {
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

	public void createRainDrop(Double boundingBox) {
		this.drops.add(new DamagingDrop(boundingBox, this));
	}
	//e

	public void setPlayerYSpeed(int i) {
		// TODO Auto-generated method stub
		this.player.setYSpeed(i);
	}
}
