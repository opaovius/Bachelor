package hashFunctionCollection;

public class HashFunction {

	IHashFunction hash = null;
	
	/**
	 * Setzt die Variante der Hashfunktion, welche man nutzen moechte.
	 * @param hash : Hashfunktion
	 */
	
	public void setHashFunction(IHashFunction hash) {
		this.hash = hash;
	}
	
	/**
	 * Gibt die Hashfunktion zurueck.
	 * @return Hashfunktion
	 */
	
	public IHashFunction getHashFunction(){
		return hash;
	}
	
	/**
	 * Wandelt ein char[] in ein int[] um mittels einer zuvor gesetzten Hashfunktion. 
	 * 
	 * @param seq : char[], welches umgewandelt wird 
	 * @param cap : Obergrenze
	 * @return int[]
	 */
	
	public int[] output(char[] seq, int cap) {
		
		return hash.toHash(seq,cap);

	}
	
}
