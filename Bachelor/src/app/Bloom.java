package app;

import java.util.ArrayList;
import java.util.BitSet;

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
		
		int kmerSize = 30;
		
		//Erstellen der HashFunktionen
		ArrayList<HashFunction> hashFs = createHashFunctions(30);
		
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
		
		for(long[] longArray : kmerListLong) {
			kmerListInt.add(BoundedInteger.boundToInt(longArray,cap));
		}
		
		ArrayList<BitSetObject> BSOList = new ArrayList<>();
		
		//Erstellen der BitSet Objekte
		for(int[] ints: kmerListInt) {
			
			BitSet bitS = new BitSet(); 
			
			for(int i: ints) {
				bitS.set(i);
			}
	
			BSOList.add(new BitSetObject("PH",bitS));
			IO.println("RT: " + Runtime.getRuntime().totalMemory());
		}
		
		for(BitSetObject b: BSOList) {
			IO.println(b.getVector().cardinality());
			IO.println(b.getVector().size());
			IO.println("----------------");
		}
		
		
		
		//Testen
		
		/*
		int counter = 0;
		
		for(int[] ints : kmerListInt) {
						
			IO.println(hashFs.get(counter).getHashFunction().getClass());
	
			TestPipeline.test(ints,kmerListLong.get(counter),genom,kmerSize,hashFs,counter);
			
			counter++;
		}
		*/
		//Erstellen des BitSets
		//createVector(hashFs, genom);
		
		Parser<ArrayList<Gen>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		ArrayList<Gen> genList = genParser.output();
		
		//checkGenes(hashFs,BSOList,genList);
		
	}
	
	//Testen, ob Gene enthalten
	private void checkGenes(ArrayList<HashFunction> hashFs, ArrayList<BitSetObject> BSOList, ArrayList<Gen> genList) {
		
		//Jedes gen hashen
		for(Gen g : genList) {
			
			if(g.sequence().length() >= 64) {
			
			}
		}
		
	}
	
	private ArrayList<HashFunction> createHashFunctions(int kmerSize){
		
		ArrayList<HashFunction> hashFs = new ArrayList<>();

		// Erstellen der verschiedenen HashFunktionen
		HashFunction hashNt = new HashFunction();
		HashFunction hashNtClassic = new HashFunction();

		hashNt.setHashFunction(new NtHash2(),kmerSize);
		hashNtClassic.setHashFunction(new NtHashClassic(),kmerSize);

		hashFs.add(hashNt);
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
