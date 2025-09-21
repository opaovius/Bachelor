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
		
		//Erstellen der verschiedenen HashFunktionen
		HashFunction<BitSet> hash1 = new HashFunction<>();
		HashFunction<BitSet> hashNt = new HashFunction<>();
		
		hash1.setHashFunction(new PolynomialHashFunction());
		hashNt.setHashFunction(new NtHash2());
		
		//Zusammenfassen in einer Liste
		List<HashFunction<BitSet>> hashFs = new ArrayList<>();
		
		hashFs.add(hash1);
		hashFs.add(hashNt);
		
		List<BitSetObject> BSOList = new ArrayList<>();
		
		//Erstellen des BitSets
		BSOList.add(createVector(hashFs, pathGenom));
		
		
		
		
		//System.out.println("places size: " + places.size());
		
		//Anaylse
		//System.out.println("Vektorgroesse: " + bitVector.size() + "\n"
		//		+ "Besetzte Bits: " + bitVector.cardinality());
		//PlaceCounter.count(places);
		//PlaceWriter.write(places);
		
	}
	
	public BitSetObject createVector(List<HashFunction<BitSet>> hashFs, String pathGenom) {
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		BitSet bitVector = new BitSet();
		String genomString = genom.toString();
		
		
		// System.out.println("check genom");
		for (HashFunction<BitSet> hf : hashFs) {
			
			//Durchlauf fuer Polynomialhashfunktionen
			if(hf.getHashFunction().getClass() == PolynomialHashFunction.class ) {
				
				bitVector = hf.output(genomString);
				
			}
			
			//Durchlauf fuer NtHash2
			if(hf.getHashFunction().getClass() == NtHash2.class) {
				
				
				//signed ntHash2
				//Strenge Verwendung von 16 k-meren
				bitVector = hf.output(genomString);
			}
			
		}

		BitSetObject BSO = new BitSetObject("test",bitVector);

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
	public void setPathGenom(String pathGenom) {
		this.pathGenom = pathGenom;
	}
	
	public void setPathGenes(String pathGenes) {
		this.pathGenes = pathGenes;
	}

}
