package app;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.PolynomialHashFunction;
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
		
		long size = Long.MAX_VALUE;
		long parameter = 5;
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		
		Parser<List<String>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		List<String> genes = genParser.output();
		
		LinkedList<Long> places = new LinkedList<>();
		int kmerSize = 5;
		
		for(int x = 0; x < genom.length() - kmerSize; x++) {
			
			HashFunction<Long> hash1 = new HashFunction<Long>(genom.substring(x, x + kmerSize), size, parameter);
			HashFunction<Long> hash2 = new HashFunction<Long>(genom.substring(x, x + kmerSize), size, 7);
			HashFunction<Long> hash3 = new HashFunction<Long>(genom.substring(x, x + kmerSize), size, 11);
			hash1.setHashFunction(new PolynomialHashFunction());
			hash2.setHashFunction(new PolynomialHashFunction());
			hash3.setHashFunction(new PolynomialHashFunction());
			places.add(hash1.output());
			places.add(hash2.output());
			places.add(hash3.output());
		}
		
		
		System.out.println("places size: " + places.size());
		
		BitSet bitVector = new BitSet();
		
		for(Long d : places) {
			//System.out.println(d);
			bitVector.set(d.intValue());
		}
		
		
		//Anaylse
		System.out.println("Vektorgroesse: " + bitVector.size() + "\n"
				+ "Besetzte Bits: " + bitVector.cardinality());
		PlaceCounter.count(places);
		PlaceWriter.write(places);
		
	}

	

}
