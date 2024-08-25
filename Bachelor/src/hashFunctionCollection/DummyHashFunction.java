package hashFunctionCollection;

public class DummyHashFunction implements IHashFunction<Double>{

	// wird nicht genutzt, ist nur eine dummyfunktion
	public Double toHash(String s, long dummy1, long dummy2) {
		
		double k = 0;
		double A = (Math.sqrt(5) - 1) / 2; //Goldener Schnitt
		double M = 512; //2^x soll am besten sein
        
		for(int x = 0; x < s.length(); x++) {
			k = k + s.codePointAt(x);
		}
        
		double hash = Math.floor(M*((k*A) % 1)); 
		
		return hash;
	}
	
}
