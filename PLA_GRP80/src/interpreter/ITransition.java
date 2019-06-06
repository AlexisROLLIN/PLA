package interpreter;
import LurkInTheShadow.*;

public class ITransition {

	ICondition condition ;
	IAction action ;
	IState target ;
	
	public ITransition(ICondition condition, IAction action, IState target){
		this.condition = condition ;
		this.action = action ;
		this.target = target ;
	}
	
	public boolean feasible(Component e) {
		// teste si la condition de la transition est satisfaite
		return condition.eval(e);
	}
	
	public IState exec(Component e) {
		// execute l'action
		// return l'état cible de la transition
		action.exec(e);
		return target;
}
	
}
