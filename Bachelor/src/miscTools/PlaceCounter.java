package miscTools;

import java.util.HashMap;
import java.util.List;

public class PlaceCounter {
	
	public static int countInt(List<Integer> places) {
		
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
		IO.println("Map Size: " + map.size());
		
		return map.size();
	}
	
	public static int countLong(List<Long> places) {
		
		int max = 0;
		
		HashMap<Long,Integer> map = new HashMap<>();
		
		for(long p : places) {
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
		IO.println("Map Size: " + map.size());
		
		return map.size();
	}

}
