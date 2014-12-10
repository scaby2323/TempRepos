import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class HighScore_Screen extends JPanel {
	
	public HighScore_Screen(){
		setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
	}
	
	public void back(){
		IntroWindow intro = new IntroWindow();
		intro.run(); 
	}
	
	public void start(){
		Game game = new Game(); 
		game.run(); 
	}

}
