package hashFunctionCollection;

public class HashFunction<T> {

	int size;
	int parameter;
	long[][] primeArray;
	IHashFunction<T> hash = null;
	
	public HashFunction(int size, int parameter, long[][] primeArray) {
		this.size = size;
		this.parameter = parameter;
		this.primeArray = primeArray;
	}
	
	public HashFunction(int size, int parameter) {
		this.size = size;
		this.parameter = parameter;
	}
	
	public void setHashFunction(IHashFunction<T> hash) {
		this.hash = hash;
	}
	
	public T output(String genom, int start, int end) {
		
		if(primeArray.length != 0) {
			T out = hash.toHash(genom, start, end, size, parameter, primeArray);
			return out;
		}
		
		T out = hash.toHash(genom, start, end, size);
		
		return out;

	}
	
}
