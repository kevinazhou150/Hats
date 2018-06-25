package hats;

import java.util.Scanner;

/* When you get a strategy, it simply outputs everything on one line.
 * This just makes it spaced better (although still not that much better.
 * It is very possible that this class will be converted to something
 * that can process the sets that we find using LineOfSagesSetFinder.java.
 */
public class AddSpacing {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String str = reader.nextLine();
		str = str.replaceAll("],", "]\n");

		System.out.println(str);
		
		reader.close();
	}
}
