package slick.spike_game;

import org.newdawn.slick.Image;

public class Player {
	
	static final float IMAGE_SCALE = 3.0f; 
	
	private int x_location;
	private int y_location;
	private Image image;
	
	public Player(int x, int y, Image i) {
		image = i.getScaledCopy(IMAGE_SCALE);
		x_location = x;
		y_location = y;
	}
	
	public void draw(){
		image.draw(x_location, y_location);
	}
	
	public void changeX(int x_diff) { x_location+= x_diff; }
	public void changeY(int y_diff) { y_location+= y_diff; }
}
