package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.Options;
import map_creator.Fleche;
import map_creator.Map;
import map_creator.MiniMap;

public class Model extends GameModel {

	public BufferedImage Sprite;
	public BufferedImage SpriteMiniMap;
	public Map map;
	public Personnage perso1;
	public MiniMap minimap;
	public Fleche fleche;

	
	public Component[][] ElementsMap;
	public Component[][] ElementsTore;
	

	public Model() {
		
		loadSprites();
		ElementsMap= new Component[48][64];
		ElementsTore= new Component[96][128];
		
		perso1 = new Personnage(this, 100, Sprite, 12, 11, 512, 384, 0.40F, 1);
		perso1.m_idx = 25;
		
		map = new Map(48, 64, this);
		
		
		
		//Charger l'image de la map
		File imageFileMap = new File("src/map_creator/MiniMap");
		try {
			SpriteMiniMap = ImageIO.read(imageFileMap);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		minimap = new MiniMap(this,100,SpriteMiniMap,1, 1, perso1.m_x-512, perso1.m_y-384, 0.23F, 1);
		
		fleche = new Fleche (this,100,Sprite,12, 11, perso1.m_x-512, perso1.m_y-384, 0.40F, 1);
		fleche.m_idx=123;
		
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
		fleche.Coordonnees();
		fleche.step(now);
	}
	
	public ListIterator<Component> components(){
			return null;  
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
