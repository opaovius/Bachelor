package app;

import java.io.IOException;

import app.helper.CheckArguments;
import miscTools.FileWriters.TimeWriter;
import miscTools.TestMethods.AllKmers;
import miscTools.TestMethods.TestPipeline;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//Hier neue Gen Datei erstellen fuer Einzellauf
		//DNARandomizer.createDNA(10_000_000,10,10000);
		//DNARandomizer.createCustomGenes(10, 100, "src/testFiles/GenomRandom" + 10_000_000 + ".fa");
		
		//besseres two bit -uff
		//2 hash kombinieren       -JO
		//aa hash bzw proteine
		// besseres cap bzw kleineres austesten
		
		int test = 1;
		
		TestPipeline.testTwoBit();
		
		//IO.println("Vaccinia");
		
		Bloom b = CheckArguments.check(args);
		
		//b.run();
		
		//TimeUnit.SECONDS.sleep(10);
		
		
		IO.println("EColi");
		/*
		
		b.setGenom("src/testFiles/GenomDir/EColiGenom.fa");
		b.setGenes("src/testFiles/GenDir/EColiGene.fa");
		
		long[] time = new long[100];
		
		for(int x = 0; x < test; x++) {
			
			long start = System.currentTimeMillis();
			
			b.run();
			
			time[x] = System.currentTimeMillis() - start;
			
			IO.print("Fortschritt: " + x + "%\r");
		}
		*/
		//TimeWriter.writeTime(time, 100);
		
		
		/*
		IO.println("Ebola");
		
		b.setPathGenom("src/testFiles/GenomDir/EbolaGenom.fa");
		
		b.run();
		*/
		System.out.print("done!                                \r");
		
	}

}
