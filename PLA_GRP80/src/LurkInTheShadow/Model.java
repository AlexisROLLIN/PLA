package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.Options;
import interpreter.IAI_Definitions;
import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;
import map_creator.Map;
import ricm3.parser.AutomataParser;
import ricm3.parser.Ast.AI_Definitions;

public class Model extends GameModel {

	public BufferedImage Sprite;
	public Map map;
	Shooter perso1;
	Mage perso2;
	Warrior perso3;
	Queen reine;
	IAutomaton Player;
	IAutomaton spawn;
	IAutomaton spawn1;
	IAutomaton spawn2;
	IAutomaton obst;
	IAutomaton monster;
	IAutomaton queen;
	IAutomaton fireball;
	IAutomaton bullet;
	IAutomaton floor;

	public char Cgmt;

	int nbElements;
	public int nb_monsters_to_be_added;
	public LinkedList<Component> components;
	public LinkedList<Ally> allies;// Allies du plateau
	public LinkedList<String> touches;
	
	public Component[][] ElementsMap;
	public Component[][] ElementsTore;
	public Component[][] ElementsMap;
	public Component[][] ElementsTore;

	public Model() throws Interpreter_Exception, Exception {

		loadSprites();
		nbElements = 0;
		this.touches = new LinkedList();
		this.components = new LinkedList();

		allies = new LinkedList<Ally>();
		nb_monsters_to_be_added = 0;

		AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file("src/Automates/Automate"));
		IAI_Definitions iai_def = ai_def.make();
		spawn = iai_def.automatas.get(0);
		spawn1 = iai_def.automatas.get(1);
		spawn2 = iai_def.automatas.get(2);
		obst = iai_def.automatas.get(3);
		floor = iai_def.automatas.get(4);
		queen = iai_def.automatas.get(5);
		monster = iai_def.automatas.get(6);

		Player = spawn;

		map = new Map(44, 64, this);

		perso1 = new Shooter(this, Sprite, 10, 9, 224, 416, 1F, 81, true);
		perso2 = new Mage(this, Sprite, 10, 9, 192, 416, 1F, 44, true);
		perso3 = new Warrior(this, Sprite, 10, 9, 160, 416, 1F, 48, true);
		reine = new Queen(this, Sprite, 10, 9, 320, 448, 1F, 13, true);

		perso1.setAutomate(spawn);
		perso2.setAutomate(spawn1);
		perso3.setAutomate(spawn2);

	//public LinkedList<Component> ElementsMap;
	
	//LinkedList<Component> m_component;

		
		//	ElementsMap = new LinkedList<Component>();

		//Options.SHOW_M1 = true;
		ElementsMap= new Component[48][64];
		ElementsTore= new Component[96][128];
		
		
		
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
		perso1.Afficher();
		Iterator<Component> iter = this.components.iterator();

		while (iter.hasNext()) {
			try {
				iter.next().step(now);

			} catch (Interpreter_Exception e) {
			}
		}
		// On ne peut pas ajouter de components pendant qu'on parcourt la liste de
		// components
		// On fait donc ça maintenant
		for (int i = 0; i < nb_monsters_to_be_added; i++) {
			new Monster(this, reine.m_sprite, reine.m_nrows, reine.m_ncols, reine.m_x, reine.m_y, 1F, 19, reine.m_show);
		}
		nb_monsters_to_be_added = 0;
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
