package app.helper;

import app.Bloom;
import app.records.Alphabet;

public class CheckArguments {
	
	/**
	 * Verarbeitet die Arguments
	 * @param args
	 * @return
	 */
	
	public static Bloom check(String[] args) {
		
		Bloom b = null;
		Alphabet alphabet = Alphabet.DNA;
		
		if(args.length == 0) return new Bloom("src/testFiles/GenomDir/VacciniaGenom.fa"
				,"src/testFiles/GenDir/VacciniaGene.fa"
				,alphabet
				,"hashNt"
				,"");
		
		
		switch(args[2]) {
		case "DNA" -> alphabet = Alphabet.DNA;
		
		case "Protein" -> alphabet = Alphabet.PROTEIN;
		
		case "Generic" -> alphabet = Alphabet.GENERIC;
		
		case "Latin" -> alphabet = Alphabet.LATIN;
		
		default -> IO.println("Default Alphabet: DNA");
		}
		
		switch(args.length) {
		case 0 -> IO.println("Default Modus");
		
		case 1 -> IO.println("WARNUNG: Zu wenig Argument -> Default Modus");
		
		case 2 -> IO.println("WARNUNG: Zu wenig Argument -> Default Modus");
		
		case 3 -> IO.println("WARNUNG: Zu wenig Argument -> Default Modus");
		
		case 4 -> b = new Bloom(args[0],args[1],alphabet,args[3],"");
		
		case 5 -> b = new Bloom(args[0], args[1],alphabet, args[3], args[4]);
		
		default -> IO.println("WARNUNG: Zu viele Argumente -> Default Modus");
		}
		
		if(b != null) {
			return b;
		}
		else {
			return new Bloom("src/testFiles/GenomDir/VacciniaGenom.fa"
					,"src/testFiles/GenDir/VacciniaGene.fa"
					,alphabet
					,"hashNt"
					,"");
		}
		
	}

}
