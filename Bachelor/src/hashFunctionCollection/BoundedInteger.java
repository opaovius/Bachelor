package hashFunctionCollection;

public class BoundedInteger {
	/**
	 * 
	 * @param longs long Array, welches zu int Umgewandelt wird
	 * @param cap Der Begrenzte bereich ergibt sich 2^cap, also ist es ein vielfaches von 2
	 * @return
	 */
	
	public static int[] boundToInt(long[] longs , int cap) {
		
		int[] ints = new int[longs.length];
		
		for(int x = 0; x < longs.length; x++) {
			ints[x] = (int) (longs[x] % Math.powExact(2, cap));
		}
		
		return ints;
	}
	
	public static int calculateCap(int size) {
		
		int x = 0;
		
		while(Math.powExact(2, x) < size) {
			x++;
			
			if(Math.powExact(2, x) < 0) {
				IO.println("Error: calculateCap Integer Overflow");
				break;
			}
		}
		
		// +2 und nicht +1, damit weniger Ueberschneidungen da sind
		return (x+2);
	}

}
