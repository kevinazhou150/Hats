package hats;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
			System.out.print("[");
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.print("], ");
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
				if(tempset1.get(persons1 - 2) >= 0 && set1.contains(tempset1)) 
					return false;
				else 
					set1.add(tempset1);
			}
		}

		return true;
	}
	
	/* Check if the last entry of each arraylist of a 2D array is nonnegative.
	 * 
	 */
	public static boolean checkArrayList (ArrayList<ArrayList<Integer>> set) {
		for(int i = 0; i < set.size(); i++) {
			if(set.get(i).get(set.get(i).size()-1) < 0)
				return false;
		}
		return true;
	}
	
	/* main method
	 * 
	 */
	public static void main (String[] args) throws InterruptedException, FileNotFoundException {
		int persons = 4;
		int colors = 6;
		
		int cases = 1;
		for(int i = colors; i > (colors - persons + 1); i--){ //find P(colors, k+2), where there are n persons and n+k colors.
			cases *= i;
		}
		
		ArrayList<ArrayList<Integer>> set = new ArrayList<ArrayList<Integer>>(); //The independent set of permutations that we want to find. 
		ArrayList<ArrayList<Integer>> visited = new ArrayList<ArrayList<Integer>>(); //Keeps track of the numbers we have tried to put into the independent set so far.
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
					tempset.add(Character.getNumericValue(number.charAt(j)));
				}
				checker.add(tempset);
				visited.add(tempset);
				for(int j = 0; j < tempset.size(); j++) {
					tempset2.add(tempset.get(j));
				}
				tempset2.add(-counter-1);
				set.add(tempset2);
				counter++;
			}
		}
		
		/* The strategy starts here.
		 * 
		 */
		PrintWriter writer = new PrintWriter(new File("sages.txt"));
		
		int counter1 = 0;
		int milestone = 0;
		outerloop: 
		while(true) { //repeat until we've visited everything
			int position = 0;
			while((set.get(position).get(persons-1) >= 0) && (position < cases-1)) { //find position, aka the first positive entry
				position++;
			}
			
			for(int i = 0; i < colors; i++) {
				if(!visited.get(position).contains(i)) {
					set.get(position).set(persons-1, i);
					visited.get(position).add(i);
					if(checkset(set, checker)) 
						break;
					else
						set.get(position).set(persons-1, -position-1);

					
				}
				else if(i == colors - 1) { //backtrack!
					while(visited.get(position).size() >= persons) {
						visited.get(position).remove(persons-1);
					}
					
					if(position <= 1 && counter1 >= 1) //Comment out this code to find all sets. This is a shortcut method.
						break outerloop;
					
					if(position > 0) {
						position--;
						set.get(position).set(persons-1, -position-1);
					}
					//else //uncomment this code to find all sets.
						//break outerloop;

				}
			}
			
			/*
			System.out.println("set: " + set); //remove comment to debug
			System.out.println("visited: " + visited + "\n");
			TimeUnit.SECONDS.sleep(1);
			*/
			
			if(checkArrayList(set)) {
				counter1++;
				//writer.println("Set " + counter1 + ": " + set);
				set.get(cases-1).set(persons-1, -position-1);
				
				if(counter1 >= Math.pow(10,  milestone)) {
					milestone++;
					System.out.println(counter1);
				}
			}
		}
		
		writer.println(counter1 * (colors - persons + 1));
		writer.close();
	}
}
