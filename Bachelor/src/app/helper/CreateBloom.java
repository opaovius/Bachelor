package app.helper;

import java.util.ArrayList;
import java.util.BitSet;

import app.records.BitSetObject;
import app.records.Genom;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import hashFunctionCollection.methods.CreateHashFunction;
import hashFunctionCollection.methods.ToHash;
import miscTools.TestMethods.TestPipeline;

public class CreateBloom {
	
	/**
	 * Erstellt ein BitSetObject, welches ein Java BitSet nutzt und einen String zu identifizierung des Genoms.
	 *  
	 * @param genom : Genom, welches in eien BitSetObject umgewandelt wird.
	 * @param kmerSize : Groesse der Kmere
	 * @return BitSetObject
	 */
	
	public static BitSetObject create(Genom genom, int kmerSize){
		
		//Erstellen der HashFunktion
		ArrayList<HashFunction> hfList = CreateHashFunction.create(kmerSize);
		ArrayList<int[]> intList = new ArrayList<>();
		
		int cap = BoundedInteger.calculateCap(genom.sequence().length);
		
		for(HashFunction hashFc: hfList) {
		
			intList.add(ToHash.hash(genom, hashFc,cap));
			 
		}
		// Erstellen der BitSet Objekte
		BitSet bitS = new BitSet((1 << cap)-1);

		for(int[] intArr: intList) {
			for(int i : intArr) {
				bitS.set(i);
			}
		}
		//Testen [T]
		TestPipeline.test(intList,genom.sequence(),kmerSize,hfList,bitS);
		
		return new BitSetObject(genom.name(), bitS, cap);
	}

}
