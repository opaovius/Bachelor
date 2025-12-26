package app;

import java.util.ArrayList;

import app.helper.CreateBloom;
import app.records.Alphabet;
import app.records.BitSetObject;
import app.records.Gen;
import app.records.Genom;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.CreateHashFunction;

public class IBF {
	
	public static void filter(ArrayList<ArrayList<Genom>> genom,ArrayList<Gen> genes,
			String hashOptionOne,String hashOptionTwo, Alphabet alphabet, int kmerSize) {

		// Erstellen der HashFunktionen
		ArrayList<HashFunction> hfList = CreateHashFunction.create(kmerSize, hashOptionOne, hashOptionTwo, alphabet);
		
		BitSetObject BSO = CreateBloom.createIBF(genom, kmerSize, hfList, alphabet);
		
	}

}
