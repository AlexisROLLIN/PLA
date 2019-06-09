package LurkInTheShadow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Personnage extends Component {
	
	int lampe_x;
	int lampe_y;
	double lampe_width;
	double lampe_height;

	public Personnage(Model model, int no, BufferedImage sprite, int rows,
			int columns, int x, int y, float scale, int screen) {
		
		super(model, no, sprite, rows, columns, x, y, sprite.getHeight(), sprite.getWidth(), scale,screen);
		m_idx = 0;
		m_show = true;
		lampe_x = 50;
		lampe_y = 50;
		lampe_width = 2.5 * lampe_x;
		lampe_height = 2.5 * lampe_y;
	}

	boolean Vision(Component c) {
		Ellipse2D.Double player = new Ellipse2D.Double(this.m_x - lampe_x, this.m_y - lampe_y, lampe_width, lampe_height);
		Rectangle objet = c.getBounds();

		if (player.intersects(objet)) {
			return true;
		}
		return false;
	}

	public void Afficher() {
		Iterator<Component> iter = m_model.components();

		while (iter.hasNext()) {
			Component c = iter.next();
			if (Vision(c)) {
				c.m_show = true;
			} else {
				c.m_show = false;
			}
		}
	}

	public void paint(Graphics g) {

		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, m_x, m_y, w, h, null);

	}

}
