package app;

public class DummyHashFunction implements IHashFunction<Double>{

	public Double toHash(String s) {
		
		double k = 5678;
		double A = 0.6829;
		double M = 2000;
        
		for(int x = 0; x < s.length(); x++) {
			k = k + s.codePointAt(x);
		}
        
		double hash = Math.floor(M*((k*A) % 1)); 
		
		return hash;
	}
	
}
