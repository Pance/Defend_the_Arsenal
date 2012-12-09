package slick.Defend;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnvironmentImpl implements Environment{
	private final double GRAVITY = 3.0f;
	private int width;
	private int height;
	private int groundHeight;
	private Image backgroundImage;
	private Image groundImage;
	private ArrayList<Enemy> enemies;
	
	public EnvironmentImpl(int width, int height, int groundHeight, ArrayList<Enemy> enemies){
		this.width = width;
		this.height = height;
		this.groundHeight = groundHeight;
		this.enemies = enemies;
	}
	
	public void initImages() throws SlickException {
		//backgroundImage = new Image("resources/background.jpeg").getScaledCopy(width, height);
		backgroundImage = new Image("resources/bg_ground_with_house.png").getScaledCopy(width, height);
		groundImage = new Image("resources/ground.png");
		groundImage = groundImage.getScaledCopy(width, groundImage.getHeight());
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
}