package hashFunctionCollection.variants;

import hashFunctionCollection.IHashFunction;
import hashFunctionCollection.methods.BoundedInteger;

public class JavaHash implements IHashFunction{
	
	private int kmerSize;
	
	/**
	 * Erstellt Substrings aus dem char[] und hasht dann mittels hashCode()
	 * @param kmerSize
	 */
	
	public JavaHash(int kmerSize) {
		this.kmerSize = kmerSize;
	}
	
	public int[] toHash(char[] seq, int cap) {
		
		int[] kmerArr = new int[seq.length - kmerSize + 1];
		
		for(int pos = 0; pos < seq.length - kmerSize + 1; pos++) {
			
			String tempKmer = new String(seq,pos,kmerSize);
			
			kmerArr[pos] = BoundedInteger.bound((tempKmer.hashCode() & ((1 << 31)-1)),cap);
		}
		
		return kmerArr;
	}

}
