package LurkInTheShadow;

import java.awt.Font;
import java.awt.Point;
import java.awt.Graphics;

public class Score {

	Model m_model;
	Font m_font;
	Point m_origin;

	Score(Model model, int x, int y, Font font) {

		m_model = model;
		m_origin = new Point(x, y);
		m_font = font;
	}

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 **/

	void paint(Graphics g) {
		g.setFont(m_font);
		g.drawString("bonjour",m_origin.x,m_origin.y);
	}
}
