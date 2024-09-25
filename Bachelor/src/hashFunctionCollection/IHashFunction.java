package hashFunctionCollection;

public interface IHashFunction<T> {
	
	public T toHash(String s, int start, int end, long size, int parameter, long[][] primeArray);

}
