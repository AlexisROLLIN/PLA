package interpreter;

import LurkInTheShadow.*;
import java.util.Iterator;
import java.util.ListIterator;

public abstract class ICondition {

	ICondition() {
	}

	public boolean eval(Component c) {
		return true;
	}

	// Operateurs booleens basiques

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

	// Conditions booleennes de base

	public static class ITrue extends ICondition {
		public ITrue() {
		}

		public boolean eval(Component c) {
			return true;
		}
	}

	public static class ICell extends ICondition {
		IDirection direction;
		IType type;

		public ICell(String direction, String type) {
			this.direction = IDirection.strToDir(direction);
			this.type = IType.strToType(type);

		}

		public boolean eval(Component e) {

			if (direction == IDirection.NORTH || (e.dir() == IDirection.NORTH && direction == IDirection.FRONT)
					|| (e.dir() == IDirection.SOUTH && direction == IDirection.BACK)
					|| (e.dir() == IDirection.WEST && direction == IDirection.RIGHT)
					|| (e.dir() == IDirection.EAST && direction == IDirection.LEFT)) {
				// On teste tous les components du plateau pour voir s'ils sont dans la case à
				// côté de la nôtre
				Iterator<Component> iter = e.m_model.components.iterator();
				while (iter.hasNext()) {
					Component comp = iter.next();
					if (comp.is_in_case(e.x(), e.y() - 32)) { // S'il y a un component dans la case d'à coté
						if (comp.type() != IType.VOID && type == IType.VOID) {
							return false;
						}

						else if ((comp.type() == type && type != IType.VOID)
								|| (type == IType.ANYTHING && comp.type() != IType.VOID)) {
							return true;
						}

					}

				}
				return type == IType.VOID;
			}

			else if (direction == IDirection.SOUTH || (e.dir() == IDirection.SOUTH && direction == IDirection.FRONT)
					|| (e.dir() == IDirection.NORTH && direction == IDirection.BACK)
					|| (e.dir() == IDirection.EAST && direction == IDirection.RIGHT)
					|| (e.dir() == IDirection.WEST && direction == IDirection.LEFT)) {
				Iterator<Component> iter = e.m_model.components.iterator();
				while (iter.hasNext()) {
					Component comp = iter.next();
					if (comp.is_in_case(e.x(), e.y() + 32)) {
						if (comp.type() != IType.VOID && type == IType.VOID) {
							return false;
						}

						else if ((comp.type() == type && type != IType.VOID)
								|| (type == IType.ANYTHING && comp.type() != IType.VOID)) {
							return true;
						}
					}
				}
				return type == IType.VOID;
			}

			else if (direction == IDirection.WEST || (e.dir() == IDirection.WEST && direction == IDirection.FRONT)
					|| (e.dir() == IDirection.EAST && direction == IDirection.BACK)
					|| (e.dir() == IDirection.SOUTH && direction == IDirection.RIGHT)
					|| (e.dir() == IDirection.NORTH && direction == IDirection.LEFT)) {
				Iterator<Component> iter = e.m_model.components.iterator();
				while (iter.hasNext()) {
					Component comp = iter.next();
					if (comp.is_in_case(e.x() - 32, e.y())) {
						if (comp.type() != IType.VOID && type == IType.VOID) {
							return false;
						}

						else if ((comp.type() == type && type != IType.VOID)
								|| (type == IType.ANYTHING && comp.type() != IType.VOID)) {
							return true;
						}
					}
				}
				return type == IType.VOID;
			}

			else {
				Iterator<Component> iter = e.m_model.components.iterator();
				while (iter.hasNext()) {
					Component comp = iter.next();

					if (comp.is_in_case(e.x() + 32, e.y())) {

						if (comp.type() != IType.VOID && type == IType.VOID) {
							return false;
						}

						else if ((comp.type() == type && type != IType.VOID)
								|| (type == IType.ANYTHING && comp.type() != IType.VOID)) {
							return true;
						}
					}
				}
				return type == IType.VOID;
			}

		}
	}

	public static class IGotPower extends ICondition {
		public IGotPower() {
		}

		public boolean eval(Component c) {
			return (c.power > 0);
		}
	}

	public static class IGotStuff extends ICondition { // TODO
		public IGotStuff() {
		}

		public boolean eval(Component c) {
			return true;
		}
	}

	public static class IKey extends ICondition { // TODO
		String touches;

		public IKey(String s) {
			this.touches = s;
		}

		public boolean eval(Component c) {
			if (c.m_model.touches.contains(this.touches)) {
				return true;

			}
			return false;
		}
	}

	public static class IMyDir extends ICondition {
		IDirection direction;

		public IMyDir(String dir) {
			this.direction = IDirection.strToDir(dir);
		}

		public boolean eval(Component c) {
			return (c.dir() == direction);
		}
	}

	public static class IClosest extends ICondition { // TODO
		IDirection d;
		IType t;

		public IClosest(String s, String t) {
			this.d = IDirection.strToDir(t);
			this.t = IType.strToType(s);
		}

		public boolean eval(Component c) {
			Component tmp;
			Component plus_proche = null;
			double dist = 50000;
			double a;
			ListIterator<Component> iter = c.m_model.components.listIterator();
			while (iter.hasNext()) {
				tmp = iter.next();
				a = c.distance(tmp, c);
				if ((tmp.m_type) == this.t && a < dist && tmp != c) {
					dist = a;
					plus_proche = tmp;
				}
			}
			if ((d == IDirection.WEST || (c.dir() == IDirection.WEST && d == IDirection.FRONT)
					|| (c.dir() == IDirection.EAST && d == IDirection.BACK)
					|| (c.dir() == IDirection.SOUTH && d == IDirection.RIGHT)
					|| (c.dir() == IDirection.NORTH && d == IDirection.LEFT)) && plus_proche != null
					&& plus_proche.m_x < c.m_x) {
				return true;
			} else if ((d == IDirection.EAST || (c.dir() == IDirection.EAST && d == IDirection.FRONT)
					|| (c.dir() == IDirection.WEST && d == IDirection.BACK)
					|| (c.dir() == IDirection.SOUTH && d == IDirection.LEFT)
					|| (c.dir() == IDirection.NORTH && d == IDirection.RIGHT)) && plus_proche != null
					&& plus_proche.m_x > c.m_x) {
				return true;
			}

			else if ((d == IDirection.NORTH || (c.dir() == IDirection.NORTH && d == IDirection.FRONT)
					|| (c.dir() == IDirection.SOUTH && d == IDirection.BACK)
					|| (c.dir() == IDirection.WEST && d == IDirection.RIGHT)
					|| (c.dir() == IDirection.EAST && d == IDirection.LEFT)) && plus_proche != null
					&& plus_proche.m_y < c.m_y) {
				return true;
			} else if ((d == IDirection.SOUTH || (c.dir() == IDirection.SOUTH && d == IDirection.FRONT)
					|| (c.dir() == IDirection.NORTH && d == IDirection.BACK)
					|| (c.dir() == IDirection.EAST && d == IDirection.RIGHT)
					|| (c.dir() == IDirection.WEST && d == IDirection.LEFT)) && plus_proche != null
					&& plus_proche.m_y > c.m_y) {
				return true;
			} else {
				return false;
			}
		}
	}
}
