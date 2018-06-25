package hats;

import java.util.Scanner;

public class AddSpacing {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String str = reader.nextLine();
		str = str.replaceAll("],", "]\n");

		System.out.println(str);
		
		reader.close();
	}
}
