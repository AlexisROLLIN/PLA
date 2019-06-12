package LurkInTheShadow;

import java.awt.image.BufferedImage;

import interpreter.IAutomaton;

public class Obstacle extends Component {

	public Obstacle(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);
		m_type = IType.OBSTACLE;
		automate=model.obst;

	}
	
}