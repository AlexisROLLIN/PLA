package interpreter;
import tests.*;

public abstract class IAction {

	IAction(){}
	boolean exec(Component e){
		return true;
	}
	
	
	public static class IHit extends IAction {
		Direction direction ;
		Integer power; //Force du coup, on peut faire un constructeur le prenant en param si besoin
		
		public IHit(String direction){
			this.direction = Direction.strToDir(direction);
			this.power = 1 ; // valeur par défaut
		}
		
		public IHit(){
			this.direction = Direction.FRONT; // Front par défaut
			this.power = 1 ; // puissance par défaut
		}
		
		public boolean exec(Component e){
			return e.hit(this.direction);
		}
	}
	
	
	public static class IMove extends IAction {
		Direction direction ;
		
		public IMove(String direction){
			this.direction = Direction.strToDir(direction);
		}
		
		public IMove(){
			this.direction = Direction.FRONT;// Front par défaut
		}
		
		public boolean exec(Component e){
			return e.move(this.direction) ;
		}
		
	}
	
	//TODO
	/*
	Jump
	Pop
	Wizz ...
	*/

	
}
