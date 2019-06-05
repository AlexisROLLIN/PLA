package interpreter;

public class IState {

	int id;
	String name ;
	// Le nom de l'état (Waiting, Hungry, Angry, ...) peut vous servir à adapter la représentation de l'entité à son humeur. 
	
	public IState(String name){
		this.name = name ;
	}
	
	public String name(){
		return this.name;
	}
	
	public int id(){
		return this.id;
	}
}
