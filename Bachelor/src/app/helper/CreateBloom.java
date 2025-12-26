package app.helper;

import java.util.ArrayList;
import java.util.BitSet;

import app.records.Alphabet;
import app.records.BitSetObject;
import app.records.Genom;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import hashFunctionCollection.methods.ToHash;
import miscTools.FileWriters.PlaceWriter;
import miscTools.TestMethods.TestPipeline;

public class CreateBloom {

	/**
	 * Erstellt ein BitSetObject, welches ein Java BitSet nutzt und einen String zu
	 * identifizierung des Genoms.
	 * 
	 * @param genom    : Genom, welches in eien BitSetObject umgewandelt wird.
	 * @param kmerSize : Groesse der Kmere
	 * @return BitSetObject
	 */
	
	public static BitSetObject createIBF(ArrayList<ArrayList<Genom>> genomList, int kmerSize
			, ArrayList<HashFunction> hfList,Alphabet alphabet) {
		
		ArrayList<Integer> capList = new ArrayList<>();
		ArrayList<BitSetObject> BSOList = new ArrayList<>();
		ArrayList<int[]> intList = new ArrayList<>();
		
		//Berechnen Max Size
		for(ArrayList<Genom> genom: genomList) {
			
			int n = 0;
			
			for(Genom g: genom) {
				n = n + g.sequence().length - kmerSize + 1;
			}
			
			capList.add(BoundedInteger.newCap(n));
		}
		
		int cap = getMax(capList);
		
		//Erstellen der einzel Bitsets
		for(ArrayList<Genom> genom: genomList) {
			for (Genom g : genom) {

				switch (alphabet) {
				case Alphabet.DNA -> {
					if (g.sequence().length < 30)	continue;
				}
				default -> {
					if (g.sequence().length < 5)	continue;
				}
				}

				for (HashFunction hashFc : hfList) {

					intList.add(ToHash.hash(g, hashFc, cap));

				}
			}
			// Erstellen der BitSet Objekte
			BitSet bitS = new BitSet(cap);

			for (int[] intArr : intList) {
				for (int i : intArr) {
					bitS.set(i);
				}
			}
			BSOList.add(new BitSetObject(genom.get(0).name(), bitS, cap));
		}
		
		return fuseIBF(BSOList);
	}

	public static BitSetObject create(ArrayList<Genom> genom, int kmerSize, ArrayList<HashFunction> hfList,
			Alphabet alphabet) {

		ArrayList<int[]> intList = new ArrayList<>();
		
		int n = 0;
		
		for(Genom g: genom) {
			n = n + g.sequence().length - kmerSize + 1;
		}
		
		int cap = BoundedInteger.newCap(n);

		for (Genom g : genom) {

			switch (alphabet) {
			case Alphabet.DNA -> {
				if (g.sequence().length < 30)	continue;
			}
			default -> {
				if (g.sequence().length < 5)	continue;
			}
			}

			for (HashFunction hashFc : hfList) {

				intList.add(ToHash.hash(g, hashFc, cap));

			}
		}
		// Erstellen der BitSet Objekte
		BitSet bitS = new BitSet(cap);

		for (int[] intArr : intList) {
			for (int i : intArr) {
				bitS.set(i);
			}
		}

		// Testen [T]
		// TestPipeline.test(intList,genom.sequence(),kmerSize,hfList,bitS);
		TestPipeline.bitTest(bitS,genom.get(0).name());
		//PlaceWriter.fusionWriteInt(intList, "src/testFiles/AnalyseDir/Places/PlacesAnalyseTest" + genom.get(0).name() + ".csv");
		
		return new BitSetObject(genom.get(0).name(), bitS, cap);
	}
	
	private static BitSetObject fuseIBF(ArrayList<BitSetObject> BSOList) {
		
		//Neues BitSet : Anzahl Genome * Laenge derer BitSets
		BitSet bitS = new BitSet(BSOList.size() * BSOList.get(0).vector().size());
		
		int card = 0;
		int indexStart = 0;
		
		for(BitSetObject BSO: BSOList) {
			
			card = card + BSO.vector().cardinality();
			
			for(int curr = 0; curr < BSO.vector().size(); curr++) {
				
				int position = (curr * BSOList.size()) + indexStart;
				
				if(BSO.vector().get(curr)) {
					bitS.set(position);
				}
			}
			indexStart++;
		}
		
		TestPipeline.bitTest(bitS,"Test");
		IO.println("Vergleich card: " + card);
		
		return BSOList.get(0);
	}

	private static int getMax(ArrayList<Integer> l) {

		int max = 0;

		for (int i : l) {
			if (i > max) {
				max = i;
			}
		}

		return max;
	}

}
