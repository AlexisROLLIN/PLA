package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.util.ListIterator;

public class Queen extends Component { // Changement sprites à faire !!

	int periode_ponte;
	int periode_marche;
	int marche;
	int ponte;
	int speed;
	int hunger;

	public Queen(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale,
			int screen) {

		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, screen);
		m_idx = 0;
		life = 100;// inutile théoriquement, mais sécurité
		power = 3;
		m_show = true;
		periode_marche = 3;// Marche à une freq de 1/3
		marche = 1;
		ponte=1;
		speed = 32;
		hunger = 0; //fin maximum = 100
	}

	@Override
	public boolean move(IDirection d) {

		hunger = hunger + (1 / periode_marche * speed)/2; // On dit qu'un Hit donne faim proportionnellement à sa vitesse de
														// deplacement

		if (marche >= periode_marche) {
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
			marche = 1;
		} else {
			marche++;
		}

		return true; // L'action s'est bien déroulée
	}

	@Override
	public boolean hit(IDirection d) { // /!\ Ne change pas de sprite

		hunger = hunger + power; // On dit qu'un Hit donne faim proportionnellement à sa puissance

		if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
				|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
				|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
				|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {

			ListIterator<Ally> iter = this.m_model.allies.listIterator();
			Ally all = iter.next();
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
			Ally all = iter.next();
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
			Ally all = iter.next();
			while (iter.hasNext()) {
				all = iter.next();
				if (all.is_in_case(m_x - 32, m_y)) {
					all.life = all.life - power;
				}
			}

		}

		else {

			ListIterator<Ally> iter = this.m_model.allies.listIterator();
			Ally all = iter.next();
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

		hunger = hunger++; // On dit qu'un Pop ne donne pas très faim à la Reine

		if (periode_marche > 0) {
			periode_marche--;
		} else { // Marche à chaque step
			speed += 32;
		}
		return true;
	}

	// Augmentation de la periode de marche/Diminution de la vitesse
	public boolean Wizz(IDirection d) {

		hunger = hunger++; // On dit qu'un Wizz ne donne pas très faim à la Reine

		if (speed <= 32) {
			speed -= 32;
		} else {
			periode_marche++;
		}
		return true;
	}

	public boolean turn(IDirection d) {

		hunger = hunger++;

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
	
	public boolean egg(){

		if(ponte>(100-hunger)/30) { //Pond une fois tous les (100-hunger)/30 steps egg
			new Monster(m_model, int no, BufferedImage sprite, int rows, int columns, m_x, m_y, 1, int screen);
			ponte=1;
		}
		else {
			ponte++;
		}
		return true;
	}
}
