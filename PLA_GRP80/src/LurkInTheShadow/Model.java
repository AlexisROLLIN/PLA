package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.Options;
import map_creator.Map;

public class Model extends GameModel {

	public BufferedImage Sprite;
	public Map map;
	Personnage perso1;

	int nbElements;
	public LinkedList<Component> ElementsM1;
	public LinkedList<Component> ElementsM2;
	public LinkedList<Component> ElementsM3;
	public LinkedList<Component> ElementsM4;
	
	public LinkedList<Ally> allies;//Allies du plateau
	
	//LinkedList<Component> m_component;

	public Model() {
		
		loadSprites();
		nbElements = 0;
		ElementsM1 = new LinkedList<Component>();
		ElementsM2 = new LinkedList<Component>();
		ElementsM3 = new LinkedList<Component>();
		ElementsM4 = new LinkedList<Component>();
		Options.SHOW_M1 = true;
		map = new Map(44, 64, this);
		ListIterator<Component> iter = this.ElementsM1.listIterator();
		Component tmp = iter.next();
		while(iter.hasNext() && tmp instanceof Obstacle ){
			tmp = iter.next();
		}
		perso1 = new Personnage(this, 100, Sprite, 10, 9, tmp.m_x, tmp.m_y, 1F, 1);
		perso1.m_idx = 25;

	}

	@Override
	public void shutdown() {

	}

	/**
	 * Simulation step.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		perso1.Afficher();
	}
	
	public ListIterator<Component> components(){
		  if(Options.SHOW_M1){
			return ElementsM1.listIterator();
		  }
		  if(Options.SHOW_M2){
			return ElementsM2.listIterator();
		  }
		  if(Options.SHOW_M3){
			return ElementsM3.listIterator();
		  }
		  else{
			return ElementsM4.listIterator();
		  }
		  
	  }
	

	private void loadSprites() {

		File imageFile = new File("src/map_creator/testSprites.png");
		try {
			Sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

}
