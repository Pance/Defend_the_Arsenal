package slick.Defend;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Bomb {
	private final double FLASH_TIME = 1.0f; // how long it takes to flip colors in seconds
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private long lifeTimeMillis = 0;
	private long flashTimeMillis = 0;
	
	private Image img1;
	private Image img2;
	
	public Bomb(int x, int y, int dx, int dy) throws SlickException {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		initImages();
	}
	
	public void initImages() throws SlickException {
		img1 = new Image("resources/bomb/bomb1.png");
		img2 = new Image("resources/bomb/bomb2.png");
	}
	
	public void draw() {
		img1.draw((int)x,(int)y);
	}
	
	public void step(long millisSinceLastStep) {
		//lifeTimeMillis+= millisSinceLastStep;
		flashTimeMillis+=millisSinceLastStep;
		
		if(dx != 0.0f)
			x+=((double)dx * (millisSinceLastStep * 0.001f));
		if(dy != 0.0f)
			y+=((double)dy * (millisSinceLastStep * 0.001f));
	}
}
