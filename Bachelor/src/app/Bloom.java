package app;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.PolynomialHashFunction;
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
		
		BigInteger size = BigInteger.valueOf(100000);
		long parameter = 3;
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		
		Parser<List<String>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		List<String> genes = genParser.output();
		
		int kmerSize = 10;
		BitSet bitVector = new BitSet(size.intValue());
		
		//System.out.println("check genom");
		for(int x = 0; x < genom.length() - kmerSize; x++) {
			
			HashFunction<BigInteger> hash1 = new HashFunction<BigInteger>(genom.substring(x, x + kmerSize), size, BigInteger.valueOf(2));
			HashFunction<BigInteger> hash2 = new HashFunction<BigInteger>(genom.substring(x, x + kmerSize), size, BigInteger.valueOf(5));
			HashFunction<BigInteger> hash3 = new HashFunction<BigInteger>(genom.substring(x, x + kmerSize), size, BigInteger.valueOf(7));
			HashFunction<BigInteger> hash4 = new HashFunction<BigInteger>(genom.substring(x, x + kmerSize), size, BigInteger.valueOf(11));
			hash1.setHashFunction(new PolynomialHashFunction());
			hash2.setHashFunction(new PolynomialHashFunction());
			hash3.setHashFunction(new PolynomialHashFunction());
			hash4.setHashFunction(new PolynomialHashFunction());
			bitVector.set(hash1.output().intValueExact());
			bitVector.set(hash2.output().intValueExact());
			bitVector.set(hash3.output().intValueExact());
			bitVector.set(hash4.output().intValueExact());
			
			
		}
		
		
		//System.out.println("places size: " + places.size());
		
		
		
		//int count = 1;
		//System.out.println("check gen");
		for(String gen: genes) {
			
			HashFunction<BigInteger> hash1 = new HashFunction<BigInteger>(gen, size, BigInteger.valueOf(2));
			HashFunction<BigInteger> hash2 = new HashFunction<BigInteger>(gen, size, BigInteger.valueOf(5));
			HashFunction<BigInteger> hash3 = new HashFunction<BigInteger>(gen, size, BigInteger.valueOf(7));
			HashFunction<BigInteger> hash4 = new HashFunction<BigInteger>(gen, size, BigInteger.valueOf(11));
			hash1.setHashFunction(new PolynomialHashFunction());
			hash2.setHashFunction(new PolynomialHashFunction());
			hash3.setHashFunction(new PolynomialHashFunction());
			hash4.setHashFunction(new PolynomialHashFunction());
			
			//System.out.println(gen);
			/*
			if(!bitVector.get(hash1.output().intValueExact())) {
				System.out.println("Gen " + count + " not inside");
			}
			else if(!bitVector.get(hash2.output().intValueExact())) {
				System.out.println("Gen " + count + " not inside");
			}
			else if(!bitVector.get(hash3.output().intValueExact())) {
				System.out.println("Gen " + count + " not inside");
			}
			else if(!bitVector.get(hash4.output().intValueExact())) {
				System.out.println("Gen " + count + " not inside");
			}
			else {
				System.out.println("Gen " + count + " likly inside");
			}
			
			count++;
			*/
		}
		
		
		//Anaylse
		//System.out.println("Vektorgroesse: " + bitVector.size() + "\n"
		//		+ "Besetzte Bits: " + bitVector.cardinality());
		//PlaceCounter.count(places);
		//PlaceWriter.write(places);
		
	}
	
	public void setPathGenom(String pathGenom) {
		this.pathGenom = pathGenom;
	}
	
	public void setPathGenes(String pathGenes) {
		this.pathGenes = pathGenes;
	}

}
