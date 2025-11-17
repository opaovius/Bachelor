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
			
			int currKmer = hashCode(1,seq,pos,kmerSize);
			
			kmerArr[pos] = BoundedInteger.bound(currKmer, cap);

		}
		return kmerArr;
	}

	//Standard Java Array.hashCode()
	private static int hashCode(int result, char[] a, int fromIndex, int length) {
        int end = fromIndex + length;
        for (int i = fromIndex; i < end; i++) {
            result = 31 * result + a[i];
        }
        return result;
    }

}
