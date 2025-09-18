package hashFunctionCollection;

import java.util.BitSet;

public abstract class HashFunctionAdapter implements IHashFunction<BitSet>{
	
	public BitSet toHash(String s, int kmersize, int size, int parameter, long[][] primeArray) {
		return new BitSet();
	}
	
	public BitSet toHash(String s, int size, BitSet set) {
		return new BitSet();
	}

}
