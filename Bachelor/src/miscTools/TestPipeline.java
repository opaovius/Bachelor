package miscTools;

import java.util.ArrayList;
import java.util.BitSet;

import hashFunctionCollection.BoundedInteger;
import hashFunctionCollection.HashFunction;

public class TestPipeline {
	
	public static void test(int[] kmersInt, long[] kmersLong, String genom , int kmerSize, ArrayList<HashFunction> hf, int counter) {
		
		//PlaceWriter.writeInt(kmers);
		
		// Anaylse
		int kmerCount = genom.length() - kmerSize + 1;
		int kmerOverlap = kmerCount - PlaceCounter.countSameKmers(genom,kmerSize);
		int notUnique = 0;
		ArrayList<String> UniqueKmers = PlaceCounter.getUniqueStrings(genom, kmerSize);
		long[] longs = {};
		ArrayList<Long> longArr = new ArrayList<>();
		ArrayList<Integer> intArr = new ArrayList<>();
		
		
		for(String s : UniqueKmers) {
			longs = hf.get(counter).output(s);
			longArr.add(longs[0]);
		}
		
		int cap = BoundedInteger.calculateCap(genom.length());
		
		for(long l : longArr) {
			intArr.add((int) l & ((1 << cap) - 1));
		}
		
		
		
		int longArrUnique = PlaceCounter.countLong(longArr);
		int intArrUnique = PlaceCounter.countInt(intArr);
		
		BitSet bit = new BitSet();
		
		for(int i : intArr) {
			bit.set(i);
		}
		
		
		IO.println("Unique kmers: " + UniqueKmers.size());
		IO.println("Gesamt Test: " + (UniqueKmers.size() + kmerOverlap));
		IO.println("Differenz Long: " + (kmersLong.length - UniqueKmers.size()));
		IO.println("Differenz Int: " + (kmersInt.length - UniqueKmers.size()));
		IO.println("Anzahl Kmere Soll: " + kmerCount);
		IO.println("Anzahl Kmere Ist: " + kmersInt.length);
		IO.println("Anzahl Ueberschneidungen Kmere im Genom: " + kmerOverlap);
		IO.println("Kmere ohne Overlap: " + (kmerCount - kmerOverlap));
		IO.println("longs size: " + longArr.size());
		IO.println("long Overlap: " + (longArr.size() - longArrUnique));
		IO.println("ints size: " + intArr.size());
		IO.println("int Overlap: " + (intArr.size() - intArrUnique));
		IO.println("Rate Long: " + (Math.round((100.0 / longArr.size() * (longArr.size() - longArrUnique)) * 100000.0) / 100000.0) + "%");
		IO.println("Rate Int: " + (Math.round((100.0 / intArr.size() * (intArr.size() - intArrUnique)) * 100000.0) / 100000.0) + "%");
		IO.println("BitSet Size: " + bit.size());
		
		System.out.println("Place done!");
		IO.println("-----------------------------");
		
	}

}
