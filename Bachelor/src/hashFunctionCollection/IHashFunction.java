package hashFunctionCollection;

import java.math.BigInteger;

public interface IHashFunction<T> {
	
	public T toHash(String s, BigInteger size, BigInteger parameter);

}
