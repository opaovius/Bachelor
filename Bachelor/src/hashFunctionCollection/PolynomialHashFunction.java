package hashFunctionCollection;

import java.math.BigInteger;

public class PolynomialHashFunction implements IHashFunction<BigInteger>{

	public BigInteger toHash(String s, BigInteger size, BigInteger base) {
		
		BigInteger hash = BigInteger.valueOf(0);
		BigInteger curr;
		BigInteger fac;
		
		for(int x = 0; x <s.length(); x++) {
			
			curr = BigInteger.valueOf(s.charAt(x));
			fac = base.pow(x);
			curr = curr.multiply(fac);
			hash = hash.add(curr);
			
		}
		
		hash = hash.mod(size);
		
		return hash;
	}
	
}

