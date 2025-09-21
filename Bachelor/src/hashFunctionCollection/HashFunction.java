package hashFunctionCollection;

public class HashFunction<T> {

	IHashFunction<T> hash = null;
	
	public void setHashFunction(IHashFunction<T> hash) {
		this.hash = hash;
	}
	
	public IHashFunction<T> getHashFunction(){
		return hash;
	}
	
	public T output(String genom) {
		
		T out = hash.toHash(genom);
		
		return out;

	}
	
}
