package hashFunctionCollection;

public class RabinFingerprint implements IHashFunction{
	
	int base = 2;
	long[] baseArr;
	
	public RabinFingerprint(int kmerSize) {
		this.baseArr = precomputeBase(base,kmerSize);
	}
	
	public int[] toHash(char[] seq, int cap) {

		for(int pos = 0; pos < baseArr.length; pos++) {
			IO.println(baseArr[pos]);
		}
		
		return new int[0];
	}
	
	private long[] precomputeBase(int base, int kmerSize) {
		
		long[] pre = new long[kmerSize];
		
		for(int pos = 0; pos < pre.length; pos++) {
			pre[pos] = Math.powExact(base, pos);
		}
		
		return pre;
	}

}
