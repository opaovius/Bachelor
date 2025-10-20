package app.helper;

public class BoundedInteger {
	
	public static int[] boundToInt(long[] longs , int cap) {
		
		int[] ints = new int[longs.length];
		
		for(int x = 0; x < longs.length; x++) {
			ints[x] = (int) longs[x] & ((1 << cap) - 1);
		}
		
		return ints;
	}
	
	public static int calculateCap(int size) {
		
		int x = 0;
		
		while(Math.powExact(2, x) < size && x <= 28) {
			x++;
		}
		
		// +2 und nicht +1, damit weniger Ueberschneidungen da sind
		return x+2;
	}

}
