package miscTools.TestMethods;

import java.util.ArrayList;
import java.util.BitSet;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import miscTools.FileWriters.PlaceWriter;

public class TestPipeline {
	
	//Test fuer mehrere kombinierte Hashfunktionen
	public static void test(ArrayList<int[]> intArr, char[] seq, int kmerSize, ArrayList<HashFunction> hfList, BitSet bitS) {
		
	}
	
	
	//Test fuer einzelne Hashfunktion
	public static void testOld(int[] kmersInt, char[] seq , int kmerSize, HashFunction hf, BitSet bitS) {
		
		String genom = new String(seq);
		
		//PlaceWriter.writeInt(kmersInt);
		
		
		// Anaylse
		int kmerCount = genom.length() - kmerSize + 1;
		int kmerOverlap = kmerCount - PlaceCounter.countSameKmers(genom,kmerSize);
		ArrayList<String> UniqueKmers = PlaceCounter.getUniqueStrings(genom, kmerSize);
		int[] ints = {};
		ArrayList<Integer> intArr = new ArrayList<>();
		int cap = BoundedInteger.calculateCap(genom.length());
		
		
		
		for(String s : UniqueKmers) {
			ints = hf.output(s.toCharArray(),cap);
			intArr.add(ints[0]);
		}
		
		
		int intArrUnique = PlaceCounter.countInt(intArr);
		
		BitSet bit = new BitSet((1 << cap) - 1);
		
		for(int i : intArr) {
			bit.set(i);
		}
		
		PlaceWriter.writeInt(intArr);
		
		
		IO.println("Unique kmers: " + UniqueKmers.size());
		IO.println("Gesamt Test: " + (UniqueKmers.size() + kmerOverlap));
		IO.println("Differenz Int: " + (kmersInt.length - UniqueKmers.size()));
		IO.println("Anzahl Kmere Soll: " + kmerCount);
		IO.println("Anzahl Kmere Ist: " + kmersInt.length);
		IO.println("Anzahl Ueberschneidungen Kmere im Genom: " + kmerOverlap);
		IO.println("Kmere ohne Overlap: " + (kmerCount - kmerOverlap));
		IO.println("ints size: " + intArr.size());
		IO.println("int Overlap: " + (intArr.size() - intArrUnique));
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
