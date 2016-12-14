import java.io.*;

public class IPv7Checker {

	private static BufferedReader br;
	private static int numIpsWithTLS = 0;

	public static void main(String[] args) {
		try {
			br = new BufferedReader(new FileReader(new File("Input.txt")));

			String line;
			while((line = br.readLine()) != null) {
				//System.out.println("--------------------");
				boolean hyperABBA = false;
				boolean ipABBA = false;


				String[] parts = line.split("]");
				for(int i = 0; i < parts.length; i++) {
					if(parts[i].contains("[")) {
						String s = parts[i].replace("[", "-");
						String[] p = s.split("-");
						if(checkABBA(p[0])) {
							ipABBA = true;
						}

						if(checkABBA(p[1])) {
							hyperABBA = true;
						}
					}else {
						if(checkABBA(parts[i])) {
							ipABBA = true;
						}
					}
				}


				if(ipABBA && !hyperABBA) {
					numIpsWithTLS++;
				}

			}


		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Number of IP address that support TLS: " + numIpsWithTLS);
			try {
				br.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
	}

	

	/*
	*checks if ther is ABBA support in section of ip address
	*
	*@param message -- segement of ip address to check
	*
	*@return true -- has ABBA support
	*@return false -- does not have ABBA support
	*/
	public static boolean checkABBA(String message) {
		
		for(int i = 0; i < message.length() - 3; i++) {
			if(message.charAt(i) == message.charAt(i + 3)) {
				if(message.charAt(i + 1) == message.charAt(i + 2)) {
					if(message.charAt(i) != message.charAt(i + 1)) {
						//System.out.println(message);
						return true;
					}
					
				}
			}
		}

		return false;
	}
}