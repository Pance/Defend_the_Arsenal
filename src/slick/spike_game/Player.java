package slick.spike_game;

import org.newdawn.slick.Image;

public class Player {
	private int x_location;
	private int y_location;
	private Image image;
	
	public Player(int x, int y, Image i) {
		image = i;
		x_location = x;
		y_location = y;
	}
	
	public void draw(){}
}
