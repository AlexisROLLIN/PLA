package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.util.ListIterator;

public class Fireball extends Projectile {

	public Fireball (Model model, BufferedImage sprite, int rows, int columns, int x, int y,float scale, int id_x, boolean show, IDirection dir, int power,int speed) {
		
		super(model, sprite, rows, columns, x, y, scale, id_x, show, dir, power,speed);
		automate=m_model.fireball;
		power = 100;
		

	}
	
	@Override
	public boolean move(IDirection d) { //Graphiques non geres

			if (d == IDirection.NORTH || (m_dir == IDirection.NORTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.SOUTH && d == IDirection.BACK)
					|| (m_dir == IDirection.WEST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.EAST && d == IDirection.LEFT)) {
				m_y -= speed;
				m_idx = 18;
				m_dir = IDirection.NORTH;
				System.out.println("Avance au Nord\n");
			}

			else if (d == IDirection.SOUTH || (m_dir == IDirection.SOUTH && d == IDirection.FRONT)
					|| (m_dir == IDirection.NORTH && d == IDirection.BACK)
					|| (m_dir == IDirection.EAST && d == IDirection.RIGHT)
					|| (m_dir == IDirection.WEST && d == IDirection.LEFT)) {
				m_y += speed;
				m_idx = 20;
				m_dir = IDirection.SOUTH;
				System.out.println("Avance au Sud \n");
			}

			else if (d == IDirection.WEST || (m_dir == IDirection.WEST && d == IDirection.FRONT)
					|| (m_dir == IDirection.EAST && d == IDirection.BACK)
					|| (m_dir == IDirection.SOUTH && d == IDirection.RIGHT)
					|| (m_dir == IDirection.NORTH && d == IDirection.LEFT)) {
				m_x -= speed;
				m_idx = 19;
				m_dir = IDirection.WEST;
				System.out.println("Avance à l'Ouest \n");
			
			}

			else {
				m_x += speed;
				m_dir = IDirection.EAST;
				m_idx = 17;
				System.out.println("Avance à l'Est \n");
			}
		return true; // L'action s'est bien déroulée
	}	
	@Override
	public boolean kamikaze() { //Graphiques non geres

		ListIterator<Monster> iter = this.m_model.monstres.listIterator();
		Monster mon = iter.next();
		while (iter.hasNext()) {
			mon = iter.next();
			if (mon.is_in_case(m_x, m_y)) {
				mon.life = mon.life - power;
			}
		}
		m_model.componentsToRemove.add(this);
		this.m_dead = true;
		return true;
	}
	
}