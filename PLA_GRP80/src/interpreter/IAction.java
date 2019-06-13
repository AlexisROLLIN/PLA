package interpreter;

import LurkInTheShadow.*;

public abstract class IAction {

	IAction() {
	}

	boolean exec(Component e) {
		return true;
	}

	public static class IHit extends IAction {

		IDirection direction ;
		int power; //Force du coup on peut faire un constructeur le prenant en param si besoin
		
		public IHit(String direction){
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
	
	//A FAIRE:
	public static class IPop extends IAction {
		IDirection direction ;
		
		public IPop(String dir){
			this.direction = IDirection.strToDir(dir);
		}
		
		public IPop(){
			this.direction = IDirection.FRONT;
		}
		
		public boolean exec(Component e){
			return e.pop(direction);
		}

	}
	
	public static class IWizz extends IAction {
		IDirection direction ;
		
		public IWizz(String dir){
			this.direction = IDirection.strToDir(dir);
		}
		
		public IWizz(){
			this.direction = IDirection.FRONT;
		}
		
		public boolean exec(Component e){
			return e.wizz(direction);
		}
	}
	
	public static class IJump extends IAction { //On ne s'en sert pas
		
		public IJump(String dir){}
		
		public IJump(){}
		
		public boolean exec(Component e){
			return e.jump();
		}
	}
	
	public static class ITurn extends IAction {
		IDirection direction ;
		
		public ITurn(String dir){
			this.direction = IDirection.strToDir(dir);
		}
		
		public ITurn(){
			this.direction = IDirection.RIGHT;
		}
		
		public boolean exec(Component e){
			return e.turn(direction);
		}
	}

	public static class IPick extends IAction { // TODO

		public IPick(String dir) {
		}

		public IPick() {
		}

		public boolean exec(Component e) {
			return e.pick();

		}
	}

	public static class IThrow extends IAction { // TODO

		public IThrow(String dir) {
		}

		public IThrow() {
		}

		public boolean exec(Component e) {
			return e.Throw();
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
			if (e.m_model.Cgmt == 'p') {
				e.Get1();
			} else if (e.m_model.Cgmt == 'm') {
				e.Get2();
			} else if (e.m_model.Cgmt == 'o') {
				e.Get3();
			} else if (e.m_model.Cgmt == 'l') {
				e.GetQueen();
			}
			return true;
		}
	}

	public static class IPower extends IAction { // TODO}

		public IPower() {
		}

		public boolean exec(Component e) {
			return e.life>0;
		}
	}

	public static class IKamikaze extends IAction { // TODO}

		public IKamikaze() {
		}

		public boolean exec(Component e) {
			return e.kamikaze();
		}
	}

	public static class IEgg extends IAction { // TODO}

		public IEgg() {
		}

		public boolean exec(Component e) {
			return e.egg();
		}
	}


}
