package app.helper;

import java.util.BitSet;

import app.records.BitSetObject;
import app.records.Genom;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import hashFunctionCollection.methods.CreateHashFunction;
import hashFunctionCollection.methods.ToHash;

public class CreateBloom {
	
	public static BitSetObject create(String pathGenom, int kmerSize){
		
		// Laden des Geoms aus Datei
		Genom genom = LoadGenom.load(pathGenom);
		
		//Erstellen der HashFunktion
		HashFunction hashFc = CreateHashFunction.create(kmerSize);
		
		int cap = BoundedInteger.calculateCap(genom.sequence().length());
		
		int[] kmerInt = ToHash.hash(genom, hashFc,cap);
		
		// Erstellen der BitSet Objekte
		BitSet bitS = new BitSet();

		for (int i : kmerInt) {
			bitS.set(i);
		}
		
		//Testen [T]
		//TestPipeline.test(kmerInt,kmerLong,genom.sequence(),kmerSize,hashFc,bitS);
		
		return new BitSetObject(genom.name(), bitS, cap);
	}

}
