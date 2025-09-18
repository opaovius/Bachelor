package hashFunctionCollection;

import java.util.BitSet;

public class HashFunction<T> {

	int size;
	int parameter;
	int kmersize;
	long[][] primeArray;
	IHashFunction<T> hash = null;
	BitSet set;
	
	public HashFunction(int size, int kmersize, int parameter, long[][] primeArray) {
		this.size = size;
		this.parameter = parameter;
		this.kmersize = kmersize;
		this.primeArray = primeArray;
	}
	
	public HashFunction(int size, BitSet set) {
		this.size = size;
		this.set = set;
	}
	
	public void setHashFunction(IHashFunction<T> hash) {
		this.hash = hash;
	}
	
	public IHashFunction<T> getHashFunction(){
		return hash;
	}
	
	public T output(String genom) {
		
		if(primeArray.length != 0) {
			T out = hash.toHash(genom, kmersize, size, parameter, primeArray);
			return out;
		}
		
		T out = hash.toHash(genom, size, set);
		
		return out;

	}
	
}
