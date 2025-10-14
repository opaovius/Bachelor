package hashFunctionCollection;

import java.util.BitSet;

public class PolynomialHashFunction implements IHashFunction{

	public long[] toHash(String genom, int kmerSize) {

		BitSet bitVector = new BitSet();
		//LinkedList<Integer> places = new LinkedList<>();
		long[] primeNumbs = {2,5,7,11};
		int size = 100000;
		long[][] primeArray = initPrime(primeNumbs,kmerSize);
		int base = 0;
		
		for(int x = 0; x < genom.length() - kmerSize; x++) {
				
			long place = cycle(genom, x, x + kmerSize, size,  base, primeArray);
			
			bitVector.set((int) place);
			//places.add((int)place);
			
		}
		return new long[2];
	}
	
	private int cycle(String s,int start, int end, int size, int base ,long[][] primeArray) {
		
		long hash = 0;
		long curr = 0;
		int power = 0;

		for(int x = start; x < end; x++) {

			curr = s.charAt(x)*primeArray[base][power];
			power++;
			hash = hash + curr;
		}

		hash = hash % size;
		
		return (int) hash;
		
		
	}
	
	private long[][] initPrime(long[] primeNumbs, int kmerSize) {

		//[Primezahlen][Primzahl hoch Index]
		long[][] inits = new long[primeNumbs.length][kmerSize];
		
		for(int x = 0; x < primeNumbs.length; x++) {
			for(int y = 0; y < kmerSize; y++) {
				inits[x][y] = powLong(primeNumbs[x],y);
			}
		}
		
		return inits;
	}
	
	private Long powLong(long base, long power) {
		long result = base;
		
		if(power == 1) {
			return result;
		}
		
		if(power == 0) {
			return 1L;
		}
		
		for(int x = 1; x < power; x++) {
			result = result*base;
		}
		
		
		return result;
	}
	
}