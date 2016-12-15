import java.io.*;

public class Keypad {

	private static int[][] keypad = new int[6][50];
	private static BufferedReader br;

	public static void main(String[] args) {
		try {
			printKeypad(keypad);	//print starting keypad
			br = new BufferedReader(new FileReader(new File("Input.txt")));
			String line;
			while((line = br.readLine()) != null) {

				System.out.println(line);	//print command
				String[] parts = line.split(" ");
				if(parts[0].equals("rect")) {
					String[] numbers = parts[1].split("x");
					int x = Integer.parseInt(numbers[0]);
					int y = Integer.parseInt(numbers[1]);
					fillRect(x, y);
				}else if(parts[0].equals("rotate")) {

					if(parts[1].equals("row")) {
						int row = Integer.parseInt(parts[2].substring(parts[2].indexOf("=") + 1));
						int amount = Integer.parseInt(parts[4]);
						rotateRow(row, amount);
					}else if(parts[1].equals("column")) {
						int col = Integer.parseInt(parts[2].substring(parts[2].indexOf("=") + 1));
						int amount = Integer.parseInt(parts[4]);
						rotateCol(col, amount);
					}else {
						System.out.println("Not a valid command.");
					}

				}else {
					System.out.println("Not a valid command.");
				}


				printKeypad(keypad);	//show board after command
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			int total = countNumOn(keypad);
			System.out.printf("There are %d lights on the keypad.", total);

			try {
				br.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	*turns on all positions within x and y
	*
	*@param x -- column
	*@param y -- row
	*/
	public static void fillRect(int x, int y) {
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				keypad[i][j] = 1;
			}
		}
	}

	/*
	*rotates given column by amount
	*
	*@param col -- col to rotate
	*@param amount -- amount to rotate by
	*/
	public static void rotateCol(int col, int amount) {
		String line = "";
		for(int i = 0; i < keypad.length; i++) {
			line += keypad[i][col];
		}

		String newLine = adjString(line, amount);
		for(int i = 0; i < keypad.length; i++) {
			keypad[i][col] = (int)newLine.charAt(i) - 48;
		}
	}

	/*
	*rotates given row by amount
	*
	*@param row -- row to rotate
	*@param amount -- amount to rotate by
	*/
	public static void rotateRow(int row, int amount) {
		String line = "";
		for(int i = 0; i < keypad[row].length; i++) {
			line += keypad[row][i];
		}

		String newLine = adjString(line, amount);
		for(int i = 0; i < keypad[row].length; i++) {
			keypad[row][i] = (int)newLine.charAt(i) - 48;
		}
	}

	/*
	*moves values in string number of times given
	*
	*@param string -- string to adjust
	*@param amount -- amount to rotate by
	*
	*@return newString -- adjusted string
	*/
	public static String adjString(String string, int amount) {
		char[] letters = string.toCharArray();
		for(int i = 0; i < string.length(); i++) {
			int newPos = i + amount;
			while(newPos >= string.length()) {
				newPos -= string.length();
			}

			letters[newPos] = string.charAt(i);
		}

		String newString = "";
		for(int i = 0; i < letters.length; i++) {
			newString += letters[i];
		}
		System.out.println("NEW STRING: " + newString);
		return newString;

	}



	/*
	*counts how many lights are on in keypad
	*
	*@param matrix -- keypad to check for on lights
	*
	*@return totalOn -- total number of lights on in keypad
	*/
	public static int countNumOn(int[][] matrix) {
		int totalOn = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] == 1) {
					totalOn++;
				}
			}
		}

		return totalOn;
	}

	/*
	*prints the keypad to the terminal
	*
	*@param matrix -- matrix to draw to screen
	*/
	public static void printKeypad(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print("|" + matrix[i][j]);
			}
			System.out.println();
		}
	}
}