package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Projectile extends Component{
	
	public Projectile (Model model, BufferedImage sprite, int rows,
			int columns, int x, int y,float scale, int id_x, boolean show, IDirection dir, int power,int speed) {
		
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);
		m_type=IType.MISSILE;
		m_dir=dir;
		model.mobileComponents.add(this);
		this.power = power;
		this.speed = speed;
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
	
	public boolean kamikaze() {
		
		Iterator<Monster> iter = m_model.monstres.iterator();
		while (iter.hasNext()) {
			Monster c = iter.next();
			if (c.is_in_case(m_x, m_y)) {
				c.life -= this.power;
			}
		}
		return true;
	}

}
