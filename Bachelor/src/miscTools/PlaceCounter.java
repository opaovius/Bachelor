package miscTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PlaceCounter {
	
	public static int countInt(int[] places) {
		
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
	
	public static int countSameKmers(String genom, int kmerSize) {
		
		HashSet<String> kmerSet = new HashSet<>();
		
		for(int counter = 0; counter < genom.length() - kmerSize + 1; counter++) {
			
			if(kmerSet.contains(genom.substring(counter, counter+kmerSize))) {
				//IO.println(genom.substring(counter, counter+kmerSize));
			}
			else {
				kmerSet.add(genom.substring(counter, counter+kmerSize));
			}
		}
		
		return kmerSet.size();
	}
	
	public static ArrayList<String> getUniqueStrings(String genom, int kmerSize){
		
		ArrayList<String> UniqueKmers = new ArrayList<>();
		HashSet<String> kmerSet = new HashSet<>();
		
		for(int counter = 0; counter < genom.length() - kmerSize + 1; counter++) {
			
			String subS = genom.substring(counter, counter+kmerSize);
			
			if(!kmerSet.contains(subS)) {
				UniqueKmers.add(subS);
				kmerSet.add(subS);
			}
			
		}
		
		return UniqueKmers;
	}

}
