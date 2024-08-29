package miscTools;

import java.math.BigInteger;
import java.util.List;

public class PlaceCounter {
	
	public static void count(List<BigInteger> places) {
		
		int size = 10000;
		
		Long[] array = new Long[size];
		
		System.out.println("Check");
		
		for(int x = 0; x < size; x++) {
			array[x] = 0L;
		}
		System.out.println("Check2");
		for(int x = 0; x < size; x++) {
			for(BigInteger d : places) {
				if(x == d.intValue()) {
					array[x]++;
				}
			}
		}
		
		long max = 0;
		System.out.println("Check3");
		for(int x = 0; x < size; x++) {
			if(array[x] > max) {
				max = array[x];
			}
		}
		
		System.out.println("Maximum: " + max);
		
		
		
	}

}
