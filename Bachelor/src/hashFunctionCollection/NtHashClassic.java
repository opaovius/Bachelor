package hashFunctionCollection;

import java.util.ArrayList;
import java.util.BitSet;

import miscTools.PlaceCounter;
import miscTools.PlaceWriter;

public class NtHashClassic implements IHashFunction<BitSet>{
	
	public BitSet toHash(String genom) {
		
		//Lookup table fuer A,C,G,T
		long[] seed = {3845706593036322284L,
				       9121647437843735232L,
				       -1261775892281119362L,
				       -4075723973616114068L};
		//Index fuer derzeitiges k-mer. 1, da init() kmer mit Index i =  0 erzeugt 
		int index = 1;
		int kmerSize = 64;
		
		ArrayList<Long> places = new ArrayList<>();
		ArrayList<Integer> doubleHashValue = new ArrayList<>();
		
		//Erzeugen des ersten kmers
		long kmer = init(seed,genom.substring(0, kmerSize));
		
		IO.println("-----------------------------");
		IO.println("Analyse NtHashClassic");
		
		
		//alle k-mere erzeugen
		for(int counter = kmerSize; counter < genom.length() - kmerSize; counter++) {
			
			kmer = cycle(kmer,genom,seed,kmerSize,index);
			index++;
			places.add(kmer);
			//Anzeigen fuer k-mer Anzahl
			//IO.println("counter: " + (counter - kmerSize + 1));
			if(counter == genom.length() - kmerSize-1) {
				IO.println("k-mer Anzahl: " + index);
			}
		}
		
		// Anaylse
		int notUnique = index - PlaceCounter.countLong(places);
		IO.println("Stellen mehrfach: " + notUnique);
		IO.println("Rate: " + (Math.round((100.0 / index * notUnique) * 100.0) / 100.0) + "%");
		System.out.println("Place done!");
		IO.println("-----------------------------");

		long min = Integer.MAX_VALUE;
		long max = Integer.MIN_VALUE;
		
		IO.println("NtHashClassic mit double Hash");
		//Start Double Hash
		for(int counter = 0; counter < places.size(); counter++) {
			
			//Rotation es urspruenglichen Wertes um 32 nach rechts
			//Danach XOR mit golden Ratio (angegeben in Hexadezimal)
			Long temp = (Long.rotateRight(places.get(counter), 32) ^ 0x9e3779b97f4a7c15L);
			
			//klassisches double Hashing
			//h = (h_1 + i * h_2) mod size
			//Hierbei wird als modulo eine Bitmaske eingesetzt
			doubleHashValue.add((int)((places.get(counter) + counter * temp) & (Math.powExact(2, 30) - 1)));
			
			
			//Analyse fuer maximalen und minimalen Wert
			if(max < (int)((places.get(counter) + counter * temp) & (Math.powExact(2, 30) - 1))) {
				max = (int)((places.get(counter) + counter * temp) & (Math.powExact(2, 30) - 1));
			}
			if(min > (int)((places.get(counter) + counter * temp) & (Math.powExact(2, 30) - 1))) {
				min = (int)((places.get(counter) + counter * temp) & (Math.powExact(2, 30) - 1));
			}
			
		}
		
		//Analyse
		int notUniqueInt = ((int) places.size()) - PlaceCounter.countInt(doubleHashValue);
		IO.println("Stellen mehrfach: " + notUniqueInt);
		IO.println("Rate: " + (Math.round((100.0 / index * notUniqueInt) * 100.0) / 100.0) + "%");
		IO.println("Maximaler Wert: " + max);
		IO.println("Minimaler Wert: " + min);
		PlaceWriter.writeInt(doubleHashValue);
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
}
