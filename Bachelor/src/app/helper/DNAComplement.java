package app.helper;

public class DNAComplement {
	
	/**
	 * 
	 * @param sequence Sequenze, welche zu ihrem Komplementaeren umgewandelt wird
	 * @return
	 */
	
	public static String complement(String sequence) {
		
		StringBuilder sb = new StringBuilder(sequence.length());
		
		for(int x = sequence.length()-1; x >= 0; x--) {
			
			switch(sequence.charAt(x)) {
			case 'A':
				sb.append('T');
				break;
			case 'T':
				sb.append('A');
				break;
			case 'C':
				sb.append('G');
				break;
			case 'G':
				sb.append('C');
				break;
			case 'N':
				sb.append('N');
				break;
			default:
				IO.println("Error bei DNAComplement.complement(): Falsches Zeichen");
				break;
			}
			
		}
		
		
		
		return sb.toString();
	}

}
