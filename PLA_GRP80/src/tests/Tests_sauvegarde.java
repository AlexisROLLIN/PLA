package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import interpreter.*;
import ricm3.parser.*;
import sauvegarde.*;
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
import java.math.*;


/*NB: commande si probleme de ReInit du Parser:
 *	 javacc -STATIC=false parser_automata.jj  */

public class Tests_sauvegarde {

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
		// Test encodage
		AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file("automate0.txt"));
		IAI_Definitions iai_def = ai_def.make();

		IAutomaton autos[] = new IAutomaton[8];
		int map[][] = new int[48][64];
		
		//Creation d'une map
		int i;
		for(i=0;i<48;i++) {
			for (int j=0; j<64; j++) {
				map[i][j] = ((int)(Math.random()*100))%2;
			}
		}
		
		//Creation d'un tableau d'automates
		i=0;
		ListIterator<IAutomaton> iter = iai_def.automatas.listIterator();
		while (iter.hasNext() && i<8) {
			IAutomaton aut = iter.next();
			autos[i]=aut;
			i++;
		}
		Sauvegarde sauv = new Sauvegarde(map,autos,"automate0.txt");
		sauv.encode("save.txt");
	}
	
	@Test
	public void test01() throws Interpreter_Exception, IOException, Exception {
		// Test decodage
		Sauvegarde sauv = Sauvegarde.decode("save.txt");
		assertTrue(sauv.tab_auto[0].name().equals("Spawn"));
	}
}