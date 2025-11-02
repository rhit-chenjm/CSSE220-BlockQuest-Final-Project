package entities;

import java.awt.Graphics;
import java.util.ArrayList;



public class Level {
	
	/*
	 * Player player = new player;
	 * ArrayList<Enemy> enemies;
	 * ArrayList<Platform> platforms;
	 * ArrayList<Collectable> collectables;
	 */
	
// constructor for level when other classes implemented
	
//	public Level(Player player, ArrayList<Enemy> enemies, 
//			ArrayList<Platform> platform, ArrayList<Collectable> collectables) {
//		
//		this.player = player;
//		this.enemies = enemies;
//		this.platform = platform; 
//		this.collectables = collectables;
//
//	}

	
	public Level(int levelNumber, Graphics g2) {
		
	}
	
	
	public void buildLevel() {
		int levelNumber = 0;

		
	switch (levelNumber) {
	
	// Level 1
	case 1: {	
		 
		
	}
	
	//Level 2
	
	case 2: {
		
	}
	
	//Default: Start screen?
	default:
		throw new IllegalArgumentException("Unexpected value: " + levelNumber);
	}
	
	}
	
	
	

}
