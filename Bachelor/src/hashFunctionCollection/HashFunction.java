package hashFunctionCollection;

public class HashFunction {

	IHashFunction hash = null;
	int kmerSize;
	
	public void setHashFunction(IHashFunction hash, int kmerSize) {
		this.hash = hash;
		this.kmerSize = kmerSize;
	}
	
	public IHashFunction getHashFunction(){
		return hash;
	}
	
	public long[] output(String genom) {
		
		return hash.toHash(genom,kmerSize);

	}
	
}
