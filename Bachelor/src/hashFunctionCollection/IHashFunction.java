package hashFunctionCollection;

public interface IHashFunction {
	
	/**
	 * Interface um zu garantieren, dass sich alle Hashfunktionen gleich verhalten.
	 * 
	 * @param seq : char[], welches umgewandelt wird
	 * @param cap : Obergrenze
	 * @return int[]
	 */
	
	public int[] toHash(char[] seq, int cap);
	
}
