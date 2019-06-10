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
	IAutomaton spawn1;
	IAutomaton spawn2;
	IAutomaton obst;
	IAutomaton monster;
	IAutomaton transe;
	IAutomaton queen;
	IAutomaton fireball;
	IAutomaton bullet;
	IAutomaton floor;

	public char Cgmt;

	int nbElements;
	public LinkedList<Component> componentsToAdd; //Composants à ajouter sur le plateau après les steps
	public LinkedList<Component> componentsToRemove;
	public LinkedList<Component> components;
	public LinkedList<Ally> allies;// Allies du plateau
	public LinkedList<String> touches;

	public Model() throws Interpreter_Exception, Exception {

		loadSprites();
		nbElements = 0;
		this.touches = new LinkedList();
		this.components = new LinkedList();

		allies = new LinkedList<Ally>();
		componentsToAdd = new LinkedList<Component>();
		componentsToRemove = new LinkedList<Component>();

		AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file("src/Automates/Automate"));
		IAI_Definitions iai_def = ai_def.make();
		Player = iai_def.automatas.get(0);
		spawn1 = iai_def.automatas.get(1);
		spawn2 = iai_def.automatas.get(2);
		obst = iai_def.automatas.get(3);
		floor = iai_def.automatas.get(4);
		queen = iai_def.automatas.get(5);
		monster = iai_def.automatas.get(6);
		transe = iai_def.automatas.get(7);

		map = new Map(44, 64, this);

		perso1 = new Shooter(this, Sprite, 10, 9, 224, 416, 1F, 81, true);
		perso2 = new Mage(this, Sprite, 10, 9, 192, 416, 1F, 44, true);
		perso3 = new Warrior(this, Sprite, 10, 9, 160, 416, 1F, 48, true);
		reine = new Queen(this, Sprite, 10, 9, 320, 448, 1F, 13, true);

		perso1.setAutomate(Player);
		perso2.setAutomate(spawn1);
		perso3.setAutomate(spawn2);

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
		Iterator<Component> iterA = this.componentsToAdd.iterator();

		while (iterA.hasNext()) {
			Component c=iterA.next();
			components.add(c);
		}
		componentsToAdd.clear(); //Vide la liste
		
		Iterator<Component> iterR = this.componentsToRemove.iterator();

		while (iterR.hasNext()) {
			Component c=iterR.next();
			components.remove(c);
		}
		componentsToRemove.clear(); //Vide la liste
		
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

