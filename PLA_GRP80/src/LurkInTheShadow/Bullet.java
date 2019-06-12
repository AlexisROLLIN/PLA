package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.util.ListIterator;

public class Bullet extends Projectile {

	public Bullet (Model model, BufferedImage sprite, int rows, int columns, int x, int y,float scale, int id_x, boolean show, IDirection dir, int power) {
		
		super(model, sprite, rows, columns, x, y, scale, id_x, show, dir, power);
		
	}
	
	@Override
	public boolean move(IDirection d) { //Graphiques non geres

		switch (d) {
			case NORTH: 
				m_y += speed;
				break;
			case SOUTH: 
				m_y -= speed;
				break;
			case EAST: 
				m_x += speed;
				break;
			default: 
				m_x -= speed;
				break;
		}
		return true; // L'action s'est bien déroulée
	}
	
	@Override
	public boolean kamikaze() { //Graphiques non geres

		ListIterator<Ally> iter = this.m_model.allies.listIterator();
		Ally all = iter.next();
		while (iter.hasNext()) {
			all = iter.next();
			if (all.is_in_case(m_x, m_y)) {
				all.life = all.life - power;
			}
		}
		
		return true; // L'action s'est bien déroulée
	}
	
}
