package cycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cycle {

	public static void main(String[] args) {
		//number of people and colors
		int n = 4, c = 6;
		
		//holds the strategy
		ArrayList<HashMap<Vector, Integer>> strat =  new ArrayList<HashMap<Vector,Integer>>();
		
		//instantiates the array
		for(int i = 0; i < n; i++){
			strat.add(new HashMap<Vector, Integer>());
		}
		//HashMap<Vector, Integer>[] strat = (HashMap<Vector, Integer>[]) (new Object[n]);
		
		//instantiates Vector to guess pairings
		for(int i = 0; i < n; i++){
			for(int j = 0; j < Math.pow(c, 2); j++){
				strat.get(i).put(new Vector(Integer.parseInt(Integer.toString(j, c)), 2), 0);
			}
		}
		
		//vars to loop for application of strategy
		int[] hats = new int[n]; //hats of people
		int[] guesses = new int[n]; //guesses of people
		int[] outs = new int[(int)(Math.pow(c, 2)) * n]; //ticks for all possible outputs. Basically a large base c number.
		Vector[] sight = new Vector[n];//represents what each person sees
		outs[0] = -1;//this is for max out*
		
		//to keep track of extreme results
		int maximum = Integer.MIN_VALUE;//best result so far
		int localmin = Integer.MAX_VALUE;//worst result of a strategy
		int score = 0;//number of people correct given a strategy and permutation
		int index = 0;//this is to tick the strategy
		ArrayList<HashMap<Vector,Integer>> optimal = new ArrayList<HashMap<Vector, Integer>>();//holds the optimal strategy so far
		//HashMap<Vector, Integer>[] optimal = (HashMap<Vector, Integer>[]) new Map[n];
		
		//instantiates optimal
		for(int i = 0; i < n; i++){
			optimal.add(new HashMap<Vector,Integer>(strat.get(i)));
		}
		
		long count = 0;//counts iterations
		
		//until all the strategies have been tested
		while(maxOut(outs, c)){
			count++;
			
			//renew the strategy
			index = 0;
			for(int i = 0; i < strat.size(); i++){
				Vector[] keys = strat.get(i).keySet().toArray(new Vector[strat.get(i).size()]);
				for(int j = 0; j < keys.length; j++){
					strat.get(i).put(keys[j], outs[index++]);
				}
			}
			
			//start from the beginning
			localmin = Integer.MAX_VALUE;
			for(int i = 0; i < n; i ++) hats[i] = 0;
			hats[0] = -1;
			
			//run all permutations
			while(maxOut(hats,c)){
				//refresh the sight array
				sight[0] = new Vector(hats[n-1]*10+hats[1], 2);
				for(int i = 1; i < n-1; i++){
					sight[i] =  new Vector(hats[i-1]*10 + hats[i+1],2);
				}
				sight[n-1] = new Vector(hats[n-2]*10 + hats[0],2);
				
				//refresh the guess array
				for(int i = 0; i < n; i++){
					guesses[i] = strat.get(i).get(sight[i]);
				}
				
				//get the score
				score = compare(hats, guesses);
				if (score < localmin) localmin = score;
			}
			
			//System.out.println(count);
			//check if the best came
			if(localmin > maximum){ 
				maximum = localmin;
				for(int i = 0; i < n; i++){
					optimal.set(i, new HashMap<Vector,Integer>(strat.get(i)));
				}
			}
			//System.out.println(localmin);
			//check for big moment
			if(localmin >= 1) System.out.println(localmin);
			
		}
		
		System.out.println(printStrat(optimal));//print out the best strategy
		System.out.println(maximum);//print out score of that strategy

	}
	
	/**
	 * Compares two arrays and sees which elements are equal
	 * @param a, first array
	 * @param b, second array
	 * @return the number of elements coinciding
	 */
	public static int compare(int[]a, int[]b){
		assert a.length == b.length;
		int count = 0;
		for(int i = 0; i < a.length; i++) if(a[i] == b[i]) count++;
		return count;
	}
	
	/**
	 * Ticks a number-array
	 * @param a an array that represents digits of a base [base] number
	 * @param base the base of the number a represents
	 * @return whether or not a is at its max
	 */
	public static boolean maxOut(int[]a, int base){
		for(int i = 0; i < a.length; i ++){
			if(a[i] != base -1){
				a[i]++;
				return true;
			}
			else a[i] = 0;
		}
		return false;
			
	}
	
	/**
	 * Prints out a strategy
	 * @param strat a Map List that represents a strategy
	 * @return a string that represents that strategy
	 */
	
	public static String printStrat(ArrayList<HashMap<Vector, Integer>> strat){
		String ret = "";
		for(int i = 0; i < strat.size(); i++){
			ret += i + ": ";
			Vector[] keys = strat.get(i).keySet().toArray(new Vector[strat.get(i).size()]);
			for(int j = 0; j < keys.length; j++){
				ret += keys[j] + "->" + strat.get(i).get(keys[j]) + " | ";
			}
			ret += "\n";
		}
		return ret;
	}

}
