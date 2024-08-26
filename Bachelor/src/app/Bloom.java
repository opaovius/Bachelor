package app;

import java.math.BigInteger;
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
		
		BigInteger size = BigInteger.valueOf(100000);
		long parameter = 3;
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		
		Parser<List<String>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		List<String> genes = genParser.output();
		
		LinkedList<BigInteger> places = new LinkedList<>();
		int kmerSize = 11;
		
		
		for(int x = 0; x < genom.length() - kmerSize; x++) {
			
			HashFunction<BigInteger> hash1 = new HashFunction<BigInteger>(genom.substring(x, x + kmerSize), size, BigInteger.valueOf(2));
			HashFunction<BigInteger> hash2 = new HashFunction<BigInteger>(genom.substring(x, x + kmerSize), size, BigInteger.valueOf(5));
			HashFunction<BigInteger> hash3 = new HashFunction<BigInteger>(genom.substring(x, x + kmerSize), size, BigInteger.valueOf(7));
			HashFunction<BigInteger> hash4 = new HashFunction<BigInteger>(genom.substring(x, x + kmerSize), size, BigInteger.valueOf(11));
			hash1.setHashFunction(new PolynomialHashFunction());
			hash2.setHashFunction(new PolynomialHashFunction());
			hash3.setHashFunction(new PolynomialHashFunction());
			hash4.setHashFunction(new PolynomialHashFunction());
			places.add(hash1.output());
			places.add(hash2.output());
			places.add(hash3.output());
			places.add(hash4.output());
			
			
		}
		
		
		System.out.println("places size: " + places.size());
		
		BitSet bitVector = new BitSet();
		/*
		for(BigInteger d : places) {
			//System.out.println(d);
			bitVector.set(d.);
		}
		*/
		
		//Anaylse
		System.out.println("Vektorgroesse: " + bitVector.size() + "\n"
				+ "Besetzte Bits: " + bitVector.cardinality());
		PlaceCounter.count(places);
		PlaceWriter.write(places);
		//DNARandomizer.createDNA(10000);
		
	}

}
