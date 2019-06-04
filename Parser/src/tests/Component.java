package tests;

import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;

public class Component {

	// Classe component de test très sommaire
	// Entre autres, un champ direction devra etre ajouté

	int power;
	int m_x, m_y;
	int m_h, m_w;
	public Model model;

	// A ajouter dans le vrai component
	IAutomaton automate;
	Direction m_dir; // doit etre NORTH,SOUTH,EAST ou WEST
	Type m_type; // Definit le type (allié, ennemi, rocher, etc) de ce component.
	// Ce type est utile lors de l'interprétation, notamment pour ICell

	public Component(int ax, int ay, int aw, int ah, int apower, Model m) {
		m_x = ax;
		m_y = ay;
		m_h=ah;
		m_w=aw;
		power = apower;
		model = m;
		m_dir = Direction.NORTH;
		m.comp.add(this);
	}

	public void setAutomate(IAutomaton aut) {
		automate = aut;
	}

	public void setType(Type type) {
		m_type = type;
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
	
	public Type type() {
		return m_type;
	}

	public boolean is_in_case(int x, int y) {//x et y sont les coord de la case
		if ((m_x >= x + 32) // trop à droite
				|| (m_x + m_w <= x) // trop à gauche
				|| (m_y >= y + 32) // trop en bas
				|| (m_y + m_h <= y)) // trop en haut
			return false;
		else
			return true;
	}

	// Avec param
	public boolean move(Direction d) {

		if (d == Direction.NORTH || (m_dir == Direction.NORTH && d == Direction.FRONT)
				|| (m_dir == Direction.SOUTH && d == Direction.BACK)
				|| (m_dir == Direction.WEST && d == Direction.RIGHT)
				|| (m_dir == Direction.EAST && d == Direction.LEFT)) {
			m_y-=32;
			m_dir = Direction.NORTH;
			System.out.println("Avance au Nord\n");
		}

		else if (d == Direction.SOUTH || (m_dir == Direction.SOUTH && d == Direction.FRONT)
				|| (m_dir == Direction.NORTH && d == Direction.BACK)
				|| (m_dir == Direction.EAST && d == Direction.RIGHT)
				|| (m_dir == Direction.WEST && d == Direction.LEFT)) {
			m_y+=32;
			m_dir = Direction.SOUTH;
			System.out.println("Avance au Sud \n");
		}

		else if (d == Direction.WEST || (m_dir == Direction.WEST && d == Direction.FRONT)
				|| (m_dir == Direction.EAST && d == Direction.BACK)
				|| (m_dir == Direction.SOUTH && d == Direction.RIGHT)
				|| (m_dir == Direction.NORTH && d == Direction.LEFT)) {
			m_x-=32;
			m_dir = Direction.WEST;
			System.out.println("Avance à l'Ouest \n");
		}

		else {
			m_x+=32;
			m_dir = Direction.EAST;
			System.out.println("Avance à l'Est \n");
		}

		return true; // L'action s'est bien déroulée
	}

	// Sans param, on suit la direction actuelle
	public boolean move() {

		switch (m_dir) {
		case NORTH:
			m_y-=32;
			break;
		case SOUTH:
			m_y+=32;
			break;
		case EAST:
			m_x+=32;
			break;
		default:
			m_x-=32;
			break;
		}

		return true; // L'action s'est bien déroulée
	}

	public void step() throws Interpreter_Exception {
		automate.step(this);
	}

	public boolean hit(Direction d) {

		// Pas le vrai hit, juste une version pour tester.
		System.out.println("Frappe\n");
		return true;// L'action s'est bien déroulée
	}

}
