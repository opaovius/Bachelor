package hashFunctionCollection;

import java.util.BitSet;

public interface IHashFunction<T> {
	
	public T toHash(String s, int kmersize, int size, int parameter, long[][] primeArray);
	
	public T toHash(String s, int size, BitSet set);

}
