import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameTwo implements Runnable {
	public void run(){
		final JFrame frame = new JFrame("Two Player BrickBreaker");
		frame.setLocation(100,300); 
		
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Running...");
		status_panel.add(status);
		final JLabel lives = new JLabel("Player 1 Lives: 3"); 
		status_panel.add(lives); 
		final JLabel score = new JLabel("Player 1 Score: 0"); 
		status_panel.add(score); 
		final JLabel times = new JLabel("Time: 0"); 
		status_panel.add(times); 
		
		final GameCourt court1 = new GameCourt(status, lives, score,times, 1);
		frame.add(court1, BorderLayout.NORTH);
		
		final JLabel status2 = new JLabel("");
		status_panel.add(status2);
		final JLabel lives2 = new JLabel("Player 2 Lives: 3"); 
		status_panel.add(lives2); 
		final JLabel score2 = new JLabel("Player 2 Score: 0"); 
		status_panel.add(score2); 
		final JLabel times2 = new JLabel("Time: 0"); 
		status_panel.add(times2); 
		
		final GameCourt court2 = new GameCourt(status2, lives2, score2, times2, 2); 
		frame.add(court2, BorderLayout.SOUTH); 
		
		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 

		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.EAST);
		
		final JButton reset = new JButton("New Game");
		final JButton quit = new JButton("Quit"); 
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court1.reset();
				court2.reset(); 
			}
		});
		control_panel.add(reset);
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				court1.quit(); 
				court2.reset(); 
			}
		});
		control_panel.add(quit); 

		// Start game
		court1.reset();

		// Start game
		court2.reset();	
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());

}
}
