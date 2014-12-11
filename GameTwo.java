import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameTwo implements Runnable {
	public void run(){
		final JFrame frame = new JFrame("Two Player BrickBreaker");
		frame.setLocation(500,500); 
		
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
		
		final GameCourtTwo court = new GameCourtTwo(status, lives, score,times);
		frame.add(court, BorderLayout.CENTER);
		
		final JLabel status2 = new JLabel("");
		status_panel.add(status2);
		final JLabel lives2 = new JLabel("Player 2 Lives: 3"); 
		status_panel.add(lives2); 
		final JLabel score2 = new JLabel("Player 2 Score: 0"); 
		status_panel.add(score2); 
		final JLabel times2 = new JLabel("Time: 0"); 
		status_panel.add(times2); 
		
		
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
