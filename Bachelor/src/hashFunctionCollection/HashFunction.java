package hashFunctionCollection;

import java.util.BitSet;

import app.Gen;

public class HashFunction {

	IHashFunction hash = null;
	
	public void setHashFunction(IHashFunction hash) {
		this.hash = hash;
	}
	
	public IHashFunction getHashFunction(){
		return hash;
	}
	
	public BitSet outputGenom(String genom) {
		
		BitSet out = hash.toHashGenom(genom);
		
		return out;

	}
	
	public int outputGen(Gen g) {
		
		int out = hash.toHashGen(g);
		
		return out;

	}
	
}
