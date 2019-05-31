package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import interpreter.IAutomaton;
import interpreter.IState;
import interpreter.ICondition;
import interpreter.IAction;
import interpreter.ITransition;
import interpreter.IBehaviour;
import interpreter.Interpreter_Exception;
import tests.Component;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;

public class TestsA {
  
  @Before
  public void before() {
    // Executed before each test
  }

  @After
  public void after() {
    // Executed after each test
  }

  
  
  @Test
  public void test00() throws Interpreter_Exception{
	  
	/*Def manuelle d'un automate à 2 états.
	  (1) - Cell() / Move(R) -> (2)
	  (2) - Cell() / Hit -> (1)
	*/  
	Model m=new Model();  
	  
	Component c = new Component(10,10,10,m);
	  
	IState etat1=new IState("E1");
	IState etat2=new IState("E2");
	
	ICondition cond1a2=new ICondition.ICell("N","D");
	ICondition cond2a1=new ICondition.ICell("N","D");
	
	IAction act1a2=new IAction.IMove("R"); //va toujours à sa droite
	IAction act2a1=new IAction.IHit("N");
	
	ITransition trans1a2=new ITransition(cond1a2,act1a2,etat2);
	ITransition trans2a1=new ITransition(cond2a1,act2a1,etat1);
	
	List<ITransition> transList1=new LinkedList<ITransition>();
	transList1.add(trans1a2);
	
	List<ITransition> transList2=new LinkedList<ITransition>();
	transList2.add(trans2a1);
	
	IBehaviour source1=new IBehaviour(etat1,transList1);
	IBehaviour source2=new IBehaviour(etat2,transList2);
	
	List<IBehaviour> behaviourList=new LinkedList<IBehaviour>();
	behaviourList.add(source1);
	behaviourList.add(source2);
	
	IAutomaton auto=new IAutomaton(etat1,behaviourList);
	  
	c.setAutomate(auto);
	c.step();
	assertTrue((auto.current()).name().equals("E2")); //On s'assure qu'on est bien passé au 2nd etat
	c.step();
	assertTrue((auto.current()).name().equals("E1"));
	c.step();
	assertTrue((auto.current()).name().equals("E2"));
	c.step();//Frappe
	c.step();//Avance Ouest
	c.step();//Frappe
	c.step();//Avance Nord
	assertTrue(c.x()==10);
	assertTrue(c.y()==10); //Il devrait avoir fait un tour normalement
  }
  
  @Test
  public void test01() throws Interpreter_Exception{
	  //Test boucle sur un état
	/*Def manuelle d'un automate à 3 transitions.
	  (1) - MyDir(S) / Wait -> (2)
	  (1) - True / Move(L) -> (1)
	  (2) - True / Wait -> (2)
	 
	 Se dirige en marchant vers le sud et et reste dans cette direction
	*/  
	Model m=new Model();  
	  
	Component c = new Component(10,10,10,m);
	  
	IState etat1=new IState("E1");
	IState etat2=new IState("E2");
	
	ICondition cond1a2=new ICondition.IMyDir("S");
	ICondition cond1a1=new ICondition.ITrue();
	ICondition cond2a2=new ICondition.ITrue();
	
	IAction act1a1=new IAction.IMove("L");
	IAction act1a2=new IAction.IWait();
	IAction act2a2=new IAction.IWait();
	
	ITransition trans1a1=new ITransition(cond1a1,act1a1,etat1);
	ITransition trans1a2=new ITransition(cond1a2,act1a2,etat2);
	ITransition trans2a2=new ITransition(cond2a2,act2a2,etat2);
	
	List<ITransition> transList1=new LinkedList<ITransition>();
	transList1.add(trans1a2); // /!\ L'ordre d'ajout des transitions est important ici: le true doit bien etre ajoute en dernier 
	transList1.add(trans1a1);
	
	List<ITransition> transList2=new LinkedList<ITransition>();
	transList2.add(trans2a2);
	
	IBehaviour source1=new IBehaviour(etat1,transList1);
	IBehaviour source2=new IBehaviour(etat2,transList2);
	
	List<IBehaviour> behaviourList=new LinkedList<IBehaviour>();
	behaviourList.add(source1);
	behaviourList.add(source2);
	
	IAutomaton auto=new IAutomaton(etat1,behaviourList);
	  
	c.setAutomate(auto);
	c.step();
	assertTrue((auto.current()).name().equals("E1")); //On s'assure qu'on est toujours au premier état
	c.step();
	assertTrue((auto.current()).name().equals("E1"));
	c.step();
	assertTrue((auto.current()).name().equals("E2"));
	c.step();
	assertTrue((auto.current()).name().equals("E2"));
  }


}
