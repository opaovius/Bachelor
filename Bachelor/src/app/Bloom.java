package app;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.NtHash2;
import hashFunctionCollection.PolynomialHashFunction;
import miscTools.PlaceCounter;
import miscTools.PlaceWriter;
import parserCollection.GenomFastaParser;
import parserCollection.Parser;

public class Bloom {
	
	String pathGenom;
	String pathGenes;

	
	public Bloom(String pathGenom, String pathGenes) {
		this.pathGenes = pathGenes;
		this.pathGenom = pathGenom;
	}
	
	public void run() {
		
		int size = 100000;
		long[] primeNumbs = {2,5,7,11};
		int kmerSize = 10;
		long[][] primeArray = initPrime(primeNumbs,kmerSize);
		BitSet ntSet = new BitSet();
		
		//Erstellen der verschiedenen HashFunktionen
		HashFunction<Integer> hash1 = new HashFunction<>(size, 0, primeArray);
		HashFunction<Integer> hashNt = new HashFunction<>(size, ntSet);
		
		hash1.setHashFunction(new PolynomialHashFunction());
		hashNt.setHashFunction(new NtHash2());
		
		//Zusammenfassen in einer Liste
		List<HashFunction<Integer>> hashFs = new ArrayList<>();
		
		hashFs.add(hash1);
		hashFs.add(hashNt);
		
		List<BitSetObject> BSOList = new ArrayList<>();
		
		//Erstellen des BitSets
		BSOList.add(createVector(hashFs, pathGenom, kmerSize, size));
		
		
		
		
		//System.out.println("places size: " + places.size());
		
		//Anaylse
		//System.out.println("Vektorgroesse: " + bitVector.size() + "\n"
		//		+ "Besetzte Bits: " + bitVector.cardinality());
		//PlaceCounter.count(places);
		//PlaceWriter.write(places);
		
	}
	
	public BitSetObject createVector(List<HashFunction<Integer>> hashFs, String pathGenom, int kmerSize, int size) {
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		BitSet bitVector = new BitSet(size);
		String genomString = genom.toString();
		LinkedList<Integer> places = new LinkedList<>();
		
		
		// System.out.println("check genom");
		for (HashFunction<Integer> hf : hashFs) {
			
			//Durchlauf fuer Polynomialhashfunktionen
			if(hf.getHashFunction().getClass() == PolynomialHashFunction.class ) {
				
				for(int x = 0; x < genom.length() - kmerSize; x++) {
						
					long place = hf.output(genomString, x, x + kmerSize);
					places.add((int)place);
					
					bitVector.set((int) place);
				}
			}
			
			//Durchlauf fuer NtHash2
			if(hf.getHashFunction().getClass() == NtHash2.class) {
				
				BitSet currKmer = new BitSet(32);
				
				//signed ntHash2
				//Strenge Verwendung von 16 k-meren
				for(int x = 0; x < genom.length() - 16; x++) {
					
					if(x == 0) {
						currKmer.set(31);
						
						long[] arr = currKmer.toLongArray();
						long value = 0L;
						
						if(arr.length > 0) {
							value = arr[0];
						}
						
						System.out.println("Value: " + (value));
						System.out.println("Long Max: " + Long.MAX_VALUE);
						System.out.println("Math pow: " + (long) Math.pow(2, 31));
					}
					
				}
			}
			
		}

		BitSetObject BSO = new BitSetObject("test",bitVector);
		
		// Anaylse
		System.out.println("Vektorgroesse: " + bitVector.size() + "\n" + "Besetzte Bits: " + bitVector.cardinality());
		PlaceCounter.count(places);
		PlaceWriter.write(places);
		System.out.println("Place done!");

		return BSO;
	}
	
	/*
	public void checkForGenes(String pathGen, List<BitSetObject> BSOList, List<HashFunction<Long>> hashFs, int kmerSize) {
		
		Parser<List<String>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		List<String> genes = genParser.output();

		// System.out.println("check gen");
		for (String gen : genes) {
			for(HashFunction<Long> HF: hashFs) {
			
				if(!BSOList.get(0).vector.get(HF.output(gen, 0, kmerSize).intValue())) {
					//System.out.println("Gen " + count + " not inside");
				}
				
			}	
		}
	}
	*/

	public long[][] initPrime(long[] primeNumbs, int kmerSize) {

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
	
	public void setPathGenom(String pathGenom) {
		this.pathGenom = pathGenom;
	}
	
	public void setPathGenes(String pathGenes) {
		this.pathGenes = pathGenes;
	}

}
