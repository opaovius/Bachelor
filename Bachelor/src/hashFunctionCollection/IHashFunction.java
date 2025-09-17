package hashFunctionCollection;

public interface IHashFunction<T> {
	
	public T toHash(String s, int start, int end, int size, int parameter, long[][] primeArray);
	
	public T toHash(String s, int start, int end, int size);

}
