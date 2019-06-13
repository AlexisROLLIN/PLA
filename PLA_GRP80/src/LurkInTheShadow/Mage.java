package LurkInTheShadow;


import java.awt.image.BufferedImage;


public class Mage extends Ally {
	int Incantation;
	public Mage(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale,
			int id_x, boolean show) {
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);

		m_step = 8;
		m_dir = IDirection.EAST;
		m_type = IType.TEAM;
		power = 20;
		splitSprite();
		this.life = 200;
		this.power = 50;
	}
	
	@Override
	public boolean hit(IDirection d) {
		if(Incantation == 2) {
		m_model.componentsToAdd.add(new Fireball(m_model, m_sprite, m_nrows, m_ncols, m_x, m_y, m_scale, 21, m_show, m_dir, power,32));
		Incantation =0;
		}
		Incantation ++;
		return true;
	}


}