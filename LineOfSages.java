package hats;

import java.util.ArrayList;

public class LineOfSages {
	public static void print2DArray (int[][] array) { //helper method that prints out a 2D array
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main (String[] args) {
		int persons = 3;
		int colors = 5;
		
		int cases = 1;
		for(int i = colors; i > 3; i--){ //find P(colors, 4)
			cases *= i;
		}
		
		int[][] set = new int[cases][persons]; //The independent set of permutations that we want to find. 
		int[][] visited = new int[cases][colors]; //Keeps track of the numbers we have tried to put into the independent set so far.
		
		
		/* Adds P(colors, 4) permutations of (persons - 1) numbers,
		 * such that all of the numbers are distinct. Then, they are put into the visited and set arrays.
		 */
		int counter = 0;
		for(int i = 0; i < Math.pow(colors, (persons - 1)); i++) {
			String number = Integer.toString(Integer.parseInt(Integer.toString(i), 10), colors);
			while(number.length() < (persons - 1)) {
				number = '0' + number;
			}
			ArrayList<Integer> distinctcheck = new ArrayList<Integer>();
			boolean distinctflag = true;
			for(int j = 0; j < number.length(); j++) {
				int x = Character.getNumericValue(number.charAt(j));
				if(distinctcheck.contains(x)) {
					distinctflag = false;
					break;
				}
				else {
					distinctcheck.add(x);
				}
			}
			if(distinctflag) {
				for(int j = 0; j < number.length(); j++) {
					visited[counter][j] = Character.getNumericValue(number.charAt(j));
					set[counter][j+1] = Character.getNumericValue(number.charAt(j));
				}
				counter++;
			}
		}
		for(int i = 0; i < set.length; i++) {
			set[i][0] = -1;
		}
		print2DArray(set);
		
		
		/*
		 * 
		 */
		
	}
}
