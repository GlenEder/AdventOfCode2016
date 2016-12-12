import java.io.*;

public class TriangleCounter {

	private static int numValidTriangles = 0;

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("Input.txt")));
			String line;
			while((line = br.readLine()) != null) {
				line = line.trim();
				String[] parts = line.split(" ");
				int[] numbers = new int[3];
				int index = 0;
				for(int i = 0; i < parts.length; i++) {
					try{
						numbers[index] = Integer.parseInt(parts[i]);
						index++;
					}catch (Exception e){

					}
				}
			
				if(isValidTriangle(numbers[0], numbers[1], numbers[2])) {
					numValidTriangles++;
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		System.out.println("Valid Triangles: " + numValidTriangles);

	}


	public static boolean isValidTriangle(int x, int y, int z) {
		if((x + y) <= z) {
			return false;
		}
		if((y + z) <= x) {
			return false;
		}
		if((x + z) <= y) {
			return false;
		}

		System.out.println(x + ":" + y + ":" + z);
		return true;
	}


}