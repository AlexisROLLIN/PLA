package create;

import java.awt.Dimension;

import edu.ricm3.game.GameUI;




public class main_test_map {
	public static void main(String[] args) {
		 Model model = new Model();
		    View view = new View(model);
		    Controller controller = new Controller(model,view);

		    Dimension d = new Dimension(1024, 768);
		    new GameUI(model,view,controller,d);
	}
}
