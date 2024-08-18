package hashFunctionCollection;

public class HashFunction<T> {

	String kmer;
	IHashFunction<T> hash = null;
	
	public HashFunction(String kmer) {
		this.kmer = kmer;
	}
	
	public void setHashFunction(IHashFunction<T> hash) {
		this.hash = hash;
	}
	
	public T output() {
		T out = hash.toHash(kmer);
		return out;
	}
	
}
