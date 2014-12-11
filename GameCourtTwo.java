import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

public class GameCourtTwo extends JPanel {

	private Circle play1_ball; // the Black ball, bounces
	private Circle play2_ball; // the Black ball, bounces

	private Rectangle play1_bar;// the Black Rectangle, keyboard control
	private Rectangle play2_bar;
	private Blocks temp;

	public boolean playing = false; // whether the game is running

	private JLabel play1_status; // Current status text (i.e. Running...)
	private JLabel play2_status;
	private JLabel play1_lives; // Current number of lives
	private JLabel play2_lives;
	private JLabel play1_score; // Current score
	private JLabel play2_score;
	private JLabel times; // Time elapsed

	// Game constants
	public static int COURT_WIDTH = 800;
	public static int COURT_HEIGHT = 600;
	public static final int BAR_VELOCITY = 20;

	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;

	// create array to draw blocks
	private Blocks[][] play1_blocks;
	private Blocks[][] play2_blocks;

	// create ArrayList of blocks that were hit
	private ArrayList<Blocks> play1_hitBlocks = new ArrayList<Blocks>();
	private ArrayList<Blocks> play2_hitBlocks = new ArrayList<Blocks>();

	public LevelScanner level_info;

	private int play1_num_lives = 3;
	private int play2_num_lives = 3;
	private int play1_num_score = 0;
	private int play2_num_score = 0;
	private int time = 0;
	private int level = 0;
	private int num_blocks = 0;
	private int space_pressed = 0; // player 1 start
	private int enter_pressed = 0; //player 2 start

	private Set<Integer> pressed = new HashSet<Integer>();

	public void makeLevel(int l) throws IOException {
		level_info = new LevelScanner("Levels.txt");
		play1_blocks = new Blocks[10][4];
		play2_blocks = new Blocks[10][4];
		TreeMap<Integer, ArrayList<String>> info_map = level_info.getLevels();
		ArrayList<String> level_info_list = info_map.get(l);
		for (int c = 0; c < level_info_list.size(); c++) {
			int y = 40 * c;
			String row = level_info_list.get(c);
			for (int r = 0; r < row.length(); r++) {
				if (row.charAt(r) == 'e') {
					play1_blocks[r][c] = null;
				} else {
					int x = 80 * r;
					temp = new Blocks(x, y, COURT_WIDTH, COURT_HEIGHT);
					play1_blocks[r][c] = temp;
					num_blocks++;
				}
			}
		}
		for (int c = 0; c < level_info_list.size(); c++) {
			int y = 40 * c + 300;
			String row = level_info_list.get(c);
			for (int r = 0; r < row.length(); r++) {
				if (row.charAt(r) == 'e') {
					play2_blocks[r][c] = null;
				} else {
					int x = 80 * r;
					temp = new Blocks(x, y, COURT_WIDTH, COURT_HEIGHT);
					play2_blocks[r][c] = temp;
				}
			}
		}
	}

	public GameCourtTwo(JLabel play1_status, JLabel play1_lives, JLabel play1_score, JLabel play2_status, JLabel play2_lives, JLabel play2_score, JLabel times) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		final Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();

