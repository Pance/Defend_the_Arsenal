package slick.Defend;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.ImageData;

import java.util.ArrayList;

public class Player {
	
	private final float IMAGE_SCALE = 3.0f;
	private final double ACCELERATION_RATE = 160.0f; // Increase dx by this amount per second
	private final double DECCELERATION_RATE = 130.0f;
	private final double MAX_RUN_SPEED = 120.0f; // Maximum speed player can move px/second
	public enum Facing { LEFT, RIGHT };
	private final double SLAP_COOLDOWN = 0.25f; // Seconds
	private final double LAUNCH_CHARGE_TIME = 0.5f; // Seconds
	private final double BOMB_COOLDOWN = 1.0f;
	
	private double x_location;
	private double y_location;
	private double dx; // The player's current rate of change in x per second
	private double dy; // The player's current rate of change in y per second
	private Facing facing = Facing.RIGHT;
	private int width = 300; // Width in pixels
	
	private Image imageRightOpen;
    private Image imageRightClosed;
	private Image imageLeft;
	private Environment environment;
	private SpriteSheet playerLeft;
	private SpriteSheet playerRight;
	
	private long lastSlapMillis = System.currentTimeMillis();
	private long chargeTimeMillis = 0;
	private long lastBombMillis = 0;
	
	public Player(int x, int y, Environment e) throws SlickException{
		x_location = x;
		y_location = y;
		environment = e;
		initImages();
	}
	
	public void initImages() throws SlickException {
		SpriteSheet sheetLeft = new SpriteSheet("resources/Hero_left.png", 32, 32);
		SpriteSheet sheetRight = new SpriteSheet("resources/Hero_right.png", 32, 32);
		imageRightOpen = sheetRight.getSprite(1,0).getScaledCopy(IMAGE_SCALE);
        imageRightClosed = sheetRight.getSprite(0,1).getScaledCopy(IMAGE_SCALE);
		imageLeft = sheetLeft.getSprite(0,0).getScaledCopy(IMAGE_SCALE);
		width = imageRightOpen.getWidth();
	}
	
	public void draw(){

		if(facing == Facing.RIGHT){
			imageRightOpen.draw((int)x_location, (int)y_location);
            imageRightClosed.draw((int)x_location, (int)y_location);
        }else
			imageLeft.draw((int)x_location, (int)y_location);
	}
	
	public void step(long millisSinceLastStep) {
		lastBombMillis+=millisSinceLastStep;
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
	
	long lastChargeMillis = 1;
	
	public void chargeSlap() {
		long now = System.currentTimeMillis();
		
		long millisSinceLastSlap = now - lastSlapMillis;		
		if((millisSinceLastSlap * 0.001f) < SLAP_COOLDOWN)
			return; // too soon to start charging again
		else {
			//we are charging
			long timeSinceLastCharge = now - lastChargeMillis;
			chargeTimeMillis += timeSinceLastCharge;
			lastChargeMillis = now;
		}
	}
	
	public void unleashSlap() {
		if(chargeTimeMillis > 0.0f) {
			long now = System.currentTimeMillis();
			
			int slap_pos = (int)x_location;
			if(facing == Facing.RIGHT)
				slap_pos+=width;
			
			if( (chargeTimeMillis * 0.001f) > LAUNCH_CHARGE_TIME) {
				//we are over teh charge time threshold, launch attack
				environment.launch((int)slap_pos, facing);
			} else {
				//we didn't charge the slap long enough, slap attack
				environment.slap((int)slap_pos, facing);
			}
			
			lastSlapMillis = now;			
		}
		chargeTimeMillis = (long)-0.00001f;
	}
	
	public void launchBomb() throws SlickException {
		if((lastBombMillis * 0.001f) > BOMB_COOLDOWN ) {
			Bomb b = new Bomb((int)x_location, (int)y_location, 0, 0);
			environment.addBomb(b);
			lastBombMillis = System.currentTimeMillis();
		}
	}
}
