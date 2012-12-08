package slick.spike_game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public class spike_game extends BasicGame{
	
	static final int PLAYER_START_X = 200;
	static final int PLAYER_START_Y = 200;
	static final int WINDOW_WIDTH = 1024;
	static final int WINDOW_HEIGHT = 600;
	static final int GROUND_HEIGHT = 300;
	static final int MAX_ENEMIES = 5;
	
	Image troll = null;
	
	Environment environment = null;
	Player player = null;
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	
	public spike_game(){
		super("Slick2d - Spike Game!");
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		
		Image land  = new Image("resources/background.jpeg");
		Image ground = new Image("resources/ground.png");
		environment = new EnvironmentImpl(WINDOW_WIDTH, WINDOW_HEIGHT, land, GROUND_HEIGHT, ground);
		
		Image player_image = new Image("resources/tux/idle/r/1.png");
		player = new Player(PLAYER_START_X, PLAYER_START_Y, player_image);
		
		troll = new Image("resources/troll/troll.png");
		troll = troll.getScaledCopy(3.0f);
		
		for(int i=0; i<MAX_ENEMIES; i++)
			enemies.add(new Enemy());
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_A))
			player.changeX(-1);
		if(input.isKeyDown(Input.KEY_D))
			player.changeX(1);
		if(input.isKeyDown(Input.KEY_S))
			player.changeY(1);
		if(input.isKeyDown(Input.KEY_W))
			player.changeY(-1);
		
		for(Enemy e : enemies)
			e.think();
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		environment.drawBackground();
		player.draw();
		environment.drawGround();
		for( Enemy e : enemies)
			troll.draw(e.getX(), e.getY());
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new spike_game());
		
		app.setDisplayMode(1024, 600, false);
		app.start();
	}
}
