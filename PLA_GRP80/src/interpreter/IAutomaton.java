package interpreter;
import java.util.List;
import java.util.ListIterator;
import LurkInTheShadow.*;

public class IAutomaton {

	IState current ;
	List<IBehaviour> behaviours ;
	
	public IAutomaton(IState initial, List<IBehaviour> behaviours){
		this.current = initial ;
		this.behaviours = behaviours ;
	}
	
	public IState current() {
		return current;
	}
	
	public boolean step(Component c) throws Interpreter_Exception{
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		// true si une transition effectuée, false si aucune transition possible (=?= mort de l'automate ?)

		// - selectionne le comportement en fonction de l'état courant
		ListIterator<IBehaviour> iter = behaviours.listIterator();
		IBehaviour currentBehaviour=null;
		while (iter.hasNext()) {
			IBehaviour b = iter.next();
			if (b.source.name().equals(current.name())) {
				currentBehaviour = b;
				break; //inutile d'aller plus loin, on a trouvé l'état qui correspond à notre état courant dans la liste d'états
			}
		}

		// Si on ne trouve pas l'etat
		if (currentBehaviour == null) {
			throw new Interpreter_Exception("Etat introuvable\n");
		}

		try { 
			current=currentBehaviour.step(c); //L'etat courant devient l'etat cible de la transition qui correspond
		}catch(Interpreter_Exception e){
			return false; //Si "aucune transition possible" est renvoyé, false
		}
		return true;
	}
}
