package app.helper;

import app.records.Alphabet;
import app.records.Genom;
import parserCollection.GenomFastaParser;
import parserCollection.Parser;

public class LoadGenom {
	
	/**
	 * Laedt das Genom aus einer Fa Datei und speichert es in
	 * einem Genom Record.
	 * 
	 * @param pathGenom : Pfad zu dem Genom
	 * @param alphabet : genutzes Alphabet
	 * @return Genom Record
	 */
	
	public static Genom load(String pathGenom,Alphabet alphabet) {
		
		Parser<Genom> genomParser = new Parser<>(pathGenom,alphabet);
		genomParser.setParser(new GenomFastaParser());
		Genom genom = genomParser.output();
		return genom;
		
	}

}
