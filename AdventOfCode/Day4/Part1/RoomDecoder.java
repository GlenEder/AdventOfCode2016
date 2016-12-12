import java.io.*;
import java.util.*;

public class RoomDecoder {

	private static int sectorIdTotal = 0;	//sum of sector id's

	public static void main(String[] args) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("Input.txt")));
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split("-");

				//gets last part of line
				String sectorAndOrder = parts[parts.length - 1];	
				//store sector id value													
				int sectorId = Integer.parseInt(sectorAndOrder.substring(0, sectorAndOrder.indexOf("[")));	
				//store checksum			
				String order = sectorAndOrder.substring(sectorAndOrder.indexOf("[") + 1, sectorAndOrder.length() - 1);
				//append list
				String message = "";
				for(int i = 0; i < parts.length - 1; i++) {
					message += parts[i];
				}

				//verify room code
				if(verifyRoomCode(message, order)) {
					sectorIdTotal += sectorId;
				}
				
			}

			//print total
			System.out.println("Total sum of sector Id's: " + sectorIdTotal);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*
	*verifies the room code 
	*
	*@param message -- letter code to be decoded
	*@param order -- check sum for code
	*
	*@return true -- is valid room code
	*@return false -- not valid room code
	*/
	public static boolean verifyRoomCode(String message, String order) {
		//count all letters in message
		int[] letters = countLetters(message);

		int[] indexs = new int[5];
		for(int i = 0; i < 5; i++) {
			indexs[i] = (int)order.charAt(i) - 97;
		}

		//check for order in check sum
		for(int i = 0; i < indexs.length - 1; i++) {
			if(letters[indexs[i]] < letters[indexs[i + 1]]) {
				return false;
			}
		}


		//check for larger numbers not in check sum
		for(int i = 0; i < letters.length; i++) {
			boolean check = true;
			for(int j = 0; j < indexs.length; j++) {
				if(i == indexs[j]) {
					check = false;
				}
			}

			if(check) {
				for(int k = 0; k < indexs.length; k++) {
					if(letters[i] > letters[indexs[k]]) {
						return false;
					}
				}
			}
		}

		
		//check alpha order if equal
		int[] counter = new int[5];
		for(int i = 0; i < indexs.length; i++) {
			counter[i] = letters[indexs[i]];
		}
		if(!verifyAlphaOrder(counter, order)) {
			return false;
		}
		


		return true;
	}


	/*
	*counts each letter amount in message
	*
	*@param message -- message to count letters
	*
	*@return letters -- array of counts for each letter
	*/
	public static int[] countLetters(String message) {
		int[] letters = new int[26];
		for(int i = 97; i <= 122; i++) {
			char letter = (char)i;
			int n = 0;
			char[] chars = message.toCharArray();
			for(int j = 0; j < chars.length; j++) {
				if(letter == chars[j]) {
					n++;
				}
			}

			letters[i - 97] = n;
		}

		return letters;
	}

	/*
	*checks if check sum is alpha order
	*
	*@param counter -- letter count for chars in message
	*@param order -- check sum to verify
	*
	*@return true -- is in alpha order
	*@return false -- not in alpha order
	*/
	public static boolean verifyAlphaOrder(int[] counter, String order) {
		char[] letters = order.toCharArray();
		for(int i = 0; i < counter.length - 1; i++) {
			if(counter[i] == counter[i + 1]) {
				int a = (int)letters[i];
				int b = (int)letters[i + 1];
				if(a > b) {
					return false;
				}
			}
		}

		return true;
	}
}