package app.HIBFDir;

import java.util.ArrayList;

import app.records.Alphabet;
import app.records.Gen;
import app.records.Genom;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.CreateHashFunction;

public class HIBF {
	
	public static void filter(ArrayList<ArrayList<Genom>> genom,ArrayList<Gen> genes,
			String hashOptionOne,String hashOptionTwo, Alphabet alphabet, int kmerSize) {
		
		// Erstellen der HashFunktionen
		ArrayList<HashFunction> hfList = CreateHashFunction.create(kmerSize, hashOptionOne, hashOptionTwo, alphabet);
		
		HIBFCreator.create(genom, kmerSize, hfList, alphabet);
		
	}

}