		addKeyListener(new KeyAdapter() {
			@Override
			public synchronized void keyPressed(KeyEvent e) {
				pressed.add(e.getKeyCode());
				if (pressed.size() > 0) {
					Iterator<Integer> iter = pressed.iterator();
					while (iter.hasNext()) {
						int click = iter.next();
						if(click == KeyEvent.VK_SPACE){
							System.out.println("space");
							space_pressed = 1;
							play1_ball.v_x = 4;
							play1_ball.v_y = -5;
						}
						else if(click == KeyEvent.VK_ENTER){
							System.out.println("enter");
							enter_pressed = 1; 
							play2_ball.v_x = 4;
							play2_ball.v_y = -5;
						}
						else if(click ==KeyEvent.VK_RIGHT){
							System.out.println("right");
							if (enter_pressed == 0) {
								play2_ball.v_x = BAR_VELOCITY;
								play2_bar.v_x = BAR_VELOCITY;
							} 
							else {
								play2_bar.v_x = BAR_VELOCITY;
							}
						}
						else if(click== KeyEvent.VK_LEFT){
							System.out.println("left");
							if (enter_pressed == 0) {
								play2_ball.v_x = -BAR_VELOCITY;
								play2_bar.v_x = -BAR_VELOCITY;
							} else {
								play2_bar.v_x = -BAR_VELOCITY;
							}
						}
						else if(click == KeyEvent.VK_D){
							System.out.println("d");
							if (space_pressed == 0) {
								play1_ball.v_x = BAR_VELOCITY;
								play1_bar.v_x = BAR_VELOCITY;
							} else {
								play1_bar.v_x = BAR_VELOCITY;
							}
						}
						else if(click ==KeyEvent.VK_A){
							System.out.println("a");
							if (space_pressed == 0) {
								System.out.println("a");
								play1_ball.v_x = -BAR_VELOCITY;
								play1_bar.v_x = -BAR_VELOCITY;
							} else {
								play1_bar.v_x = -BAR_VELOCITY;
							}
						}
					}
				}
			}
			@Override
			public synchronized void keyReleased(KeyEvent e) {
				System.out.println("before " + pressed);
				pressed.remove(e.getKeyCode());
			
				if (enter_pressed == 0) {
					play2_ball.v_x = 0;
					play2_bar.v_x = 0;
				} 
				else if(enter_pressed == 1){
					play2_bar.v_x = 0;
				}
				if (space_pressed == 0) {
					play1_ball.v_x = 0;
					play1_bar.v_x = 0;
				} 
				else if(space_pressed == 1){
					play1_bar.v_x = 0;
				}
			}
		});
		//System.out.println(pressed);

