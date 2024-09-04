package app;

import java.util.ArrayList;
import java.util.List;

import miscTools.DNARandomizer;
import miscTools.TimeWriter;

public class Main {

	public static void main(String[] args) {
		
		//DNARandomizer.createDNA(10_000,10,100);
		
		Bloom b = new Bloom("src/testFiles/GenomRandom10000.fa","src/testFiles/GenRandomGenom" + 10_000 +"KS10GC100.fa");
		//substring -> index
		//hashfunktion im konstruktor, string in output()
		//testen mit groﬂen daten,laufzeit
		//long - bigint test
		
		//b.run();
		
		int testCount = 100;
		List<List<Long>> testList = new ArrayList<>();
		
		List<Long> genom10_000Gen100 = new ArrayList<>();
		
		System.out.println("Genom10_000");
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			b.run();
			genom10_000Gen100.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom10_000Gen100);
		
		System.out.println();
		System.out.println("Genom100_000");
		
		List<Long> genom100_000Gen100 = new ArrayList<>();
		b.setPathGenom("src/testFiles/GenomRandom" + 100_000 + ".fa");
		b.setPathGenes("src/testFiles/GenRandomGenom" + 100_000 + "KS10GC100.fa");
		
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			b.run();
			genom100_000Gen100.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom100_000Gen100);
		
		System.out.println();
		System.out.println("Genom1_000_000");
		
		List<Long> genom1_000_000Gen100 = new ArrayList<>();
		b.setPathGenom("src/testFiles/GenomRandom" + 1_000_000 + ".fa");
		b.setPathGenes("src/testFiles/GenRandomGenom" + 1_000_000 + "KS10GC100.fa");
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			
			b.run();
			genom1_000_000Gen100.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom1_000_000Gen100);
		
		System.out.println();
		System.out.println("Genom10_000_000");
		
		List<Long> genom10_000_000Gen100 = new ArrayList<>();
		b.setPathGenom("src/testFiles/GenomRandom" + 10_000_000 + ".fa");
		b.setPathGenes("src/testFiles/GenRandomGenom" + 10_000_000 + "KS10GC100.fa");
		
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			b.run();
			genom10_000_000Gen100.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom10_000_000Gen100);
		
		
		TimeWriter.writeTime(testList, testCount);
		
	}

}
