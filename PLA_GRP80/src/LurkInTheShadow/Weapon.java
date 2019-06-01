package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Weapon extends Component {

	public Weapon(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
			boolean show) {
		super(m, x, y, w, h, scale, sprite, rows, col, id_x, show);
	}

	public Enemy doesDamageTo(int stepX, int stepY) {
		Component comp;
		Iterator<Component> iter = inCollisionWith(stepX, stepY).iterator();
		while (iter.hasNext()) {
			comp = iter.next();
			if (comp instanceof Enemy) {
				return (Enemy) comp;
			}
		}

		return null;
	}
}
