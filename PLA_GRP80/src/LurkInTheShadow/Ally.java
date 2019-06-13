package LurkInTheShadow;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import interpreter.IAutomaton;

public class Ally extends Component {

	int myAmmo;

	public Ally(Model model, BufferedImage sprite, int rows, int columns,
			int x, int y, int h, int w, float scale, int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, h, w, scale, id_x, show);
		model.allies.add(this);
		speed = 32;
		life = 100;
		puissance_eclairage = 2;

	}

	@Override
	public boolean move(IDirection d) { // Graphiques non geres

		if (d == IDirection.NORTH
				|| (m_dir == IDirection.NORTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
				|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {
			m_y -= speed;
			m_dir = IDirection.NORTH;
			// System.out.println("Avance au Nord\n");

			if (m_type == IType.PLAYER) {
				m_model.map.iViewport--;
				if (m_model.mainPlayed.m_y < 0) {
					m_model.mainPlayed.m_y = 1504;
					m_model.map.iViewport = 59;
				}
			}
		}

		else if (d == IDirection.SOUTH
				|| (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
				|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
			m_y += speed;
			m_dir = IDirection.SOUTH;
			// System.out.println("Avance au Sud \n");

			if (m_type == IType.PLAYER) {
				m_model.map.iViewport++;
				if (m_model.mainPlayed.m_y > 1504) {
					m_model.mainPlayed.m_y = 0;
					m_model.map.iViewport = 12;
				}
			}
		}

		else if (d == IDirection.WEST
				|| (m_dir == IDirection.WEST && d == IDirection.FRONT)
				|| (m_dir == IDirection.EAST && d == IDirection.BACK)
				|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
				|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {
			m_x -= speed;
			m_dir = IDirection.WEST;
			// System.out.println("Avance à l'Ouest \n");

			if (m_type == IType.PLAYER) {
				m_model.map.jViewport--;
				if (m_model.mainPlayed.m_x < 0) {
					m_model.mainPlayed.m_x = 2016;
					m_model.map.jViewport = 79;
				}
			}
		}

		else {
			m_x += speed;
			m_dir = IDirection.EAST;
			// System.out.println("Avance à l'Est \n");

			if (m_type == IType.PLAYER) {
				m_model.map.jViewport++;
				if (m_model.mainPlayed.m_x > 2016) {
					m_model.mainPlayed.m_x = 0;
					m_model.map.jViewport = 16;
				}
			}
		}
		return true; // L'action s'est bien déroulée
	}

	public boolean turn(IDirection d) {

		if (d == IDirection.NORTH
				|| (m_dir == IDirection.NORTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
				|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {
			m_dir = IDirection.NORTH;
		}

		else if (d == IDirection.SOUTH
				|| (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
				|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
			m_dir = IDirection.SOUTH;
		}

		else if (d == IDirection.WEST
				|| (m_dir == IDirection.WEST && d == IDirection.FRONT)
				|| (m_dir == IDirection.EAST && d == IDirection.BACK)
				|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
				|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {
			m_dir = IDirection.WEST;
		}

		else {
			m_dir = IDirection.EAST;
		}

		return true;
	}

	// A appeler quand !IGotPower
	public boolean kamikaze() {
		m_model.allies.remove(this);
		m_model.componentsToRemove.add(this);
		return true;
	}

	@Override
	public boolean jump() {
		if (m_model.battery.m_durability != 0
				&& this.m_model.mainPlayed.lampe_x == 50) {
			this.m_model.mainPlayed.lampe_x += 25;
			this.m_model.mainPlayed.lampe_y += 25;
			this.m_model.mainPlayed.lampe_width = 2.4 * this.m_model.mainPlayed.lampe_x;
			this.m_model.mainPlayed.lampe_height = 2.4 * this.m_model.mainPlayed.lampe_y;
			this.m_model.mainPlayed.puissance_eclairage = 2;
		} else if (m_model.battery.m_durability != 0
				&& this.m_model.mainPlayed.lampe_x == 75) {
			this.m_model.mainPlayed.lampe_x += 25;
			this.m_model.mainPlayed.lampe_y += 25;
			this.m_model.mainPlayed.lampe_width = 2.3 * this.m_model.mainPlayed.lampe_x;
			this.m_model.mainPlayed.lampe_height = 2.3 * this.m_model.mainPlayed.lampe_y;
			this.m_model.mainPlayed.puissance_eclairage = 3;
		} else if (m_model.battery.m_durability != 0
				&& this.m_model.mainPlayed.lampe_x == 100) {
			this.m_model.mainPlayed.lampe_x += 30;
			this.m_model.mainPlayed.lampe_y += 30;
			this.m_model.mainPlayed.lampe_width = 2.25 * this.m_model.mainPlayed.lampe_x;
			this.m_model.mainPlayed.lampe_height = 2.25 * this.m_model.mainPlayed.lampe_y;
			this.m_model.mainPlayed.puissance_eclairage = 4;
		}

		return true;
	}

	@Override
	public boolean Throw() {
		if (this.m_model.mainPlayed.lampe_x == 75) {
			this.m_model.mainPlayed.lampe_x -= 25;
			this.m_model.mainPlayed.lampe_y -= 25;
			this.m_model.mainPlayed.lampe_width = 2.5 * this.m_model.mainPlayed.lampe_x;
			this.m_model.mainPlayed.lampe_height = 2.5 * this.m_model.mainPlayed.lampe_y;
			this.m_model.mainPlayed.puissance_eclairage = 1;
		} else if (this.m_model.mainPlayed.lampe_x == 100) {
			this.m_model.mainPlayed.lampe_x -= 25;
			this.m_model.mainPlayed.lampe_y -= 25;
			this.m_model.mainPlayed.lampe_width = 2.4 * this.m_model.mainPlayed.lampe_x;
			this.m_model.mainPlayed.lampe_height = 2.4 * this.m_model.mainPlayed.lampe_y;
			this.m_model.mainPlayed.puissance_eclairage = 2;
		} else if (this.m_model.mainPlayed.lampe_x == 130) {
			this.m_model.mainPlayed.lampe_x -= 30;
			this.m_model.mainPlayed.lampe_y -= 30;
			this.m_model.mainPlayed.lampe_width = 2.3 * this.m_model.mainPlayed.lampe_x;
			this.m_model.mainPlayed.lampe_height = 2.3 * this.m_model.mainPlayed.lampe_y;
			this.m_model.mainPlayed.puissance_eclairage = 3;
		}
		return true;
	}

	public boolean pick() {
		Iterator<Component> iter = m_model.components.iterator();

		while (iter.hasNext()) {
			Component c = iter.next();

			if (c.m_x == this.m_x && c.m_y == this.m_y && c instanceof Items) {
				if (((Items) c).itemType == 1) {
					myAmmo += 5;
					m_model.nbAmmo--;
					m_model.componentsToRemove.add(c);
					m_model.items.remove(c);
					return true;
				}
				if (((Items) c).itemType == 2) {
					m_model.nbBattery--;
					m_model.battery.refill(Options.MAX_DURABILITY);
					m_model.componentsToRemove.add(c);
					m_model.items.remove(c);
					return true;
				}
				if (((Items) c).itemType == 3) {
					m_model.nbCmd--;
					c.GetQueen();
					m_model.componentsToRemove.add(c);
					m_model.items.remove(c);
					return true;
				}
				if (((Items) c).itemType == 4) {
					m_model.nbLife--;
					this.life++;
					m_model.componentsToRemove.add(c);
					m_model.items.remove(c);
					return true;
				}
			}
		}
		return false;
	}

}
/*
 * (Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int
 * rows, int col, int id_x, boolean show, int screen) {
 */