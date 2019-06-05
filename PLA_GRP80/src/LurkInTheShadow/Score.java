package LurkInTheShadow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.border.Border;

import java.awt.Graphics;

public class Score {

	Model m_model;
	Font m_font;
	Point m_origin;
	int m_w, m_h;
	int m_points;

	Score(Model model, int x, int y, Font font) {

		m_model = model;
		m_origin = new Point(x, y);
		m_font = font;
		m_points = 99999999;
	}

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 **/

	void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(114, 85, m_w, 85);
		g.setColor(Color.white);
		g.setFont(m_font);
		g.drawString(String.valueOf(m_points), 114, 85);
	}
}
