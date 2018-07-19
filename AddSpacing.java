package hats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddSpacing {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("3p5c.txt"));
		while(reader.hasNext()) {
			
			String str = reader.nextLine();
			
			/* Data processing methods lie ahead. 
			 * Choose which ones you want to apply to your string.
			 */
			
			//str = str.replaceAll("],", "]\n"); //1. adds spacing
			
			int index = 8;
			str = str.substring(str.indexOf("["));
			
			String lasts = "";
			while(index < str.length()) {
				lasts += (str.charAt(index));
				index += 11;
				if((index-8) % 44 == 0)
					lasts += "\n";
			}
			
			
			System.out.println(lasts);
			
		}
		reader.close();
	}
}
