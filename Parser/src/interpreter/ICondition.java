package interpreter;

import tests.Component;
import tests.Direction;
import tests.Type;

public abstract class ICondition {

	ICondition() {
	}

	public boolean eval(Component c) {
		return true;
	}

	
	//Operateurs booleens basiques
	
	public static class IOr extends ICondition { // Operateur OU

		ICondition m_left;
		ICondition m_right;

		public IOr(ICondition left, ICondition right) {
			m_left = left;
			m_right = right;
		}

		public boolean eval(Component c) {
			return m_left.eval(c) || m_right.eval(c);
		}
	}

	public static class IAnd extends ICondition { // Operateur ET

		ICondition m_left;
		ICondition m_right;

		public IAnd(ICondition left, ICondition right) {
			m_left = left;
			m_right = right;
		}

		public boolean eval(Component c) {
			return m_left.eval(c) && m_right.eval(c);
		}
	}

	public static class INot extends ICondition { // Operateur NON

		ICondition m_cond;

		public INot(ICondition cond) {
			m_cond = cond;
		}

		public boolean eval(Component c) {
			return !(m_cond.eval(c));
		}
	}
	
	
	//Conditions booleennes de base
	
	public static class ITrue extends ICondition {
		public ITrue() {}

		public boolean eval(Component c) {
			return true;
		}
	}

	public static class ICell extends ICondition { //TODO
		Direction direction;
		Type type;

		public ICell(String direction, String type){
			this.direction = Direction.strToDir(direction);
			this.type=Type.strToType(type);
			
		}

		public boolean eval(Component e) {
			//A changer, defini comme ça juste pour les tests
			return true; //On dit que la case est toujours libre
		}
	}

	public static class IGotPower extends ICondition {
		public IGotPower() {}

		public boolean eval(Component c) {
			return (c.power() > 0);
		}
	}
	
	public static class IGotStuff extends ICondition { //TODO
		public IGotStuff() {}

		public boolean eval(Component c) {
			return true;
		}
	}
	
	public static class IKey extends ICondition { //TODO
		public IKey(String s) {}

		public boolean eval(Component c) {
			return true;
		}
	}
	
	public static class IMyDir extends ICondition {
		Direction direction;
		
		public IMyDir(String dir) {
			this.direction=Direction.strToDir(dir);
		}

		public boolean eval(Component c) {
			return (c.dir()==direction);
		}
	}

	public static class IClosest extends ICondition { //TODO
		public IClosest(String s, String t) {}

		public boolean eval(Component c) {
			return true;
		}
	}

}
