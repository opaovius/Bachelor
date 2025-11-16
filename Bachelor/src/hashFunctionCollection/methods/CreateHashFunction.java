package hashFunctionCollection.methods;

import java.util.ArrayList;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.variants.JavaHash;
import hashFunctionCollection.variants.NtHash2;
import hashFunctionCollection.variants.NtHashClassic;
import hashFunctionCollection.variants.RabinFingerprint;
import hashFunctionCollection.variants.TwoBitHash;

public class CreateHashFunction {
	
	/**
	 * Erstellt die verschiedenen Hashfunktionen
	 * 
	 * @param kmerSize
	 * @param hashOptionOne : erste Hashfunktion
	 * @param hashOptionTwo : zweite Hashfunktion, kann leer sein, damit nur eine erzeugt wird
	 * @return HashFunction
	 */
	
	public static ArrayList<HashFunction> create(int kmerSize, String hashOptionOne, String hashOptionTwo){
		
		ArrayList<HashFunction> hfList = new ArrayList<>();
		
		HashFunction hashFuncOne = new HashFunction();
		HashFunction hashFuncTwo = new HashFunction();
		
		hfList.add(hashFuncOne);
		
		if(!hashOptionTwo.equals("")) {
			hfList.add(hashFuncTwo);
		}
		
		for(HashFunction hashFunc : hfList) {
			switch (hashOptionOne) {
			case "hashNt" -> hashFunc.setHashFunction(new NtHashClassic(kmerSize));

			case "hashNt2" -> hashFunc.setHashFunction(new NtHash2(kmerSize));

			case "hashRabin" -> hashFunc.setHashFunction(new RabinFingerprint(kmerSize));

			case "hashTwoBit" -> hashFunc.setHashFunction(new TwoBitHash(kmerSize));

			case "hashJava" -> hashFunc.setHashFunction(new JavaHash(kmerSize));
			}
		}
		
		return hfList;
	}
	
	public static ArrayList<HashFunction> create(int kmerSize){
		

		// Erstellen der verschiedenen HashFunktionen
		HashFunction hashNt2 = new HashFunction();
		HashFunction hashNtClassic = new HashFunction();
		//HashFunction hashNtTest = new HashFunction();
		HashFunction hashRabin = new HashFunction();
		HashFunction hashTwoBit = new HashFunction();
		HashFunction hashJava = new HashFunction();

		hashNt2.setHashFunction(new NtHash2(kmerSize));
		hashNtClassic.setHashFunction(new NtHashClassic(kmerSize));
		//hashNtTest.setHashFunction(new NtHash2Test(kmerSize));
		hashRabin.setHashFunction(new RabinFingerprint(kmerSize));
		hashTwoBit.setHashFunction(new TwoBitHash(kmerSize));
		hashJava.setHashFunction(new JavaHash(kmerSize));
		
		ArrayList<HashFunction> hfList = new ArrayList<>();
		
		hfList.add(hashNtClassic);
		hfList.add(hashNt2);
		
		return hfList;
	}

}
