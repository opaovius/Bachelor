package hashFunctionCollection.methods;

public class BoundedInteger {
	
	/**
	 * Begrenzt alle Longwerte in einem Array auf das Cap, welches im Integerbereich ist.
	 * 
	 * @param longs : Array mit den Longwerten
	 * @param cap : Wert, auf welchen die Werte begrenzt werden
	 * @return Integer Array
	 */
	
	public static int[] boundToInt(long[] longs , int cap) {
		
		int[] ints = new int[longs.length];
		
		for(int x = 0; x < longs.length; x++) {
			int temp = (int) longs[x] % cap;
			ints[x] = Math.abs(temp);
		}
		
		return ints;
	}
	
	/**
	 * Begrenz einen Long wert auf das Cap, welches im Integerbereich ist.
	 * 
	 * @param singleLong : Input Long
	 * @param cap : Wert, auf welchen der Wert begrenzt wird
	 * @return Integer Wert
	 */
	
	public static int boundToInt(long singleLong, int cap) {
		
		return Math.abs((int) singleLong % cap);
		
	}
	
	/**
	 *  Begrenzt einen Integer auf das Cap
	 * @param i : Input Integer
	 * @param cap : Wert, auf welchen der Wert begrenzt wird
	 * @return Integer Wert
	 */
	
	public static int bound(int i, int cap) {
		
		return Math.abs(i % cap);
		
	}
	
	/**
	 *  Berechnet die Obergrenze auf welche das BitSet begrenzt werden soll.
	 * 
	 * @param size : Groesse des Genoms
	 * @return Integer Wert, welcher die Obergrenze beschreibt
	 */
	
	public static int calculateCap(int size) {
		
		int x = 0;
		
		while(Math.powExact(2, x) < size && x <= 28) {
			x++;
		}
		
		x = x + 2;
		
		// +2 und nicht +1, damit weniger Ueberschneidungen da sind
		return Math.powExact(2, x);
	}

}
