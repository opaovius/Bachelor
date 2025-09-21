package hashFunctionCollection;

import java.util.BitSet;

public class NtHash2 implements IHashFunction<BitSet>{
	
	@Override
	public BitSet toHash(String genom) {
		
		genom = "ACGTACGTACGTACGT";
		
		genom = "AAAAAAAAAAAAAAAA";
		
		BitSet kmerBitSet = new BitSet(16);
		
		
		//Einmal durch das ganze Genom
		//16 k-mersize
		for(int genomPlace = 0; genomPlace <= genom.length() - 16; genomPlace++) {
			
			int setCounter = 0;
			
			for(int kmerPlace = genomPlace; kmerPlace < genomPlace + 16; kmerPlace++) {

				
				//Zu Begin komplettes BitSet mti Basen befuellen
				if(genomPlace == 0) {
					//A = 00, C = 01, G = 10, T = 11
					if(genom.charAt(kmerPlace) == 'C') {
						kmerBitSet.set(setCounter);
						setCounter += 2;
					}
					else if(genom.charAt(kmerPlace) == 'G') {
						setCounter++;
						kmerBitSet.set(setCounter);
						setCounter++;
					}
					else if(genom.charAt(kmerPlace) == 'T') {
						kmerBitSet.set(setCounter);
						setCounter++;
						kmerBitSet.set(setCounter);
						setCounter++;
					}
					else {
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
		return new BitSet();
	}

}
