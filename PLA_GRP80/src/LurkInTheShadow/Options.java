package LurkInTheShadow;

import javax.swing.JComboBox;
import javax.swing.Spring;
import interpreter.IAutomaton;

public class Options {
	public static final int W_WIDTH = 1024;
	public static final int W_HEIGHT = 768;

	public static final int NB_I = 3;

	public static final int I_WIDTH = W_WIDTH / NB_I;
	public static final int IHM_WIDTH = 64;

	public static final int PW_HEIGHT = W_HEIGHT - IHM_WIDTH;
	
	public static final float MAX_DURABILITY = 100000;
	
	public static final int MAX_SCORE = Integer.MAX_VALUE;
	
	public static IAutomaton AUTOMATA_PLAYER;
	public static IAutomaton AUTOMATA_WARRIOR;
	public static IAutomaton AUTOMATA_SHOOTER;
	public static IAutomaton AUTOMATA_MAGE;
	public static IAutomaton AUTOMATA_FIREBALL;
	public static IAutomaton AUTOMATA_BULLET;
	public static IAutomaton AUTOMATA_MONSTER;
	public static IAutomaton AUTOMATA_QUEEN;
	public static IAutomaton AUTOMATA_OBST;
	public static IAutomaton AUTOMATA_FLOOR;
	public static IAutomaton AUTOMATA_ITEMS;
	public static boolean option_load;
	public static int map[][];
	public static Music bgm;
}