package app;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		//Hier neue Gen Datei erstellen fuer Einzellauf
		//DNARandomizer.createDNA(10_000_000,10,10000);
		//DNARandomizer.createCustomGenes(10, 100, "src/testFiles/GenomRandom" + 10_000_000 + ".fa");
		
		//IO.println("Vaccinia");
		
		Bloom b = new Bloom("src/testFiles/VacciniaGenom.fa","src/testFiles/VacciniaGene.fa");
		
		//b.run();
		
		//TimeUnit.SECONDS.sleep(10);
		
		
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
