package hashFunctionCollection;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

import app.Gen;
import miscTools.PlaceCounter;
import miscTools.PlaceWriter;
import miscTools.SeedTable;

public class NtHashClassic implements IHashFunction{
	
	private HashMap<Character,Long> seed;
	
	public NtHashClassic() {
		this.seed = SeedTable.load();
	}
	
	public BitSet toHashGenom(String genom) {
		
		//Index fuer derzeitiges k-mer. 1, da init() kmer mit Index i =  0 erzeugt 
		int index = 1;
		int kmerSize = 64;
		
		ArrayList<Long> places = new ArrayList<>();
		ArrayList<Integer> doubleHashValue = new ArrayList<>();
		
		//Erzeugen des ersten kmers
		long kmer = init(genom.substring(0, kmerSize));
		
		IO.println("-----------------------------");
		IO.println("Analyse NtHashClassic");
		
		
		//alle k-mere erzeugen
		for(int counter = kmerSize; counter < genom.length() - kmerSize; counter++) {
			
			kmer = cycle(kmer,genom,kmerSize,index);
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
	
	public int toHashGen(Gen gen) {
		
		int index = 1;
		int kmerSize = 64;
		
		ArrayList<Long> places = new ArrayList<>();
		ArrayList<Integer> doubleHashValue = new ArrayList<>();
		
		
		long kmer = init(gen.sequence().substring(0, kmerSize));
		
		IO.println(kmer);
		
		return 0;
	}
	
	private long cycle(long kmer, String genom, int kmerSize, int i) {
		
		//Einmal links rotieren
		kmer = Long.rotateLeft(kmer, 1);
		
		long tempKmer = 0L;
		
		//Long Wert aus seedTable nehmen, basierend auf Zeichen in Genom bei i - 1
		tempKmer = seed.get(genom.charAt(i - 1));
		
		//Links rotation um kmer-groesse
		tempKmer = Long.rotateLeft(tempKmer, kmerSize);
		
		//XOR
		kmer = kmer ^ tempKmer;
		
		//Long Wert aus seedTable nehmen, basierend auf Zeichen in Genom bei i + kmerSize - 1
		tempKmer = seed.get(genom.charAt(i + kmerSize - 1));
		
		//XOR
		return kmer ^ tempKmer;
		
	}
	
	private long init(String kmerString) {
		
		long tempKmer = 0L;
		long endKmer = 0L;
		
		for(int counter = 0; counter < kmerString.length(); counter++) {
			
			//Long Wert aus seedTable nehmen, basierend auf Zeichen in Genom
			tempKmer = seed.get(kmerString.charAt(counter));
			
			// k - counter mal nach links rollen
			tempKmer =  Long.rotateLeft(tempKmer, kmerString.length() - counter - 1);
			//XOR
			endKmer = endKmer ^ tempKmer;
		}
		
		return endKmer;
	}
}
