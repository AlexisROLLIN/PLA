package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.Options;
import map_creator.Map;

public class Model extends GameModel {

	public BufferedImage Sprite;
	public Map map;
	Personnage perso1;
	int nbAmmo;
	int nbBattery;
	int nbCmd;
	int nbLife;

	int nbElements;
	public LinkedList<Component> ElementsM1;
	public LinkedList<Component> ElementsM2;
	public LinkedList<Component> ElementsM3;
	public LinkedList<Component> ElementsM4;
	
	//LinkedList<Component> m_component;

	public Model() {
		
		loadSprites();
		nbElements = 0;
		nbAmmo = 0;
		nbBattery = 0;
		nbCmd = 0;
		nbLife = 0;
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
		//perso1.m_idx = 25;

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
		perso1.Pick();
		while (nbAmmo < 10) {
			PlaceRandom(1);
		}
		while (nbBattery < 10) {
			PlaceRandom(2);
		}
		while (nbCmd < 3) {
			PlaceRandom(3);
		}
		while (nbLife < 5) {
			PlaceRandom(4);
		}
		System.out.println(perso1.myAmmo);
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
	
	int Random(int min, int max) {
		return (ThreadLocalRandom.current().nextInt(min,max+1));
	}
	
	void PlaceRandom(int itemNumber) {
		int x,y;
		int map;
		boolean free;
		
		do {
			free = false;
			x = Random(1,64);
			y = Random(1,48);
			if (x < 33 && y < 25) {
				map = 1;
			}
			else if (x >= 33 && y < 25) {
				map = 2;
			}
			else if (x < 33 && y >= 25) {
				map = 3;
			}
			else {
				map = 4;
			}
			
			ListIterator<Component> iter;
			
			if (map == 1) {
				iter = this.ElementsM1.listIterator();
				x = x*32;
				y = y*32;
			}
			else if (map == 2) {
				iter = this.ElementsM2.listIterator();
				x = x*32 - 1024;
				y = y*32;
			}
			else if (map == 3) {
				iter = this.ElementsM3.listIterator();
				x = x*32;
				y = y*32 - 768;
			}
			else{
				iter = this.ElementsM4.listIterator();
				x = x*32 - 1024;
				y = y*32 - 768;
			}
			
			
			while (iter.hasNext() && !free) {
				Component c = iter.next();
				
				if (c.m_x == x && c.m_y == y && c instanceof Sol) {
					free = true;
				}
			}
		}while (!free);
		
		Items item = new Items(this, 100, Sprite, 10, 9, x, y, 1F, map,itemNumber);
				
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
