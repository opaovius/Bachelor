package app.IBFDir;

import java.util.ArrayList;
import java.util.BitSet;

import app.records.Alphabet;
import app.records.Gen;
import app.records.IBFObject;
import hashFunctionCollection.HashFunction;

public class CheckGenesIBF {

	public static void check(IBFObject IBF, ArrayList<Gen> genes, int kmerSize, ArrayList<HashFunction> hfList,
			Alphabet alphabet) {

		ArrayList<int[]> intList = new ArrayList<>();
		int hit = 0;

		for (Gen g : genes) {

			// Skip bei zu kleinen Sequenzen
			if (g.sequence().length < kmerSize)
				continue;

			// HashWerte berechnen
			for (HashFunction hf : hfList) {
				intList.add(hf.output(g.sequence(), IBF.cap()));
			}

			int[] hitCount = new int[IBF.filterCount()];

			// Erstellen von neuem Bitset, in welchem steht, ob die Gene enthalten sind
			BitSet candidate = new BitSet(IBF.filterCount());

			for (int x = 0; x < intList.get(0).length; x++) {

				candidate.set(0, IBF.filterCount());

				for (int y = 0; y < intList.size(); y++) {

					int pos = intList.get(y)[x];

					int base = pos * IBF.filterCount();

					for (int f = 0; f < IBF.filterCount(); f++) {
						if (!IBF.vector().get(base + f)) {
							candidate.clear(f);
						}
					}
				}

				for (int f = candidate.nextSetBit(0); f >= 0; f = candidate.nextSetBit(f + 1)) {

					hitCount[f]++;
				}

			}

			int cutoff = intList.get(0).length * 9 / 10;
			for (int f = 0; f < IBF.filterCount(); f++) {
				if (hitCount[f] >= cutoff) {
					//IO.println("Gen " + g.name() + " vlt. in Filter " + f);
					hit++;
					//break;
				}
			}

			intList.clear();
		}

		IO.println(hit);

	}

}
