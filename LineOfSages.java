//am currently working on this --Kaz

package hats;
import java.util.ArrayList;

/* I will use the example of persons = 3, colors = 5 to make things simple.
 * My goal is to create 20 ordered triples such that there does not exist a pair of them that are "1 change apart",
 * i.e. you can change one number from a triple to get to another triple.
 * For example, consider (1, 2, 3) and (1, 2, 4). If person 3 hears "1" and "2", he won't know what to do.
 * That's why I want to avoid that.
 * So, this code tries to find those ordered triples. First, I make 20 ordered pairs (0,1), (0,2), and so on until (4,3).
 * Then I add a number to each ordered pair until it all works out.
 */

public class LineOfSages {
	
	public static void print2DArray (int[][] array) { //helper method that prints out a 2D array
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/* Check if, for each pair of permutations, you can't change 1 number to get from one to the other.
	 * Equivalently, this means that if we remove all numbers from one column, then the remaining permutations are all distinct.
	 * This is what I have programmed here.
	 */
	public static boolean checkset (ArrayList<ArrayList<Integer>> set, ArrayList<ArrayList<Integer>> checker) {
		int persons1 = set.get(0).size();
		
		for(int i = 0; i < persons1 - 1; i++) {
			ArrayList<ArrayList<Integer>> set1 = new ArrayList<ArrayList<Integer>>();
			for(int j = 0; j < set.size(); j++) {
				ArrayList<Integer> tempset1 = new ArrayList<Integer>();
				for(int k = 0; k < persons1; k++) {
					if(i != k) {
						tempset1.add(set.get(j).get(k));
					}
				}
				if(set1.contains(tempset1)) 
					return false;
				else 
					set1.add(tempset1);
			}
			System.out.println("Set formed by removing entry " + i + ": " + set1);
		}

		return true;
	}
	
	/* main method
	 * 
	 */
	public static void main (String[] args) {
		int persons = 3;
		int colors = 5;
		
		int cases = 1;
		for(int i = colors; i > 3; i--){ //find P(colors, 4)
			cases *= i;
		}
		
		ArrayList<ArrayList<Integer>> set = new ArrayList<ArrayList<Integer>>(); //The independent set of permutations that we want to find. 
		int[][] visited = new int[cases][colors]; //Keeps track of the numbers we have tried to put into the independent set so far.
		ArrayList<ArrayList<Integer>> checker = new ArrayList<ArrayList<Integer>>(); //all n-1 tuples such that all of the numbers in the n-1 tuples are distinct
		
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
				ArrayList<Integer> tempset = new ArrayList<Integer>();
				ArrayList<Integer> tempset2 = new ArrayList<Integer>();
				for(int j = 0; j < number.length(); j++) {
					visited[counter][j] = Character.getNumericValue(number.charAt(j));
					tempset.add(Character.getNumericValue(number.charAt(j)));
				}
				checker.add(tempset);
				for(int j = 0; j < tempset.size(); j++) {
					tempset2.add(tempset.get(j));
				}
				tempset2.add(-counter-1);
				set.add(tempset2);
				counter++;
			}
		}
		
		System.out.println(set);
		System.out.println(checker);
		
		/* The strategy starts here.
		 * 
		 */
		checkset(set, checker);
	}
}
