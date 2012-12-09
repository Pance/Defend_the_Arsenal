package slick.Defend;

import org.newdawn.slick.SlickException;
import slick.Defend.Player.Facing;

public interface Environment {

		int getGroundHeight();
		void drawBackground();
		void drawGround();
		double getGravity();
		void initImages() throws SlickException;
		void slap(int x_position, Facing facing);
		void launch(int x_position, Facing facing);
		//void setWidth(int w);
		//void setHeight(int h);
}
