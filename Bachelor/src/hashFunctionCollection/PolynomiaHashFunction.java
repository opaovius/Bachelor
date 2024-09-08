package hashFunctionCollection;

public class PolynomiaHashFunction implements IHashFunction<Long>{

	public Long toHash(String s,int start, int end, long size, long base) {

		long hash = 0;
		long curr = 0;
		int power = 0;

		for(int x = start; x < end; x++) {

			curr = s.charAt(x)*powLong(base, power);
			power++;
			hash = hash + curr;
		}

		hash = hash % size;
		
		return hash;
	}
	
	private Long powLong(long base, long power) {
		long result = base;
		
		if(power == 1) {
			return result;
		}
		
		if(power == 0) {
			return 1L;
		}
		
		for(int x = 1; x < power; x++) {
			result = result*base;
		}
		
		
		return result;
	}
	
	private Long powLongBySquare(long base, long power) {
		long result = base;
		
		if(power == 1) {
			return result;
		}
		
		if(power == 0) {
			return 1L;
		}
		
		//wenn exponent gerade
		if(power % 2 == 0) {
			result = powLongBySquare(base*base,power/2);
		}
		
		//wenn exponent ungerade
		if(power % 2 == 1) {
			result = result*powLongBySquare(result*result,(power-1)/2);
		}
		
		return result;
	}
	
}

