package hashFunctionCollection;

import hashFunctionCollection.methods.BoundedInteger;

public class RabinFingerprint implements IHashFunction{
	
	int kmerSize;
	
	public RabinFingerprint(int kmerSize) {
		this.kmerSize = kmerSize;
	}
	
	public int[] toHash(char[] seq, int cap) {

		RabinKarp rk = new RabinKarp(seq);
		int[] kmerArr = new int[seq.length - kmerSize + 1];
		
		
		for(int pos = 0; pos < seq.length - kmerSize; pos++) {
			long tempKmer =  rk.getSubHash(pos, pos + kmerSize);
			kmerArr[pos] = BoundedInteger.boundToInt(tempKmer,cap);
		}

		
		
		return kmerArr;
	}
	
	
	private class RabinKarp{
		
	    private final long base = 31;
	    private final long mod = 1_000_000_007L;
	    private long[] hash;
	    private long[] power;
		
		public RabinKarp(char[] seq) {
		
			hash = new long[seq.length];
			power = new long[seq.length];
			
			hash[0] = charToInt(seq[0]);
			power[0] = 1;
			
			for(int pos = 1; pos < seq.length; pos++) {
				hash[pos] = (((hash[pos-1] * base) % mod) + charToInt(seq[pos]) % mod);
				power[pos] = (power[pos-1] * base) % mod;
			}
			
		}
		
		//Char zu Int konversion
		private long charToInt(char c) {
	        return c - 'A' + 1;
	    }
		
		//Sub hash ausrechnen
	    public long getSubHash(int l, int r) {
	        long h = hash[r];
	        if (l > 0) {
	        	h = (h - ((hash[l-1] * power[r - l - 1])%mod)% mod);
	        }
	        return h;
	    }
		
	}

}

