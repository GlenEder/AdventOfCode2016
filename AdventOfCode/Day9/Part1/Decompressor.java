import java.io.*;

public class Decompressor {
	

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("Input.txt")));
			String line = br.readLine();

			String newLine = "";
			int pos = 0;
			while(pos < line.length()) {
				System.out.println("Looking at " + line.charAt(pos));
				if(line.charAt(pos) != '(') {
					newLine += line.charAt(pos);
					pos++;
				}else {
					int indexOfFront = line.indexOf("(", pos - 1);
					int indexOfBack = line.indexOf(")", pos);
					String part = line.substring(indexOfFront + 1, indexOfBack);

					int distance = Integer.parseInt(part.substring(0, part.indexOf("x")));
					int dupTimes = Integer.parseInt(part.substring(part.indexOf("x") + 1));

					String toDup = line.substring(indexOfBack + 1, indexOfBack + distance + 1);
					System.out.println("To duplicate: " + toDup);
					String duped = duplicateString(toDup, dupTimes);
					newLine += duped;
					pos = indexOfBack + distance + 1;
				}
			}



			System.out.println("Length of decompressed file: " + newLine.length());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	/*
	*duplicates a given string a number of times
	*
	*@param s -- string to duplicate
	*@param numTimes -- number of times to duplicate string
	*
	*@return dupString -- duplicated string
	*/
	public static String duplicateString(String s, int numTimes) {
		String dupString = "";
		for(int i = 0; i < numTimes; i++) {
			dupString += s;
		}

		return dupString;
	}
}