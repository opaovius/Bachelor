package app;

public class Main {

	public static void main(String[] args) {
		
		//Hier neue Gen Datei erstellen fuer Einzellauf
		//DNARandomizer.createDNA(10_000_000,10,10000);
		//DNARandomizer.createCustomGenes(10, 100, "src/testFiles/GenomRandom" + 10_000_000 + ".fa");
		
		IO.println("Vaccinia");
		
		Bloom b = new Bloom("src/testFiles/VacciniaGenom.fa","src/testFiles/VacciniaGene.fa");
		
		//Mehrere Genome als input
		//je genom ein bitset
		//Gene der laenge 100 bis 150
		//Gen kmer ab 0 stopp -> also wenn erste hashfunktion 0 ausgibt, kann man beim derzeitigen gen aufhoeren
		//Hashfunktionkonflikt testen
		
		b.run();
		
		/*
		IO.println("EColi");
		
		b.setPathGenom("src/testFiles/EColiGenom.fa");
		
		
		b.run();
		/*
		IO.println("Ebola");
		
		b.setPathGenom("src/testFiles/EbolaGenom.fa");
		
		b.run();
		*/
		System.out.println("done!");
		
	}

}
