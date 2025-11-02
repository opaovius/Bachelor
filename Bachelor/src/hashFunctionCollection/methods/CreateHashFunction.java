package hashFunctionCollection.methods;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.variants.NtHash2;
import hashFunctionCollection.variants.NtHashClassic;
import hashFunctionCollection.variants.RabinFingerprint;
import hashFunctionCollection.variants.TwoBitHash;

public class CreateHashFunction {
	
	public static HashFunction create(int kmerSize){
		

		// Erstellen der verschiedenen HashFunktionen
		HashFunction hashNt2 = new HashFunction();
		HashFunction hashNtClassic = new HashFunction();
		//HashFunction hashNtTest = new HashFunction();
		HashFunction hashRabin = new HashFunction();
		HashFunction hashTwoBit = new HashFunction();

		hashNt2.setHashFunction(new NtHash2(kmerSize));
		hashNtClassic.setHashFunction(new NtHashClassic(kmerSize));
		//hashNtTest.setHashFunction(new NtHash2Test(kmerSize));
		hashRabin.setHashFunction(new RabinFingerprint(kmerSize));
		hashTwoBit.setHashFunction(new TwoBitHash(kmerSize));
		
		return hashTwoBit;
	}

}
