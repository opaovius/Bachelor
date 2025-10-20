package hashFunctionCollection.methods;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.NtHash2;
import hashFunctionCollection.NtHashClassic;

public class CreateHashFunction {
	
	public static HashFunction create(int kmerSize){
		

		// Erstellen der verschiedenen HashFunktionen
		HashFunction hashNt = new HashFunction();
		HashFunction hashNtClassic = new HashFunction();

		hashNt.setHashFunction(new NtHash2(),kmerSize);
		hashNtClassic.setHashFunction(new NtHashClassic(),kmerSize);
		
		return hashNt;
	}

}
