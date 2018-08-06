package hats;

public class Cycleverify {
	public static void print2DArray (int[][] array) { //helper method that prints out a 2D array
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int people = 6;
		boolean flag = true;
		int[][] strategy0 = 
			   {{1, 2, 1},
				{2, 2, 0},
				{1, 0, 0}};
		int[][] strategyn = 
			   {{0, 0, 1},
				{2, 1, 1},
				{2, 0, 2}};
		int[][] strategy = 
			   {{1, 0, 0},
				{1, 2, 1},
				{2, 2, 0}};
		
		for(int i = 0; i < Math.pow(3, people); i++) {
			String configuration = Integer.toString(Integer.parseInt(Integer.toString(i), 10), 3);
			while(configuration.length() < people) {
				configuration = "0" + configuration;
			}
			
			String guesses = "";
			for(int j = 0; j < people; j++) {
				int left = Character.getNumericValue(configuration.charAt((j-1+people) % people));
				int right = Character.getNumericValue(configuration.charAt((j+1) % people));
				if(j == 0) 
					guesses += strategy0[left][right];
				else if (j == people-1) 
					guesses += strategyn[left][right];
				else
					guesses += strategy[left][right];
			}
			
			for(int j = 0; j < people; j++) {
				if(guesses.charAt(j) == configuration.charAt(j)) 
					break;
				if(j == people - 1) {
					System.out.println("Strategy fails: " + configuration + " " + guesses);
					flag = false;
				}
			}
			
		}
		if(flag) {
			System.out.println("Strategy successful");
		}
	}

}
