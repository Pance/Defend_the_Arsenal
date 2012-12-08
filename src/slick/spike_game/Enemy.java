package slick.spike_game;

import java.util.Date;
import java.util.Random;

public class Enemy {
	private int x;
	private int y;
	private int direction;
	private Random brain = new Random();
	private long lastThink;
	private int thinkInterval = 5;
	
	public Enemy() {
		x = 100;
		y = 100;
		direction = brain.nextInt() % 8;
		lastThink = new Date().getTime();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void think() {
		// if the time since last
		long now = new Date().getTime();
		int secondsSinceLastThink = (int)(now-lastThink)/1000;
		
		if( secondsSinceLastThink < (brain.nextInt()%thinkInterval) )
			return;
		direction = brain.nextInt() % 8;
		
		switch(direction) {
		case 0:
			x++;
			y++;
			break;
		case 1:
			x++;
			y--;
			break;
		case 2:
			x--;
			y++;
			break;
		case 3:
			x--;
			y--;
			break;
		case 4:
			x++;
			break;
		case 5:
			x--;
			break;
		case 6:
			y++;
			break;
		case 7:
			y--;
			break;
		default:
			x++;
			y++;
		}
		
		//keep this guy on the screen
		if(x > 800)
			x = 750;
		if(x < 0)
			x = 0;
		if(y > 600)
			y = 550;
		if(y < 0)
			y = 0;
		
		lastThink = new Date().getTime();
	}
}
