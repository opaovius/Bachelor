package hashFunctionCollection.methods;

import app.records.Gen;
import app.records.Genom;
import hashFunctionCollection.HashFunction;

public class ToHash {
	
	/**
	 * Nutzt ueberladenen Methoden um Gene und Genome mittels hash() in
	 * ein Integer Array umzuwandeln.
	 * 
	 * @param genom : Ausgewaehltes Genom
	 * @param hashFc : Ausgewaehlte Hashfunktion
	 * @param cap : Obergrenze
	 * @return Integer Array
	 */
	
	public static int[] hash(Genom genom, HashFunction hashFc, int cap) {

		// Erzeugen des int-Index-Werte
		int[] kmerInt = hashFc.output(genom.sequence(),cap);

		return kmerInt;
	}
	
	/**
	 * Nutzt ueberladenen Methoden um Gene und Genome mittels hash() in
	 * ein Integer Array umzuwandeln.
	 * 
	 * @param gen : Ausgewaeltes Gen
	 * @param hashFc : Ausgewaehlte Hashfunktion
	 * @param cap : Obergrenze
	 * @return Integer Array
	 */
	
	public static int[] hash(Gen gen, HashFunction hashFc, int cap) {
		
		// Erzeugen des int-Index-Werte
		int[] kmerInt = hashFc.output(gen.sequence(),cap);

		return kmerInt;

	}

}
