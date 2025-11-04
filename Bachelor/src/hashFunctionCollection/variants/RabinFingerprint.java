package hashFunctionCollection.variants;

import java.util.ArrayList;

import hashFunctionCollection.IHashFunction;
import hashFunctionCollection.methods.BoundedInteger;

public class RabinFingerprint implements IHashFunction{
	
	int kmerSize;
	
	/**
	 * Erstellt einen Hashwert nach dem Muster m_0 + m_1*x + ... + m_{n-1} x^{n-1}.
	 * 
	 * @param kmerSize : Groesse der Kmere
	 */
	
	public RabinFingerprint(int kmerSize) {
		this.kmerSize = kmerSize;
	}
	
	public int[] toHash(char[] seq, int cap) {

		RabinKarp rk = new RabinKarp(seq);
		int[] kmerArr = new int[seq.length - kmerSize + 1];
		
		ArrayList<Long> test = new ArrayList<>();
		
		for(int pos = 0; pos < seq.length - kmerSize + 1; pos++) {
			long tempKmer =  rk.getSubHash(pos, pos + kmerSize);
			test.add(tempKmer);
			kmerArr[pos] = BoundedInteger.boundToInt(tempKmer,cap);
		}
		
		return kmerArr;
	}
	
	
	private class RabinKarp{
		
	    private final long base = 31;
	    private final long mod = 100_000_000_007L;
	    private long[] hash;
	    private long[] power;
		
		public RabinKarp(char[] seq) {
		
			hash = new long[seq.length];
			power = new long[seq.length];
			hash[0] = charToInt(seq[0]);
			power[0] = 1;
			
			for(int pos = 1; pos < seq.length; pos++) {
				 hash[pos] = add(mul(hash[pos - 1], base), charToInt(seq[pos]));
		            power[pos] = mul(power[pos - 1], base);
			}
			
		}
			
		//Char zu Int konversion
		private long charToInt(char c) {
	        return c - 'A' + 1;
	    }
		
		//Sub hash ausrechnen
	    public long getSubHash(int l, int r) {
	        long h = hash[r-1];
	        if (l > 0) {
	        	 h = sub(h, mul(hash[l - 1], power[r - l]));
	        }
	        return h;
	    }
	    
	    //Operationen mit Mod
	    private long add(long a, long b) {
	        a += b;
	        if (a >= mod) a -= mod;
	        return a;
	    }

	    private long sub(long a, long b) {
	        a -= b;
	        if (a < 0) a += mod;
	        return a;
	    }

	    private long mul(long a, long b) {
	        return ((1L * a * b) % mod);
	    }
		
	}

}

