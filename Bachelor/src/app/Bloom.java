package app;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

import hashFunctionCollection.DummyHashFunction;
import hashFunctionCollection.HashFunction;
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
		
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		
		Parser<List<String>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		List<String> genes = genParser.output();
		
		LinkedList<Double> places = new LinkedList<>();
		int kmerSize = 5;
		
		for(int x = 0; x < genom.length() - kmerSize; x++) {
			
			HashFunction<Double> hash = new HashFunction<Double>(genom.substring(x, x + kmerSize));
			hash.setHashFunction(new DummyHashFunction());
			places.add(hash.output());
		}
		
		System.out.println("places size: " + places.size());
		
		BitSet bitVector = new BitSet();
		
		for(Double d : places) {
			bitVector.set(d.intValue());
		}
		
		
		//Anaylse
		System.out.println("Vektorgroesse: " + bitVector.size() + "\n"
				+ "Besetzte Bits: " + bitVector.cardinality());
		PlaceCounter.count(places);
		PlaceWriter.write(places);
		
	}

	

}
