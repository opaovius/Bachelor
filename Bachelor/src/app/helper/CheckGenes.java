package app.helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import app.records.Alphabet;
import app.records.BitSetObject;
import app.records.Gen;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.ToHash;

public class CheckGenes {
	/**
	 * Ueberprueft, ob die Gene im Genom vorkommen. Falls ein Gen nicht vorkommt wird das Komplement erzeugt.
	 * Gene kleiner als die KmerSize werden ignoriert.
	 * 
	 * @param BSO : BitSet, welches ueberprueft wird.
	 * @param genList : Liste, welche die Gene besitzt
	 * @param kmerSize : Groesse der Kmere
	 */
	
	public static void check(ArrayList<BitSetObject> BSOList, ArrayList<Gen> genList,int kmerSize, ArrayList<HashFunction> hfList,Alphabet alphabet) {
		
		switch(alphabet) {
		
		case Alphabet.DNA -> {
			for(BitSetObject BSO: BSOList) {
				checkDNA(BSO,genList,kmerSize,hfList);
			}
		}
		
		default -> {
			checkDefault(BSOList,genList,kmerSize,hfList);
		}
		
		}
		
	}
	
	private static void checkDefault(ArrayList<BitSetObject> BSOList, ArrayList<Gen> genList,int kmerSize, ArrayList<HashFunction> hfList) {
		
		StringBuilder sb = new StringBuilder();
		int found = 0;
		int count = 0;
		
		for(Gen g: genList) {
			
			for(BitSetObject BSO: BSOList) {
				boolean inside = isInside(hfList,BSO,g);
				
				if(inside) {
					sb.append("GenNr: " + count + " found in" + BSO.name() + "\n");
					found++;
				}
			}
			count++;
		}
		
		IO.println("FoundNR:" + found);
		write(sb.toString());
		
		
	}
	
	private static void checkDNA(BitSetObject BSO, ArrayList<Gen> genList,int kmerSize, ArrayList<HashFunction> hfList) {
		
		StringBuilder sb = new StringBuilder();
		int found = 0;
		int notFound = 0;
		Alphabet alphabet = genList.get(0).alphabet();
		
		
		for(Gen gen: genList) {
			
			//Falls Gen kleiner als kmerSize, skip
			if(gen.sequence().length < kmerSize) {
				sb.append(gen.name() + "\n To small!\n");
				continue;
			}
			
			boolean insideCom = false;
			
			//Nachgucken, ob die hashwerte des Gen im BitSet gesetzt sind
			//Falls nein, dann abbruch
			boolean inside = isInside(hfList, BSO, gen);
					
			//Pruefen ,ob das Komplement enthalten ist
			if(!inside) {
				
				Gen genComplement = new Gen(gen.name() + " complement"
						,DNAComplement.complement(gen.sequence()),alphabet);
				
				insideCom = isInside(hfList,BSO,genComplement);
				
				if(insideCom) {
					sb.append(genComplement.name() + "\n Found!\n");
					found++;
				}
				
			}
			
			if(inside) {
				sb.append(gen.name() + "\n Found!\n");
				found++;
			}
			else if(!inside & !insideCom){
				sb.append(gen.name() + "\n Not found!\n");
				notFound++;
			}
		}
		
		ratio(found,notFound);
		
		//GenCheckWriter.write(sb);
		
	}
	
	private static boolean isInside(ArrayList<HashFunction> hfList, BitSetObject BSO, Gen gen) {
		
		boolean inside = true;
		ArrayList<int[]> intList = new ArrayList<>();
		double insideCap = 10.0;
		int notInsideCounter = 0;
		int kmerCount = 0;
		
		//Gen hashen
		for(HashFunction hf: hfList) {
			intList.add(ToHash.hash(gen,hf,BSO.cap()));
		}
		
		for(int x = 0; x < intList.size(); x++) {
			kmerCount = kmerCount + intList.get(x).length;
		}
		
		
		//Nachgucken, ob die hashwerte des Gen im BitSet gesetzt sind
		//Falls 10% nein, dann abbruch
		for (int[] kmerInt : intList) {
			for (int index : kmerInt) {

				if (!BSO.vector().get(index)) {
					notInsideCounter++;
				}
				
				double currPercent = (100.0/kmerCount*notInsideCounter);
				
				if(currPercent > insideCap) {
					inside = false;
					break;
				}
				
			}
		}
		
		return inside;
	}
	
	//Berechnet die Verteilung zwischen gefundenen und nicht gefundenen Genen
	private static void ratio(int found, int notFound) {
		
		int sum = found + notFound;
		
		IO.println("Found|Not Found");
		IO.println(found + "|" + notFound);
		IO.println("Rate:" + Math.round((100.0/sum*found)* 100000.0 / 100000.0) + "%");
		
	}
	
	private static void write(String s) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("src/testFiles/AnalyseDir/TalesResult.txt"));
			
			br.write(s);
			br.close();
		}
		catch(IOException e) {
			IO.println("IOException: CheckGenes.write()");
		}
	}

}
