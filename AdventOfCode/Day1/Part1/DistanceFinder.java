
public class DistanceFinder {

	private static String currentDirection = "U";
	private static int x = 0;
	private static int y = 0;

	public static void main(String[] args) {
		String command = "L5, R1, R4, L5, L4, R3, R1, L1, R4, R5, L1, L3, R4, L2, L4, R2, L4, L1, R3, R1, R1, L1, R1, L5, R5, R2, L5, R2, R1, L2, L4, L4, R191, R2, R5, R1, L1, L2, R5, L2, L3, R4, L1, L1, R1, R50, L1, R1, R76, R5, R4, R2, L5, L3, L5, R2, R1, L1, R2, L3, R4, R2, L1, L1, R4, L1, L1, R185, R1, L5, L4, L5, L3, R2, R3, R1, L5, R1, L3, L2, L2, R5, L1, L1, L3, R1, R4, L2, L1, L1, L3, L4, R5, L2, R3, R5, R1, L4, R5, L3, R3, R3, R1, R1, R5, R2, L2, R5, L5, L4, R4, R3, R5, R1, L3, R1, L2, L2, R3, R4, L1, R4, L1, R4, R3, L1, L4, L1, L5, L2, R2, L1, R1, L5, L3, R4, L1, R5, L5, L5, L1, L3, R1, R5, L2, L4, L5, L1, L1, L2, R5, R5, L4, R3, L2, L1, L3, L4, L5, L5, L2, R4, R3, L5, R4, R2, R1, L5";
		String[] commands = command.split(", ");


		for(int i = 0; i < commands.length; i++) {
			String direction = commands[i].substring(0,1);
			int distance = Integer.parseInt(commands[i].substring(1));
			System.out.println(currentDirection + " " + direction + " " + distance);
			move(distance, direction);

		}
		

		System.out.printf("X: %d, Y: %d%n", x, y);

		int total = Math.abs(x) + Math.abs(y);
		System.out.printf("Total Blocks: %d%n", total);
		
	}


	public static void move(int distance, String direction) {

		if(direction.equals("R")) {
			switch (currentDirection) {
				case "U":
					currentDirection = "R";
					break;
				case "R":
					currentDirection = "D";
					break;
				case "D":
					currentDirection = "L";
					break;
				case "L":
					currentDirection = "U";
					break;
				default:
					break;
			}
		}else {
			switch (currentDirection) {
				case "U":
					currentDirection = "L";
					break;
				case "R":
					currentDirection = "U";
					break;
				case "D":
					currentDirection = "R";
					break;
				case "L":
					currentDirection = "D";
					break;
				default:
					break;
			}
		}


		switch (currentDirection) {
				case "U":
					y += distance;
					break;
				case "R":
					x += distance;
					break;
				case "D":
					y -= distance;
					break;
				case "L":
					x -= distance;
					break;
				default:
					break;
			}
	}
}