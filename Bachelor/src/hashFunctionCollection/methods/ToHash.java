package hashFunctionCollection.methods;

import app.helper.BoundedInteger;
import app.records.Gen;
import app.records.Genom;
import hashFunctionCollection.HashFunction;

public class ToHash {
	
	public static int[] hash(Genom genom, HashFunction hashFc, int cap) {

		// Erzeugen des Long-Index-Werte
		long[] kmerLong = hashFc.output(genom.sequence());

		// Begrenzen auf gewuenschte Groesse + Umwandlung zu Integer
		int[] kmerInt = BoundedInteger.boundToInt(kmerLong, cap);

		return kmerInt;
	}
	
	public static int[] hash(Gen gen, HashFunction hashFc, int cap) {
		
		// Erzeugen des Long-Index-Werte
		long[] kmerLong = hashFc.output(gen.sequence());

		// Begrenzen auf gewuenschte Groesse + Umwandlung zu Integer
		int[] kmerInt = BoundedInteger.boundToInt(kmerLong, cap);

		return kmerInt;

	}

}
