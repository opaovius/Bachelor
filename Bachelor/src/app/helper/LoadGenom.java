package app.helper;

import app.records.Genom;
import parserCollection.GenomFastaParser;
import parserCollection.Parser;

public class LoadGenom {
	
	/**
	 * Laedt das Genom aus einer Fa Datei und speichert es in
	 * einem Genom Record.
	 * 
	 * @param pathGenom : Pfad zu dem Genom
	 * @return Genom Record
	 */
	
	public static Genom load(String pathGenom) {
		
		Parser<Genom> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		Genom genom = genomParser.output();
		return genom;
		
	}

}
