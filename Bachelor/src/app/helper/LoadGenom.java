package app.helper;

import app.records.Genom;
import parserCollection.GenomFastaParser;
import parserCollection.Parser;

public class LoadGenom {
	
	public static Genom load(String pathGenom) {
		
		Parser<Genom> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		Genom genom = genomParser.output();
		return genom;
		
	}

}
