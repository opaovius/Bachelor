package app.helper;

import app.Bloom;

public class CheckArguments {
	
	/**
	 * Verarbeitet die Arguments
	 * @param args
	 * @return
	 */
	
	public static Bloom check(String[] args) {
		
		Bloom b = null;
		
		switch(args.length) {
		case 0 -> IO.println("Default Modus");
		
		case 1 -> IO.println("WARNUNG: Zu wenig Argument -> Default Modus");
		
		case 2 -> IO.println("WARNUNG: Zu wenig Argument -> Default Modus");
		
		case 3 -> b = new Bloom(args[0],args[1],args[2],"");
		
		case 4 -> b = new Bloom(args[0], args[1], args[3], args[4]);
		
		default -> IO.println("WARNUNG: Zu viele Argumente -> Default Modus");
		}
		
		if(b != null) {
			return b;
		}
		else {
			return new Bloom("src/testFiles/GenomDir/VacciniaGenom.fa"
					,"src/testFiles/GenDir/VacciniaGene.fa"
					,"hashMurmur"
					,"");
		}
		
	}

}
