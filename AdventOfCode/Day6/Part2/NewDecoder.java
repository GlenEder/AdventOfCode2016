import java.io.*;

public class NewDecoder {

	private static int[][] counter = new int[8][26];

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("Input.txt")));
			String line;
			while((line = br.readLine()) != null) {
				char[] letters = line.toCharArray();
				for(int i = 0; i < 8; i++) {
					int n = (int)letters[i] - 97;
					counter[i][n]++;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}


		String message = "";

		for(int i = 0; i < counter.length; i++) {
			int smallest = counter[i][0];
			int spot = 0;
			for(int j = 0; j < counter[i].length; j++) {
				if(counter[i][j] < smallest) {
					smallest = counter[i][j];
					spot = j;
				}
			}

			message += (char)(spot + 97);
		}

		System.out.println("Decoded Message: " + message);
	}
}