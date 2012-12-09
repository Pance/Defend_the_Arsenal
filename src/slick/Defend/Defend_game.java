package slick.Defend;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public class Defend_game extends BasicGame{
	
	static final int PLAYER_START_X = 350;
	static final int PLAYER_START_Y = 230;
	static final int WINDOW_WIDTH = 1024;
	static final int WINDOW_HEIGHT = 600;
	static final int GROUND_HEIGHT = 300;
	static final int MAX_ENEMIES = 5;
	static long lastTime = 0;
	
	Image troll = null;
	
	Environment environment = null;
	Player player = null;
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	
	public Defend_game(){
		super("Defend the Arsenal!");
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {		

		environment = new EnvironmentImpl(WINDOW_WIDTH, WINDOW_HEIGHT, GROUND_HEIGHT, enemies);
		environment.initImages();
		
		player = new Player(PLAYER_START_X, PLAYER_START_Y, environment);
		player.initImages();
		
		troll = new Image("resources/troll/troll.png");
		troll = troll.getScaledCopy(3.0f);
		
		for(int i=0; i<MAX_ENEMIES; i++)
			enemies.add(new Enemy(player));
		
		lastTime = System.currentTimeMillis();
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		
		long now = System.currentTimeMillis();
		
		//input.isKeyDown()
		if(input.isKeyDown(Input.KEY_A))
			player.accelerateLeft(now - lastTime);
		if(input.isKeyDown(Input.KEY_D))
			player.accelerateRight(now - lastTime);
		if( (!input.isKeyDown(Input.KEY_A)) && (!input.isKeyDown(Input.KEY_D)) )
			player.deccelerate(now - lastTime);
		//if(input.isKeyDown(Input.KEY_S))
			//player.changeY(1);
		//if(input.isKeyDown(Input.KEY_W))
			//player.changeY(-1);
		
		player.step(now - lastTime);
		
		for(Enemy e : enemies)
			e.think(now - lastTime);
		
		lastTime = now;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		environment.drawBackground();
		player.draw();
		environment.drawGround();
		for( Enemy e : enemies)
			troll.draw(e.getX(), e.getY());
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Defend_game());
		
		app.setDisplayMode(1024, 600, false);
		app.start();
	}
}
