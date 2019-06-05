package create;

import java.awt.Dimension;



import LurkInTheShadow.Controller;
import LurkInTheShadow.Model;
import LurkInTheShadow.View;
import edu.ricm3.game.GameUI;




public class main_test_map {
	public static void main(String[] args) {
		 Model model = new Model();
		    LurkInTheShadow.View view = new View(model);
		    Controller controller = new Controller(model,view);

		    Dimension d = new Dimension(1024, 768);
		    new GameUI(model,view,controller,d);
	}
}
