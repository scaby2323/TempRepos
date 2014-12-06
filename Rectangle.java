import java.awt.*;

/** 
 * Basic shape for the bar that the ball will hit off of 
 */
public class Rectangle extends GameObj {
	
	public static final int SIZE_Y = 30;
	public static final int SIZE_X = 150; 
	public static final int INIT_X = 215;
	public static final int INIT_Y = 365;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;

	public Rectangle(int courtWidth, int courtHeight){
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, SIZE_X, SIZE_Y, courtWidth,
				courtHeight);	
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.BLACK); 
		g.fillRect(pos_x, pos_y, width, height); 
//		System.out.println("pos_x = " + pos_x + "pos_y = " + pos_y + " widgth " + width + " height " + height);
	}
	
	
}
