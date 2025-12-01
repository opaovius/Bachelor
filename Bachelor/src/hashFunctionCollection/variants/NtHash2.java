package hashFunctionCollection.variants;

import java.util.HashMap;

import app.records.Alphabet;
import hashFunctionCollection.IHashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import hashFunctionCollection.methods.NtHash2LookupTable;
import hashFunctionCollection.methods.SeedTable;

public class NtHash2 implements IHashFunction{
	
	HashMap<Character,Long> seed;
	long[][] look;
	int kmerSize;
	
	/**
	 * Nutzt 64 Bitwerte wie NtHash, aber nutzt eine besondere srol() Bitrotation.
	 * Bei dieser werden Bit 0 mit Bit 32 und Bit 33 mit 63 vertauscht und danach
	 * eine links shift rotation angewendet 
	 * @param kmerSize : idealerweise 30
	 */
	
	public NtHash2(int kmerSize, Alphabet alphabet) {
		this.seed = SeedTable.load(alphabet);
		this.look = NtHash2LookupTable.load();
		this.kmerSize = kmerSize;
	}
	
	public int[] toHash(char[] seq, int cap) {
		
		int[] kmerArray = new int[seq.length - kmerSize + 1];
		
		//Erstes Kmer berechnen
		long tempKmer = init(seq);
		
		kmerArray[0] = BoundedInteger.boundToInt(tempKmer, cap);
		
		int index = 1;
		
		//Restliche Kmere berechnen
		for(int counter = kmerSize; counter < seq.length; counter++) {
			 
			tempKmer = cycle(tempKmer, seq, index);
			kmerArray[index] = BoundedInteger.boundToInt(tempKmer, cap);
			index++;
		}
		
		return kmerArray;
	}
	
	private long cycle(long kmer, char[] seq, int i) {
		
		long endKmer = srol(kmer);
		
		long tempKmer = 0L;
		
		//Wert aus der LookupTable auslesen
		switch(seq[i-1]) {
			case 'A' -> tempKmer = look[0][kmerSize-1];
		
			case 'C' -> tempKmer = look[1][kmerSize-1];
		
			case 'G' -> tempKmer = look[2][kmerSize-1];
			
			case 'T' -> tempKmer = look[3][kmerSize-1];
			
			case 'N' -> tempKmer = look[4][kmerSize-1];
			
			default -> IO.println("Fehler Bei NtHash2.cycle: Zeichen nicht im Lookup");
		}
		
		//XOR
		endKmer = endKmer ^ tempKmer;
		
		tempKmer = seed.get(seq[i + kmerSize - 1]);
		
		//return mit XOR
		return endKmer ^ tempKmer;
	}
	
	private long init(char[] seq) {
		
		long tempKmer = 0;
		long endKmer = 0;
		
		for(int counter = 0; counter < kmerSize; counter++) {
					
			char c = seq[counter];
			
			int index = kmerSize - 2 - counter;
			
			if (index >= 0) {

				switch (c) {
					case 'A' -> tempKmer = look[0][index];
				
					case 'C' -> tempKmer = look[1][index];
			
					case 'G' -> tempKmer = look[2][index];
				
					case 'T' -> tempKmer = look[3][index];
				
					case 'N' -> tempKmer = look[4][index];
				
					default -> IO.println("Fehler Bei NtHash2.srol: Zeichen nicht im Lookup");
				}
			}
			else {
				tempKmer = seed.get(c);
			}
			
			
			endKmer = endKmer ^ tempKmer;
			
		}
		
		return endKmer;
	}
	
	
	private long srol(long curr) {
		
		//Bit 0 mit Bit 32 und Bit 33 mit Bit 63 tauschen und dann shiften
		long m = ((curr & 0x8000000000000000L) >>> 30) | ((curr & 0x100000000L) >>> 32) ;
		
		return ((curr << 1) & 0xFFFFFFFDFFFFFFFFL) | m;
	}
	
}