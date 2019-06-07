package tests;

public enum IType {

	ADVERSAIRE,DANGER,GATE,JUMPABLE,MISSILE,OBSTACLE,PRENABLE,TEAM,VOID,PLAYER,ANYTHING;
	
	public static IType strToType(String str) {
		
		switch(str) {
			case "A": return IType.ADVERSAIRE;
			case "D": return IType.DANGER;
			case "G": return IType.GATE;
			case "J": return IType.JUMPABLE;
			case "M": return IType.MISSILE;
			case "O": return IType.OBSTACLE;
			case "P": return IType.PRENABLE;
			case "T": return IType.TEAM;
			case "@": return IType.PLAYER;
			case "V": return IType.VOID;
			case "An": return IType.ANYTHING;
			default: System.out.println("Type inconnu, VOID par défaut \n");
				return IType.VOID;
		}
		
	}
	
}
