package hashFunctionCollection;

public class HashFunction {

	IHashFunction hash = null;
	
	public void setHashFunction(IHashFunction hash) {
		this.hash = hash;
	}
	
	public IHashFunction getHashFunction(){
		return hash;
	}
	
	public int[] output(char[] genom, int cap) {
		
		return hash.toHash(genom,cap);

	}
	
}
