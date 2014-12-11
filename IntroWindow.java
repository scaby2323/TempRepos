import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;


public class IntroWindow{

	
	public void run() {
		
		//Top-level frame with the opening
		final JFrame frame = new JFrame("BrickBreaker"); 
		frame.setLayout(new FlowLayout()); 
		frame.setSize(400,400);
		frame.setLocation(300,300);
		
		final JPanel welcome_panel = new JPanel(); 
		frame.add(welcome_panel);
		final JLabel welcome = new JLabel("Welcome to BrickBreaker!"); 
		welcome_panel.add(welcome); 
		
		final JPanel control_panel = new JPanel(); 
		frame.add(control_panel); 
		
		final JTextField username = new JTextField("Name"); 
		username.setSize(50,50);
		final String name = username.getText(); 	
	
		control_panel.add(username); 
		
		final JButton one_play = new JButton("One Player");
		final JButton two_play = new JButton("Two Player"); 
		final JButton instructions = new  JButton("How To Play");
		final JButton quit = new JButton("Quit"); 
		final JButton scores = new JButton("High Scores"); 
		
		final IntroScreen screen = new IntroScreen(one_play, instructions, quit); 
		frame.add(screen); 
		
		one_play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				screen.one_play(name); 
			}
		}); 
		control_panel.add(one_play); 
		
		two_play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				screen.two_play(); 
			}
		});
		control_panel.add(two_play); 
		
		instructions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				screen.instructions(); 
			}
		});
		control_panel.add(instructions); 
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				screen.quit(); 
			}
		}); 
		control_panel.add(quit); 
		
		scores.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				screen.scores(); 
			}
		}); 
		control_panel.add(scores);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
		
	}

	
}
	

