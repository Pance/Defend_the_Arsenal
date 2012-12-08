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
	
	Image land = null;
	Image player = null;
	Image troll = null;
	int player_x = 200;
	int player_y = 200;
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	int enemy_max = 5;
	
	public spike_game(){
		super("Slick2d - Spike Game!");
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		land  = new Image("resources/background.jpeg");
		player = new Image("resources/tux/idle/r/1.png");
		player = player.getScaledCopy(2.0f);
		troll = new Image("resources/troll/troll.png");
		troll = troll.getScaledCopy(3.0f);
		
		for(int i=0; i<enemy_max; i++)
			enemies.add(new Enemy());
	}
	
	@Override
	public void update(GameContainer gc, int deltsa) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_A))
			player_x--;
		if(input.isKeyDown(Input.KEY_D))
			player_x++;
		if(input.isKeyDown(Input.KEY_S))
			player_y++;
		if(input.isKeyDown(Input.KEY_W))
			player_y--;
		
		for(Enemy e : enemies)
			e.think();
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		land.draw(0,0);
		
		player.draw(player_x, player_y);
		troll.draw(player_x+50, player_y+50);
		for( Enemy e : enemies)
			troll.draw(e.getX(), e.getY());
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new spike_game());
		
		app.setDisplayMode(1024, 768, false);
		app.start();
	}
}
