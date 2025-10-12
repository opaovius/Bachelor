package app;

import java.util.ArrayList;

import hashFunctionCollection.BoundedInteger;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.NtHash2;
import hashFunctionCollection.NtHashClassic;
import miscTools.TestPipeline;
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
		
		//Erstellen der HashFunktionen
		ArrayList<HashFunction> hashFs = createHashFunctions();
		
		//Laden des Geoms aus Datei
		String genom = loadGenom(pathGenom);
		
		ArrayList<long[]> kmerListLong = new ArrayList<>();
		
		//Erzeugen des Long-Index-Werte
		for(HashFunction hf : hashFs) {
			kmerListLong.add(hf.output(genom));
		}
		
		ArrayList<int[]> kmerListInt = new ArrayList<>();
		
		//Begrenzen auf gewuenschte Groesse + Umwandlung zu Integer
		
		int cap = BoundedInteger.calculateCap(genom.length());
		
		IO.println("cap: " + cap);
		
		for(long[] longArray : kmerListLong) {
			kmerListInt.add(BoundedInteger.boundToInt(longArray,cap));
		}
		
		//Testen
		for(int[] ints : kmerListInt) {
		 TestPipeline.test(ints);
		}
		
		//Erstellen des BitSets
		//createVector(hashFs, genom);
		
		Parser<ArrayList<Gen>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		ArrayList<Gen> genList = genParser.output();
		
		//checkGenes(hashFs,BSOList,genList);
		
	}
	
	//Erstellt mittels Hashfunktionen eine Liste aus BitSetObjekten
	private ArrayList<BitSetObject> createVector(ArrayList<HashFunction> hashFs, String genom) {
		
		ArrayList<BitSetObject> bitVectors = new ArrayList<>();
		
		IO.println(genom.length());
		IO.println("Ziel: " + (genom.length() - 64 + 1));
		
		// BitSets erstellen mittes der Hashfunktionen
		// Am Ende am besten nur eine, aber jetzt mehrere zum austesten
		for (HashFunction hf : hashFs) {
			
			hf.output(genom);
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
		HashFunction hashNt = new HashFunction();
		HashFunction hashNtClassic = new HashFunction();

		hashNt.setHashFunction(new NtHash2());
		hashNtClassic.setHashFunction(new NtHashClassic());

		hashFs.add(hashNtClassic);

		return hashFs;
	}
	
	private String loadGenom(String pathGenom) {
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		return genom.toString();
		
	}
	
	public void setPathGenom(String pathGenom) {
		this.pathGenom = pathGenom;
	}
	
	public void setPathGenes(String pathGenes) {
		this.pathGenes = pathGenes;
	}

}
