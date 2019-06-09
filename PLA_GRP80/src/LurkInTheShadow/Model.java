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
	IAutomaton Player;
	public char Cgmt;
	
	int nbElements;
	public LinkedList<Component> components;
	public LinkedList<Component> ElementsM1;
	public LinkedList<Component> ElementsM2;
	public LinkedList<Component> ElementsM3;
	public LinkedList<Component> ElementsM4;

	public LinkedList<Ally> allies;//Allies du plateau
	public LinkedList<String> touches;


	public Model() throws Interpreter_Exception, Exception {
		
		loadSprites();
		nbElements = 0;
		this.touches = new LinkedList();
		this.components = new LinkedList();
		IAutomaton spawn;
		IAutomaton spawn1;
		IAutomaton spawn2;
		ElementsM1 = new LinkedList<Component>();
		ElementsM2 = new LinkedList<Component>();
		ElementsM3 = new LinkedList<Component>();
		ElementsM4 = new LinkedList<Component>();
		
		allies=new LinkedList<Ally>();
		
		Options.SHOW_M1 = true;
		map = new Map(44, 64, this);
		ListIterator<Component> iter = this.ElementsM1.listIterator();
		Component tmp = iter.next();
		while(iter.hasNext() && tmp.m_type==IType.OBSTACLE){
			tmp = iter.next();
		}

		perso1 = new Shooter(this, Sprite, 10, 9, 200, 400, 1F, 81, true, 1);
		perso2 = new Mage(this, Sprite, 10, 9, 136, 400, 1F, 44, true, 1);
		perso3 = new Warrior(this, Sprite, 10, 9, 104, 400, 1F, 48, true, 1);
		
		
		AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file("src/Automates/Automate"));
		IAI_Definitions iai_def = ai_def.make();
		spawn = iai_def.automatas.get(0);
		spawn1 = iai_def.automatas.get(1);
		spawn2 = iai_def.automatas.get(2);
		Player = spawn;
		perso1.setAutomate(spawn);
		perso2.setAutomate(spawn1);
		perso3.setAutomate(spawn2);
		this.components.add(perso1);
		this.components.add(perso2);
		this.components.add(perso3);

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
