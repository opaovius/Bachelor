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
		Alphabet alphabet = Alphabet.TALES;
		
		
		if(args.length == 0) return new Bloom("src/testFiles/Tales/List_of_TALEs_incl_length_RVDs.tsv"
				,"src/testFiles/GenDir/Tales20Percent.tsv"
				,alphabet
				,"hashNt"
				,"hashNt2");
		
		
		/*
		if(args.length == 0) return new Bloom("src/testFiles/GenomDir/EColi_Genom.fa"
				,"src/testFiles/GenDir/EColiGeneProtein.fa"
				,alphabet
				,"hashNt"
				,"hashNt2");
		*/
		
		switch(args[2].toLowerCase()) {
		case "dna" -> alphabet = Alphabet.DNA;
		
		case "protein" -> alphabet = Alphabet.PROTEIN;
		
		case "generic" -> alphabet = Alphabet.GENERIC;
		
		case "latin" -> alphabet = Alphabet.LATIN;
		
		case "tales" -> alphabet = Alphabet.TALES;
		
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
			return new Bloom("src/testFiles/GenomDir/Vaccinia_Genom.fa"
					,"src/testFiles/GenDir/VacciniaGene.fa"
					,alphabet
					,"hashNt"
					,"");
		}
		
	}

}
