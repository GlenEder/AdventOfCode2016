import java.io.*;

public class VerticalTriangleCounter {

	private static int numValidTriangles = 0;

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("Input.txt")));
			String line;
			int row = 0;
			int[][] numbers = new int[3][3];
			while((line = br.readLine()) != null) {
				line = line.trim();
				String[] parts = line.split(" ");
				int[] nums = new int[3];
				int index = 0;

				for(int i = 0; i < parts.length; i++) {
					try{
						nums[index] = Integer.parseInt(parts[i]);
						index++;
					}catch (Exception e){

					}
				}
				System.out.println(row);
				for(int i = 0; i < nums.length; i++) {
					numbers[row][i] = nums[i];
				}
				row++;
				
				
				if(row > 2) {
					for(int i = 0; i < 3; i++) {
						if(isValidTriangle(numbers[0][i], numbers[1][i], numbers[2][i])) {
							numValidTriangles++;
						}
					}

					row = 0;
				}
			
				

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		System.out.println("Valid Triangles: " + numValidTriangles);

	}

	/*
	*checks if sides can make a real triangle
	*
	*@param x -- side 1
	*@param y -- side 2
	*@param z -- side 3
	*
	*@return true -- valid triangle
	*@reutrn false -- invalid triangle
	*/
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

		System.out.println(x + ":" + y + ":" + z);		//print out valid triangle sides
		return true;
	}


}