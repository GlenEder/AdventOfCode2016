import java.io.*;

public class UpgradedPasscode {

	private static String[][] keyPad =     {{" ", " ", "1", " ", " "}, 
											{" ", "2", "3", "4", " "},
											{"5", "6", "7", "8", "9"},
											{" ", "A", "B", "C", " "},
											{" ", " ", "D", " ", " "}};

	private static int x = 0;		//start at 5
	private static int y = 2;
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
		boolean changedX = false;
		boolean changedY = false;

		if(direction.equals("U")) {
			y--;
			changedY = true;
		}else if(direction.equals("R")) {
			x++;
			changedX = true;
		}else if(direction.equals("D")) {
			y++;
			changedY = true;
		}else if(direction.equals("L")){
			x--;
			changedX = true;
		}else {
			System.out.println("not valid direciton");
		}


		//constrain x
		if(changedX) {
			if(y == 0 || y == 4) {
				x = 2;
			}else if (y == 1 || y == 3) {
				if(x < 1) {
					x = 1;
				}else if(x > 3) {
					x = 3;
				}
			}else {
				if(x < 0) {
					x = 0;
				}else if (x > 4) {
					x = 4;
				}
			}
		}

		//constrain y
		if(changedY) {
			if(x == 0 || x == 4) {
				y = 2;
			}else if (x == 1 || x == 3) {
				if(y < 1) {
					y = 1;
				}else if (y > 3) {
					y = 3;
				}
			}else {
				if(y < 0) {
					y = 0;
				}else if (y > 4) {
					y = 4;
				}
			}
		}

		System.out.println(direction + " " +x + " " + y);
	}
}