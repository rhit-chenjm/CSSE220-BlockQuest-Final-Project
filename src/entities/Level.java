package entities;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import blocks.AbstractBlock;
import blocks.Coin;
import blocks.Collectable;
import blocks.DollarBill;
import blocks.Enemy;
import blocks.Goose;
import blocks.Player;
import game.GameComponent;

/**
 * @author Kathryn Jonas
 * @function 
 */
public class Level extends JComponent{
	
	private List<Enemy> enemies = new ArrayList<>();
	private Player player;
	private List<Platform> platforms = new ArrayList<>();
	private Platform testPlatform;
	private Platform lowTestPlatform;
	private Platform floor;
	private List<Collectable> collectables = new ArrayList<>();
	private Collectable testHighCollectable;
	private Collectable testLowCollectable;
	private int levelNumber;

	
	private Background background;
	
	
	private JLabel label = new JLabel();
	private int numBalls = 0;
	private int numStrikes = 0;
	
	public void updateLabel(int numBalls, int numStrikes) {
		this.label.setText("<html>Balls: " + numBalls + "<br />Strikes: " + numStrikes + "</HTML>");
	}

	public Level(int levelNumber, GameComponent g) {
		this.levelNumber = levelNumber;
		switch (levelNumber) { 
		case 0: {
			levelZero(g);
			break;
		}
		
		case 1: {
			levelOne(g);
			break;
		}
		
		case 2: {
			levelTwo(g);
			break;
		}
		
		case 3: {
			levelThree(g);
			break;
		}
		
		case 4: {
			levelFour(g);
			break;
		}
		
		case 5: {
			gameOver(g);
			break;
		}
		
		default: {
			throw new IllegalArgumentException("Unexpected value: " + levelNumber);		
			
			}
		}
	}
	
	public int getLevelNumber() {
		return this.levelNumber;
	}
	private void levelZero(GameComponent g) {
		
	}
	private void levelOne(GameComponent g) {
		this.background = new Background(1);
		this.testPlatform = new Platform(30, 200, 200, 20);
		this.lowTestPlatform = new Platform(250, 400, 300, 20);
		this.floor = new Platform(-10, 750, 1020, 50);
		this.platforms.add(this.testPlatform);
		this.platforms.add(this.lowTestPlatform);
		this.platforms.add(this.floor);
	 	this.testLowCollectable = new Coin(300, 100, 0, 0, g);
		this.testHighCollectable = new DollarBill(100, 80, 0, 0, g);
		this.collectables.add(this.testLowCollectable);
		this.collectables.add(this.testHighCollectable);
		this.enemies.add(new Goose(200, 100, 5, 0, g));
		this.enemies.add(new Goose(30,  100, 0, 5, g));
		this.enemies.add(new Goose(130, 150, 0, 5, g));
		this.enemies.add(new Goose(230, 200, 0, 5, g));
		this.player =  new Player(10, 0, g);
	
	}
	
	private void levelTwo(GameComponent g) {
		this.background = new Background(2);
		this.testPlatform = new Platform(400, 200, 300, 20);
		this.lowTestPlatform = new Platform(100, 200, 300, 20);
		this.floor = new Platform(-10, 750, 1020, 50);
		this.platforms.add(this.testPlatform);
		this.platforms.add(this.floor);
	 	this.testLowCollectable = new Coin(100, 100, 0, 0, g);
		this.testHighCollectable = new DollarBill(300, 80, 0, 0, g);
		this.collectables.add(this.testLowCollectable);
		this.collectables.add(this.testHighCollectable);
		this.player =  new Player(10, 0, g);
		this.enemies.add(new Goose(200, 100, 5, 0, g));

	}
	
	private void levelThree(GameComponent g) {
		//background
		this.background = new Background(3);
		//platforms
		this.platforms.add(new Platform(300, 600, 900, 20));
		this.platforms.add(new Platform(50, 50, 100, 20));
		this.platforms.add(new Platform(600, 100, 600, 20));
		this.platforms.add(new Platform(100, 200, 300, 20));
		this.platforms.add(new Platform(-10, 750, 1020, 50));
		//collectables
		this.collectables.add(new Coin(350, 550, 0, 0, g));
		this.collectables.add(new Coin(500, 600, 0, 0, g));
		this.collectables.add(new DollarBill(150, 300, 0, 0, g));
		//enemies
		this.enemies.add(new Goose(100, 200, 5, 0, g));
		this.enemies.add(new Goose(500, 600, 0, 5, g));
		//player
		this.player = new Player(10, 0, g);
	
	}
	
	private void levelFour(GameComponent g) {
		//background
		this.background = new Background(4);
		//platforms
		this.platforms.add(new Platform(675, 400, 800, 20));
		this.platforms.add(new Platform(200, 100, 300, 20));
		this.platforms.add(new Platform(50, 600, 900, 20));
		//collectables
		this.collectables.add(new Coin(500, 500, 0, 0, g));
		
		//enemies
		this.enemies.add(new Goose(400, 200, 5, 0, g));
		
		//player
		this.player = new Player(10, 0, g);
	}
	
	private void gameOver(GameComponent g) {
//		//player
//		this.player = new Player(10, 0, g);
//		JFrame frame = new JFrame();
//
//		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 6));
//		JButton addBall = new JButton("Add Ball");
//		JButton addStrike = new JButton("Add Strike");
//
//		frame.add(label, BorderLayout.CENTER);
//		frame.add(panel, BorderLayout.SOUTH);
//
//		panel.setLayout(new FlowLayout());
//		panel.setPreferredSize(new Dimension(300, 60));
//
//		addBall.addActionListener(e -> {
//			if (numBalls < 3) {
//				numBalls++;
//			} else {
//				numBalls = 0;
//				numStrikes = 0;
//			}
//
//			this.updateLabel(numBalls, numStrikes);
//		});
//
//		addStrike.addActionListener(e -> {
//			if (numStrikes < 2) {
//				numStrikes++;
//			} else {
//				numBalls = 0;
//				numStrikes = 0;
//			}
//
//			this.updateLabel(numBalls, numStrikes);
//		});
//
//		panel.add(addBall);
//		panel.add(addStrike);
//
//		// The following line is given to show you how to use the given method:
//		updateLabel(0, 0);
//
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
	}
	
	public void drawOn(Graphics2D g2) {
		background.drawOn(g2);
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
