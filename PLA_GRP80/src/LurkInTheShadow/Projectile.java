package LurkInTheShadow;

import java.awt.image.BufferedImage;

public class Projectile extends Component {
	
	public Projectile (Model model, BufferedImage sprite, int rows,
			int columns, int x, int y,float scale, int id_x, boolean show, IDirection dir) {
		
		super(model, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale, id_x, show);
		m_type=IType.MISSILE;
		m_dir=dir;

	}

}
