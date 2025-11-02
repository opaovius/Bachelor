package hashFunctionCollection.variants;

import java.util.HashMap;

import hashFunctionCollection.IHashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import hashFunctionCollection.methods.SeedTable;

public class NtHashClassic implements IHashFunction{
	
	private HashMap<Character,Long> seed;
	private int kmerSize;
	
	/**
	 * Weisst den Basen 64 Bit werte zu, welche vorher zufaellig generiert wurden.
	 * Mittels links shifts und XOR operationen werden Kmere in einem rolling Hash berechnet
	 * @param kmerSize : Laenge der Kmere, idealerweise 30
	 */
	
	public NtHashClassic(int kmerSize) {
		this.seed = SeedTable.load();
		this.kmerSize = kmerSize;
	}
	
	public int[] toHash(char[] seq, int cap) {
		
		//Index fuer derzeitiges k-mer. 1, da init() kmer mit Index i =  0 erzeugt 
		int index = 1;
		
		//ArrayList<Long> places = new ArrayList<>();
		int[] kmerArray = new int[seq.length - kmerSize + 1];
		
		//Erzeugen des ersten kmers
		long tempKmer = init(seq);
		
		kmerArray[0] = BoundedInteger.boundToInt(tempKmer, cap);
		
		//alle k-mere erzeugen
		for(int counter = kmerSize; counter < seq.length; counter++) {
			
			tempKmer = cycle(tempKmer,seq,index);
			kmerArray[index] = BoundedInteger.boundToInt(tempKmer, cap); 
			index++;
		}
		
		return kmerArray;
	}
	
	private long cycle(long kmer, char[] seq, int i) {
		
		//Einmal links rotieren
		kmer = Long.rotateLeft(kmer, 1);
		
		long tempKmer = 0L;
		
		//Long Wert aus seedTable nehmen, basierend auf Zeichen in Genom bei i - 1
		tempKmer = seed.get(seq[i - 1]);
		
		//Links rotation um kmer-groesse
		tempKmer = Long.rotateLeft(tempKmer, kmerSize);
		
		//XOR
		kmer = kmer ^ tempKmer;
		
		//Long Wert aus seedTable nehmen, basierend auf Zeichen in Genom bei i + kmerSize - 1
		tempKmer = seed.get(seq[i + kmerSize - 1]);
		
		//XOR
		return kmer ^ tempKmer;
		
	}
	
	private long init(char[] seq) {
		
		long tempKmer = 0L;
		long endKmer = 0L;
		
		for(int counter = 0; counter < kmerSize; counter++) {
			
			//Long Wert aus seedTable nehmen, basierend auf Zeichen in Genom
			tempKmer = seed.get(seq[counter]);
			
			// k - counter mal nach links rollen
			tempKmer =  Long.rotateLeft(tempKmer, kmerSize - counter - 1);
			//XOR
			endKmer = endKmer ^ tempKmer;
		}
		
		return endKmer;
	}
}
