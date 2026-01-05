package app.DefaultBFDir;

import java.util.ArrayList;

import app.helper.CreateBloom;
import app.records.Alphabet;
import app.records.BitSetObject;
import app.records.Gen;
import app.records.Genom;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.CreateHashFunction;

public class DefaultBF {

	public static void filter(ArrayList<ArrayList<Genom>> genom,ArrayList<Gen> genes,
			String hashOptionOne,String hashOptionTwo, Alphabet alphabet, int kmerSize) {

		ArrayList<BitSetObject> BSOList = new ArrayList<>();

		// Erstellen der HashFunktionen
		ArrayList<HashFunction> hfList = CreateHashFunction.create(kmerSize, hashOptionOne, hashOptionTwo, alphabet);

		// Erstellen einer Liste aus BitSetObjekten

		for (ArrayList<Genom> g : genom) {
			BSOList.add(CreateBloom.create(g, kmerSize, hfList, alphabet));
		}
		// Nachpruefen, ob Gene enthalten sind
		CheckGenes.check(BSOList, genes, kmerSize, hfList, alphabet);

		// GenGenerator.takeTales(genom);
		// GenPermutator.permutate(genes);
		// ProteinGenerator.generate(genom.getFirst().getFirst());
	}

}
