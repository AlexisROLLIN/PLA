package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import LurkInTheShadow.Mage;
import LurkInTheShadow.Mur;
import java.awt.Color;

public class Model extends GameModel {
	BufferedImage m_testSprite;
	BufferedImage m_background;
	Mur m_Mur;
	Mage m_Mage;
	Feu m_feu;
	LinkedList<Component> components;

	public Model() {
	    loadSprites();
	    this.components = new LinkedList();
	    m_Mage = new Mage(this, 300, 300, 32, 32, 39,true,100,1,9,10,3F,m_testSprite,0);
	    m_feu = new Feu(this,m_testSprite,9,10,18,1F,true,1);
	    m_Mur = new Mur(this, 400, 400, 32, 32, 5, 9, 10, 3F, m_testSprite,true);
	    this.components.add(m_Mage);
	    this.components.add(m_feu);
	    this.components.add(m_Mur);
	}

	public Mage cowboys() {
		return m_Mage;
	}

	public Feu feu() {
		return m_feu;
	}
	
	public Mur Mur() {
		return m_Mur;
	}

	@Override
	public void step(long now) {

		Iterator<Component> iter = this.components.iterator();
		while (iter.hasNext()) {
			iter.next().step(now);
		}
	}

	private void loadSprites() {
		File imageFile = new File("src/Sprites/testSprites.png");

		try {
			m_testSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		 imageFile = new File("src/Sprites/logo.png");

		try {
			m_background = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	@Override
	public void shutdown() {

	}
}
