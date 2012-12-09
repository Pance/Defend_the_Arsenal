package slick.spike_game;

import java.util.Date;
import java.util.Random;

public class Enemy {
	private int x;
	private int y;
	
	private Player player;
	
	public Enemy() {
		x = 100;
		y = 100;
	}
	
	public Enemy(Player player) {
		this();
		this.player = player;		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void think() {
		
		// we just want to move towards the player
		if( x > player.getX())
			x--;
		if( x < player.getX())
			x++;
		
		//keep this guy on the screen
		if(x > 800)
			x = 750;
		if(x < 0)
			x = 0;
		if(y > 600)
			y = 550;
		if(y < 0)
			y = 0;
	}
}
