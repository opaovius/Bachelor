package hashFunctionCollection.variants;

import hashFunctionCollection.IHashFunction;
import hashFunctionCollection.methods.BoundedInteger;

public class TwoBitHashTest implements IHashFunction{
	
	private int kmerSize;
	private int mask;
	/**
	 * Diese Variante weiﬂt den Basen A,C,G,T jeweils zwei Bit zu, zB C = 01.
	 * Beim verarbeiten eines Kmers wird der 2 Bitwert der Base genommen und dann 2 mal nach
	 * links geshifftet. Danach wird die naechste hinzugefuegt, bis man einen 60Bit wert hat
	 * @param kmerSize : laenge der Kmere (Sollte maximal 30 sein)
	 */
	public TwoBitHashTest(int kmerSize) {
		this.kmerSize = 15;
		this.mask = (1 << (2*kmerSize))-1;
	}
	
	public int[] toHash(char[] seq, int cap) {
		
		int[] kmerArr = new int[seq.length - kmerSize + 1];
		
		int tempKmer = init(seq);

		//kmerArr[0] = BoundedInteger.boundToInt(tempKmer, cap);
	
		kmerArr[0] = tempKmer;
		
		for(int pos = kmerSize; pos < seq.length; pos++) {
			tempKmer = cycle(seq,tempKmer,pos);
			//kmerArr[pos-kmerSize+1] = BoundedInteger.boundToInt(tempKmer, cap);
			kmerArr[pos-kmerSize+1] = tempKmer;
		}
		
		return kmerArr;
	}
	
	//Restliche Kmer berechnen
	private int cycle(char[] seq, int tempKmer, int pos) {
		
		char c = seq[pos];
		
		tempKmer = tempKmer << 2;
		
		int curr = switch(c) {
			case '0' -> 0;
		
			case '1' -> 1;
		
			case '2' -> 2;
			
			case '3' -> 3;
		
			default -> 0;
		};
		
		return (tempKmer + curr) & mask;
	}
	
	// Erstes Kmer erstellen
	private int init(char[] seq) {
		
		int tempKmer = 0;
		
		for(int pos = 0; pos < kmerSize; pos++) {
			
			char c = seq[pos];
			
			tempKmer = tempKmer << 2;
			
			int curr = switch(c) {
				case '0' -> 0;
		
				case '1' -> 1;
		
				case '2' -> 2;
			
				case '3' -> 3;
		
				default -> 0;
			};
			
		tempKmer = tempKmer + curr;
			
		}
		
		return tempKmer;
	}

}
