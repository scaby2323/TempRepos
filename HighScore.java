
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter; 
import java.io.PrintWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class HighScore {
	
	public void run(){
		final JFrame frame = new JFrame("High Scores");
		frame.setLocation(300,300); 
		
		final JPanel scores_panel = new JPanel(); 
		frame.add(scores_panel, BorderLayout.NORTH);
		final JLabel scores = new JLabel("read file with scores!"); 
		scores_panel.add(scores); 
		
		final HighScore_Screen high_score = new HighScore_Screen(); 
		frame.add(high_score, BorderLayout.CENTER); 
		
		final JButton back = new JButton("Main Menu");
		final JPanel control_panel = new JPanel(); 
		frame.add(control_panel, BorderLayout.SOUTH); 
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				high_score.back(); 
			}
		});
		control_panel.add(back); 
		
		final JButton start = new JButton("Start Game"); 
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				high_score.start(); 
			}
		});
		control_panel.add(start); 
		
		frame.pack(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
		
	}
		
	
}
