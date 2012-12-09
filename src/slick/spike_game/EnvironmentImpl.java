package slick.spike_game;

import org.newdawn.slick.Image;

public class EnvironmentImpl implements Environment{
	private final double GRAVITY = 3.0f;
	private int width;
	private int height;
	private int groundHeight;
	private Image backgroundImage;
	private Image groundImage;
	
	public EnvironmentImpl(int width, int height, Image image, int groundHeight, Image groundImage){
		this.width = width;
		this.height = height;
		this.groundHeight = groundHeight;
		backgroundImage = image.getScaledCopy(width, height);
		this.groundImage = groundImage.getScaledCopy(width, groundImage.getHeight());
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