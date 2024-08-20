package miscTools;

import java.util.List;

public class PlaceCounter {
	
	public static void count(List<Double> places) {
		
		int size = 5000;
		
		Double[] array = new Double[size];
		
		System.out.println("Check");
		
		for(int x = 0; x < size; x++) {
			array[x] = 0.0;
		}
		System.out.println("Check2");
		for(int x = 0; x < size; x++) {
			for(double d : places) {
				if(x == d) {
					array[x]++;
				}
			}
		}
		
		double max = 0;
		System.out.println("Check3");
		for(int x = 0; x < size; x++) {
			if(array[x] > max) {
				max = array[x];
			}
		}
		
		System.out.println("Maximum: " + max);
		
		
		
	}

}
