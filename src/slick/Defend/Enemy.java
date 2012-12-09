package slick.Defend;

import org.newdawn.slick.Image;

import slick.Defend.Player.Facing;

public class Enemy {
	private final double FALL_SPEED = 40.0f; // pixels per second
	
	private double x;
	private double y;
	private double speed = 20.0f; // pix/second
	private int width = 200;
	private int groundHeight;
	private Image image;
	
	private Player player;
	
	public Enemy() {
		x = 0;
		y = 250;
		int groundHeight = (int)y;
	}
	
	public Enemy(Player player) {
		this();
		this.player = player;		
	}
	
	public Enemy(Player player, Image img) {
		this(player);
		image = img;
		width = img.getWidth();
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public boolean collides(int x_position) {
		if(this.x < x_position && x_position < (this.x+width) )
			return true;
		return false;
	}
	
	public void knockback(Facing direction) {
		if(direction == Facing.RIGHT)
			x+=200;
		if(direction == Facing.LEFT)
			x-=200;
	}
	
	public void fling(Facing direction) {
		if(direction == Facing.RIGHT) {
			y-=300;
			x+=200;
		}
		if(direction == Facing.LEFT) {
			y-=300;
			x-=200;
		}
	}
	
	public void think(long millisSinceLastThink) {
		if( y < groundHeight+image.getHeight() ) {
			y+=(FALL_SPEED * ((double)millisSinceLastThink * 0.001f));
		} else {
			// if we aren't falling, we just want to move towards the player
			//x_location+=(MAX_RUN_SPEED * (millisSinceLastStep * 0.001f));
			if( x > player.getX())
				x-=(speed * ((double)millisSinceLastThink * 0.001f));
			if( x < player.getX())
				x+=(speed * ((double)millisSinceLastThink * 0.001f));
		}
	}
}
