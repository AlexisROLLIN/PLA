package interpreter;

import tests.*;

public abstract class IAction {

	IAction() {
	}

	boolean exec(Component e) {
		return true;
	}

	public static class IHit extends IAction {
		IDirection direction;
		Integer power; // Force du coup, on peut faire un constructeur le prenant en param si besoin

		public IHit(String direction) {
			this.direction = IDirection.strToDir(direction);
			this.power = 1; // valeur par défaut
		}

		public IHit() {
			this.direction = IDirection.FRONT; // Front par défaut
			this.power = 1; // puissance par défaut
		}

		public boolean exec(Component e) {
			return e.hit(this.direction);
		}
	}

	public static class IMove extends IAction {
		IDirection direction;

		public IMove(String direction) {
			this.direction = IDirection.strToDir(direction);
		}

		public IMove() {
			this.direction = IDirection.FRONT;// Front par défaut
		}

		public boolean exec(Component e) {
			return e.move(this.direction);
		}

	}

	public static class IWait extends IAction {

		public IWait() {
		}

		public boolean exec(Component e) {
			return true;
		}

	}

	// A FAIRE:
	public static class IPop extends IAction { // TODO

		public IPop(String dir) {
		}

		public IPop() {
		}

		public boolean exec(Component e) {
			return true;
		}

	}

	public static class IWizz extends IAction { // TODO

		public IWizz(String dir) {
		}

		public IWizz() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	public static class IJump extends IAction { // TODO

		public IJump(String dir) {
		}

		public IJump() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	public static class ITurn extends IAction { // TODO

		public ITurn(String dir) {
		}

		public ITurn() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	public static class IPick extends IAction { // TODO

		public IPick(String dir) {
		}

		public IPick() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	public static class IThrow extends IAction { // TODO

		public IThrow(String dir) {
		}

		public IThrow() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	public static class IProtect extends IAction { // TODO

		public IProtect(String dir) {
		}

		public IProtect() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	public static class IStore extends IAction { // TODO}

		public IStore() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	public static class IGet extends IAction { // TODO}

		public IGet() {
		}

		public boolean exec(Component e) {
			if (e.model.Cgmt == 'p') {
				e.Get1();
			} else if (e.model.Cgmt == 'm') {
				e.Get2();
			} else  if(e.model.Cgmt == 'o'){
				e.Get3();
			}
			return true;
		}
	}

	public static class IPower extends IAction { // TODO}

		public IPower() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	public static class IKamikaze extends IAction { // TODO}

		public IKamikaze() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	public static class IEgg extends IAction { // TODO}

		public IEgg() {
		}

		public boolean exec(Component e) {
			return true;
		}
	}

	// TODO
	/*
	 * Jump Pop Wizz ...
	 */

}
