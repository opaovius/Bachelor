package hashFunctionCollection.variants;

import hashFunctionCollection.IHashFunction;
import hashFunctionCollection.methods.BoundedInteger;

public class TwoBitHash implements IHashFunction{
	
	private int kmerSize;
	private long mask;
	/**
	 * Diese Variante weiﬂt den Basen A,C,G,T jeweils zwei Bit zu, zB C = 01.
	 * Beim verarbeiten eines Kmers wird der 2 Bitwert der Base genommen und dann 2 mal nach
	 * links geshifftet. Danach wird die naechste hinzugefuegt, bis man einen 60Bit wert hat
	 * @param kmerSize : laenge der Kmere (Sollte maximal 30 sein)
	 */
	public TwoBitHash(int kmerSize) {
		this.kmerSize = kmerSize;
		this.mask = (1L << (2*kmerSize))-1;
	}
	
	public int[] toHash(char[] seq, int cap) {
		
		int[] kmerArr = new int[seq.length - kmerSize + 1];
		
		long tempKmer = init(seq);

		kmerArr[0] = BoundedInteger.boundToInt(tempKmer, cap);
		
		for(int pos = kmerSize; pos < seq.length; pos++) {
			tempKmer = cycle(seq,tempKmer,pos);
			kmerArr[pos-kmerSize] = BoundedInteger.boundToInt(tempKmer, cap);
		}
		
		return kmerArr;
	}
	
	//Restliche Kmer berechnen
	private long cycle(char[] seq, long tempKmer, int pos) {
		
		char c = seq[pos];
		
		tempKmer = tempKmer << 2;
		
		long curr = switch(c) {
			case 'A' -> 0;
		
			case 'C' -> 1;
		
			case 'G' -> 2;
			
			case 'T' -> 3;
		
			default -> 0;
		};
		
		return (tempKmer + curr) & mask;
	}
	
	// Erstes Kmer erstellen
	private long init(char[] seq) {
		
		long tempKmer = 0L;
		
		for(int pos = 0; pos < kmerSize; pos++) {
			
			char c = seq[pos];
			
			tempKmer = tempKmer << 2;
			
			long curr = switch(c) {
				case 'A' -> 0;
		
				case 'C' -> 1;
		
				case 'G' -> 2;
			
				case 'T' -> 3;
		
				default -> 0;
			};
			
		tempKmer = tempKmer + curr;
			
		}
		
		return tempKmer;
	}

}
