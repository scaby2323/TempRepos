import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Instructions implements Runnable{
	public void run(){
		final JFrame frame = new JFrame("BrickBreaker"); 
		frame.setLocation(300,300);
		
		final JPanel info_panel = new JPanel(); 
		frame.add(info_panel,BorderLayout.EAST); 
		final JLabel instructions = new JLabel("blah blah blah you know"
				+ "how to play the game\n don't be dumb"); 
		info_panel.add(instructions); 
		
		final JPanel feat_panel = new JPanel(); 
		frame.add(feat_panel, BorderLayout.WEST); 
		final JLabel features = new JLabel("blah blah my games so cool "
				+ "but actually probs not"); 
		feat_panel.add(features); 
		
		final Instructions_Screen instruct = new Instructions_Screen(); 
		frame.add(instruct, BorderLayout.CENTER); 
		
		final JButton start = new JButton("Start Game"); 
		final JPanel control_panel = new JPanel(); 
		frame.add(control_panel, BorderLayout.SOUTH); 
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instruct.start();
			}
		});
		control_panel.add(start);
		
		final JButton quit = new JButton("Quit"); 
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				instruct.quit(); 
			}
		});
		control_panel.add(quit); 
		
		final JButton back = new JButton("Back"); 
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				instruct.back(); 
			}
			
		});
		control_panel.add(back); 
		
		
		
		frame.pack(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}
	
}
