package hashFunctionCollection;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.Random;

import miscTools.PlaceCounter;
import miscTools.PlaceWriter;

public class NtHashClassic implements IHashFunction<BitSet>{
	
	public BitSet toHash(String genom) {
		
		//Lookup table fuer A,C,G,T
		long[] seed = {3845706593036322284L,
				       9121647437843735232L,
				       -1261775892281119362L,
				       -4075723973616114068L};
		int i = 1;
		int kmerSize = 32;
		
		LinkedList<Long> places = new LinkedList<>();
		
		//Erzeugen des ersten kmers
		long kmer = init(seed,genom.substring(0, kmerSize));
		
		
		//alle k-mere erzeugen
		for(int counter = kmerSize; counter < genom.length() - kmerSize; counter++) {
			
			kmer = cycle(kmer,genom,seed,kmerSize,i);
			i++;
			places.add(kmer);
			//Anzeigen fuer k-mer Anzahl, Vaci -> 194_647
			//IO.println("counter: " + (counter - kmerSize + 1));
		}
		
		// Anaylse
		IO.println("-----------------------------");
		IO.println("Analyse NtHashClassic");
		//System.out.println("Vektorgroesse: " + bitVector.size() + "\n" + "Besetzte Bits: " + bitVector.cardinality());
		PlaceCounter.countLong(places);
		PlaceWriter.write(places);
		System.out.println("Place done!");
		IO.println("-----------------------------");
	
		
		
		
		
		
		return new BitSet();
	}
	
	private long cycle(long kmer, String genom, long[] seed, int kmerSize, int i) {
		
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
			IO.println(nucleotidTemp);
			break;

		}
		
		kmerTemp = Long.rotateLeft(kmerTemp, kmerSize);
		
		kmer = kmer ^ kmerTemp;
		
		nucleotidTemp = genom.charAt(i + kmerSize - 1);
		
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
			break;

		}
		
		return kmer ^ kmerTemp;
		
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
	@SuppressWarnings("unused")
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
