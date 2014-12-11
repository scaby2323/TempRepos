/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public void run() {


		final JFrame frame = new JFrame("BrickBreaker");
		frame.setLocation(300, 300);

		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Running...");
		status_panel.add(status);
		final JLabel lives = new JLabel("Lives: 3"); 
		status_panel.add(lives); 
		final JLabel score = new JLabel("Score: 0"); 
		status_panel.add(score); 
		final JLabel times = new JLabel("Time: 0"); 
		status_panel.add(times); 


		final GameCourt court = new GameCourt(status, lives, score,times);
		frame.add(court, BorderLayout.CENTER);

		
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);
		
		//Reset button
		final JButton reset = new JButton("New Game");
		//Quit button
		final JButton quit = new JButton("Quit"); 
		final JButton back = new JButton("Main Menu"); 
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		control_panel.add(reset);
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				court.quit(); 
			}
		});
		control_panel.add(quit); 
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				court.back(); 
			}
		});
		control_panel.add(back); 

		
		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
