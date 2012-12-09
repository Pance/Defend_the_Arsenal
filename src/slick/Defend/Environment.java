package slick.Defend;

import org.newdawn.slick.SlickException;

public interface Environment {

		int getGroundHeight();
		void drawBackground();
		void drawGround();
		double getGravity();
		void initImages() throws SlickException;
		//void setWidth(int w);
		//void setHeight(int h);
}
