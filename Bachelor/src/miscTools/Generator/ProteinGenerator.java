package miscTools.Generator;

import app.records.Genom;

public class ProteinGenerator {
	
	/**
	 * Wandelt ein DNA-Genom in ein Preotein-Genom um. Dabei enstehen 6 Sequenzen aus den 3 Leserahmen * 2 Straenge.
	 * @param genom : umzuwandelndes Genom
	 * @param start : Index fuer den Start des Leserahmens
	 */

	public static void generate(Genom genom, int start) {
		
		char[] seq = genom.sequence();
		StringBuilder tripplet = new StringBuilder("");
		StringBuilder protein = new StringBuilder("");
		
		for(int pos = start; pos < seq.length; pos = pos + 3) {
	
		tripplet.append(seq[pos]);
		tripplet.append(seq[pos+1]);
		tripplet.append(seq[pos+2]);
			
		
		switch(tripplet.toString()) {
		
		case "AAA", "AAG" -> protein.append('K');
		
		case "AAC", "AAU" -> protein.append('N');
		
		case "ACG", "ACA", "ACT", "ACC" -> protein.append('T');
		
		default -> IO.println();
			
		}
		
		tripplet.setLength(0);
			
		}
		
	}
	
}
