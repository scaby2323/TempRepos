import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameTwo implements Runnable {
	public void run(){
		final JFrame frame = new JFrame("Two Player BrickBreaker");
		frame.setLocation(500,500); 
		
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel play1_status = new JLabel("Running...");
		status_panel.add(play1_status);
		final JLabel play1_lives = new JLabel("Player 1 Lives: 3"); 
		status_panel.add(play1_lives); 
		final JLabel play1_score = new JLabel("Player 1 Score: 0"); 
		status_panel.add(play1_score); 
		final JLabel times = new JLabel("Time: 0"); 
		status_panel.add(times); 
		
		
		
		final JLabel play2_status = new JLabel("");
		status_panel.add(play2_status);
		final JLabel play2_lives = new JLabel("Player 2 Lives: 3"); 
		status_panel.add(play2_lives); 
		final JLabel play2_score = new JLabel("Player 2 Score: 0"); 
		status_panel.add(play2_score); 

		final GameCourtTwo court = new GameCourtTwo(play1_status, play1_lives, play1_score, play2_status, play2_lives, play2_score, times);
		frame.add(court, BorderLayout.CENTER);
		
		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 

		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);
		
		final JButton reset = new JButton("New Game");
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

		// Start game
		court.reset();

	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());

}
}
