/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;



/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel{

	// the state of the game logic
	private Circle ball; // the Black ball, bounces
	private Rectangle bar;// the Black Rectangle, keyboard control
	private Blocks temp; 
	
	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)
	private JLabel lives; // Current number of lives
	private JLabel score; // Current score 
	private JLabel times; // Time elapsed 

	// Game constants
	public static int COURT_WIDTH = 800;
	public static int COURT_HEIGHT = 400;
	public static final int BAR_VELOCITY = 20;
	
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;
	
	private int num_rows = 4; 
	private int num_cols = 10;
	//create array to draw blocks
	private Blocks [][] blocks = new Blocks[num_cols][num_rows];
	
	//create ArrayList of blocks that were hit
	private ArrayList<Blocks> hitBlocks = new ArrayList<Blocks>(); 
	
	public LevelScanner level_info;
	
	private int num_lives = 3; 
	private int num_score = 0; 
	private int time = 0; 
	private int time_final; 
	private int level = 0; 
	private int num_blocks = num_rows*num_cols; 
	
	private int space_pressed = 0; 
	
	/*public void getLevelInfo() throws IOException{
		level_info = new LevelScanner("Levels.txt");
		ArrayList<String> info_list = level_info.getLevelInfo(level); 
		num_rows = info_list.size(); 
		String col_size = info_list.get(0); 
		for (int c = 0; c < col_size.length(); c++){
			if(col_size.charAt(c) == '.'){
				num_cols++; 
			}
			else{
				num_cols += (int)(col_size.charAt(c)); 
			}
		}
		num_blocks = num_rows*num_cols; 
		blocks = new Blocks[num_rows][num_cols];
		Iterator<String> iter = info_list.iterator(); 
		while(iter.hasNext()){
			String row = iter.next(); 
			String[] row_info = row.split(" "); 
			for(int r = 0; r<row_info.length; r++){
				String check = row_info[r]; 
				for(int c = 0; c<check.length(); c++){
					if(check.charAt(c) == '.'){ 
						blocks[r][c] = null;
						num_cols++; 
					}
					else{
						int k = (int)check.charAt(c); 
						for(int i = c; i < k; i++){
							int w = 80*(r); 
							int h = 40*(i); 
							temp = new Blocks(w, h, COURT_WIDTH, COURT_HEIGHT);
							blocks[r][i] = temp; 
							num_cols++;
						}
						c = k;
					}
				}
			}
		}
	}*/
	
	
	public GameCourt(JLabel status, JLabel lives, JLabel score, JLabel times, final int player) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		final Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		
		timer.start(); // MAKE SURE TO START THE TIMER!
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				System.out.println(space_pressed);
				//if spacebar is pressed start the game
				if(e.getKeyCode() == KeyEvent.VK_SPACE && space_pressed == 0){
					ball.v_x = 4; 
					ball.v_y = -5; 
					space_pressed = 1; 
					//listen to rest of the pressed keys
					addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent e){
							if(player == 1){
								if(e.getKeyCode() == KeyEvent.VK_A){
									bar.v_x = -BAR_VELOCITY;
								}
								else if(e.getKeyCode() == KeyEvent.VK_D){
									bar.v_x = BAR_VELOCITY; 
								}
							}
							else if(player == 2){
								if(e.getKeyCode() == KeyEvent.VK_LEFT){
									bar.v_x = -BAR_VELOCITY; 
								}
								else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
									bar.v_x = BAR_VELOCITY; 
								}
							}
						}
						public void keyReleased(KeyEvent e){
							bar.v_x = 0; 
							bar.v_y = 0; 
						}	
					});
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT && space_pressed == 0){
					bar.v_x = -BAR_VELOCITY; 
					ball.v_x = -BAR_VELOCITY; 
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT && space_pressed == 0){
					bar.v_x = BAR_VELOCITY; 
					ball.v_x = BAR_VELOCITY; 
				}	
			}
			public void keyReleased(KeyEvent e){
				if(space_pressed == 0){
					bar.v_x = 0; 
					bar.v_y = 0; 
					ball.v_x = 0; 
					ball.v_y = 0;
				}			
			}	
		}); 

		

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// This key listener allows the bar to move as long
		// as an arrow key is pressed, by changing the bar's
		// velocity accordingly. (The tick method below actually
		// moves the bar.)

			COURT_WIDTH = 800;
			COURT_HEIGHT = 400;

		

		this.status = status;
		this.lives = lives; 
		this.score = score; 
		this.times = times; 
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {

		ball = new Circle(COURT_WIDTH, COURT_HEIGHT);
		bar = new Rectangle(COURT_WIDTH, COURT_HEIGHT); 
		hitBlocks = new ArrayList<Blocks>(); 
		num_lives = 3; 
		num_score = 0; 
		time = 0; 
		
/*		try {
			getLevelInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	
		
		for(int i = 0; i < blocks.length; i++){
			for(int j = 0; j <  blocks[0].length; j++){
				int w = 80*(i); 
				int h = 40*(j); 
				temp = new Blocks(w, h, COURT_WIDTH, COURT_HEIGHT);
				blocks[i][j] = temp; 
			}
		}
	
		
		playing = true;
		status.setText("Playing...");
		lives.setText("Lives: " + num_lives); 
		score.setText("Score: " + num_score); 
		times.setText("Time: " + times);

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	public void quit() {
		System.exit(0);		
	}
	
	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		time ++;
		times.setText("Time: " + time); 
		
		if (playing) {
			// advance the bar and ball in their
			// current direction.
			bar.move();
			ball.move();

			// make the ball bounce off walls...
			ball.bounce(ball.hitWall());
			//make the ball bounce off bar
			if(ball.willIntersect(bar)){
				System.out.println("bat");
				ball.bounce(ball.hitObj(bar));
			}
			
			for(int r = 0; r < blocks.length; r++){
				for(int c = 0; c< blocks[0].length; c++ ){
					Blocks check = blocks[r][c]; 
					if(check!=null){
						if(ball.willIntersect(check)){
							System.out.println("block");
							ball.bounce(ball.hitObj(check)); 
							check.setHit(true);
							hitBlocks.add(check); 
							blocks[r][c] = null; 
							num_score = hitBlocks.size()*100;
							score.setText("Score: " + num_score);
							}
						else{
							check.setHit(false); 
							num_score = hitBlocks.size()*100; 
							score.setText("Score: " + num_score); 
						}
					}
				}
			}
			
		/*	if(time%200 == 0){
				//ball.setSpeed(true);
			}*/
			
			if(time%500 == 0){
				for(int r = 0; r<blocks.length; r++){
					for(int c = 0; c<blocks[0].length; c++){
						if(blocks[r][c]!= null)
							blocks[r][c].moveDown();
					}
				}
			}
			
			//check for ending
			
			if(hitBlocks.size() == num_blocks){
				level++; 
				newGame();  
			}
			
			if(ball.pos_y >= (ball.max_y - ball.width)) {
				num_lives--; 
				space_pressed = 0; 
				if(num_lives == 0){
					playing = false; 
					time_final = time; 
					status.setText("GAME OVER!"); 
					score.setText("Final Score: " + num_score); 
					lives.setText("Lives: " + num_lives); 
					times.setText("Time: " + time_final); 
				}
				else{
					playing = true; 
					status.setText("Lost a life :(");
					lives.setText("Lives: " + num_lives); 
					score.setText("Score: " + num_score);
					ball = new Circle(COURT_WIDTH, COURT_HEIGHT);
					bar = new Rectangle(COURT_WIDTH, COURT_HEIGHT); 
				}	
			}
			
			for(int r = 0; r < blocks.length; r++){
				for(int c = 0; c < blocks[0].length; c++){
					if(blocks[r][c]!= null && blocks[r][c].pos_y >= blocks[r][c].max_y){
						playing = false; 
						time_final = time; 
						status.setText("Game Over!");
						times.setText("Time: " + time_final); 
					}
				}
			}		
			// update the display
			repaint();
		}
	}
	
	public void newGame(){
		hitBlocks = new ArrayList<Blocks>();
		ball = new Circle(COURT_WIDTH, COURT_HEIGHT); 
		bar = new Rectangle(COURT_WIDTH, COURT_HEIGHT);
		time = 0; 
		space_pressed = 0; 
		for(int i = 0; i < blocks.length; i++){
			for(int j = 0; j <  blocks[0].length; j++){
				int w = 80*(i); 
				int h = 40*(j); 
				temp = new Blocks(w, h, COURT_WIDTH, COURT_HEIGHT);
				blocks[i][j] = temp;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(playing){
		ball.draw(g);
		}
		bar.draw(g); 

		for(int i = 0; i < blocks.length; i++){
			for(int j = 0; j < blocks[0].length ; j++){
				Blocks check = blocks[i][j]; 
				if(check != null){
					if(check.hasHit() == false){
					check.draw(g);
					if(j%2 == 0 && i%2 == 0){check.setColors(g, Color.red);}
					else if(j%2 != 0 && i%2 != 0){check.setColors(g,Color.red);}
					else if(j%2 != 0 && i%2 == 0){check.setColors(g, Color.blue);}
					else{check.setColors(g, Color.blue);}
					}
				}
			}
		}
		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}


}
