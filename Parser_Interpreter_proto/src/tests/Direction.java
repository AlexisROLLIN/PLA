package tests;

public enum Direction {
	NORTH, SOUTH, WEST, EAST, FRONT, BACK, RIGHT, LEFT;
	
	public static Direction strToDir(String str) {
		
		switch(str) {
			case "N": return Direction.NORTH;
			case "S": return Direction.SOUTH;
			case "E": return Direction.EAST;
			case "W": return Direction.WEST;
			case "F": return Direction.FRONT;
			case "B": return Direction.BACK;
			case "R": return Direction.RIGHT;
			case "L": return Direction.LEFT;
			default: System.out.println("Direction inconnue, FRONT par défaut \n");
				return Direction.FRONT;
		}
		
	}
}
