package interpreter;
import LurkInTheShadow.*;

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
		IDirection direction;
		IType type;

		public ICell(String direction, String type){
			this.direction = IDirection.strToDir(direction);
			this.type=IType.strToType(type);
			
		}

		public boolean eval(Component e) {
			
			if (direction == IDirection.NORTH || (e.dir() == IDirection.NORTH && direction == IDirection.FRONT)
					|| (e.dir() == IDirection.SOUTH && direction == IDirection.BACK)
					|| (e.dir() == IDirection.WEST && direction == IDirection.RIGHT)
					|| (e.dir() == IDirection.EAST && direction == IDirection.LEFT)) {
				//On teste tous les components du plateau pour voir s'ils sont dans la case à côté de la nôtre
				ListIterator<Component> iter= e.m_model.components();
				while (iter.hasNext()) {
					Component comp=iter.next();
					if (comp.is_in_case(e.x(), e.y()-32)) { //S'il y a un component dans la case d'à coté
						if(comp.type()!=IType.VOID && type==IType.VOID) {
							return false;
						}
						
						else if((comp.type()==type && type!=IType.VOID) || (type==IType.ANYTHING && comp.type()!=IType.VOID)){
							return true;}
						}
				}
				return type==IType.VOID;
			}

			else if (direction == IDirection.SOUTH || (e.dir() == IDirection.SOUTH && direction == IDirection.FRONT)
					|| (e.dir() == IDirection.NORTH && direction == IDirection.BACK)
					|| (e.dir() == IDirection.EAST && direction == IDirection.RIGHT)
					|| (e.dir() == IDirection.WEST && direction == IDirection.LEFT)) {
				ListIterator<Component> iter= e.m_model.components();
				while (iter.hasNext()) {
					Component comp=iter.next();
					if (comp.is_in_case(e.x(), e.y()+32)) {
						if(comp.type()!=IType.VOID && type==IType.VOID) {
							return false;
						}
						
						else if((comp.type()==type && type!=IType.VOID) || (type==IType.ANYTHING && comp.type()!=IType.VOID)){
							return true;}
						}
				}
				return type==IType.VOID;
			}

			else if (direction == IDirection.WEST || (e.dir() == IDirection.WEST && direction == IDirection.FRONT)
					|| (e.dir() == IDirection.EAST && direction == IDirection.BACK)
					|| (e.dir() == IDirection.SOUTH && direction == IDirection.RIGHT)
					|| (e.dir() == IDirection.NORTH && direction == IDirection.LEFT)) {
				ListIterator<Component> iter= e.m_model.components();
				while (iter.hasNext()) {
					Component comp=iter.next();
					if (comp.is_in_case(e.x()-32, e.y())) {
						if(comp.type()!=IType.VOID && type==IType.VOID) {
							return false;
						}
						
						else if((comp.type()==type && type!=IType.VOID) || (type==IType.ANYTHING && comp.type()!=IType.VOID)){
							return true;}
						}
				}
				return type==IType.VOID;
			}

			else {
				ListIterator<Component> iter= e.m_model.components();
				while (iter.hasNext()) {
					Component comp=iter.next();
					
					if (comp.is_in_case(e.x()+32, e.y())) {
						
						if(comp.type()!=IType.VOID && type==IType.VOID) {
							return false;
						}
						
						else if((comp.type()==type && type!=IType.VOID) || (type==IType.ANYTHING && comp.type()!=IType.VOID)){
							return true;}
					}
				}
				return type==IType.VOID;
			}
			
		}
	}

	public static class IGotPower extends ICondition {
		public IGotPower() {}

		public boolean eval(Component c) {
			return (c.power > 0);
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
		IDirection direction;
		
		public IMyDir(String dir) {
			this.direction=IDirection.strToDir(dir);
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
