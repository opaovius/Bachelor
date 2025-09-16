package app;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.PolynomiaHashFunction;
import miscTools.DNARandomizer;
import miscTools.PlaceCounter;
import miscTools.PlaceWriter;
import parserCollection.GenFastaParser;
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
		
		Long size = 100000L;
		long[] primeNumbs = {2,5,7,11};
		int kmerSize = 10;
		long[][] primeArray = initPrime(primeNumbs,kmerSize);
		
		HashFunction<Long> hash1 = new HashFunction<Long>(size, 0, primeArray);
		//HashFunction<Long> hash2 = new HashFunction<Long>(size, 1, primeArray);
		//HashFunction<Long> hash3 = new HashFunction<Long>(size, 2, primeArray);
		//HashFunction<Long> hash4 = new HashFunction<Long>(size, 3, primeArray);
		hash1.setHashFunction(new PolynomiaHashFunction());
		//hash2.setHashFunction(new PolynomiaHashFunction());
		//hash3.setHashFunction(new PolynomiaHashFunction());
		//hash4.setHashFunction(new PolynomiaHashFunction());
		List<HashFunction<Long>> hashFs = new ArrayList<>();
		hashFs.add(hash1);
		//hashFs.add(hash2);
		//hashFs.add(hash3);
		//hashFs.add(hash4);
		
		List<BitSetObject> BSOList = new ArrayList<>();
		
		//for(String s : paths)
		BSOList.add(createVector(hashFs, pathGenom, kmerSize, size));
		
		
		
		//System.out.println("places size: " + places.size());
		
		//Anaylse
		//System.out.println("Vektorgroesse: " + bitVector.size() + "\n"
		//		+ "Besetzte Bits: " + bitVector.cardinality());
		//PlaceCounter.count(places);
		//PlaceWriter.write(places);
		
	}
	
	public BitSetObject createVector(List<HashFunction<Long>> hashFs, String pathGenom, int kmerSize, Long size) {
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		BitSet bitVector = new BitSet(size.intValue());
		String genomString = genom.toString();
		LinkedList<Integer> places = new LinkedList<>();
		
		
		// System.out.println("check genom");
		for (int x = 0; x < genom.length() - kmerSize; x++) {
			for(HashFunction<Long> hf : hashFs) {
				
				long place = hf.output(genomString, x, x + kmerSize);
				places.add((int)place);
				
				bitVector.set((int) place);
			}
		}

		BitSetObject BSO = new BitSetObject("test",bitVector);
		
		//Anaylse
			System.out.println("Vektorgroesse: " + bitVector.size() + "\n"
					+ "Besetzte Bits: " + bitVector.cardinality());
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
