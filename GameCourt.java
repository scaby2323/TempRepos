/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private Circle ball; // the Golden ball, bounces
	private Rectangle bar;// the Black Rectangle, keyboard control
	private Blocks temp; 
	
	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)
	private JLabel lives; // Current number of lives
	private JLabel score; 
	private JLabel times;

	// Game constants
	public static final int COURT_WIDTH = 800;
	public static final int COURT_HEIGHT = 400;
	public static final int BAR_VELOCITY = 20;
	
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;
	
	//create array to draw blocks
	private Blocks [][] blocks = new Blocks[COURT_WIDTH/80][COURT_HEIGHT/100];
	
	//create ArrayList of blocks that were hit
	private ArrayList<Blocks> hitBlocks = new ArrayList<Blocks>(); 
	
	private int num_lives = 3; 
	private int num_score = 0; 
	private int time = 0; 
	private int num_blocks = 40; 
	
	public GameCourt(JLabel status, JLabel lives, JLabel score, JLabel times) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// This key listener allows the bar to move as long
		// as an arrow key is pressed, by changing the bar's
		// velocity accordingly. (The tick method below actually
		// moves the bar.)
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					bar.v_x = -BAR_VELOCITY;
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					bar.v_x = BAR_VELOCITY;
			}

			public void keyReleased(KeyEvent e) {
				bar.v_x = 0;
				bar.v_y = 0;
			}
		});

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
		times.setText("Time: " + time + " vel_x " + ball.v_x + " vel_y " + ball.v_y); 
		
		if (playing) {
			// advance the bar and ball in their
			// current direction.
			bar.move();
			ball.move();

			// make the ball bounce off walls...
			ball.bounce(ball.hitWall());
			//make the ball bounce off bar
			if(ball.willIntersect(bar)){
				ball.bounce(ball.hitObj(bar));
			}
			
			
			
			for(int r = 0; r < blocks.length; r++){
				for(int c = 0; c< blocks[0].length; c++ ){
					Blocks check = blocks[r][c]; 
					if(check!=null){
						if(ball.intersects(check)){
							ball.bounce(ball.hitObj(check)); 
							check.setHit(true);
							hitBlocks.add(check); 
							blocks[r][c] = null; 
							num_score = hitBlocks.size()*100;
							score.setText("Score: " + num_score); 
							System.out.println("num of hit blocks = " + hitBlocks.size());
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
			
			if(time%300 == 0){
				for(int r = 0; r<blocks.length; r++){
					for(int c = 0; c<blocks[0].length; c++){
						if(blocks[r][c]!= null)
							blocks[r][c].moveDown();
					}
				}
			}
			
			//check for ending
			
			if(hitBlocks.size() == num_blocks){
				playing = false; 
				status.setText("YOU WIN!"); 
			}

			if(ball.pos_y == ball.max_y) {
				num_lives--; 
				if(num_lives == 0){
					playing = false; 
					status.setText("GAME OVER!"); 
					score.setText("Final Score: " + num_score); 
					lives.setText("Lives: " + num_lives);
				}
				else{
					playing = true; 
					status.setText("Lost a life :(");
					lives.setText("Lives: " + num_lives); 
					score.setText("Score: " + num_score);
					ball = new Circle(COURT_WIDTH, COURT_HEIGHT);
				}	
			}
			
			for(int r = 0; r < blocks.length; r++){
				for(int c = 0; c < blocks[0].length; c++)
					if(blocks[r][c]!= null && blocks[r][c].pos_y >= blocks[r][c].max_y){
						playing = false; 
						status.setText("Game Over!");
					}
			}
			
			// update the display
			repaint();
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
