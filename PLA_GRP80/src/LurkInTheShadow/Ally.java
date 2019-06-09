package LurkInTheShadow;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import interpreter.IAutomaton;

public class Ally extends Component {
	
	public Ally(Model model, BufferedImage sprite, int rows, int columns, int x, int y, int h, int w, float scale,
				int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, h, w, scale, id_x, show);
		model.allies.add(this);
		
	}
	
	
	boolean Vision(Component c) {
		int x = 50;
		int y = 50;
		Ellipse2D.Double player = new Ellipse2D.Double(this.m_x - x, this.m_y - y, 2.5 * x, 2.5 * y);
		Rectangle objet = c.getBounds();

		if (player.intersects(objet)) {
			return true;
		}
		return false;
		//return true; //Pour tests
	}

	public void Afficher() {
		Iterator<Component> iter = m_model.components.listIterator();

		while (iter.hasNext()) {
			Component c = iter.next();
			if (Vision(c)) {
				c.m_show = true;
			} else {
				c.m_show = false;
			}
		}
	}

}
/*(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
	 boolean show,  int screen) {*/