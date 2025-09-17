package app;

import java.util.ArrayList;
import java.util.List;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.PolynomialHashFunction;
import miscTools.DNARandomizer;
import miscTools.TimeWriter;

public class Main {

	public static void main(String[] args) {
		
		//Hier neue Gen Datei erstellen fuer Einzellauf
		//DNARandomizer.createDNA(10_000_000,10,10000);
		//DNARandomizer.createCustomGenes(10, 100, "src/testFiles/GenomRandom" + 10_000_000 + ".fa");
		
		Bloom b = new Bloom("src/testFiles/VacciniaGenom.fa","src/testFiles/VacciniaGene.fa");
		
		//Mehrere Genome als input
		//je genom ein bitset
		//Gene der laenge 100 bis 150
		//Gen kmer ab 0 stopp -> also wenn erste hashfunktion 0 ausgibt, kann man beim derzeitigen gen aufhoeren
		//Hashfunktionkonflikt testen
		
		b.run();
		System.out.println("done!");
		/*
		int testCount = 100;
		List<List<Long>> testList = new ArrayList<>();
		
		List<Long> genom10_000Gen100 = new ArrayList<>();
		
		System.out.println("Vaccinia");
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			b.run();
			genom10_000Gen100.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		System.out.println();
		
		testList.add(genom10_000Gen100);
		/*
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
		System.out.println();
		
		testList.add(genom10_000_000Gen100);
		
		
		
		TimeWriter.writeTime(testList, testCount);
		
		*/
		//GenCountTests
		
		/*
		int testCount = 100;
		List<List<Long>> testList = new ArrayList<>();
		
		List<Long> genom10_000Gen100 = new ArrayList<>();
		
		System.out.println("GenCount 100");
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			b.run();
			genom10_000Gen100.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom10_000Gen100);
		
		System.out.println();
		System.out.println("GenCount 1_000");
		
		List<Long> genom100_000Gen100 = new ArrayList<>();
		b.setPathGenom("src/testFiles/GenomRandom" + 100_000 + ".fa");
		b.setPathGenes("src/testFiles/GenRandomGenom" + 100_000 + "KS10GC" + 1_000 + ".fa");
		
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			b.run();
			genom100_000Gen100.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom100_000Gen100);
		
		System.out.println();
		System.out.println("GenCount 10_000");
		
		List<Long> genom1_000_000Gen100 = new ArrayList<>();
		b.setPathGenom("src/testFiles/GenomRandom" + 100_000 + ".fa");
		b.setPathGenes("src/testFiles/GenRandomGenom" + 100_000 + "KS10GC" + 10_000 + ".fa");
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			
			b.run();
			genom1_000_000Gen100.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom1_000_000Gen100);
		
		System.out.println();
		System.out.println("GenCount 100_000");
		
		List<Long> genom1_000_000Gen100_000 = new ArrayList<>();
		b.setPathGenom("src/testFiles/GenomRandom" + 100_000 + ".fa");
		b.setPathGenes("src/testFiles/GenRandomGenom" + 100_000 + "KS10GC" + 100_000 + ".fa");
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			
			b.run();
			genom1_000_000Gen100_000.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom1_000_000Gen100_000);
		
		System.out.println();
		System.out.println("GenCount 1_000_000");
		
		List<Long> genom1_000_000Gen1_000_000 = new ArrayList<>();
		b.setPathGenom("src/testFiles/GenomRandom" + 100_000 + ".fa");
		b.setPathGenes("src/testFiles/GenRandomGenom" + 100_000 + "KS10GC" + 1_000_000 + ".fa");
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			
			b.run();
			genom1_000_000Gen1_000_000.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom1_000_000Gen1_000_000);
		
		System.out.println();
		
		System.out.println();
		System.out.println("GenCount 10_000_000");
		
		List<Long> genom1_000_000Gen10_000_000 = new ArrayList<>();
		b.setPathGenom("src/testFiles/GenomRandom" + 100_000 + ".fa");
		b.setPathGenes("src/testFiles/GenRandomGenom" + 100_000 + "KS10GC" + 10_000_000 + ".fa");
		
		for(int test = 0; test < testCount; test++) {
			long start = System.currentTimeMillis();
			
			b.run();
			genom1_000_000Gen10_000_000.add(System.currentTimeMillis() - start);
			System.out.print("\rFortschritt: " + test + "%");
		}
		System.out.print("\rFertig!");
		
		testList.add(genom1_000_000Gen10_000_000);
		
		System.out.println();
		
		TimeWriter.writeTime(testList, testCount);
		
		*/
	}

}
