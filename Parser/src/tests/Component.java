package tests;
import interpreter.IAutomaton;

public class Component {
	
	//Classe component de test très sommaire
	//Entre autres, un champ direction devra etre ajouté
	
	int power;
	int x;
	int y;
	IAutomaton automate;
	
	
	public Component(int ax, int ay,int apower){
		x= ax;
		y=ay;
		power=apower;
	}
	
	public void setAutomate(IAutomaton aut) {
		automate=aut;
	}
	
	public int power() {
		return power;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public boolean move(Direction d) {

		//Pas le vrai move, juste une version pour tester. Pour le vrai un switch sera nécessaire
		if (d == Direction.NORTH) {
			System.out.println("Avance\n");
		}
		else {
			System.out.println("Avance pas\n");
		}
		return true; //L'action s'est bien déroulée
	}
	
	public boolean hit(Direction d) {

		//Pas le vrai hit, juste une version pour tester.
		if (d == Direction.NORTH) {
			System.out.println("Frappe\n");
		}
		else {
			System.out.println("Frappe pas\n");
		}
		return true;//L'action s'est bien déroulée
	}
	
}
