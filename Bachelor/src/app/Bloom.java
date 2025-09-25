package app;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.NtHash2;
import hashFunctionCollection.NtHashClassic;
import hashFunctionCollection.PolynomialHashFunction;
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
		HashFunction<BitSet> hashNtClassic = new HashFunction<>();
		
		hash1.setHashFunction(new PolynomialHashFunction());
		hashNt.setHashFunction(new NtHash2());
		hashNtClassic.setHashFunction(new NtHashClassic());
		
		//Zusammenfassen in einer Liste
		List<HashFunction<BitSet>> hashFs = new ArrayList<>();
		
		hashFs.add(hash1);
		hashFs.add(hashNt);
		hashFs.add(hashNtClassic);
		
		List<BitSetObject> BSOList = new ArrayList<>();
		
		//Erstellen des BitSets
		BSOList.add(createVector(hashFs, pathGenom));
		
	}
	
	public BitSetObject createVector(List<HashFunction<BitSet>> hashFs, String pathGenom) {
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		BitSet bitVector = new BitSet();
		String genomString = genom.toString();
		
		
		// System.out.println("check genom");
		for (HashFunction<BitSet> hf : hashFs) {
			
			bitVector = hf.output(genomString);
			
		}

		BitSetObject BSO = new BitSetObject("test",bitVector);

		return BSO;
	}
	
	public void setPathGenom(String pathGenom) {
		this.pathGenom = pathGenom;
	}
	
	public void setPathGenes(String pathGenes) {
		this.pathGenes = pathGenes;
	}

}
