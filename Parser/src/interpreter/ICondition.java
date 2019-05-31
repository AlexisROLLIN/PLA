package interpreter;

import tests.Component;
import tests.Direction;

public abstract class ICondition {

	ICondition() {
	}

	public boolean eval(Component c) {
		return true;
	} // à redéfinir dans chaque sous-classe

	public static class ITrue extends ICondition {
		public ITrue() {}

		public boolean eval(Component c) {
			return true;
		}
	}

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

	public static class ICell extends ICondition {
		Direction direction;
		Component c;

		public ICell(String direction, String component){
			this.direction = Direction.strToDir(direction);
			//def du component A FAIRE
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

	// TODO
	/*
	 * public class True GotPower, GotStuff Key MyDir Cell Closest
	 */

}
