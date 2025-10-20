package app.helper;

import java.util.ArrayList;

import app.records.BitSetObject;
import app.records.Gen;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.CreateHashFunction;
import hashFunctionCollection.methods.ToHash;
import miscTools.GenCheckWriter;

public class CheckGenes {
	
	public static void check(BitSetObject BSO, ArrayList<Gen> genList,int kmerSize) {
		
		HashFunction hashFc = CreateHashFunction.create(kmerSize);
		StringBuilder sb = new StringBuilder();
		
		for(Gen gen: genList) {
			
			boolean inside = true;
			
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
			
			if(inside) {
				sb.append(gen.name() + "\n Found!" + "\n");
			}
			else {
				sb.append(gen.name() + "\n Not found!" + "\n");
			}
		}
		
		GenCheckWriter.write(sb);
		
	}

}
