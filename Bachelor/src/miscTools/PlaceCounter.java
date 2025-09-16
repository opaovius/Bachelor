package miscTools;

import java.util.HashMap;
import java.util.List;

public class PlaceCounter {
	
	public static void count(List<Integer> places) {
		
		int max = 0;
		
		HashMap<Integer,Integer> map = new HashMap<>();
		
		for(int p : places) {
			if(!map.containsKey(p)) {
				map.put(p, 1);
			}
			else {
				int temp = map.get(p);
				map.put(p, temp+1);
			}
		}
		
		for(int v: map.values()) {
			if(max < v) {
				max = v;
			}
		}
		
		System.out.println("Maximum: " + max);
	}

}
