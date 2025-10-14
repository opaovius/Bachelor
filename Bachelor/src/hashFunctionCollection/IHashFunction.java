package hashFunctionCollection;

public interface IHashFunction {
	
	public long[] toHash(String s, int kmerSize);
	
}
