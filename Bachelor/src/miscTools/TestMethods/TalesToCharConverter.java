package miscTools.TestMethods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TalesToCharConverter {

	HashMap<String,Character> table;
	
	public TalesToCharConverter() {
		this.table = read();
	}
	
	public void convert(ArrayList<String> token) {

		StringBuilder sb = new StringBuilder("");

		int counter = 33;

		token.sort(null);

		for (String s : token) {

			char c = (char) counter;

			sb.append(s + "\t" + c + "\n");
			counter++;
		}

		sb.append("XX" + "\t" + ((char) counter) + "\n");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/testFiles/SeedTableDir/TalesCharsConv.tsv"));

			bw.write(sb.toString());

			bw.close();
		} catch (IOException e) {
			IO.println("IOException: TalesToCharConverter.convert()");
		}

	}

	public char[] convertArray(String[] seq) {

		char[] conv = new char[seq.length];
		
		for(int x = 0; x < seq.length; x++) {
			conv[x] = table.get(seq[x]);
		}
		
		return conv;
	}

	private HashMap<String, Character> read() {
		HashMap<String, Character> table = new HashMap<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader("src/testFiles/SeedTableDir/TalesCharsConv.tsv"));
			String curr = br.readLine();

			while (curr != null) {

				String[] arr = curr.split("\t");

				table.put(arr[0], arr[1].charAt(0));

				curr = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			IO.println("IOException: TalesToCharConverter.convertArray()");
		}

		return table;
	}

}
