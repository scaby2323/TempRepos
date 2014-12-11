/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;

/**
 * A basic game object displayed as a yellow circle, starting in the upper left
 * corner of the game court.
 * 
 */
public class Circle extends GameObj {

	public static final int SIZE = 20;
	/*public static final int INIT_POS_X = 410;
	public static final int INIT_POS_Y = 345;*/
	public static int INIT_VEL_X = 0;
	public static int INIT_VEL_Y = 0;
	

	public Circle(int x, int y, int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, x, y, SIZE, SIZE,
				courtWidth, courtHeight);
	}
	
	public void setV_X(int v){
		INIT_VEL_X = v; 
	}
	
	public void setV_Y(int v){
		INIT_VEL_Y = v;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(pos_x, pos_y, width, height);
	}
}