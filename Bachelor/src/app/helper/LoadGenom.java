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

	public static ArrayList<Genom> load(String pathGenom, Alphabet alphabet) {

		ArrayList<Genom> genom = new ArrayList<>();
		Parser<Genom> genomParser = new Parser<>(pathGenom, alphabet);
		genomParser.setParser(new GenomFastaParser());
		
		StringBuilder sb = new StringBuilder("src/testFiles/GenomDir/EColi");

		switch (alphabet) {
		case Alphabet.DNA -> genom.add(genomParser.output());
		
		case Alphabet.PROTEIN -> {
		 	genomParser.setPath(sb.append("_COMP_LR_0.fa").toString());
		 	genom.add(genomParser.output());
		 	sb = new StringBuilder("src/testFiles/GenomDir/EColi");
		 	genomParser.setPath(sb.append("_COMP_LR_1.fa").toString());
		 	genom.add(genomParser.output());
		 	sb = new StringBuilder("src/testFiles/GenomDir/EColi");
		 	genomParser.setPath(sb.append("_COMP_LR_2.fa").toString());
		 	genom.add(genomParser.output());
		 	sb = new StringBuilder("src/testFiles/GenomDir/EColi");
		 	genomParser.setPath(sb.append("_LR_0.fa").toString());
		 	genom.add(genomParser.output());
		 	sb = new StringBuilder("src/testFiles/GenomDir/EColi");
		 	genomParser.setPath(sb.append("_LR_1.fa").toString());
		 	genom.add(genomParser.output());
		 	sb = new StringBuilder("src/testFiles/GenomDir/EColi");
		 	genomParser.setPath(sb.append("_LR_2.fa").toString());
		 	genom.add(genomParser.output());
		}
		default -> IO.println("Nicht Implementiert: LoadGenom()");
		}

		return genom;

	}

}
