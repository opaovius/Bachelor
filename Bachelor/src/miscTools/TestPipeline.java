package miscTools;

import java.util.ArrayList;

public class TestPipeline {
	
	public static void test(int[] kmersInt, long[] kmersLong, String genom , int kmerSize) {
		
		//PlaceWriter.writeInt(kmers);
		
		// Anaylse
		int kmerCount = genom.length() - kmerSize + 1;
		int kmerOverlap = kmerCount - PlaceCounter.countSameKmers(genom,kmerSize);
		int notUnique = kmersInt.length - PlaceCounter.countInt(kmersInt) - kmerOverlap;
		ArrayList<String> UniqueKmers = PlaceCounter.getUniqueStrings(genom, kmerSize);
		
		IO.println("Unique kmers: " + UniqueKmers.size());
		IO.println("Geasmt Test: " + (UniqueKmers.size() + kmerOverlap));
		IO.println("Differenz Long: " + (kmersLong.length - UniqueKmers.size()));
		IO.println("Differenz Int: " + (kmersInt.length - UniqueKmers.size()));
		IO.println("Anzahl Kmere Soll: " + kmerCount);
		IO.println("Anzahl Kmere Ist: " + kmersInt.length);
		IO.println("Anzahl Ueberschneidungen Kmere im Genom: " + kmerOverlap);
		IO.println("Stellen mehrfach: " + notUnique);
		IO.println("Rate: " + (Math.round((100.0 / kmersInt.length * notUnique) * 100000.0) / 100000.0) + "%");
		System.out.println("Place done!");
		IO.println("-----------------------------");
		
	}

}
