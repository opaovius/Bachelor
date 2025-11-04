package app.helper;

import java.util.ArrayList;

import app.records.Gen;
import parserCollection.GenFastaParser;
import parserCollection.Parser;

public class LoadGenes {
	
	/**
	 * Laedt die Gene aus einer Fa Date und speichert sie in einer 
	 * ArrayList aus Gen Records. 
	 * 
	 * @param pathGenes : Pfad zu den Genen
	 * @return : ArrayList mit den Genen
	 */
	
	public static ArrayList<Gen> load(String pathGenes){
		
		Parser<ArrayList<Gen>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		ArrayList<Gen> genList = genParser.output();
		
		return genList;
	}

}
