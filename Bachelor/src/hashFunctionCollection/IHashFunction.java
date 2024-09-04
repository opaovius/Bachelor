package hashFunctionCollection;

public interface IHashFunction<T> {
	
	public T toHash(String s, long size, long parameter);

}