		setFocusable(true);
		this.play1_status = play1_status;
		this.play2_status = play2_status;
		this.play1_lives = play1_lives;
		this.play2_lives = play2_lives;
		this.play1_score = play1_score;
		this.play2_score = play2_score;
		this.times = times;

	}

	public void tick() {
		time++;
		times.setText("Time: " + time);

		if (playing) {
			play1_bar.move();
			play2_bar.move();
			play1_ball.move();
			play2_ball.move();
		}

		if(play2_ball.pos_y <= 300){
			play2_ball.v_y = 5;
		}
		
		play1_ball.bounce(play1_ball.hitWall());
		play2_ball.bounce(play2_ball.hitWall());

		if (play1_ball.willIntersect(play1_bar)) {
			play1_ball.bounce(play1_ball.hitObj(play1_bar));
		}
		if (play2_ball.willIntersect(play2_bar)) {
			play2_ball.bounce(play2_ball.hitObj(play2_bar));
		}

		for (int r = 0; r < play1_blocks.length; r++) {
			for (int c = 0; c < play1_blocks[0].length; c++) {
				Blocks check = play1_blocks[r][c];
				if (check != null) {
					if (play1_ball.willIntersect(check)) {
						play1_ball.bounce(play1_ball.hitObj(check));
						check.setHit(true);
						play1_hitBlocks.add(check);
						play1_blocks[r][c] = null;
						play1_num_score = play1_hitBlocks.size() * 100;
						play1_score.setText("Score: " + play1_num_score
								+ " Level: " + level + " Blocks Hit: "
								+ play1_hitBlocks.size() + " out of "
								+ num_blocks);
					} else {
						check.setHit(false);
						play1_num_score = play1_hitBlocks.size() * 100;
						play1_score.setText("Score: " + play1_num_score
								+ " Level: " + level + " Blocks Hit: "
								+ play1_hitBlocks.size() + " out of "
								+ num_blocks);
					}
				}
			}
		}

		for (int r = 0; r < play2_blocks.length; r++) {
			for (int c = 0; c < play2_blocks[0].length; c++) {
				Blocks check = play2_blocks[r][c];
				if (check != null) {
					if (play2_ball.willIntersect(check)) {
						play2_ball.bounce(play2_ball.hitObj(check));
						check.setHit(true);
						play2_hitBlocks.add(check);
						play2_blocks[r][c] = null;
						play2_num_score = play2_hitBlocks.size() * 100;
						play2_score.setText("Score: " + play2_num_score
								+ " Level: " + level + " Blocks Hit: "
								+ play2_hitBlocks.size() + " out of "
								+ num_blocks);
					} else {
						check.setHit(false);
						play2_num_score = play2_hitBlocks.size() * 100;
						play2_score.setText("Score: " + play2_num_score
								+ " Level: " + level + " Blocks Hit: "
								+ play2_hitBlocks.size() + " out of "
								+ num_blocks);
					}
				}
			}
		}

		if (time % 500 == 0) {
			for (int r = 0; r < play1_blocks.length; r++) {
				for (int c = 0; c < play1_blocks[0].length; c++) {
					if (play1_blocks[r][c] != null)
						play1_blocks[r][c].moveDown();
					if (play2_blocks[r][c] != null)
						play2_blocks[r][c].moveDown();
					
				}
			}
		}

		if (play1_hitBlocks.size() == num_blocks) {
			playing = false;
			play1_status.setText("Player 1 wins you hit all the blocks!");
			play2_status.setText("Player 2 lost!");
			play1_score.setText("Final Score: " + play1_num_score);
			play2_score.setText("Final Score: " + play2_num_score);
		} else if (play2_hitBlocks.size() == num_blocks) {
			playing = false;
			play1_status.setText("Player 2 hit all the blocks! Player 1 lost!");
			play2_status.setText("Player 2 wins you hit all the blocks!");
			play1_score.setText("Final Score: " + play1_num_score);
			play2_score.setText("Final Score: " + play2_num_score);
		}

		if (play1_ball.pos_y >= 300) {
			play1_num_lives--;
			space_pressed = 0;
			if (play1_num_lives == 0) {
				playing = false;
				play1_status.setText("Out of Lives! Player 1 lost");
				play2_status.setText("Player 1 is out of lives. Player 2 wins!");
				play1_score.setText("Final Score: " + play1_num_score);
				play2_score.setText("Final Score: " + play2_num_score);
			} else if(play1_num_lives > 0) {
				playing = true;
				play1_status.setText("Lost a life :(");
				play1_lives.setText("Lives: " + play1_num_lives);
				play1_score.setText("Score: " + play1_num_score + " Level: "
						+ level + " Blocks Hit: " + play1_hitBlocks.size());
				play1_ball = new Circle(410, 240, COURT_WIDTH, COURT_HEIGHT);
				play1_bar = new Rectangle(380, 260, COURT_WIDTH, COURT_HEIGHT);
			}
		}

		if (play2_ball.pos_y >= play2_ball.max_y) {
			play2_num_lives--;
			enter_pressed = 0;
			if (play2_num_lives == 0) {
				playing = false;
				play2_status.setText("Out of Lives! Player 2 lost");
				play1_status.setText("Player 1 wins!");
				play1_score.setText("Final Score: " + play1_num_score);
				play2_score.setText("Final Score: " + play2_num_score);
			} else if (play2_num_lives > 0){
				playing = true;
				play2_status.setText("Lost a life :(");
				play2_lives.setText("Lives: " + play2_num_lives);
				play2_score.setText("Score: " + play2_num_score + " Level: "
						+ level + " Blocks Hit: " + play2_hitBlocks.size());
				play2_ball = new Circle(410, 545, COURT_WIDTH, COURT_HEIGHT);
				play2_bar = new Rectangle(380, 565, COURT_WIDTH, COURT_HEIGHT);
			}
		}

		for (int r = 0; r < play1_blocks.length; r++) {
			for (int c = 0; c < play1_blocks[0].length; c++) {
				if (play1_blocks[r][c] != null
						&& play1_blocks[r][c].pos_y >= play1_blocks[r][c].max_y) {
					playing = false;
					play1_status.setText("Player 1 Loses");
					play2_status.setText("Player 2 Wins!");
					play1_score.setText("Score: " + play1_num_score
							+ " Level: " + level + " Blocks Hit: "
							+ play1_hitBlocks.size());
					play2_score.setText("Score: " + play2_num_score
							+ " Level: " + level + " Blocks Hit: "
							+ play2_hitBlocks.size());
				}
			}
		}

		for (int r = 0; r < play2_blocks.length; r++) {
			for (int c = 0; c < play2_blocks[0].length; c++) {
				if (play2_blocks[r][c] != null
						&& play2_blocks[r][c].pos_y >= play2_blocks[r][c].max_y) {
					playing = false;
					play1_status.setText("Player 1 Wins!");
					play2_status.setText("Player 2 Loses!");
					play1_score.setText("Score: " + play1_num_score
							+ " Level: " + level + " Blocks Hit: "
							+ play1_hitBlocks.size());
					play2_score.setText("Score: " + play2_num_score
							+ " Level: " + level + " Blocks Hit: "
							+ play2_hitBlocks.size());
				}
			}
		}
		repaint();
	}

	public void quit() {
		playing = false;
		System.exit(0);
	}

	public void back() {
		IntroWindow intro = new IntroWindow();
		intro.run();
	}

	public void reset() {
		play1_ball = new Circle(410, 240, COURT_WIDTH, COURT_HEIGHT);
		play1_bar = new Rectangle(380, 260, COURT_WIDTH, COURT_HEIGHT);
		play1_hitBlocks = new ArrayList<Blocks>();
		play1_num_lives = 3;
		play1_num_score = 0;
		play2_ball = new Circle(410, 545, COURT_WIDTH, COURT_HEIGHT);
		play2_bar = new Rectangle(380, 565, COURT_WIDTH, COURT_HEIGHT);
		play2_hitBlocks = new ArrayList<Blocks>();
		play2_num_lives = 3;
		play2_num_score = 0;
		time = 0;
		space_pressed = 0;

		try {
			makeLevel(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		playing = true;
		play1_status.setText("Playing...");
		play1_lives.setText("Lives: " + play1_num_lives);
		play1_score.setText("Score: " + play1_num_score + " Level: " + level
				+ " Blocks Hit: " + play1_hitBlocks.size() + " out of "
				+ num_blocks);
		play2_status.setText("Playing...");
		play2_lives.setText("Lives: " + play2_num_lives);
		play2_score.setText("Score: " + play2_num_score + " Level: " + level
				+ " Blocks Hit: " + play2_hitBlocks.size() + " out of "
				+ num_blocks);
		times.setText("Time: " + times);

		requestFocusInWindow();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (playing) {
			play1_ball.draw(g);
			play2_ball.draw(g);
		}
		play1_bar.draw(g);
		play2_bar.draw(g);

		for (int i = 0; i < play1_blocks.length; i++) {
			for (int j = 0; j < play1_blocks[0].length; j++) {
				Blocks check = play1_blocks[i][j];
				if (check != null) {
					if (check.hasHit() == false) {
						check.draw(g);
						if (j % 2 == 0 && i % 2 == 0) {
							check.setColors(g, Color.red);
						} else if (j % 2 != 0 && i % 2 != 0) {
							check.setColors(g, Color.red);
						} else if (j % 2 != 0 && i % 2 == 0) {
							check.setColors(g, Color.blue);
						} else {
							check.setColors(g, Color.blue);
						}
					}
				}
			}
		}

		for (int i = 0; i < play2_blocks.length; i++) {
			for (int j = 0; j < play2_blocks[0].length; j++) {
				Blocks check = play2_blocks[i][j];
				if (check != null) {
					if (check.hasHit() == false) {
						check.draw(g);
						if (j % 2 == 0 && i % 2 == 0) {
							check.setColors(g, Color.green);
						} else if (j % 2 != 0 && i % 2 != 0) {
							check.setColors(g, Color.green);
						} else if (j % 2 != 0 && i % 2 == 0) {
							check.setColors(g, Color.yellow);
						} else {
							check.setColors(g, Color.yellow);
						}
					}
				}
			}
		}
	}

}
