package app.helper;

import java.util.ArrayList;

import app.records.BitSetObject;
import app.records.Gen;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.CreateHashFunction;
import hashFunctionCollection.methods.ToHash;
import miscTools.FileWriters.GenCheckWriter;

public class CheckGenes {
	
	public static void check(BitSetObject BSO, ArrayList<Gen> genList,int kmerSize) {
		
		HashFunction hashFc = CreateHashFunction.create(kmerSize);
		StringBuilder sb = new StringBuilder();
		int found = 0;
		int notFound = 0;
		
		
		for(Gen gen: genList) {
			
			//Falls Gen kleiner als kmerSize, skip
			if(gen.sequence().length() < kmerSize) {
				sb.append(gen.name() + "\n To small!\n");
				continue;
			}
			
			boolean inside = true;
			boolean insideCom = false;
			
			//Gen hashen
			int[] kmerInt = ToHash.hash(gen,hashFc,BSO.cap());
			
			//Nachgucken, ob die hashwerte des Gen im BitSet gesetzt sind
			//Falls nein, dann abbruch
			for(int index: kmerInt) {
				
				if(!BSO.vector().get(index)) {
					inside = false;
					break;
				}
			}
			
			//Pruefen ,ob das Komplement enthalten ist
			if(!inside) {
				
				insideCom = true;
				
				Gen genComplement = new Gen(gen.name() + " complement"
						,DNAComplement.complement(gen.sequence()));
				
				int[] kmerIntReverse = ToHash.hash(genComplement, hashFc, BSO.cap());
				
				for(int index: kmerIntReverse) {
					
					if(!BSO.vector().get(index)) {
						insideCom = false;
						break;
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
		
		ratio(found,notFound);
		
		GenCheckWriter.write(sb);
		
	}
	
	private static void ratio(int found, int notFound) {
		
		int sum = found + notFound;
		
		IO.println("Found|Not Found");
		IO.println(found + "|" + notFound);
		IO.println("Rate:" + Math.round((100.0/sum*found)* 100000.0 / 100000.0) + "%");
		
	}

}
