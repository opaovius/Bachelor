package app;

import java.util.ArrayList;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.NtHash2;
import hashFunctionCollection.NtHashClassic;
import hashFunctionCollection.PolynomialHashFunction;
import parserCollection.GenFastaParser;
import parserCollection.GenomFastaParser;
import parserCollection.Parser;

public class Bloom {
	
	private String pathGenom;
	private String pathGenes;

	
	public Bloom(String pathGenom, String pathGenes) {
		this.pathGenes = pathGenes;
		this.pathGenom = pathGenom;
	}
	
	public void run() {
		
		//Zusammenfassen in einer Liste
		ArrayList<HashFunction> hashFs = createHashFunctions();
		
		ArrayList<BitSetObject> BSOList = new ArrayList<>();
		
		//Erstellen des BitSets
		BSOList = createVector(hashFs, pathGenom);
		
		Parser<ArrayList<Gen>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		ArrayList<Gen> genList = genParser.output();
		
		//checkGenes(hashFs,BSOList,genList);
		
	}
	
	//Erstellt mittels Hashfunktionen eine Liste aus BitSetObjekten
	private ArrayList<BitSetObject> createVector(ArrayList<HashFunction> hashFs, String pathGenom) {
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		ArrayList<BitSetObject> bitVectors = new ArrayList<>();
		String genomString = genom.toString();
		
		IO.println(genomString.length());
		IO.println("Ziel: " + (genomString.length() - 64 + 1));
		
		// BitSets erstellen mittes der Hashfunktionen
		// Am Ende am besten nur eine, aber jetzt mehrere zum austesten
		for (HashFunction hf : hashFs) {
			
			hf.output(genomString);
			//bitVectors.add(new BitSetObject("PLACEHOLDER",  hf.output(genomString)));
			
		}
		
		return bitVectors;
	}
	
	//Testen, ob Gene enthalten
	private void checkGenes(ArrayList<HashFunction> hashFs, ArrayList<BitSetObject> BSOList, ArrayList<Gen> genList) {
		
		//Jedes gen hashen
		for(Gen g : genList) {
			
			if(g.sequence().length() >= 64) {
			
			}
		}
		
	}
	
	private ArrayList<HashFunction> createHashFunctions(){
		
		ArrayList<HashFunction> hashFs = new ArrayList<>();

		// Erstellen der verschiedenen HashFunktionen
		HashFunction hash1 = new HashFunction();
		HashFunction hashNt = new HashFunction();
		HashFunction hashNtClassic = new HashFunction();

		hash1.setHashFunction(new PolynomialHashFunction());
		hashNt.setHashFunction(new NtHash2());
		hashNtClassic.setHashFunction(new NtHashClassic());

		hashFs.add(hash1);
		hashFs.add(hashNt);
		hashFs.add(hashNtClassic);

		return hashFs;
	}
	
	public void setPathGenom(String pathGenom) {
		this.pathGenom = pathGenom;
	}
	
	public void setPathGenes(String pathGenes) {
		this.pathGenes = pathGenes;
	}

}
