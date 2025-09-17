package hashFunctionCollection;

import java.util.BitSet;

public abstract class HashFunctionAdapter implements IHashFunction<Integer>{
	
	public Integer toHash(String s, int start, int end, int size, int parameter, long[][] primeArray) {
		return -1;
	}
	
	public Integer toHash(String s, int start, int end, int size, BitSet set) {
		return -1;
	}

}
