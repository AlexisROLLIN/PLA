package interpreter;
import java.util.List;
import java.util.ListIterator;
import LurkInTheShadow.*;


public class IBehaviour {

	IState source ;
	List<ITransition> transitions ;
	
	public IBehaviour(IState source, List<ITransition> transitions){
		this.source = source ; 
		this.transitions = transitions ;
	}
	
	public IState step(Component e) throws Interpreter_Exception{
		// - selectionne la première transition faisable
		// - lève une exception si aucune transition possible
		ListIterator<ITransition> iter = transitions.listIterator();
		while (iter.hasNext()) {
			ITransition t = iter.next();
			if (t.condition.eval(e)) {
				return t.exec(e);
			}
		}
		throw new Interpreter_Exception("Aucune transition possible \n");
	}
	
}
