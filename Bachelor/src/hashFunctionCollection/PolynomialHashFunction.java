package hashFunctionCollection;

public class PolynomialHashFunction extends HashFunctionAdapter{

	@Override
	public Integer toHash(String s,int start, int end, long size, int base ,long[][] primeArray) {

		long hash = 0;
		long curr = 0;
		int power = 0;

		for(int x = start; x < end; x++) {

			curr = s.charAt(x)*primeArray[base][power];
			power++;
			hash = hash + curr;
		}

		hash = hash % size;
		
		return (int) hash;
	}
	
}