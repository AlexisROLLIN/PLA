package LurkInTheShadow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
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
		m_points = 0;
	}

	void earn(int points) {
		if (0 > m_points + points) {
			m_points = Options.MAX_SCORE;
		} else {
			m_points += points;
		}
	}

	void lose(int points) {
		if (0 > m_points - points) {
			m_points = 0;
		} else {
			m_points -= points;
		}
	}

	/**
	 * paint the score on the IHM.
	 * 
	 * @param g
	 **/

	void paint(Graphics g) {
		g.setColor(Color.white);
		g.setFont(m_font);
		g.drawString(String.valueOf(m_points), m_origin.x, m_origin.y);
	}
}
