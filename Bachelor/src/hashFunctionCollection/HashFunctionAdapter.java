package hashFunctionCollection;

public abstract class HashFunctionAdapter implements IHashFunction<Integer>{
	
	public Integer toHash(String s, int start, int end, long size, int parameter, long[][] primeArray) {
		return 0;
	}
	
	public Integer toHash(String s, int start, int end, int size) {
		return 0;
	}

}
