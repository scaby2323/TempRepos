import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class IntroScreen extends JPanel {
	
	public IntroScreen(JButton start, JButton instructions, JButton quit){
	
	setBorder(BorderFactory.createLineBorder(Color.BLACK));

	}
	
	public void start() {
		Game game = new Game();
		game.run(); 	
	}

	public void instructions() {
		Instructions instruct = new Instructions(); 
		instruct.run(); 
	}

	public void quit() {
		System.exit(0);
	}
	
	
	
	

}
