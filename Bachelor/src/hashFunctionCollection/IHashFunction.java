package hashFunctionCollection;

public interface IHashFunction {
	
	public long[] toHash(char[] s, int kmerSize);
	
}
