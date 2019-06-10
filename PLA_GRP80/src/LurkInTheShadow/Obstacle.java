package LurkInTheShadow;

import java.awt.image.BufferedImage;

<<<<<<< HEAD
import interpreter.IAutomaton;

public class Obstacle extends Component {

	public Obstacle(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight()*(int)scale, sprite.getWidth()*(int)scale, scale, id_x, show);
		m_idx = 0;
		m_type = IType.OBSTACLE;
		automate=model.obst;
=======
public class Obstacle extends Component {
	
	
	
	public Obstacle(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale, int screen) {
		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale,screen);
		boolean m_show; //Explication
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
>>>>>>> tmpGaetan
	}
	
}
