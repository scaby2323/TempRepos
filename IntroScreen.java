import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class IntroScreen extends JPanel {
	
	public IntroScreen(JButton start, JButton instructions, JButton quit){
	
	setBorder(BorderFactory.createLineBorder(Color.BLACK));

	}

	public void instructions() {
		Instructions instruct = new Instructions(); 
		instruct.run(); 
	}

	public void quit() {
		System.exit(0);
	}

	public void one_play() {
		Game game = new Game();
		game.run(); 	
		
	}

	public void two_play() {
		GameTwo game = new GameTwo(); 
		game.run(); 
	}

	public void scores() {
		HighScore score = new HighScore(); 
		score.run(); 
		
	}
	
	
	
	

}
