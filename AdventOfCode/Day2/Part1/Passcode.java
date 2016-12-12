import java.io.*;

public class Passcode {

	private static int[][] keyPad =   {{1, 2, 3}, 
										{4, 5, 6}, 
										{7, 8, 9}};

	private static int x = 1;		//start in middle of keypad
	private static int y = 1;
	private static String passcode = "";

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("Input.txt")));
			String line;
			while((line = br.readLine()) != null) {
				for(int i = 0; i < line.length(); i++) {
					move(line.substring(i, i+1));
				}
				passcode += keyPad[y][x];
			}

			System.out.println(passcode);

		}catch(Exception e) {

		}
	}

	public static void move(String direction) {
		if(direction.equals("U")) {
			y--;
		}else if(direction.equals("R")) {
			x++;
		}else if(direction.equals("D")) {
			y++;
		}else if(direction.equals("L")){
			x--;
		}else {
			System.out.println("not valid direciton");
		}


		//constrain x
		if(x < 0) {
			x = 0;
		}else if (x > 2) {
			x = 2;
		}

		//constrain y
		if(y < 0) {
			y = 0;
		}else if (y > 2) {
			y = 2;
		}
	}
}