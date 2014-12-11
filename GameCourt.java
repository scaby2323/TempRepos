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

	//create array to draw blocks
	private Blocks [][] blocks;

	//create ArrayList of blocks that were hit
	private ArrayList<Blocks> hitBlocks = new ArrayList<Blocks>(); 

	public LevelScanner level_info;

	private int num_lives = 3; 
	private int num_score = 0; 
	private int time = 0; 
	private int level = 0;  
	private int num_blocks = 0; 
	private int space_pressed = 0; 

	public void makeLevel(int l) throws IOException{
		level_info = new LevelScanner("Levels.txt");
		blocks = new Blocks[10][4]; 
		TreeMap<Integer, ArrayList<String>> info_map = level_info.getLevels();
		ArrayList<String> level_info_list = info_map.get(l);
		for(int c = 0; c < level_info_list.size(); c++){
			int y = 40*c; 
			String row = level_info_list.get(c);
			for(int r = 0; r< row.length(); r++){
				if(row.charAt(r) == 'e'){
					blocks[r][c] = null; 
				}
				else{
					int x = 80*r; 
					temp = new Blocks(x,y,COURT_WIDTH, COURT_HEIGHT); 
					blocks[r][c] = temp;
					num_blocks++; 
				}
			}
		}
	}

	public GameCourt(JLabel status, JLabel lives, JLabel score, JLabel times) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		final Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});

		timer.start(); 
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				switch(e.getKeyCode()){
				case KeyEvent.VK_SPACE: 
					ball.v_x = 4; 
					ball.v_y = -5; 
					space_pressed = 1; 
					break;
				case KeyEvent.VK_RIGHT:
					if(space_pressed == 0){
						ball.v_x = BAR_VELOCITY; 
						bar.v_x = BAR_VELOCITY; 
					}
					else{
						bar.v_x = BAR_VELOCITY; 
					}
					break;
				case KeyEvent.VK_LEFT:
						if(space_pressed == 0){
							ball.v_x = -BAR_VELOCITY; 
							bar.v_x = -BAR_VELOCITY; 
						}
						else{
							bar.v_x = -BAR_VELOCITY; 
						}
					break; 
				}
			}
			public void keyReleased(KeyEvent e){
				if(space_pressed == 0){
					ball.v_x = 0; 
					bar.v_x = 0; 
				}
				else{
					bar.v_x = 0; 
				}
			}
		}); 

		setFocusable(true);

		this.status = status;
		this.lives = lives; 
		this.score = score; 
		this.times = times; 
	}

	public void reset() {

		ball = new Circle(410, 345, COURT_WIDTH, COURT_HEIGHT);
		bar = new Rectangle(380,365,COURT_WIDTH, COURT_HEIGHT); 
		hitBlocks = new ArrayList<Blocks>(); 
		num_lives = 3; 
		num_score = 0; 
		time = 0; 
		space_pressed = 0; 
		level = 0; 

		try {
			makeLevel(level); 
		} catch (IOException e) {
			e.printStackTrace();
		} 

		playing = true;
		status.setText("Playing...");
		lives.setText("Lives: " + num_lives); 
		score.setText("Score: " + num_score + " Level: " + level + 
				" Blocks Hit: " + hitBlocks.size() + " out of " + num_blocks); 
		times.setText("Time: " + times);

		requestFocusInWindow();
	}

	public void nextLevel(){
		level++; 
		ball = new Circle(410, 345, COURT_WIDTH, COURT_HEIGHT); 
		bar = new Rectangle(380, 365, COURT_WIDTH, COURT_HEIGHT);
		hitBlocks = new ArrayList<Blocks>();
		num_blocks = 0 ;
		space_pressed = 0; 
		
		try {
			makeLevel(level);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		playing = true; 
		status.setText("Playing...");
		lives.setText("Lives: " + num_lives); 
		score.setText("Score: " + num_score + " Level: " + level + " Blocks Hit: " + hitBlocks.size()  + " out of " + num_blocks); 
		times.setText("Time: " + times);
		
		requestFocusInWindow(); 

	}

	public void quit() {
		playing = false; 
		System.exit(0);		
	}

	void tick() {
		time ++;
		times.setText("Time: " + time); 

		if (playing) {
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
						if(ball.willIntersect(check)){
							ball.bounce(ball.hitObj(check)); 
							check.setHit(true);
							hitBlocks.add(check); 
							blocks[r][c] = null; 
							num_score = hitBlocks.size()*100;
							score.setText("Score: " + num_score + " Level: " +
							level + " Blocks Hit: " + hitBlocks.size()  + " out of " + num_blocks);
						}
						else{
							check.setHit(false); 
							num_score = hitBlocks.size()*100; 
							score.setText("Score: " + num_score + " Level: " + 
							level + " Blocks Hit: " + hitBlocks.size() + " out of " + num_blocks); 
						}
					}
				}
			}

			/*if(time%200 == 0 && space_pressed == 1){
				ball.setSpeed(true);
			}
*/
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
				if(level < 5){
					nextLevel(); 
				}
				else{
					playing = false;
					status.setText("YOU WON!!! :)"); 
					score.setText("Final Score: " + num_score);
				}
			}
			if(ball.pos_y >= ball.max_y) {
				num_lives--; 
				space_pressed = 0; 
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
					score.setText("Score: " + num_score + " Level: " +
							level + " Blocks Hit: " + hitBlocks.size());
					ball = new Circle(410, 345, COURT_WIDTH, COURT_HEIGHT);
					bar = new Rectangle(380,365,COURT_WIDTH, COURT_HEIGHT); 
				}	
			}
			for(int r = 0; r < blocks.length; r++){
				for(int c = 0; c < blocks[0].length; c++){
					if(blocks[r][c]!= null && blocks[r][c].pos_y >= blocks[r][c].max_y){
						playing = false; 
						status.setText("Game Over!");
						score.setText("Score: " + num_score + " Level: " + level 
								+ " Blocks Hit: " + hitBlocks.size()); 
					}
				}
			}		
			repaint();
		}
	}

	public void back() {
		playing = false; 
		IntroWindow intro = new IntroWindow();
		intro.run(); 
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
