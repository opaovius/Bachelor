package hashFunctionCollection.methods;

import java.util.HashMap;

import app.records.Alphabet;

public class VerifyTable {

	public static long verifySeed(HashMap<Character,Long> seed, char c, Alphabet alphabet) {
		
		if(seed.containsKey(c)) {
			return seed.get(c);
		}
		else if(alphabet == Alphabet.TALES) {
			return seed.get('W');
		}
		else if(alphabet == Alphabet.PROTEIN) {
			return seed.get('X');
		}
		else if(alphabet == Alphabet.DNA) {
			return seed.get('T');
		}
		else {
			IO.println("Unbekanntes Zeichen: " + c + " bei VerifyTable.verifySeed()");
			return 0;
		}
		
	}
	
	public static long verifyLook(HashMap<Character,HashMap<Integer,Long>> look, char c,int i, Alphabet alphabet) {
		
		if(look.containsKey(c)) {
			return look.get(c).get(i);
		}
		else if(alphabet == Alphabet.TALES) {
			return look.get('W').get(i);
		}
		else if(alphabet == Alphabet.PROTEIN) {
			return look.get('X').get(i);
		}
		else if(alphabet == Alphabet.DNA) {
			return look.get('T').get(i);
		}
		else {
			IO.println("Unbekanntes Zeichen: " + c + " bei VerifyTable.verifyLook()");
			return 0;
		}
	}
	
	
	
}
