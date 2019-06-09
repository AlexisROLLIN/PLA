package LurkInTheShadow;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.awt.Image;

import edu.ricm3.game.GameView;
import edu.ricm3.game.Options;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.black;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;

	public View(Model m) {
		m_model = m;
	}

	public void step(long now) {

	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, null);

		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();

		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());

		Iterator<Component> iter = this.m_model.components.iterator();
		while (iter.hasNext()) {
			Component c = iter.next();

			
			if (c.m_show) {
				c.paint(g);
			}
		}

	}

}
