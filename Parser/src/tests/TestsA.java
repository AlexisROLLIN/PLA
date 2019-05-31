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
	  
	/*Def manuelle d'un automate à 2 états. /!\ Ne marche qu'avec le Cell et le Hit mals definis
	  (1) - Cell() / Move -> (2)
	  (2) - Cell() / Hit -> (1)
	*/  
	  
	Component c = new Component(10,10,10);
	  
	IState etat1=new IState("E1");
	IState etat2=new IState("E2");
	
	ICondition cond1a2=new ICondition.ICell("","");
	ICondition cond2a1=new ICondition.ICell("","");
	
	IAction act1a2=new IAction.IMove("N");
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
	auto.step(c);
	assertTrue((auto.current()).name().equals("E2")); //On s'assure qu'on est bien passé au 2nd etat
	auto.step(c);
	assertTrue((auto.current()).name().equals("E1"));
	auto.step(c);
	assertTrue((auto.current()).name().equals("E2"));
  }


}
