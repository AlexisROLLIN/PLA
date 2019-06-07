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
	public Personnage perso1;

	int nbElements;
	
	public Component[][] ElementsMap;
	//public LinkedList<Component> ElementsMap;
	public LinkedList<Component> ElementsViewPort;
	
	//LinkedList<Component> m_component;

	public Model() {
		
		loadSprites();
//		nbElements = 0;

		
		//	ElementsMap = new LinkedList<Component>();

		//Options.SHOW_M1 = true;
		ElementsMap= new Component[48][64];
		
		perso1 = new Personnage(this, 100, Sprite, 10, 9, 512, 384, 1F, 1);
		perso1.m_idx = 25;
		
		map = new Map(48, 64, this);
		
		
//		ListIterator<Component> iter = this.ElementsMap.listIterator();
//		Component tmp = iter.next();
//		while(iter.hasNext() && tmp instanceof Obstacle ){
//			tmp = iter.next();
//		}
		
		
		
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
		//perso1.Afficher();
		
	}
	
	public ListIterator<Component> components(){
			return ElementsViewPort.listIterator();  
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
