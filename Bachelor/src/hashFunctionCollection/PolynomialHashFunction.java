package hashFunctionCollection;

import java.util.BitSet;

import app.Gen;

public class PolynomialHashFunction implements IHashFunction{

	public BitSet toHashGenom(String genom) {

		BitSet bitVector = new BitSet();
		//LinkedList<Integer> places = new LinkedList<>();
		long[] primeNumbs = {2,5,7,11};
		int kmerSize = 10;
		int size = 100000;
		long[][] primeArray = initPrime(primeNumbs,kmerSize);
		int base = 0;
		
		for(int x = 0; x < genom.length() - kmerSize; x++) {
				
			long place = cycle(genom, x, x + kmerSize, size,  base, primeArray);
			
			bitVector.set((int) place);
			//places.add((int)place);
			
		}

		/*
		// Anaylse
		System.out.println("Vektorgroesse: " + bitVector.size() + "\n" + "Besetzte Bits: " + bitVector.cardinality());
		PlaceCounter.count(places);
		PlaceWriter.write(places);
		System.out.println("Place done!");
		*/
		return bitVector;
	}
	
	public int toHashGen(Gen gen) {
		return 0;
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