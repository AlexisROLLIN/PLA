package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import interpreter.*;
import ricm3.parser.*;
import LurkInTheShadow.*;
import ricm3.parser.Ast.AI_Definitions;
import map_creator.*;

import java.util.List;
import java.util.ListIterator;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Tests_interpreter {

	@Before
	public void before() {
		// Executed before each test
	}

	@After
	public void after() {
		// Executed after each test
	}

	@Test
	public void test00() throws Interpreter_Exception, IOException, Exception {

		/*
		 * Def manuelle d'un automate à 2 états. 
		 * (1) - Cell() / Move(R) -> (2) 
		 * (2) -Cell() / Hit -> (1)
		 * 
		 */

		Model m = new Model();

		// Juste utile pour le constructeur
		File imageFile = new File("src/map_creator/testSprites.png");
		BufferedImage sprite = ImageIO.read(imageFile);

		// Vider la map
		ListIterator<Component> iter = m.components.listIterator();
		while (iter.hasNext()) {
			Component c = iter.next();
			c.m_type = IType.VOID;
		}

		Monster c = new Monster(m, sprite, 1, 1, 10, 10, (float) 1.0, 1, true);
		c.m_h = 32;
		c.m_w = 32;

		IState etat1 = new IState("E1");
		IState etat2 = new IState("E2");

		ICondition cond1a2 = new ICondition.ICell("R", "V"); // Si case droite vide
		ICondition cond2a1 = new ICondition.ICell("R", "V");

		IAction act1a2 = new IAction.IMove("R"); // va toujours à sa droite
		IAction act2a1 = new IAction.IHit("N");

		ITransition trans1a2 = new ITransition(cond1a2, act1a2, etat2);
		ITransition trans2a1 = new ITransition(cond2a1, act2a1, etat1);

		List<ITransition> transList1 = new LinkedList<ITransition>();
		transList1.add(trans1a2);

		List<ITransition> transList2 = new LinkedList<ITransition>();
		transList2.add(trans2a1);

		IBehaviour source1 = new IBehaviour(etat1, transList1);
		IBehaviour source2 = new IBehaviour(etat2, transList2);

		List<IBehaviour> behaviourList = new LinkedList<IBehaviour>();
		behaviourList.add(source1);
		behaviourList.add(source2);

		IAutomaton auto = new IAutomaton(etat1, behaviourList, "A1");

		c.setAutomate(auto);
		c.step(61L);
		assertTrue((auto.current()).name().equals("E2")); // On s'assure qu'on est bien passé au 2nd etat
		c.step(61L);
		assertTrue((auto.current()).name().equals("E1"));
		c.step(61L);
		assertTrue((auto.current()).name().equals("E2"));
		c.step(61L);// Frappe
		c.step(61L);// Avance Ouest
		c.step(61L);// Frappe
		c.step(61L);// Avance Nord
		assertTrue(c.x() == 10);
		assertTrue(c.y() == 10); // Il devrait avoir fait un tour normalement
	}

	@Test
	public void test01() throws Interpreter_Exception, IOException, Exception {
		// Test boucle sur un état
		/*
		 * Def manuelle d'un automate à 3 transitions. (1) - MyDir(S) / Wait -> (2) (1)
		 * - !MyDir(S)/ Move(L) -> (1) (2) - True / Wait -> (2)
		 * 
		 * Se dirige en marchant vers le sud et et reste dans cette direction
		 */
		Model m = new Model();
		File imageFile = new File("src/map_creator/testSprites.png");
		BufferedImage sprite = ImageIO.read(imageFile);

		// Vider la map
		ListIterator<Component> iter = m.components.listIterator();
		while (iter.hasNext()) {
			Component c = iter.next();
			c.m_type = IType.VOID;
		}

		Monster c = new Monster(m, sprite, 1, 1, 10, 10, (float) 1.0, 1, true);
		c.m_h = 32;
		c.m_w = 32;

		IState etat1 = new IState("E1");
		IState etat2 = new IState("E2");

		ICondition cond1a2 = new ICondition.IMyDir("S");
		ICondition cond1a1 = new ICondition.INot(cond1a2);
		ICondition cond2a2 = new ICondition.INot(cond1a2);

		IAction act1a1 = new IAction.IMove("L");
		IAction act1a2 = new IAction.IWait();
		IAction act2a2 = new IAction.IWait();

		ITransition trans1a1 = new ITransition(cond1a1, act1a1, etat1);
		ITransition trans1a2 = new ITransition(cond1a2, act1a2, etat2);
		ITransition trans2a2 = new ITransition(cond2a2, act2a2, etat2);

		List<ITransition> transList1 = new LinkedList<ITransition>();
		transList1.add(trans1a2);
		transList1.add(trans1a1);

		List<ITransition> transList2 = new LinkedList<ITransition>();
		transList2.add(trans2a2);

		IBehaviour source1 = new IBehaviour(etat1, transList1);
		IBehaviour source2 = new IBehaviour(etat2, transList2);

		List<IBehaviour> behaviourList = new LinkedList<IBehaviour>();
		behaviourList.add(source1);
		behaviourList.add(source2);

		IAutomaton auto = new IAutomaton(etat1, behaviourList, "A2");

		c.setAutomate(auto);
		c.step(0);
		assertTrue((auto.current()).name().equals("E1")); // On s'assure qu'on est toujours au premier état
		c.step(0);
		assertTrue((auto.current()).name().equals("E1"));
		c.step(0);
		assertTrue((auto.current()).name().equals("E2"));
		c.step(0);
		assertTrue((auto.current()).name().equals("E2"));
	}

	@Test
	public void test02() throws Interpreter_Exception, IOException, Exception {
		// Test Interaction entre 2 automates

		/*
		 * Def manuelle d'un premier automate à 3 transitions. (1) -Cell(E,V)/Move(E)->
		 * (1) (1) -!Cell(E,V)/Hit(E)->(2) (2) -True/Wait->(2) Je marche vers l'Est tant
		 * qu'il n'y a aucune entité sur la case Est et si je tombe face à une entité je
		 * frappe en sa direction
		 */
		Model m = new Model();
		File imageFile = new File("src/map_creator/testSprites.png");
		BufferedImage sprite = ImageIO.read(imageFile);

		// Vider la map
		ListIterator<Component> iter = m.components.listIterator();
		while (iter.hasNext()) {
			Component c = iter.next();
			c.m_type = IType.VOID;
		}

		Monster c_A = new Monster(m, sprite, 1, 1, 0, 0, (float) 1.0, 1, true); // en (0,0)
		c_A.m_type = IType.ADVERSAIRE;
		c_A.m_h = 32;
		c_A.m_w = 32;

		IState etat1_A = new IState("E1_A");
		IState etat2_A = new IState("E2_A");

		ICondition cond1a1_A = new ICondition.ICell("E", "V");
		ICondition cond1a2_A = new ICondition.INot(cond1a1_A);
		ICondition cond2a2_A = new ICondition.ITrue();

		IAction act1a1_A = new IAction.IMove("E");
		IAction act1a2_A = new IAction.IHit("E");
		IAction act2a2_A = new IAction.IWait();

		ITransition trans1a1_A = new ITransition(cond1a1_A, act1a1_A, etat1_A);
		ITransition trans1a2_A = new ITransition(cond1a2_A, act1a2_A, etat2_A);
		ITransition trans2a2_A = new ITransition(cond2a2_A, act2a2_A, etat2_A);

		List<ITransition> transList1_A = new LinkedList<ITransition>();
		transList1_A.add(trans1a1_A);// Ordre d'ajout des transitions important
		transList1_A.add(trans1a2_A);

		List<ITransition> transList2_A = new LinkedList<ITransition>();
		transList2_A.add(trans2a2_A);

		IBehaviour source1_A = new IBehaviour(etat1_A, transList1_A);
		IBehaviour source2_A = new IBehaviour(etat2_A, transList2_A);

		List<IBehaviour> behaviourList_A = new LinkedList<IBehaviour>();
		behaviourList_A.add(source1_A);
		behaviourList_A.add(source2_A);

		IAutomaton auto_A = new IAutomaton(etat1_A, behaviourList_A, "A3");

		/*
		 * Def manuelle d'un second automate à 3 transitions. (1) -Cell(W,V)/Move(E)->
		 * (1) (1) -!Cell(W,V)/Hit(W)->(2) (2) -True/Wait->(2) Je marche vers l'Ouest
		 * tant qu'il n'y a aucune entité sur la case Ouest et si je tombe face à une
		 * entité je frappe en sa direction
		 */

		Monster c_B = new Monster(m, sprite, 1, 1, 96, 0, (float) 1.0, 1, true); // en (96,0), soit 3 cases à l'est de c_A
		c_B.m_type = IType.ADVERSAIRE;
		c_B.m_h = 32;
		c_B.m_w = 32;

		IState etat1_B = new IState("E1_B");
		IState etat2_B = new IState("E2_B");

		ICondition cond1a1_B = new ICondition.ICell("O", "V");
		ICondition cond1a2_B = new ICondition.INot(cond1a1_B);
		ICondition cond2a2_B = new ICondition.ITrue();

		IAction act1a1_B = new IAction.IMove("O");
		IAction act1a2_B = new IAction.IHit("O");
		IAction act2a2_B = new IAction.IWait();

		ITransition trans1a1_B = new ITransition(cond1a1_B, act1a1_B, etat1_B);
		ITransition trans1a2_B = new ITransition(cond1a2_B, act1a2_B, etat2_B);
		ITransition trans2a2_B = new ITransition(cond2a2_B, act2a2_B, etat2_B);

		List<ITransition> transList1_B = new LinkedList<ITransition>();
		transList1_B.add(trans1a1_B);// Ordre d'ajout des transitions important
		transList1_B.add(trans1a2_B);

		List<ITransition> transList2_B = new LinkedList<ITransition>();
		transList2_B.add(trans2a2_B);

		IBehaviour source1_B = new IBehaviour(etat1_B, transList1_B);
		IBehaviour source2_B = new IBehaviour(etat2_B, transList2_B);

		List<IBehaviour> behaviourList_B = new LinkedList<IBehaviour>();
		behaviourList_B.add(source1_B);
		behaviourList_B.add(source2_B);

		IAutomaton auto_B = new IAutomaton(etat1_B, behaviourList_B, "B3");

		c_A.setAutomate(auto_A);
		c_B.setAutomate(auto_B);
		c_A.step(0);
		assertTrue((auto_A.current()).name().equals("E1_A")); // Supposé avancer vers l'est
		c_B.step(0);
		assertTrue((auto_B.current()).name().equals("E1_B"));
		c_A.step(0);
		assertTrue((auto_A.current()).name().equals("E2_A")); // Les deux components sont censés se rencontrer
		c_B.step(0);
		assertTrue((auto_B.current()).name().equals("E2_B"));

	}

	@Test
	public void test03() throws Interpreter_Exception, Exception {

		/*
		 * AI_Definitions ai_def =
		 * ((AI_Definitions)AutomataParser.from_file("automate0.txt")); IAI_Definitions
		 * iai_def = ai_def.make(); IAutomaton spawn=iai_def.automatas.get(0);
		 * assertTrue(spawn.current().name().equals("Init"));
		 */

		Model m = new Model();
		File imageFile = new File("src/map_creator/testSprites.png");
		BufferedImage sprite = ImageIO.read(imageFile);

		// Vider la map
		ListIterator<Component> iter = m.components.listIterator();
		while (iter.hasNext()) {
			Component co = iter.next();
			co.m_type = IType.VOID;
		}

		Component c = new Component(m, sprite, 1, 1, 0, 0, 10, 10, (float) 1.0, 1, true); // en (0,0)
		c.m_type = IType.ADVERSAIRE;
		c.m_h = 32;
		c.m_w = 32;

		AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file("automate0.txt"));
		IAI_Definitions iai_def = ai_def.make();
		IAutomaton spawn = iai_def.automatas.get(0);
		assertTrue(spawn.current().name().equals("Init"));
		spawn.step(c);
		assertTrue(spawn.current().name().equals("Turn0"));
	}

}
