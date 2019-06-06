package interpreter;

import tests.Component;
import tests.Direction;
import tests.Type;
import java.util.ListIterator;

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

	public static class ICell extends ICondition {
		Direction direction;
		Type type;

		public ICell(String direction, String type){
			this.direction = Direction.strToDir(direction);
			this.type=Type.strToType(type);
			
		}

		public boolean eval(Component e) {
			
			if (direction == Direction.NORTH || (e.dir() == Direction.NORTH && direction == Direction.FRONT)
					|| (e.dir() == Direction.SOUTH && direction == Direction.BACK)
					|| (e.dir() == Direction.WEST && direction == Direction.RIGHT)
					|| (e.dir() == Direction.EAST && direction == Direction.LEFT)) {
				//On teste tous les components du plateau pour voir s'ils sont dans la case à côté de la nôtre
				ListIterator<Component> iter= e.model.comp.listIterator();
				while (iter.hasNext()) {
					Component comp=iter.next();
					if (comp.is_in_case(e.x(), e.y()-32)) { //S'il y a un component dans la case d'à coté
						if (type==Type.VOID) {//Si on s'attend à une case vide
							return false;
						}
						else if(comp.type()==type || type==Type.ANYTHING){//Si on s'attend à un composant reel
							return true;}
						}
				}
				//Case d'à coté vide
				return type==Type.VOID;
			}

			else if (direction == Direction.SOUTH || (e.dir() == Direction.SOUTH && direction == Direction.FRONT)
					|| (e.dir() == Direction.NORTH && direction == Direction.BACK)
					|| (e.dir() == Direction.EAST && direction == Direction.RIGHT)
					|| (e.dir() == Direction.WEST && direction == Direction.LEFT)) {
				ListIterator<Component> iter= e.model.comp.listIterator();
				while (iter.hasNext()) {
					Component comp=iter.next();
					if (comp.is_in_case(e.x(), e.y()+32)) {
						if (type==Type.VOID) {
							return false;
						}
						else if(comp.type()==type || type==Type.ANYTHING){
							return true;}
						}
				}
				return type==Type.VOID;
			}

			else if (direction == Direction.WEST || (e.dir() == Direction.WEST && direction == Direction.FRONT)
					|| (e.dir() == Direction.EAST && direction == Direction.BACK)
					|| (e.dir() == Direction.SOUTH && direction == Direction.RIGHT)
					|| (e.dir() == Direction.NORTH && direction == Direction.LEFT)) {
				ListIterator<Component> iter= e.model.comp.listIterator();
				while (iter.hasNext()) {
					Component comp=iter.next();
					if (comp.is_in_case(e.x()-32, e.y())) {
						if (type==Type.VOID) {
							return false;
						}
						else if(comp.type()==type || type==Type.ANYTHING){
							return true;}
						}
				}
				return type==Type.VOID;
			}

			else {
				ListIterator<Component> iter= e.model.comp.listIterator();
				while (iter.hasNext()) {
					Component comp=iter.next();
					if (comp.is_in_case(e.x()+32, e.y())) {
						if (type==Type.VOID) {
							return false;
						}
						else if(comp.type()==type || type==Type.ANYTHING){
							return true;}
						}
				}
				return type==Type.VOID;
			}
			
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
