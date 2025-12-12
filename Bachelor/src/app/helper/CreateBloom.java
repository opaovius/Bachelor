package app.helper;

import java.util.ArrayList;
import java.util.BitSet;

import app.records.BitSetObject;
import app.records.Genom;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import hashFunctionCollection.methods.ToHash;
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

	public static BitSetObject create(ArrayList<Genom> genom, int kmerSize, ArrayList<HashFunction> hfList) {

		ArrayList<int[]> intList = new ArrayList<>();
		ArrayList<Integer> capList = new ArrayList<>();

		for (Genom g : genom) {
			capList.add(BoundedInteger.calculateCap(g.sequence().length));
		}

		int cap = getMax(capList);

		for (Genom g : genom) {
			for (HashFunction hashFc : hfList) {

				intList.add(ToHash.hash(g, hashFc, cap));

			}
		}
		// Erstellen der BitSet Objekte
		BitSet bitS = new BitSet((1 << cap) - 1);

		for (int[] intArr : intList) {
			for (int i : intArr) {
				bitS.set(i);

			}
		}

		// Testen [T]
		//TestPipeline.test(intList,genom.sequence(),kmerSize,hfList,bitS);
		TestPipeline.bitTest(bitS);
		
		return new BitSetObject(genom.get(0).name(), bitS, cap);
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
