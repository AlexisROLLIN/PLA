package mesProto;

import java.awt.Dimension;

import edu.ricm3.game.GameUI;

public class GameMain {
	
	public static void main(String[] args) {
	
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model,view);

		Dimension d = new Dimension(1920, 1024);
		new GameUI(model,view,controller,d);
    
    return;
  }

}
