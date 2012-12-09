package slick.Defend;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Player {
	
	private final float IMAGE_SCALE = 3.0f;
	private final double ACCELERATION_RATE = 160.0f; // Increase dx by this amount per second
	private final double DECCELERATION_RATE = 130.0f;
	private final double MAX_RUN_SPEED = 120.0f; // Maximum speed player can move px/second
	private enum Facing { LEFT, RIGHT };
	
	private double x_location;
	private double y_location;
	private double dx; // The player's current rate of change in x per second
	private double dy; // The player's current rate of change in y per second
	private Facing facing = Facing.RIGHT;
	
	private Image imageRight;
	private Image imageLeft;
	private Environment environment;
	
	
	
	public Player(int x, int y, Environment e) {
		x_location = x;
		y_location = y;
		environment = e;
	}
	
	public void initImages() throws SlickException {
		imageRight = new Image("resources/tux/idle/r/1.png").getScaledCopy(IMAGE_SCALE);
		imageLeft = new Image("resources/tux/idle/l/1.png").getScaledCopy(IMAGE_SCALE);
		
	}
	
	public void draw(){
		if(facing == Facing.RIGHT)
			imageRight.draw((int)x_location, (int)y_location);
		else
			imageLeft.draw((int)x_location, (int)y_location);
	}
	
	public void step(long millisSinceLastStep) {
		//if(isInAir())
			//dy-=environment.getGravity();
		//y_location = y_location + (dy/(millisSinceLastStep/1000));
		if(dx != 0.0f)
			x_location+=((double)dx * (millisSinceLastStep * 0.001f));
	}
	
	public boolean isInAir() {
		return y_location > environment.getGroundHeight()+1;
	}
	
	public void accelerateLeft(long millisSinceLastStep) {
		dx-=( ACCELERATION_RATE * (millisSinceLastStep * 0.001f) );
		if( dx < -MAX_RUN_SPEED )
			dx = -MAX_RUN_SPEED;
		//dx = -MAX_RUN_SPEED;
		facing = Facing.LEFT;
	}
	
	public void accelerateRight(long millisSinceLastStep) {
		dx+=( ACCELERATION_RATE * (millisSinceLastStep * 0.001f) );
		if( dx > MAX_RUN_SPEED )
			dx = MAX_RUN_SPEED;
		//dx = MAX_RUN_SPEED;
		facing = Facing.RIGHT;
	}
	
	public void deccelerate(long millisSinceLastStep) {
		if(dx > 0 )
			dx-=( DECCELERATION_RATE * (millisSinceLastStep * 0.001f) );
		if(dx < 0 )
			dx+=( DECCELERATION_RATE * (millisSinceLastStep * 0.001f) );
	}
	
	public int getX() {
		return (int) x_location;
	}
	
	public void startSlap() {
		//environment.
	}
}
