package hashFunctionCollection;

import java.util.BitSet;

public class NtHash2 implements IHashFunction<BitSet>{
	
	public BitSet toHash(String genom) {
		
		genom = "ACGTACGTACGTACGT";
		
		genom = "CAAAAAAAAAAAAAAA";
		
		BitSet kmerBitSet = new BitSet(32);
		
		//Einmal durch das ganze Genom
		//16 k-mersize
		for(int genomPlace = 0; genomPlace <= genom.length() - 16; genomPlace++) {

			if (genomPlace == 0) {
				
				int setCounter = 0;
				
				for (int kmerPlace = genomPlace; kmerPlace < genomPlace + 16; kmerPlace++) {

					// Zu Begin komplettes BitSet mti Basen befuellen
					// A = 00, C = 01, G = 10, T = 11
					if (genom.charAt(kmerPlace) == 'C') {
						kmerBitSet.set(setCounter);
						setCounter += 2;
					} else if (genom.charAt(kmerPlace) == 'G') {
						setCounter++;
						kmerBitSet.set(setCounter);
						setCounter++;
					} else if (genom.charAt(kmerPlace) == 'T') {
						kmerBitSet.set(setCounter);
						setCounter++;
						kmerBitSet.set(setCounter);
						setCounter++;
					} else {
						setCounter += 2;
					}
				}
			}
			
			

		}
		
		long[] testarray = kmerBitSet.toLongArray();
		
		if(testarray.length == 0) {
			System.out.println(0);
		}
		else {
			System.out.println(testarray[0]);
		}
		
		System.out.println(kmerBitSet.size());
		
		return new BitSet();
	}

}
