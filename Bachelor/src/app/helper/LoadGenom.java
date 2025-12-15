package app.helper;

import java.util.ArrayList;

import app.records.Alphabet;
import app.records.Genom;
import parserCollection.GenomFastaParser;
import parserCollection.Parser;

public class LoadGenom {

	/**
	 * Laedt das Genom aus einer Fa Datei und speichert es in einem Genom Record.
	 * 
	 * @param pathGenom : Pfad zu dem Genom
	 * @param alphabet  : genutzes Alphabet
	 * @return Genom Record
	 */

	public static ArrayList<ArrayList<Genom>> load(String pathGenom, Alphabet alphabet) {

		ArrayList<ArrayList<Genom>> genom = new ArrayList<>();
		Parser<ArrayList<ArrayList<Genom>>> genomParser = new Parser<>(pathGenom, alphabet);
		genomParser.setParser(new GenomFastaParser());
		
		StringBuilder sb = new StringBuilder("src/testFiles/GenomDir/EColi");

		switch (alphabet) {
		case Alphabet.DNA -> genom = genomParser.output();
		
		case Alphabet.PROTEIN -> {
		 	IO.println("under const");
		}
		
		case Alphabet.TALES -> genom = genomParser.output();
		
		default -> IO.println("Nicht Implementiert: LoadGenom()");
		}

		return genom;

	}

}
