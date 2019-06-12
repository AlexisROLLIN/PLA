package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.util.ListIterator;

import interpreter.IAutomaton;

public class Queen extends Component { // Changement sprites à faire !!

	int periode_ponte;
	int periode_marche;
	int marche;
	int ponte;
	int hunger;

	public Queen(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale,
			int id_x, boolean show) {

		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);
		life = 100;// inutile théoriquement, mais sécurité
		power = 3;
		periode_marche = 3;// Marche à une freq de 1/3
		marche = 1;
		ponte=5000;
		speed = 32;
		hunger = 0; //faim maximum = 100
		m_type=IType.ADVERSAIRE;
		automate=model.queen;
		power=100;
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
				
				if(m_type==IType.PLAYER) {
					m_model.map.iViewport--;
					if(m_model.mainPlayed.m_y<0){
						m_model.mainPlayed.m_y=1504;
						m_model.map.iViewport=59;
					}
				}
			}

			else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
					|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
				m_y += speed;
				m_dir = IDirection.SOUTH;
				System.out.println("Avance au Sud \n");
				
				if(m_type==IType.PLAYER) {
					m_model.map.iViewport++;
					if(m_model.mainPlayed.m_y>1504){
						m_model.mainPlayed.m_y=0;
						m_model.map.iViewport=12;
					}
				}
			}

			else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
					|| (m_dir == IDirection.EAST && d == IDirection.BACK)
					|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
					|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {
				m_x -= speed;
				m_dir = IDirection.WEST;
				System.out.println("Avance à l'Ouest \n");
				
				if(m_type==IType.PLAYER) {
					m_model.map.jViewport--;
					if(m_model.mainPlayed.m_x<0){
						m_model.mainPlayed.m_x=2016;
						m_model.map.jViewport=79;
					}
				}
			}

			else {
				m_x += speed;
				m_dir = IDirection.EAST;
				System.out.println("Avance à l'Est \n");
				
				if(m_type==IType.PLAYER) {
					m_model.map.jViewport++;
					if(m_model.mainPlayed.m_x>2016){
						m_model.mainPlayed.m_x=0;
						m_model.map.jViewport=16;
					}
				}
			}
			marche = 1;
		} else {
			marche++;
		}

		return true; // L'action s'est bien déroulée
	}

	@Override
	public boolean hit(IDirection d) { // /!\ Ne change pas de sprite

		hunger = hunger + power/10; // On dit qu'un Hit donne faim proportionnellement à sa puissance

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
	
	@Override
	public boolean egg(){
		
		if(ponte>(100-hunger)/30 && m_model.monstres.size()<=30) { //Pond une fois tous les (100-hunger)/30 steps egg

			m_model.componentsToAdd.add(new Monster(m_model, m_sprite, m_nrows, m_ncols, m_x, m_y, m_scale, 17, m_show));

			ponte=1;
		}
		else {
			ponte++;
		}
		return true;
	}
}
