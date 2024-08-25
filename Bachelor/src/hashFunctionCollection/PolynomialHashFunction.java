package hashFunctionCollection;

public class PolynomialHashFunction implements IHashFunction<Long>{

	public Long toHash(String s, long size, long base) {
		
		long hash = 0;
		
		for(int x = 0; x <s.length(); x++) {
			
			Double curr = s.charAt(x)*Math.pow(base, x);
			
			hash = hash + curr.longValue();
		}
		
		hash = hash % size;
		
		//if(hash < 0) System.out.println(hash);
		
		return hash;
	}
	
}
