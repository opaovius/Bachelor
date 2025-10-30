package hashFunctionCollection.methods;

import app.records.Gen;
import app.records.Genom;
import hashFunctionCollection.HashFunction;

public class ToHash {
	
	public static int[] hash(Genom genom, HashFunction hashFc, int cap) {

		// Erzeugen des int-Index-Werte
		int[] kmerInt = hashFc.output(genom.sequence(),cap);

		return kmerInt;
	}
	
	public static int[] hash(Gen gen, HashFunction hashFc, int cap) {
		
		// Erzeugen des int-Index-Werte
		int[] kmerInt = hashFc.output(gen.sequence(),cap);

		return kmerInt;

	}

}
