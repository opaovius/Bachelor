package hashFunctionCollection;

import java.util.HashMap;

import hashFunctionCollection.methods.SeedTable;

public class NtHashClassic implements IHashFunction{
	
	private HashMap<Character,Long> seed;
	
	public NtHashClassic() {
		this.seed = SeedTable.load();
	}
	
	public long[] toHash(char[] seq, int kmerSize) {
		
		//Index fuer derzeitiges k-mer. 1, da init() kmer mit Index i =  0 erzeugt 
		int index = 1;
		
		//ArrayList<Long> places = new ArrayList<>();
		long[] kmerArray = new long[seq.length - kmerSize + 1];
		
		//Erzeugen des ersten kmers
		long kmer = init(seq,kmerSize);
		kmerArray[0] = kmer;
		
		//alle k-mere erzeugen
		for(int counter = kmerSize; counter < seq.length; counter++) {
			
			kmer = cycle(kmer,seq,kmerSize,index);
			kmerArray[index] = kmer; 
			index++;
		}
		
		return kmerArray;
	}
	
	private long cycle(long kmer, char[] seq, int kmerSize, int i) {
		
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
	
	private long init(char[] seq, int kmerSize) {
		
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
