package hashFunctionCollection;

public class HashFunction {

	IHashFunction hash = null;
	
	public void setHashFunction(IHashFunction hash) {
		this.hash = hash;
	}
	
	public IHashFunction getHashFunction(){
		return hash;
	}
	
	public long[] output(String genom) {
		
		return hash.toHash(genom);

	}
	
}
