package tests;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import tests.Model;
import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;

public class Component {

	// Classe component de test très sommaire
	// Entre autres, un champ direction devra etre ajouté

	int power;
	public Model model;
	int m_x, m_y;
	int m_w, m_h;
	float m_scale;
	BufferedImage[] m_sprites;
	BufferedImage m_sprite;
	int m_nrows, m_ncols;
	int id_x;
	int m_step;
	boolean show;
	long m_lastMove;

	// A ajouter dans le vrai component
	IAutomaton automate;
	public Direction m_dir; //doit etre NORTH,SOUTH,EAST ou WEST
	public Type m_type; //Definit le type (allié, ennemi, rocher, etc) de ce component.
	//Ce type est utile lors de l'interprétation, notamment pour ICell

	public Component(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col,
			int id_x, boolean show){
		this.model = m;
		this.m_x = x;
		this.m_y = y;
		this.m_w = w;
		this.m_h = h;
		this.m_scale = scale;
		this.m_sprite = sprite;
		this.m_nrows = rows;
		this.m_ncols = col;
		this.id_x = id_x;
		this.show = show;
		splitSprite();
	}
	
	void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;

		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
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

		return true;
	
	}

	// Sans param, on suit la direction actuelle
	public boolean move() {

		return true;
	}
	
	public void step(long now) throws Interpreter_Exception{
		
	}

	public boolean hit(Direction d) {

		// Pas le vrai hit, juste une version pour tester.
		System.out.println("Frappe\n");
		return true;// L'action s'est bien déroulée
	}
	
	public void paint(Graphics g) {
		Image img = m_sprites[id_x];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);
	}

}