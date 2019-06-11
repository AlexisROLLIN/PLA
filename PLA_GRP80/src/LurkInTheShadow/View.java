package LurkInTheShadow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import edu.ricm3.game.GameView;
import LurkInTheShadow.Mage;
import LurkInTheShadow.Model;
import java.awt.Image;

public class View extends GameView {
	Color m_background = Color.black;
	Model m_model;
	long m_last;
	int m_npaints;
	int m_fps;

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

		Image image = m_model.m_background;
		g.drawImage(image, 0, 0, 1024, 768, null);

		m_model.m_main.vision();

		Component currComp;
		Iterator<Component> iter = m_model.components.iterator();
		while (iter.hasNext()) {
			currComp = iter.next();
			if (currComp.m_show) {
				currComp.paint(g);
			}
		}

		m_model.m_IHM.paint(g);

		m_model.m_battery.paint(g);

		g.setColor(Color.black);
		g.drawLine(Options.I_WIDTH, 0, Options.I_WIDTH, Options.IHM_WIDTH);

		//

		g.setColor(Color.black);
		g.drawLine(Options.I_WIDTH * 2, 0, Options.I_WIDTH * 2, Options.IHM_WIDTH * 2);

		m_model.m_score.paint(g);
	}
}
