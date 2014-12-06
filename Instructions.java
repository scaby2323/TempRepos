import java.awt.BorderLayout;

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
		
		frame.pack(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new IntroWindow());
		}
	
}
