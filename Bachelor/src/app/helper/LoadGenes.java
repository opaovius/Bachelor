package app.helper;

import java.util.ArrayList;

import app.records.Gen;
import parserCollection.GenFastaParser;
import parserCollection.Parser;

public class LoadGenes {
	
	public static ArrayList<Gen> load(String pathGenes){
		
		Parser<ArrayList<Gen>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		ArrayList<Gen> genList = genParser.output();
		
		return genList;
	}

}
