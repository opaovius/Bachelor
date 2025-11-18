package hashFunctionCollection.variants;

import java.io.UnsupportedEncodingException;

import hashFunctionCollection.IHashFunction;
import hashFunctionCollection.methods.BoundedInteger;

public class JenkinsOneAtATimeHash implements IHashFunction{
	
	int kmerSize;
	
	public JenkinsOneAtATimeHash(int kmerSize) {
		this.kmerSize = kmerSize;
	}
	
	public int[] toHash(char[] seq, int cap) {
		
		int[] kmerArr = new int[seq.length - kmerSize + 1];
		
		for(int pos = 0; pos < seq.length - kmerSize + 1; pos++) {
			
			String temp = new String(seq,pos,kmerSize);
			
			int currKmer = 0;
			
			try {
				currKmer = cycle(temp.getBytes("US-ASCII"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			kmerArr[pos] = BoundedInteger.bound(currKmer, cap);
		}
		
		return kmerArr;
	}
	
	public int cycle(byte[] bytes) {
		
		int hash = 0;
		
		for(byte b : bytes) {
			hash = hash + b;
			hash = hash + (hash << 10);
			hash = hash ^ (hash >>> 6);
		}
		
		hash = hash + (hash << 3);
		hash = hash ^ (hash >>> 11);
		hash = hash + (hash << 15);
		
		return hash;
	}

}
