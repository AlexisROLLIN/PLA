package LurkInTheShadow;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import interpreter.IAutomaton;

public class Ally extends Component {

	int speed;
	
	public Ally(Model model, BufferedImage sprite, int rows, int columns, int x, int y, int h, int w, float scale,
				int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, h, w, scale, id_x, show);
		model.allies.add(this);
		speed=32;
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
		return true; // L'action s'est bien déroulée
	}
	
	
	boolean Vision(Component c) {
		int x = 50;
		int y = 50;
		Ellipse2D.Double player = new Ellipse2D.Double(this.m_x - x, this.m_y - y, 2.5 * x, 2.5 * y);
		Rectangle objet = c.getBounds();

		if (player.intersects(objet)) {
			return true;
		}
		//return false;
		return true; //Pour tests
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
	
	//A appeler quand !IGotPower
	public boolean kamikaze() {
		m_model.allies.remove(this);
		m_model.componentsToRemove.add(this);
		return true;
	}

}
/*(Model m, int x, int y, int w, int h, float scale, BufferedImage sprite, int rows, int col, int id_x,
	 boolean show,  int screen) {*/