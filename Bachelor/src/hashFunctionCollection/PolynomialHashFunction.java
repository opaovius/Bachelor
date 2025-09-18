package hashFunctionCollection;

import java.util.BitSet;
import java.util.LinkedList;

import miscTools.PlaceCounter;
import miscTools.PlaceWriter;

public class PolynomialHashFunction extends HashFunctionAdapter{

	@Override
	public BitSet toHash(String genom, int kmerSize, int size, int base ,long[][] primeArray) {

		BitSet bitVector = new BitSet();
		LinkedList<Integer> places = new LinkedList<>();
		
		for(int x = 0; x < genom.length() - kmerSize; x++) {
				
			long place = cycle(genom, x, x + kmerSize, size,  base, primeArray);
			
			bitVector.set((int) place);
			places.add((int)place);
			
		}

		// Anaylse
		System.out.println("Vektorgroesse: " + bitVector.size() + "\n" + "Besetzte Bits: " + bitVector.cardinality());
		PlaceCounter.count(places);
		PlaceWriter.write(places);
		System.out.println("Place done!");

		return new BitSet();
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
	
}