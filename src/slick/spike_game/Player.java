package slick.spike_game;

import org.newdawn.slick.Image;

public class Player {
	
	private final float IMAGE_SCALE = 3.0f;
	private final double ACCELERATION_RATE = 5.0f; // Increase dx by this amount per second
	private final double MAX_RUN_SPEED = 8.0f; // Maximum speed player can move
	
	private double x_location;
	private double y_location;
	private double dx; // The player's current rate of change in x per second
	private double dy; // The player's current rate of change in y per second
	
	//private int x_location;
	//private int y_location;
	private Image image;
	private Environment environment;
	
	public Player(int x, int y, Image i, Environment e) {
		image = i.getScaledCopy(IMAGE_SCALE);
		x_location = x;
		y_location = y;
		environment = e;
	}
	
	public void draw(){
		image.draw((int)x_location, (int)y_location);
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
	}
	
	public void accelerateRight(long millisSinceLastStep) {
		/*
		dx += (ACCELERATION_RATE/(millisSinceLastStep/1000));
		if(dx > MAX_RUN_SPEED)
			dx = MAX_RUN_SPEED;
		*/
		x_location+=(MAX_RUN_SPEED * (millisSinceLastStep/10));
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
}
