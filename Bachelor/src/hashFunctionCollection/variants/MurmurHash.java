package hashFunctionCollection.variants;

import hashFunctionCollection.IHashFunction;
import hashFunctionCollection.methods.BoundedInteger;

public class MurmurHash implements IHashFunction{
	
	int kmerSize;
	final int c1 = 0xcc9e2d51;
	final int c2 = 0x1b873593;
	final int r1 = 15;
	final int r2 = 13;
	final int seed = 0x9747b28c;
	final int m = 5;
	final int n = 0xe6546b64;
	
	public MurmurHash(int kmerSize) {
		this.kmerSize = kmerSize;
	}
	
	public int[] toHash(char[] seq, int cap) {
	
		int[] kmerArr = new int[seq.length - kmerSize + 1];
		
		for(int pos = 0; pos < seq.length - kmerSize + 1; pos++) {
			int currKmer = cycle(seq,pos);
			kmerArr[pos] = BoundedInteger.bound(currKmer, cap);
		}
		
		
		return kmerArr;
	}
	
	private int cycle(char[] seq, int pos) {
		
		int[] currKeys = new int[kmerSize/2];
		int hash = seed;
		
		for(int x = 0; x < kmerSize; x = x + 2) {
			
			//Zwei chars in ein 4Byte chunk packen
			currKeys[x/2] = (seq[pos+x] << 16| seq[pos+x+1]);
		}
		
		//Jedes 4 Byte chunk durchmischen
		for(int key: currKeys) {
			key = key * c1;
			key = Integer.rotateLeft(key, r1);
			key = key * c2;
			
			hash = hash ^ key;
			hash = Integer.rotateLeft(hash, r2);
			hash = (hash * m) + n;
		}
		
		//Extra durchmischen
		hash = hash ^ kmerSize;
		hash = hash ^ (hash >> 16);
		hash = hash * 0x85ebca6b;
		hash = hash ^ (hash >> 13);
		hash = hash * 0xc2b2ae35;
		hash = hash ^ (hash >> 16);
		
		
		return hash;
	}

}
