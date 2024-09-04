package hashFunctionCollection;

public class HashFunction<T> {

	String kmer;
	long size;
	long parameter;
	IHashFunction<T> hash = null;
	
	public HashFunction(String kmer, long size, long parameter) {
		this.kmer = kmer;
		this.size = size;
		this.parameter = parameter;
	}
	
	public void setHashFunction(IHashFunction<T> hash) {
		this.hash = hash;
	}
	
	public T output() {
		T out = hash.toHash(kmer, size, parameter);
		return out;
	}
	
}
