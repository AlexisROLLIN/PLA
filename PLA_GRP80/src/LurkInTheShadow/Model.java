package LurkInTheShadow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.Options;
import interpreter.IAI_Definitions;
import interpreter.IAutomaton;
import interpreter.Interpreter_Exception;
import map_creator.Map;
import ricm3.parser.AutomataParser;
import ricm3.parser.Ast.AI_Definitions;
import sauvegarde.Sauvegarde;
import map_creator.Fleche;
import map_creator.Map;
import map_creator.MiniMap;

public class Model extends GameModel {

	public BufferedImage Sprite;
	public BufferedImage SpriteMiniMap;
	public Map map;

	public Component mainPlayed;
	public Shooter perso1;
	public Mage perso2;
	public Warrior perso3;
	public Queen reine;
	
	int nbAmmo;
	int nbBattery;
	int nbCmd;
	int nbLife;

	IAutomaton Player;
	IAutomaton leader;
	IAutomaton spawn1;
	IAutomaton spawn2;
	IAutomaton obst;
	IAutomaton monster;
	IAutomaton queen;
	IAutomaton fireball;
	IAutomaton bullet;
	IAutomaton floor;
	IAutomaton transe;
	IAutomaton monstre_desoriente;
	IAutomaton item;

	public char Cgmt;
	public MiniMap minimap;
	public Fleche fleche;

	int nbElements;
	public LinkedList<Component> componentsToAdd; // Composants √† ajouter sur
													// le plateau apr√®s les
													// steps
	public LinkedList<Component> componentsToRemove;
	public LinkedList<Component> components;
	public LinkedList<Ally> allies;// Allies du plateau
	public LinkedList<Monster> monstres;// Monstres du plateau
<<<<<<< HEAD
	public LinkedList<Component> mobileComponents; // A afficher par dessus le
													// plateau
=======
	public LinkedList<Projectile> projectiles;// Projectiles
	public LinkedList<Component> items;// Items
	public LinkedList<Component> mobileComponents; // A afficher par dessus le plateau
>>>>>>> tmpIntegration
	public LinkedList<String> touches;

	// Tore viewport
	public Component[][] ElementsMap;
	public Component[][] ElementsTore;
	// public LinkedList<Component> ElementsMap;
	public LinkedList<Component> ElementsViewPort;

	public Model() throws Interpreter_Exception, Exception {

		loadSprites();
		nbElements = 0;
		this.touches = new LinkedList();
		this.components = new LinkedList();

		// Listes utiles
		allies = new LinkedList<Ally>();
		monstres = new LinkedList<Monster>();
		//projectiles = new LinkedList<Projectile>();
		items = new LinkedList<Component>();
		mobileComponents = new LinkedList<Component>();
		componentsToAdd = new LinkedList<Component>();
		componentsToRemove = new LinkedList<Component>();

<<<<<<< HEAD
		AI_Definitions ai_def = ((AI_Definitions) AutomataParser
				.from_file("src/Automates/Automate"));
		IAI_Definitions iai_def = ai_def.make();
		Player = iai_def.automatas.get(0);
		leader = iai_def.automatas.get(1);
		spawn1 = iai_def.automatas.get(2);
		spawn2 = iai_def.automatas.get(3);
		obst = iai_def.automatas.get(4);
		floor = iai_def.automatas.get(5);
		queen = iai_def.automatas.get(6);
		monster = iai_def.automatas.get(7);
		transe = iai_def.automatas.get(8);
		monstre_desoriente = iai_def.automatas.get(7);
		fireball = obst;
		bullet = obst;
		
=======
		AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file("src/Automates/Automate2.txt"));
		IAI_Definitions iai_def = ai_def.make();
		Player = iai_def.automatas.get(0);
		leader = iai_def.automatas.get(3);
		spawn1 = iai_def.automatas.get(1);
		spawn2 = iai_def.automatas.get(2);
		obst = iai_def.automatas.get(6);
		floor = iai_def.automatas.get(7);
		queen = iai_def.automatas.get(4);
		monster = iai_def.automatas.get(5);
		transe = iai_def.automatas.get(8);//
		monstre_desoriente = iai_def.automatas.get(7);//
		fireball = iai_def.automatas.get(9);
		bullet = iai_def.automatas.get(8);;
		item = iai_def.automatas.get(7);
>>>>>>> tmpIntegration

		perso1 = new Shooter(this, Sprite, 12, 11, 0, 0, 1F, 81, true);
		perso2 = new Mage(this, Sprite, 12, 11, 0, 0, 1F, 44, true);
		perso3 = new Warrior(this, Sprite, 12, 11, 0, 0, 1F, 48, true);
		reine = new Queen(this, Sprite, 12, 11, 320, 448, 2F, 112, true);

		perso1.setAutomate(Player);
		mainPlayed = perso1;
		perso2.setAutomate(spawn1);
		perso3.setAutomate(spawn2);

		ElementsMap = new Component[48][64];
		ElementsTore = new Component[96][128];

		map = new Map(48, 64, this);
		
		
		//Spwan des personnages sur la map
		spawn(perso1);
		map.firstCase();
		
