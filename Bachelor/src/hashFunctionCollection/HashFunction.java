package hashFunctionCollection;

public class HashFunction<T> {

	long size;
	long parameter;
	IHashFunction<T> hash = null;
	
	public HashFunction(long size, long parameter) {
		this.size = size;
		this.parameter = parameter;
	}
	
	public void setHashFunction(IHashFunction<T> hash) {
		this.hash = hash;
	}
	
	public T output(String genom, int start, int end) {
		T out = hash.toHash(genom,start, end, size, parameter);
		return out;
	}
	
}
