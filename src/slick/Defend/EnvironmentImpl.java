package slick.Defend;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import slick.Defend.Player.Facing;

public class EnvironmentImpl implements Environment{
	private final double GRAVITY = 3.0f;
	private int width;
	private int height;
	private int groundHeight;
	private Image backgroundImage;
	private Image groundImage;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bomb> bombs;
	
	public EnvironmentImpl(int width, int height, int groundHeight, ArrayList<Enemy> enemies) throws SlickException{
		this.width = width;
		this.height = height;
		this.groundHeight = groundHeight;
		this.enemies = enemies;
		initImages();
	}
	
	public void initImages() throws SlickException {
		//backgroundImage = new Image("resources/background.jpeg").getScaledCopy(width, height);
		backgroundImage = new Image("resources/bg_ground_with_house.png").getScaledCopy(width, height);
		groundImage = new Image("resources/gate.png");
		groundImage = groundImage.getScaledCopy(width, groundImage.getHeight()-100);
	}
	
	public int getGroundHeight() {
		return groundHeight;
	}
	
	public void drawBackground() {
		backgroundImage.draw(0,0);
	}
	
	public void drawGround() {
		groundImage.draw(0, groundHeight);
	}
	
	public void draw() {
		drawBackground();
		drawGround();
	}
	
	public double getGravity() {
		return GRAVITY;
	}
	
	private Enemy getClosestEnemy(int x_position, Facing facing) {
		Enemy closest = null;
		for(Enemy e : enemies) {
			if(e.collides(x_position)) {
				if(closest == null)
					closest = e;
				else {
					if(facing == Facing.RIGHT) {
						if(e.getX() < closest.getX() && e.getX() > x_position)
							closest = e;
					}
					if(facing == Facing.LEFT) {
						if(e.getX() > closest.getX() && e.getX() < x_position)
							closest = e;
					}
				}
			}
		}
		return closest;
	}
	
	public void slap(int x_position, Facing facing) {
		Enemy closestEnemy = getClosestEnemy(x_position, facing);
		if(closestEnemy != null)
			closestEnemy.knockback(facing);
		/*
		for(Enemy e : enemies) {
			if(e.collides(x_position)) {
				e.knockback(facing);
				return;
			}
		}*/
	}
	
	public void launch(int x_position, Facing facing) {
		Enemy closestEnemy = getClosestEnemy(x_position, facing);
		if(closestEnemy != null)
			closestEnemy.fling(facing);
		/*
		for(Enemy e: enemies) {
			if(e.collides(x_position)) {
				e.fling(facing);
				return;
			}
		}
		*/
	}

	public void addBomb(Bomb b) {
		bombs.add(b);
	}
}