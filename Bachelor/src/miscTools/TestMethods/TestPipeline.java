package miscTools.TestMethods;

import java.util.ArrayList;
import java.util.BitSet;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import miscTools.FileWriters.PlaceWriter;

public class TestPipeline {
	
	public static void test(int[] kmersInt, String genom , int kmerSize, HashFunction hf, BitSet bitS) {
		
		//PlaceWriter.writeInt(kmersInt);
		
		
		// Anaylse
		int kmerCount = genom.length() - kmerSize + 1;
		int kmerOverlap = kmerCount - PlaceCounter.countSameKmers(genom,kmerSize);
		ArrayList<String> UniqueKmers = PlaceCounter.getUniqueStrings(genom, kmerSize);
		long[] longs = {};
		ArrayList<Long> longArr = new ArrayList<>();
		ArrayList<Integer> intArr = new ArrayList<>();
		
		
		for(String s : UniqueKmers) {
			longs = hf.output(s);
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
		
		PlaceWriter.writeInt(intArr);
		
		
		IO.println("Unique kmers: " + UniqueKmers.size());
		IO.println("Gesamt Test: " + (UniqueKmers.size() + kmerOverlap));
		//IO.println("Differenz Long: " + (kmersLong.length - UniqueKmers.size()));
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
		IO.println("BitSet Vergleich: " + bitS.size());
		IO.println("BitSet card: " + bit.cardinality());
		IO.println("Card Vergleich: " + bitS.cardinality());
		IO.println("Rate BitS: " + Math.round((100.0/bitS.size()*bitS.cardinality())* 100000.0 / 100000.0) + "%");
		
		System.out.println("Place done!");
		IO.println("-----------------------------");
		
	}

}
