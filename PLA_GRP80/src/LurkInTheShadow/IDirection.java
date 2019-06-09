package LurkInTheShadow;

public enum IDirection {
	NORTH, SOUTH, WEST, EAST, FRONT, BACK, RIGHT, LEFT;
	
	public static IDirection strToDir(String str) {
		
		switch(str) {
			case "N": return IDirection.NORTH;
			case "S": return IDirection.SOUTH;
			case "E": return IDirection.EAST;
			case "O": return IDirection.WEST;
			case "F": return IDirection.FRONT;
			case "B": return IDirection.BACK;
			case "R": return IDirection.RIGHT;
			case "L": return IDirection.LEFT;
			default: System.out.println("Direction inconnue, FRONT par défaut \n");
				return IDirection.FRONT;
		}
		
	}
}
