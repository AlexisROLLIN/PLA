package LurkInTheShadow;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
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
	IAutomaton warrior;
	IAutomaton shooter;
	IAutomaton mage;
	IAutomaton obst;
	IAutomaton monster;
	IAutomaton queen;
	IAutomaton fireball;
	IAutomaton bullet;
	IAutomaton floor;
	IAutomaton item;

	// Automates systeme
	IAutomaton leader;
	IAutomaton spawn1;
	IAutomaton spawn2;
	IAutomaton transe;
	IAutomaton monstre_desoriente;

	public char Cgmt;
	public MiniMap minimap;
	public Fleche fleche;
	public Battery battery;
	public Score score;

	int nbElements;
	public LinkedList<Component> componentsToAdd; // Composants Ã  ajouter sur
													// le plateau aprÃ¨s les
													// steps
	public LinkedList<Component> componentsToRemove;
	public LinkedList<Component> components;
	public LinkedList<Ally> allies;// Allies du plateau
	public LinkedList<Monster> monstres;// Monstres du plateau
	public LinkedList<Projectile> projectiles;// Projectiles
	public LinkedList<Component> items;// Items
	public LinkedList<Component> mobileComponents; // A afficher par dessus le
													// plateau
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
		// projectiles = new LinkedList<Projectile>();
		items = new LinkedList<Component>();
		mobileComponents = new LinkedList<Component>();
		componentsToAdd = new LinkedList<Component>();
		componentsToRemove = new LinkedList<Component>();

		Player = Options.AUTOMATA_PLAYER;
		shooter = Options.AUTOMATA_SHOOTER;
		warrior = Options.AUTOMATA_WARRIOR;
		mage = Options.AUTOMATA_MAGE;

		leader = Options.AUTOMATA_SHOOTER;
		spawn1 = Options.AUTOMATA_WARRIOR;
		spawn2 = Options.AUTOMATA_MAGE;
		transe = Options.AUTOMATA_FLOOR;
		monstre_desoriente = Options.AUTOMATA_MONSTER;

		fireball = Options.AUTOMATA_FIREBALL;
		bullet = Options.AUTOMATA_BULLET;
		obst = Options.AUTOMATA_OBST;
		floor = Options.AUTOMATA_FLOOR;
		queen = Options.AUTOMATA_QUEEN;
		monster = Options.AUTOMATA_MONSTER;
		item = Options.AUTOMATA_ITEMS;

		IAutomaton[] tableau_autos_save = new IAutomaton[11];
		tableau_autos_save[0] = Player;
		tableau_autos_save[1] = warrior;
		tableau_autos_save[2] = shooter;
		tableau_autos_save[3] = mage;
		tableau_autos_save[4] = fireball;
		tableau_autos_save[5] = bullet;
		tableau_autos_save[6] = monster;
		tableau_autos_save[7] = queen;
		tableau_autos_save[8] = obst;
		tableau_autos_save[9] = floor;
		tableau_autos_save[10] = item;

		/*
		 * Automates systemes transe = Options.AUTOMATA_TRANSE;
		 * monstre_desoriente = Options.AUTOMATA_MONSTRE_DESO; spawn1= spawn2=
		 * fireball = Options.AUTOMATA_FIREBALL; bullet =
		 * Options.AUTOMATA_BULLET; item = Options.AUTOMATA_ITEMS;
		 */

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

		if (Options.option_load == true) {
			map = new Map(48, 64, this, Options.map);
		} else {
			map = new Map(48, 64, this);
		}

		// On sauvegarde Ã  chaque fois lÃ  /!\
		Sauvegarde save = new Sauvegarde(map.tab, tableau_autos_save,
				"src/Automates/Automate2.txt");
		save.encode("save.txt");

		// Spwan des personnages sur la map
		spawn(perso1);
		map.firstCase();

		perso2.m_x = perso1.m_x - 32;
		perso2.m_y = perso1.m_y - 32;

		perso3.m_x = perso1.m_x - 32;
		perso3.m_y = perso1.m_y + 32;

		spawn(reine);

		// Test sauvegarde

		// Charger l'image de la map
		File imageFileMap = new File("src/Sprites/MiniMap");
		try {
			SpriteMiniMap = ImageIO.read(imageFileMap);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		minimap = new MiniMap(this, SpriteMiniMap, 1, 1, 0, 0, 0.23F, 0, true);

		fleche = new Fleche(this, Sprite, 12, 11, 0, 0, 1F, 123, true);
		
		battery = new Battery(this, Sprite,12,11, 32, 132, 5F, 8, true);
		
		Font font = new Font("TimesRoman", Font.BOLD, 32);
		score = new Score(this, 230, 30, font);

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
		// On fait donc Ã§a maintenant
		Iterator<Component> iterA = this.componentsToAdd.iterator();

		while (iterA.hasNext()) {
			Component c = iterA.next();
			components.add(c);
			if (c.m_type != IType.OBSTACLE && c.m_type != IType.VOID
					&& c.m_type != IType.PRENABLE) {
				mobileComponents.add(c);
			}
			if (c.m_type == IType.PRENABLE) {
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
			if (c.m_type == IType.PRENABLE) {
				items.remove(c);
			}
		}
		componentsToRemove.clear(); // Vide la liste

		fleche.Coordonnees();
		fleche.step(now);

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
		
		this.battery.consumes(this.mainPlayed.puissance_eclairage);
		
		if (battery.m_durability == 0){
			this.mainPlayed.lampe_x = 50;
			this.mainPlayed.lampe_y = 50;
			this.mainPlayed.lampe_width = 2.5 * this.mainPlayed.lampe_x;
			this.mainPlayed.lampe_height = 2.5 * this.mainPlayed.lampe_y;
			this.mainPlayed.puissance_eclairage = 1;
		}

	}

	public ListIterator<Component> components() {
		return ElementsViewPort.listIterator();
	}

	public void spawn(Component personnage) {
		boolean spawn = false;
		int i;
		int j;
		while (!spawn) {
			if (personnage instanceof Queen) {
				i = (int) (Math.random() * (40 - 1));// Coordonnée plutot centré
														// sur la map
				j = (int) (Math.random() * (32 - 8));
			} else {
				i = (int) (Math.random() * (38 - 8));// Coordonnée plutot centré
														// sur la map
				j = (int) (Math.random() * (64 - 8));
			}

			if (ElementsMap[i][j] instanceof Sol) {
				if (ElementsMap[i - 1][j - 1] instanceof Sol
						&& ElementsMap[i + 1][j - 1] instanceof Sol) {
					personnage.m_x = j * 32;
					personnage.m_y = i * 32;
					spawn = true;
				}
			}
		}

	}

	int Random(int min, int max) {
		return (ThreadLocalRandom.current().nextInt(min, max + 1));
	}

	void PlaceRandom(int itemNumber) {
		int x, y;
		boolean free;

		do {
			free = false;
			x = Random(1, 64);
			y = Random(1, 48);

			x = x * 32;
			y = y * 32;

			ListIterator<Component> iter;
			iter = this.components.listIterator();

			while (iter.hasNext() && !free) {
				Component c = iter.next();

				if (c.m_x == x && c.m_y == y && c.m_type == IType.VOID) {
					free = true;
				}
			}
		} while (!free);

		Items item = new Items(this, Sprite, 12, 11, x, y, 1F, true, itemNumber);

	}

	private void loadSprites() {

		File imageFile = new File("src/Sprites/testSprite.png");
		try {
			Sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

}
