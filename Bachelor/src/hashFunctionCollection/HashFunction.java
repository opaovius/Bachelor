package hashFunctionCollection;

public class HashFunction<T> {

	long size;
	int parameter;
	long[][] primeArray;
	IHashFunction<T> hash = null;
	
	public HashFunction(long size, int parameter, long[][] primeArray) {
		this.size = size;
		this.parameter = parameter;
		this.primeArray = primeArray;
	}
	
	public void setHashFunction(IHashFunction<T> hash) {
		this.hash = hash;
	}
	
	public T output(String genom, int start, int end) {
		T out = hash.toHash(genom, start, end, size, parameter, primeArray);
		return out;
	}
	
}
