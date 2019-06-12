package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.util.ListIterator;

import interpreter.IAutomaton;

public class Monster extends Component {
	
	int speed;
	
	public Monster(Model model, BufferedImage sprite, int rows,
			int columns, int x, int y,float scale, int id_x, boolean show) {
		
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);
		speed=8;
		m_type=IType.ADVERSAIRE;
		automate=model.monster;
		model.monstres.add(this);
		power=5;
		life=20;
	}
	
	@Override
	public boolean move(IDirection d) { //Graphiques non geres

			if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
					|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {
				m_y -= speed;
				m_dir = IDirection.NORTH;
				System.out.println("Avance au Nord\n");
			}

			else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
					|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
				m_y += speed;
				m_dir = IDirection.SOUTH;
				System.out.println("Avance au Sud \n");
			}

			else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
					|| (m_dir == IDirection.EAST && d == IDirection.BACK)
					|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
					|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {
				m_x -= speed;
				m_dir = IDirection.WEST;
				System.out.println("Avance à l'Ouest \n");
			}

			else {
				m_x += speed;
				m_dir = IDirection.EAST;
				System.out.println("Avance à l'Est \n");
			}
		return true; // L'action s'est bien déroulée
	}

	@Override
	public boolean hit(IDirection d) {

		if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
				|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {

			ListIterator<Ally> iter = this.m_model.allies.listIterator();
			Ally all;
			while (iter.hasNext()) {
				all = iter.next();
				if (all.is_in_case(m_x, m_y - 32)) {
					all.life = all.life - power; // L'ennemi perd des pv
				}
			}
		}

		else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
				|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {

			ListIterator<Ally> iter = this.m_model.allies.listIterator();
			Ally all;
			while (iter.hasNext()) {
				all = iter.next();
				if (all.is_in_case(m_x, m_y + 32)) {
					all.life = all.life - power;
				}
			}
		}

		else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
				|| (m_dir == IDirection.EAST && d == IDirection.BACK)
				|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
				|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {

			ListIterator<Ally> iter = this.m_model.allies.listIterator();
			Ally all;
			while (iter.hasNext()) {
				all = iter.next();
				if (all.is_in_case(m_x - 32, m_y)) {
					all.life = all.life - power;
				}
			}

		}

		else {

			ListIterator<Ally> iter = this.m_model.allies.listIterator();
			Ally all;
			while (iter.hasNext()) {
				all = iter.next();
				if (all.is_in_case(m_x + 32, m_y)) {
					all.life = all.life - power;
				}
			}
		}

		return true;
	}

	// Diminution de la periode de marche/Augmentation la vitesse
	public boolean Pop(IDirection d) {
		speed += 32;
		return true;
	}

	// Augmentation de la periode de marche/Diminution de la vitesse
	public boolean Wizz(IDirection d) {
		speed -= 32;
		return true;
	}

	public boolean turn(IDirection d) {

		if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
				|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {
			m_dir = IDirection.NORTH;
		}

		else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
				|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
			m_dir = IDirection.SOUTH;
		}

		else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
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
	
	public boolean kamikaze() {
		m_model.componentsToRemove.add(this);
		return true;
	}

}
