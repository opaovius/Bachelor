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
	
	public long[] toHash(String genom,int kmerSize) {
		
		long[] kmerArray = new long[genom.length() - kmerSize + 1];
		
		kmerArray[0] = init(genom.substring(0, kmerSize));
		
		int index = 1;
		
		for(int counter = kmerSize; counter < genom.length(); counter++) {
			
			kmerArray[index] = cycle(kmerArray[index-1], genom, kmerSize , index);
			index++;
		}
		
		return kmerArray;
	}
	
	private long cycle(long kmer, String genom, int kmerSize, int i) {
		
		long endKmer = srol(kmer);
		
		long tempKmer = seed.get(genom.charAt(i - 1));
		
		//kmerSize mal srol anwenden
		for(int k = 0; k < kmerSize; k++) {
			tempKmer = srol(tempKmer);
		}
		
		//XOR
		endKmer = endKmer ^ tempKmer;
		
		tempKmer = seed.get(genom.charAt(i - 1 + kmerSize));
		
		//return mit XOR
		return endKmer ^ tempKmer;
	}
	
	private long init(String kmerString) {
		
		long tempKmer = 0;
		long endKmer = 0;
		
		for(int counter = 0; counter < kmerString.length(); counter++) {
			
			tempKmer = seed.get(kmerString.charAt(counter));
			
			for(int x = 0; x < kmerString.length() - 1 - counter; x++) {
				tempKmer =  srol(tempKmer);
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
	
	private long srolCycle(int c, int d, long[][] MS_Tab_31L, long[][] MS_Tab_33R) {
		
		return (MS_Tab_31L[c][d % 31] | MS_Tab_33R[c][d % 33]);
	}
	
}