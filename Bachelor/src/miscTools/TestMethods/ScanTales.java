package miscTools.TestMethods;

import java.util.ArrayList;
import java.util.HashMap;

import app.records.Genom;
import hashFunctionCollection.methods.SeedTable;

public class ScanTales {

	public static void scan(ArrayList<Genom> gs){
		
		HashMap<String,Integer> tokens = new HashMap<>();
		StringBuilder sb = new StringBuilder("");
		
		for(Genom g : gs) {
		
			char[] seq = g.sequence();
			
			for(int x = 0; x < seq.length; x = x + 3) {
				sb.append(seq[x]);
				sb.append(seq[x+1]);
				
				if(!tokens.containsKey(sb.toString())) {
					tokens.put(sb.toString(), 1);
				}
				else {
					tokens.put(sb.toString(), tokens.get(sb.toString()) + 1);
				}
				sb.setLength(0);
			}
		}
		
		ArrayList<String> stringList = new ArrayList<>();
		
		for(String s : tokens.keySet()) {
			stringList.add(s);
		}
		SeedTable.createTales();
		//TalesToCharConverter.convert(stringList);
	}
}
