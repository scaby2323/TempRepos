import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class Instructions_Screen extends JPanel{

	
	public Instructions_Screen(){
		setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		
	}

	public void start() {
		Game game = new Game();
		game.run(); 	
	}
	
	public void quit() {
		System.exit(0);
	}

	public void back() {
		IntroWindow intro = new IntroWindow(); 
		intro.run(); 
		
	}

}

