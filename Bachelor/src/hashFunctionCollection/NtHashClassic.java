package hashFunctionCollection;

import java.util.BitSet;
import java.util.Random;

public class NtHashClassic implements IHashFunction<BitSet>{
	
	public BitSet toHash(String genom) {
		
		//Lookup table fuer A,C,G,T
		long[] seed = {3845706593036322284L,
				       9121647437843735232L,
				       -1261775892281119362L,
				       -4075723973616114068L};
		int i = 1;
		
		//Erzeugen des ersten kmers
		long kmer = init(seed,genom.substring(0, 64));
		
		for(int counter = 64; counter < genom.length() - 64; counter++) {
			
			//Einmal links rotieren
			kmer = Long.rotateLeft(kmer, 1);
			
			long kmerTemp = 0L;
			char nucleotidTemp = genom.charAt(i - 1);
			
			switch (nucleotidTemp) {
			case 'A':
				kmerTemp = seed[0];
				break;
			case 'C':
				kmerTemp = seed[1];
				break;
			case 'G':
				kmerTemp = seed[2];
				break;
			case 'T':
				kmerTemp = seed[3];
				break;
			default:
				kmerTemp = 0L;
				IO.println("Fehler bei NtHashClassic: nucleo - 1");
				break;

			}
			// sinnlos????
			//kmer = Long.rotateLeft(kmerTemp, 64);
			
			kmer = kmer ^ kmerTemp;
			
			nucleotidTemp = genom.charAt(i + 64 - 1);
			
			switch (nucleotidTemp) {
			case 'A':
				kmerTemp = seed[0];
				break;
			case 'C':
				kmerTemp = seed[1];
				break;
			case 'G':
				kmerTemp = seed[2];
				break;
			case 'T':
				kmerTemp = seed[3];
				break;
			default:
				kmerTemp = 0L;
				IO.println("Fehler bei NtHashClassic: nucleo - 1");
				break;

			}
			
			kmer = kmer ^ kmerTemp;
			
			i++;

		}
		
		
		IO.println(kmer);
		
		
		
		
		
		return new BitSet();
	}
	
	private long init(long[] seed, String kmerString) {
		
		long tempKmer = 0L;
		long endKmer = 0L;
		
		for(int counter = 0; counter < kmerString.length(); counter++) {
			
			char nucleotid = kmerString.charAt(counter);
			
			switch (nucleotid) {
				case 'A': 	tempKmer = seed[0]; 
							break;
				case 'C': 	tempKmer = seed[1];
							break;
				case 'G': 	tempKmer = seed[2];
							break;
				case 'T':	tempKmer = seed[3];
							break;
				default:	tempKmer = 0L;
							IO.println("Fehler bei NtHashClassic: init()");
							break;
			}
			
			// k - counter mal nach links rollen
			tempKmer =  Long.rotateLeft(tempKmer, kmerString.length() - counter - 1);
			//XOR
			endKmer = endKmer ^ tempKmer;
		}
		
		return endKmer;
	}
	
	//genutzt zum erzeugen von zufaelligen 64Bit long werten
	private void random() {
		
		BitSet random = new BitSet();
		Random coin = new Random();
		
		for(int counter = 0; counter < 64; counter++) {
			if(coin.nextBoolean()) {
				random.set(counter);
			}
		}
		
		long[] arr = random.toLongArray();
		
		IO.println(arr[0]);
		IO.println(Long.MAX_VALUE);
		
	}

}
