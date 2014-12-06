import java.awt.Color;
import java.awt.Graphics;

public class Blocks extends GameObj {

	public static final int SIZE_X = 80;
	public static final int SIZE_Y = 40; 
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	public int count = 0; 
	public boolean hit = false; 
	
	public Blocks(int init_x, int init_y, int court_width, int court_height) {
		super(INIT_VEL_X, INIT_VEL_Y, init_x, init_y, SIZE_X, SIZE_Y, 
				court_width, court_height);
		// TODO Auto-generated constructor stub
	}

	public void setColors(Graphics g, Color c){
		g.setColor(c); 
		g.fillRect(pos_x, pos_y, width, height); 
	}
	
	public void setHit(boolean hit){
		if(hasHit() == false){
			this.hit = hit; 
		}
		else
			hit = false; 
	}
	
	public boolean hasHit(){
		return hit;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(pos_x, pos_y, width, height);
		count++; 
	}

}
