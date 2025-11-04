package entities;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import blocks.AbstractBlock;
import blocks.Collectable;
import blocks.Enemy;
import blocks.Goose;
import blocks.Player;
import game.GameComponent;

/*
 * @author Kathryn Jonas
 */



public class Level extends JComponent{
	
	
	private List<Enemy> enemies = new ArrayList<>();
	private Player player;
	private List<Platform> platforms = new ArrayList<>();
	private Platform testPlatform;
	private Platform lowTestPlatform;
	private List<Collectable> collectables = new ArrayList<>();
	private Collectable testHighCollectable;
	private Collectable testLowCollectable;
	
	
	private Background background1;
	
	private int levelNumber = 1;
	


	
	public Level(int levelNumber, GameComponent g) {
		
		switch (levelNumber) {
		
		case 1: {
			levelOne(g);
			break;
		}
		
		default: {
			throw new IllegalArgumentException("Unexpected value: " + levelNumber);		
			
		}
		}
		
		
	}
	
	
	private void levelOne(GameComponent g) {
		this.background1 = new Background(1);
		this.testPlatform = new Platform(30, 200, 200, 20);
		this.lowTestPlatform = new Platform(250, 400, 300, 20);
		this.platforms.add(this.testPlatform);
		this.platforms.add(this.lowTestPlatform);
	 	this.testLowCollectable = new Collectable(300, 100, 0, 0, g);
		this.testHighCollectable = new Collectable (100, 80, 0, 0, g);
		this.collectables.add(this.testLowCollectable);
		this.collectables.add(this.testHighCollectable);
		this.player =  new Player(10, 0, g);
		this.enemies.add(new Goose(200, 100, 5, 0, g));
		this.enemies.add(new Goose(30,  100, 0, 5, g));
		this.enemies.add(new Goose(130, 150, 0, 5, g));
		this.enemies.add(new Goose(230, 200, 0, 5, g));
	
	}
	
	public void drawOn(Graphics2D g2) {
		background1.drawOn(g2);
		for (Enemy enemy : this.enemies) {
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
	
	
	public Player getPlayer() {
		return this.player;
	}
	
	public List<Enemy> getEnemies(){
		return this.enemies;
	}
	 
	public List<Platform> getPlatforms(){
		return this.platforms;
	}
	
	public List<Collectable> getCollectables(){
		return this.collectables;
	}
	

	

}
