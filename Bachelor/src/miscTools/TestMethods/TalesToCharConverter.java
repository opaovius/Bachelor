package miscTools.TestMethods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TalesToCharConverter {

	public static void convert(ArrayList<String> token) {
		
		StringBuilder sb = new StringBuilder("");
		
		int counter = 33;
		
		token.sort(null);
		
		for(String s: token) {
			
			char c = (char) counter;
			
			sb.append(s + "\t" + c + "\n");
			counter++;
		}
		
		sb.append("XX" + "\t" + ((char) counter) + "\n");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/testFiles/SeedTableDir/TalesCharsConv.tsv"));
			
			bw.write(sb.toString());
			
			bw.close();
		}
		catch(IOException e) {
			IO.println("IOException: TalesToCharConverter.convert()");
		}
		
	}
	
}
