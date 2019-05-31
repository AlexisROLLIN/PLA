package tests;

import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;

public class Component {

	// Classe component de test très sommaire
	// Entre autres, un champ direction devra etre ajouté

	int power;
	int m_x, m_y;
	Model model;

	// A ajouter dans le vrai component
	IAutomaton automate;
	Direction m_dir; //doit etre NORTH,SOUTH,EAST ou WEST
	Type m_type; //Definit le type (allié, ennemi, rocher, etc) de ce component.
	//Ce type est utile lors de l'interprétation, notamment pour ICell

	public Component(int ax, int ay,int apower,Model m){
		m_x= ax;
		m_y=ay;
		power=apower;
		model=m;
		m_dir=Direction.NORTH;
		m.comp.add(this);
	}

	public void setAutomate(IAutomaton aut) {
		automate = aut;
	}

	public void setType(Type type) {
		m_type=type;
	}
	
	public int power() {
		return power;
	}

	public int x() {
		return m_x;
	}

	public int y() {
		return m_y;
	}

	public Direction dir() {
		return m_dir;
	}
	
	// Avec param
	public boolean move(Direction d) {

		if (d == Direction.NORTH || (m_dir == Direction.NORTH && d == Direction.FRONT)
				|| (m_dir == Direction.SOUTH && d == Direction.BACK)
				|| (m_dir == Direction.WEST && d == Direction.RIGHT)
				|| (m_dir == Direction.EAST && d == Direction.LEFT)) {
			m_y--;
			m_dir = Direction.NORTH;
			System.out.println("Avance au Nord\n");
		}

		else if (d == Direction.SOUTH || (m_dir == Direction.SOUTH && d == Direction.FRONT)
				|| (m_dir == Direction.NORTH && d == Direction.BACK)
				|| (m_dir == Direction.EAST && d == Direction.RIGHT)
				|| (m_dir == Direction.WEST && d == Direction.LEFT)) {
			m_y++;
			m_dir = Direction.SOUTH;
			System.out.println("Avance au Sud \n");
		}

		else if (d == Direction.WEST || (m_dir == Direction.WEST && d == Direction.FRONT)
				|| (m_dir == Direction.EAST && d == Direction.BACK)
				|| (m_dir == Direction.SOUTH && d == Direction.RIGHT)
				|| (m_dir == Direction.NORTH && d == Direction.LEFT)) {
			m_x--;
			m_dir = Direction.WEST;
			System.out.println("Avance à l'Ouest \n");
		}

		else {
			m_x++;
			m_dir = Direction.EAST;
			System.out.println("Avance à l'Est \n");
		}

		return true; // L'action s'est bien déroulée
	}

	// Sans param, on suit la direction actuelle
	public boolean move() {

		switch(m_dir) {
			case NORTH:
				m_y--;
				break;
			case SOUTH:
				m_y++;
				break;
			case EAST:
				m_x++;
				break;
			default:
				m_x--;
				break;
		}
		
		return true; //L'action s'est bien déroulée
	}
	
	public void step() throws Interpreter_Exception{
		automate.step(this);
	}

	public boolean hit(Direction d) {

		// Pas le vrai hit, juste une version pour tester.
		System.out.println("Frappe\n");
		return true;// L'action s'est bien déroulée
	}

}
