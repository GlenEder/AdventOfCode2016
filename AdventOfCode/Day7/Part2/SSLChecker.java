import java.io.*;
import java.util.*;

public class SSLChecker {

	private static BufferedReader br;
	private static int numIpsWithSSL = 0;

	public static void main(String[] args) {
		try {
			br = new BufferedReader(new FileReader(new File("Input.txt")));

			String line;
			while((line = br.readLine()) != null) {
				//System.out.println("--------------------");
				

				ArrayList<String> ABAs = new ArrayList<>();

				String[] parts = line.split("]");		//split message up 
				for(int i = 0; i < parts.length; i++) {	//check for aba
					if(parts[i].contains("[")) {
						ArrayList<String> aba = checkABA(parts[i].substring(0, parts[i].indexOf("[")));
						if(aba.size() > 0) {
							for(int j = 0; j < aba.size(); j++) {
								ABAs.add(aba.get(j));
							}
						}
					}else {
						ArrayList<String> aba2 = checkABA(parts[i]);
						if(aba2.size() > 0) {
							for(int j = 0; j < aba2.size(); j++) {
								ABAs.add(aba2.get(j));
							}
						}
					}
				}

				ArrayList<String> BABs = new ArrayList<>();
				for(int i = 0; i < parts.length; i++) {
					if(parts[i].contains("[")) {
						ArrayList<String> bab = checkBAB(parts[i].substring(parts[i].indexOf("[") + 1));
						for(int j = 0; j < bab.size(); j++) {
							BABs.add(bab.get(j));
						}
					}
				}


				boolean matched = false;
				for(int i = 0; i < ABAs.size(); i++) {
					for(int j = 0; j < BABs.size(); j++) {
						if(compareABAandBAB(ABAs.get(i), BABs.get(j))) {
							if(!matched){
								numIpsWithSSL++;
								matched = true;
							}
							
							
						}
					}
				}
				


				

			}


		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Number of IP address that support SSL: " + numIpsWithSSL);
			try {
				br.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
	}

	

	/*
	*checks if ther is ABA support in section of ip address
	*
	*@param message -- segement of ip address to check
	*
	*@return abas -- array list of strings that are aba's
	*/
	public static ArrayList<String> checkABA(String message) {
		ArrayList<String> abas = new ArrayList<>();
		for(int i = 0; i < message.length() - 2; i++) {
			if(message.charAt(i) == message.charAt(i + 2)) {
				if(message.charAt(i) != message.charAt(i + 1)) {
					//System.out.println(message);
					abas.add(message.substring(i, i + 3));
				}
				
			}
		}

		return abas;
	}

	/*
	*checks if ther is BAB support in section of ip address
	*
	*@param message -- segement of ip address to check
	*
	*@return babs -- array list of strings that are bab's
	*/
	public static ArrayList<String> checkBAB(String message) {
		ArrayList<String> babs = new ArrayList<>();
		for(int i = 0; i < message.length() - 2; i++) {
			if(message.charAt(i) == message.charAt(i + 2)) {
				if(message.charAt(i) != message.charAt(i + 1)) {
					babs.add(message.substring(i, i + 3));
				}
			}
		}

		return babs;
	} 

	/*
	*compares the aba to the bab
	*
	*@param aba -- aba of message
	*@param bab -- bab of message
	*
	*@return true -- aba is bab flipped
	*@return false -- aba is not bab flipped
	*/
	public static boolean compareABAandBAB(String aba, String bab) {
		if(aba.charAt(0) == bab.charAt(1)) {
			if(aba.charAt(1) == bab.charAt(0)) {
				return true;
			}
		}

		return false;
	}
}