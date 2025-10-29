package hashFunctionCollection;

import java.util.HashMap;

import hashFunctionCollection.methods.NtHash2LookupTable;
import hashFunctionCollection.methods.SeedTable;

public class NtHash2 implements IHashFunction{
	
	HashMap<Character,Long> seed;
	long[][] look;
	
	public NtHash2() {
		this.seed = SeedTable.load();
		this.look = NtHash2LookupTable.load();
	}
	
	public long[] toHash(char[] seq,int kmerSize) {
		
		long[] kmerArray = new long[seq.length - kmerSize + 1];
		
		//Erstes Kmer berechnen
		kmerArray[0] = init(seq,kmerSize);
		
		int index = 1;
		
		//Restliche Kmere berechnen
		for(int counter = kmerSize; counter < seq.length; counter++) {
			
			kmerArray[index] = cycle(kmerArray[index-1], seq, kmerSize , index);
			index++;
		}
		
		return kmerArray;
	}
	
	private long cycle(long kmer, char[] seq, int kmerSize, int i) {
		
		long endKmer = srol(kmer);
		
		long tempKmer = 0L;
		
		//Wert aus der LookupTable auslesen
		switch(seq[i-1]) {
		case 'A': 
			tempKmer = look[0][kmerSize-1];
			break;
		case 'C':
			tempKmer = look[1][kmerSize-1];
			break;
		case 'G':
			tempKmer = look[2][kmerSize-1];
			break;
		case 'T':
			tempKmer = look[3][kmerSize-1];
			break;
		case 'N':
			tempKmer = look[4][kmerSize-1];
			break;
		default:
			IO.println("Fehler Bei NtHash2.cycle: Zeichen nicht im Lookup");
			break;
				
		}
		
		//XOR
		endKmer = endKmer ^ tempKmer;
		
		tempKmer = seed.get(seq[i - 1 + kmerSize]);
		
		//return mit XOR
		return endKmer ^ tempKmer;
	}
	
	private long init(char[] seq,int kmerSize) {
		
		long tempKmer = 0;
		long endKmer = 0;
		
		for(int counter = 0; counter < kmerSize; counter++) {
					
			char c = seq[counter];
			
			int index = kmerSize - 2 - counter;

			if (index >= 0) {

				switch (c) {
				case 'A':
					tempKmer = look[0][index];
					break;
				case 'C':
					tempKmer = look[1][index];
					break;
				case 'G':
					tempKmer = look[2][index];
					break;
				case 'T':
					tempKmer = look[3][index];
					break;
				case 'N':
					tempKmer = look[4][index];
					break;
				default:
					IO.println("Fehler Bei NtHash2.srol: Zeichen nicht im Lookup");
					break;

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