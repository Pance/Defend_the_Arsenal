package slick.spike_game;

import org.newdawn.slick.Image;

public class Player {
	
	private final float IMAGE_SCALE = 3.0f;
	private final double ACCELERATION_RATE = 5.0f; // Increase dx by this amount per second
	private final double MAX_RUN_SPEED = 8.0f; // Maximum speed player can move
	private enum Facing { LEFT, RIGHT };
	
	private double x_location;
	private double y_location;
	private double dx; // The player's current rate of change in x per second
	private double dy; // The player's current rate of change in y per second
	private Facing facing = Facing.RIGHT;
	
	private Image imageRight;
	private Image imageLeft;
	private Environment environment;
	
	public Player(int x, int y, Image right, Image left, Environment e) {
		imageRight = right.getScaledCopy(IMAGE_SCALE);
		imageLeft = left.getScaledCopy(IMAGE_SCALE);
		x_location = x;
		y_location = y;
		environment = e;
	}
	
	public void draw(){
		if(facing == Facing.RIGHT)
			imageRight.draw((int)x_location, (int)y_location);
		else
			imageLeft.draw((int)x_location, (int)y_location);
	}
	
	public void step(long millisSinceLastStep) {
		if(isInAir())
			dy-=environment.getGravity();
		//x_location = x_location + (dx*(millisSinceLastStep/1000));
		//y_location = y_location + (dy/(millisSinceLastStep/1000));
	}
	
	public boolean isInAir() {
		return y_location > environment.getGroundHeight()+1;
	}
	
	public void accelerateLeft(long millisSinceLastStep) {
		/*
		dx -= (ACCELERATION_RATE/(millisSinceLastStep/1000));
		if(-dx > MAX_RUN_SPEED)
			dx = -MAX_RUN_SPEED;
		*/
		x_location-=(MAX_RUN_SPEED * (millisSinceLastStep/10));
		facing = Facing.LEFT;
	}
	
	public void accelerateRight(long millisSinceLastStep) {
		/*
		dx += (ACCELERATION_RATE/(millisSinceLastStep/1000));
		if(dx > MAX_RUN_SPEED)
			dx = MAX_RUN_SPEED;
		*/
		x_location+=(MAX_RUN_SPEED * (millisSinceLastStep/10));
		facing = Facing.RIGHT;
	}
	
	public void deccelerate(long timeSinceLastStep) {
		dx = 0;
		/*
		if(dx > 0.0f)
			dx-= (ACCELERATION_RATE*2)/(timeSinceLastStep/1000);
		if(dx < 0.0f)
			dx+= (ACCELERATION_RATE*2)/(timeSinceLastStep/1000);
			*/
	}
	
	public int getX() {
		return (int) x_location;
	}
	
	public void slap() {
		
	}
}
