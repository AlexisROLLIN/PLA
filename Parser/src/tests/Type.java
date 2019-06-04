package tests;

public enum Type {

	ADVERSAIRE,DANGER,GATE,JUMPABLE,MISSILE,OBSTACLE,PRENABLE,TEAM,VOID,PLAYER,ANYTHING;
	
	public static Type strToType(String str) {
		
		switch(str) {
			case "A": return Type.ADVERSAIRE;
			case "D": return Type.DANGER;
			case "G": return Type.GATE;
			case "J": return Type.JUMPABLE;
			case "M": return Type.MISSILE;
			case "O": return Type.OBSTACLE;
			case "P": return Type.PRENABLE;
			case "T": return Type.TEAM;
			case "@": return Type.PLAYER;
			case "_": return Type.ANYTHING;
			case "V": return Type.VOID;
			default: System.out.println("Type inconnu, VOID par défaut \n");
				return Type.VOID;
		}
		
	}
	
}
