package hashFunctionCollection;

import java.util.BitSet;

public interface IHashFunction<T> {
	
	public T toHash(String s, int start, int end, int size, int parameter, long[][] primeArray);
	
	public T toHash(String s, int start, int end, int size, BitSet set);

}
