package app.IBFDir;

import java.util.ArrayList;

import app.helper.CreateBloom;
import app.records.Alphabet;
import app.records.Gen;
import app.records.Genom;
import app.records.IBFObject;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.CreateHashFunction;
import miscTools.TestMethods.TestPipeline;

public class IBF {
	
	public static void filter(ArrayList<ArrayList<Genom>> genom,ArrayList<Gen> genes,
			String hashOptionOne,String hashOptionTwo, Alphabet alphabet, int kmerSize) {

		// Erstellen der HashFunktionen
		ArrayList<HashFunction> hfList = CreateHashFunction.create(kmerSize, hashOptionOne, hashOptionTwo, alphabet);
		
		IBFObject IBF = CreateBloom.createIBF(genom, kmerSize, hfList, alphabet);
		
		//TestPipeline.IBFPlaces(IBF);
		
		// Nachpruefen, ob Gene enthalten sind
		CheckGenesIBF.check(IBF, genes, kmerSize, hfList, alphabet);
		
	}

}
