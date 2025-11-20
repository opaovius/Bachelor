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
		
		
		
	}
	
	private static StringBuilder toProtein(char[] seq, int start, boolean complement) {
		
		StringBuilder tripplet = new StringBuilder("");
		StringBuilder protein = new StringBuilder(seq.length/2);
		
		int pos = start;
		
		if(complement) {
			pos = seq.length - start;
		}
		
		while(pos >= 0 && pos < seq.length) {
	
		tripplet.append(seq[pos]);
		tripplet.append(seq[pos+1]);
		tripplet.append(seq[pos+2]);
			
		
		//klassische codesonne als switch
		switch(tripplet.toString()) {
		
		case "AAA", "AAG" -> protein.append('K');
		
		case "AAC", "AAU" -> protein.append('N');
		
		case "ACG", "ACA", "ACT", "ACC" -> protein.append('T');
		
		case "ATG" -> protein.append('M');
		
		case "ATA", "ATC", "ATT" -> protein.append('I');
		
		case "CGA", "CGC", "CGG", "CGT", "AGG", "AGA" -> protein.append('R');
		
		case "CAA", "CAG" -> protein.append('Q');
		
		case "CAC", "CAT" -> protein.append('H');
		
		case "CCA", "CCC", "CCG", "CCT" -> protein.append('P');
		
		case "CTA", "CTC", "CTG", "CTT", "TTA", "TTG" -> protein.append('L');
		
		case "TGG" -> protein.append('W');
		
		case "TGA", "TAG", "TAA" -> protein.append('*');
		
		case "TGC", "TGT" -> protein.append('C');
		
		case "TAC", "TAT" -> protein.append('Y');
		
		case "TCA", "TCC", "TCG", "TCT", "AGC", "AGT" -> protein.append('S');
		
		case "TTT", "TTC" -> protein.append('F');
		
		case "GGA", "GGC", "GGG", "GGT" -> protein.append('G');
		
		case "GAA", "GAG" -> protein.append('E');
		
		case "GAC", "GAT" -> protein.append('D');
		
		case "GCA", "GCC", "GCG", "GCT" -> protein.append('A');
		
		case "GTA", "GTC", "GTG", "GTT" -> protein.append('V');
		
		default -> protein.append('X');
			
		}
		
		tripplet.setLength(0);
			
		}
		
		return protein;
		
	}
	
}
