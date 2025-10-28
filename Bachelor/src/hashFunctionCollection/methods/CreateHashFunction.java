package hashFunctionCollection.methods;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.NtHash2;
import hashFunctionCollection.NtHash2Test;
import hashFunctionCollection.NtHashClassic;

public class CreateHashFunction {
	
	public static HashFunction create(int kmerSize){
		

		// Erstellen der verschiedenen HashFunktionen
		HashFunction hashNt2 = new HashFunction();
		HashFunction hashNtClassic = new HashFunction();
		//HashFunction hashNtTest = new HashFunction();

		hashNt2.setHashFunction(new NtHash2(),kmerSize);
		hashNtClassic.setHashFunction(new NtHashClassic(),kmerSize);
		//hashNtTest.setHashFunction(new NtHash2Test(), kmerSize);
		
		return hashNt2;
	}

}
