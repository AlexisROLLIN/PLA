package mesProto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;

public class Model extends GameModel{
	
	Perso m_perso;
	BufferedImage m_persoSprite;
	BufferedImage m_ghostSprite;
	LinkedList<Component> m_component;
	
	public Model() {
		loadSprite();
		m_component = new LinkedList<Component>();
		m_perso = new Perso(this, 100, m_persoSprite, 4, 6, 940, 250, 2F);
		int n = 0;
		int k = 0;
		for(int i = 0; i < 45; i++) {
			k = 0;
			for(int j = 0; j < 60; j++) {
				Floor f = new Floor(this, 100, m_ghostSprite, 8, 12, k, n, 1F);
				k += 32;
			}
			n += 24;
		}
	}
	
	public void shutdown() {
	}
	
	public Iterator<Component> components(){
		  return m_component.iterator();
	  }
	
	public void step(long now) {
		m_perso.Afficher();
	}
	
	private void loadSprite() {
		File imageFile = new File("game.sample/sprites/winchester.png");
		try {
			m_persoSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("game.sample/sprites/Ghost_Sheet.png");
	    try {
	      m_ghostSprite = ImageIO.read(imageFile);
	    } catch (IOException ex) {
	      ex.printStackTrace();
	      System.exit(-1);
	    }
		
	}

}
