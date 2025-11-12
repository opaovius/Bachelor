package app.helper;

import java.util.ArrayList;

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
	
	public static void check(BitSetObject BSO, ArrayList<Gen> genList,int kmerSize, ArrayList<HashFunction> hfList) {
		
		StringBuilder sb = new StringBuilder();
		int found = 0;
		int notFound = 0;
		
		
		for(Gen gen: genList) {
			
			//Falls Gen kleiner als kmerSize, skip
			if(gen.sequence().length < kmerSize) {
				sb.append(gen.name() + "\n To small!\n");
				continue;
			}
			
			boolean inside = true;
			boolean insideCom = false;
			ArrayList<int[]> intList = new ArrayList<>();
			
			//Gen hashen
			for(HashFunction hf: hfList) {
				intList.add(ToHash.hash(gen,hf,BSO.cap()));
			}
			
			//Nachgucken, ob die hashwerte des Gen im BitSet gesetzt sind
			//Falls nein, dann abbruch
			for (int[] kmerInt : intList) {
				for (int index : kmerInt) {

					if (!BSO.vector().get(index)) {
						inside = false;
						break;
					}
				}
			}
			//Pruefen ,ob das Komplement enthalten ist
			if(!inside) {
				
				insideCom = true;
				
				Gen genComplement = new Gen(gen.name() + " complement"
						,DNAComplement.complement(gen.sequence()));
				
				ArrayList<int[]> intListReverse = new ArrayList<>();
				
				for(HashFunction hf: hfList) {
					intListReverse.add(ToHash.hash(genComplement, hf, BSO.cap()));
				}
				
				for (int[] kmerIntReverse : intListReverse) {
					for (int index : kmerIntReverse) {

						if (!BSO.vector().get(index)) {
							insideCom = false;
							break;
						}
					}
				}
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
		
		//ratio(found,notFound);
		
		//GenCheckWriter.write(sb);
		
	}
	
	//Berechnet die Verteilung zwischen gefundenen und nicht gefundenen Genen
	private static void ratio(int found, int notFound) {
		
		int sum = found + notFound;
		
		IO.println("Found|Not Found");
		IO.println(found + "|" + notFound);
		IO.println("Rate:" + Math.round((100.0/sum*found)* 100000.0 / 100000.0) + "%");
		
	}

}
