package app;

import miscTools.DNARandomizer;

public class Main {

	public static void main(String[] args) {
		
		DNARandomizer.createDNA(100000000,10,100);
		
		//Bloom b = new Bloom("src/testFiles/GenomRandom10000.fa","src/testFiles/GenRandomKS10GC100.fa");
		//substring -> index
		//hashfunktion im konstruktor, string in output()
		//testen mit großen daten,laufzeit
		//long - bigint test
		
		//b.run();
	}

}
