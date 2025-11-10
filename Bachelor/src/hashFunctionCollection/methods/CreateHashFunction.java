package hashFunctionCollection.methods;

import java.util.ArrayList;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.variants.NtHashClassic;
import hashFunctionCollection.variants.RabinFingerprint;
import hashFunctionCollection.variants.TwoBitHash;

public class CreateHashFunction {
	
	/**
	 * Erstellt die verschiedenen Hashfunktionen
	 * 
	 * @param kmerSize
	 * @return HashFunction
	 */
	
	public static ArrayList<HashFunction> create(int kmerSize){
		

		// Erstellen der verschiedenen HashFunktionen
		//HashFunction hashNt2 = new HashFunction();
		HashFunction hashNtClassic = new HashFunction();
		//HashFunction hashNtTest = new HashFunction();
		HashFunction hashRabin = new HashFunction();
		HashFunction hashTwoBit = new HashFunction();
		//HashFunction hashJava = new HashFunction();

		//hashNt2.setHashFunction(new NtHash2(kmerSize));
		hashNtClassic.setHashFunction(new NtHashClassic(kmerSize));
		//hashNtTest.setHashFunction(new NtHash2Test(kmerSize));
		hashRabin.setHashFunction(new RabinFingerprint(kmerSize));
		hashTwoBit.setHashFunction(new TwoBitHash(kmerSize));
		//hashJava.setHashFunction(new JavaHash(kmerSize));
		
		ArrayList<HashFunction> hfList = new ArrayList<>();
		
		hfList.add(hashNtClassic);
		
		return hfList;
	}

}
