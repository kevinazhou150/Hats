package bipartite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		int n = 4, c = 3;
		ArrayList<HashMap<Vector, Integer>> strat =  new ArrayList<HashMap<Vector,Integer>>();
		for(int i = 0; i < n; i++){
			strat.add(new HashMap<Vector, Integer>());
		}
		//HashMap<Vector, Integer>[] strat = (HashMap<Vector, Integer>[]) (new Object[n]);
		for(int i = 0; i < c; i++){
			strat.get(0).put(new Vector(i, 1), 0);
		}
		for(int i = 1; i < n-1; i++){
			for(int j = 0; j < Math.pow(c, 2); j++){
				strat.get(i).put(new Vector(Integer.parseInt(Integer.toString(j, c)), 2), 0);
			}
		}
		for(int i = 0; i < c; i++){
			strat.get(n-1).put(new Vector(i,1), 0);
		}
		
		int[] hats = new int[n];
		int[] guesses = new int[n];
		int[] outs = new int[(int)(Math.pow(c, 2)) * (n-2) + c * 2];
		Vector[] sight = new Vector[n];
		outs[0] = -1;
		int maximum = Integer.MIN_VALUE;
		int localmin = Integer.MAX_VALUE;
		int score = 0;
		int index = 0;
		ArrayList<HashMap<Vector,Integer>> optimal = new ArrayList<HashMap<Vector, Integer>>();
		//HashMap<Vector, Integer>[] optimal = (HashMap<Vector, Integer>[]) new Map[n];
		for(int i = 0; i < n; i++){
			optimal.add(new HashMap<Vector,Integer>(strat.get(i)));
		}
		
		long count = 0;
		while(maxOut(outs, c)){
			count++;
			index = 0;
			for(int i = 0; i < strat.size(); i++){
				Vector[] keys = strat.get(i).keySet().toArray(new Vector[strat.get(i).size()]);
				for(int j = 0; j < keys.length; j++){
					strat.get(i).put(keys[j], outs[index++]);
				}
			}
			
			localmin = Integer.MAX_VALUE;
			for(int i = 0; i < n; i ++) hats[i] = 0;
			hats[0] = -1;
			while(maxOut(hats,c)){
				sight[0] = new Vector(hats[1], 1);
				for(int i = 1; i < n-1; i++){
					sight[i] =  new Vector(hats[i-1]*10 + hats[i+1],2);
				}
				sight[n-1] = new Vector(hats[n-2],1);
				for(int i = 0; i < n; i++){
					guesses[i] = strat.get(i).get(sight[i]);
				}
				score = compare(hats, guesses);
				if (score < localmin) localmin = score;
			}
			System.out.println(count);
			if(localmin > maximum){ 
				maximum = localmin;
				for(int i = 0; i < n; i++){
					optimal.set(i, new HashMap<Vector,Integer>(strat.get(i)));
				}
			}
			if(localmin == 1) break;
			
		}
		
		System.out.println(printStrat(optimal));

	}
	
	public static int compare(int[]a, int[]b){
		assert a.length == b.length;
		int count = 0;
		for(int i = 0; i < a.length; i++) if(a[i] == b[i]) count++;
		return count;
	}
	
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