		perso2.m_x=perso1.m_x-32;
		perso2.m_y=perso1.m_y-32;
		
		perso3.m_x=perso1.m_x-32;
		perso3.m_y=perso1.m_y+32;
		
		spawn(reine);
		

		// Charger l'image de la map
		File imageFileMap = new File("src/Sprites/MiniMap");
		try {
			SpriteMiniMap = ImageIO.read(imageFileMap);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		minimap = new MiniMap(this, SpriteMiniMap, 1, 1, 0,
				0, 0.23F, 0, true);

		fleche = new Fleche(this, Sprite, 12, 11, 0,
				0, 1F, 123, true);

	}

	@Override
	public void shutdown() {

	}

	/**
	 * Simulation step.
	 * 
	 * @param now
	 *            is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		mainPlayed.Afficher();
		
		Iterator<Component> iter = this.components.iterator();

		while (iter.hasNext()) {
			try {
				iter.next().step(now);

			} catch (Interpreter_Exception e) {
			}
		}
		// On ne peut pas ajouter de components pendant qu'on parcourt la liste
		// de
		// components
		// On fait donc √ßa maintenant
		Iterator<Component> iterA = this.componentsToAdd.iterator();

		while (iterA.hasNext()) {
			Component c = iterA.next();
			components.add(c);
			if (c.m_type != IType.OBSTACLE && c.m_type != IType.VOID && c.m_type!=IType.PRENABLE) {
				mobileComponents.add(c);
			}
			if(c.m_type == IType.PRENABLE) {
				items.add(c);
			}
		}
		componentsToAdd.clear(); // Vide la liste

		Iterator<Component> iterR = this.componentsToRemove.iterator();

		while (iterR.hasNext()) {
			Component c = iterR.next();
			components.remove(c);
			if (c.m_type != IType.OBSTACLE && c.m_type != IType.VOID) {
				mobileComponents.remove(c);
			}
			if(c.m_type == IType.PRENABLE) {
				items.remove(c);
			}
		}
		componentsToRemove.clear(); // Vide la liste
<<<<<<< HEAD
		fleche.Coordonnees();
		fleche.step(now);
=======
		
		
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

>>>>>>> tmpIntegration
	}

	public ListIterator<Component> components() {
		return ElementsViewPort.listIterator();
	}

<<<<<<< HEAD
	public void spawn(Component personnage){
		boolean spawn=false;
		while (!spawn){
			int i = (int) (Math.random() * ( 40 - 5 ));//CoordonnÈe plutot centrÈ sur la map
			int j = (int) (Math.random() * ( 60 - 5 ));
			
			if(ElementsMap[i][j] instanceof Sol){
				if(ElementsMap[i-1][j-1] instanceof Sol && ElementsMap[i+1][j-1] instanceof Sol){
					personnage.m_x=j*32;
					personnage.m_y=i*32;
					spawn=true;
				}
			}
		}
	}

=======
	int Random(int min, int max) {
		return (ThreadLocalRandom.current().nextInt(min,max+1));
	}
	
	void PlaceRandom(int itemNumber) {
		int x,y;
		boolean free;
		
		do {
			free = false;
			x = Random(1,64);
			y = Random(1,48);
			
				x = x*32;
				y = y*32;
			
			ListIterator<Component> iter;
			iter = this.components.listIterator();
			
			while (iter.hasNext() && !free) {
				Component c = iter.next();
				
				if (c.m_x == x && c.m_y == y && c.m_type==IType.VOID) {
					free = true;
				}
			}
		}while (!free);
		
		Items item = new Items(this, Sprite, 10, 9, x, y, 1F, true, itemNumber);
				
	}
	
>>>>>>> tmpIntegration
	private void loadSprites() {

		File imageFile = new File("src/Sprites/testSprite.png");
		try {
			Sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

	public void save_config() {

		IAutomaton auto[] = new IAutomaton[12];
		auto[0] = Player;
		auto[1] = leader;
		auto[2] = spawn1;
		auto[3] = spawn2;
		auto[4] = obst;
		auto[5] = monster;
		auto[6] = queen;
		auto[7] = fireball;
		auto[8] = bullet;
		auto[9] = floor;
		auto[10] = transe;
		auto[11] = monstre_desoriente;
		Sauvegarde sauv = new Sauvegarde(map.tab, auto,
				"src/Automates/Automate");
		sauv.encode("game_save.txt");
	}

	public boolean load_config() {

		Sauvegarde sauv = Sauvegarde.decode("game_save.txt");
		if (sauv == null) {
			return false;
		}

		map = new Map(48, 64, this, sauv.tab_map);
		Player = sauv.tab_auto[0];
		leader = sauv.tab_auto[1];
		spawn1 = sauv.tab_auto[2];
		spawn2 = sauv.tab_auto[3];
		obst = sauv.tab_auto[4];
		monster = sauv.tab_auto[5];
		queen = sauv.tab_auto[6];
		fireball = sauv.tab_auto[7];
		bullet = sauv.tab_auto[8];
		floor = sauv.tab_auto[9];
		transe = sauv.tab_auto[10];
		monstre_desoriente = sauv.tab_auto[11];
		return true;
	}

}
