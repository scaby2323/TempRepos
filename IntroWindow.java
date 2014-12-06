import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class IntroWindow implements Runnable{

	@Override
	public void run() {
		
		//Top-level frame with the opening
		final JFrame frame = new JFrame("BrickBreaker"); 
		frame.setLocation(300,300);
		
		final JPanel welcome_panel = new JPanel(); 
		frame.add(welcome_panel, BorderLayout.NORTH);
		final JLabel welcome = new JLabel("Welcome to BrickBreaker!"); 
		welcome_panel.add(welcome); 
		
		final JPanel control_panel = new JPanel(); 
		frame.add(control_panel, BorderLayout.PAGE_END); 
		
		final JButton start = new JButton("Start Game");
		final JButton instructions = new  JButton("How To Play");
		final JButton quit = new JButton("Quit"); 
		
		final IntroScreen screen = new IntroScreen(start, instructions, quit); 
		frame.add(screen, BorderLayout.CENTER); 
			
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				screen.start(); 
			}
		}); 
		control_panel.add(start); 
		
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
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new IntroWindow());
	}
}
	
