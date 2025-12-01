package hashFunctionCollection.methods;

import java.util.ArrayList;

import app.records.Alphabet;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.variants.JavaHash;
import hashFunctionCollection.variants.JenkinsOneAtATimeHash;
import hashFunctionCollection.variants.MurmurHash;
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
	
	public static ArrayList<HashFunction> create(int kmerSize, String hashOptionOne, String hashOptionTwo, Alphabet alphabet){
		
		ArrayList<HashFunction> hfList = new ArrayList<>();
		ArrayList<String> optionList = new ArrayList<>();		
		HashFunction hashFuncOne = new HashFunction();
		HashFunction hashFuncTwo = new HashFunction();
		
		hfList.add(hashFuncOne);
		optionList.add(hashOptionOne);
		optionList.add(hashOptionTwo);
		
		if(!hashOptionTwo.equals("")) {
			hfList.add(hashFuncTwo);
		}
		
		int count = 0;
		
		for(HashFunction hashFunc : hfList) {
			switch (optionList.get(count)) {
			case "hashNt" -> hashFunc.setHashFunction(new NtHashClassic(kmerSize,alphabet));

			case "hashNt2" -> hashFunc.setHashFunction(new NtHash2(kmerSize,alphabet));

			case "hashRabin" -> hashFunc.setHashFunction(new RabinFingerprint(kmerSize));

			case "hashTwoBit" -> hashFunc.setHashFunction(new TwoBitHash(kmerSize));

			case "hashJava" -> hashFunc.setHashFunction(new JavaHash(kmerSize));
			
			case "hashMurmur" -> hashFunc.setHashFunction(new MurmurHash(kmerSize));
			
			case "hashJenkins" -> hashFunc.setHashFunction(new JenkinsOneAtATimeHash(kmerSize));
			
			default -> {IO.println("WARNUNG: Hashfunktion nicht gefunden!!! -> NtHash wird angewandt");
				hashFunc.setHashFunction(new NtHashClassic(kmerSize,alphabet));
				}
			}
			count++;
		}
		
		return hfList;
	}
}
