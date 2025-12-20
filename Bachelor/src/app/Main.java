package app;

import java.io.IOException;

import app.helper.CheckArguments;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		/*
		 * 
		 * Was tun bei Sequenzen kleiner 5?
		 * 
		 * 
		 * 
		 */
		
		int test = 1;
		
		//IO.println("Vaccinia");
		
		Bloom b = CheckArguments.check(args);
		
		//b.run();
		
		//TimeUnit.SECONDS.sleep(10);
		
		
		IO.println("EColi");
		
		long[] time = new long[100];
		
		for(int x = 0; x < test; x++) {
			
			long start = System.currentTimeMillis();
			
			b.run();
			
			time[x] = System.currentTimeMillis() - start;
			
			IO.print("Fortschritt: " + x + "%\r");
		}
		
		//TimeWriter.writeTime(time, 100);
		
		
		/*
		IO.println("Ebola");
		
		b.setPathGenom("src/testFiles/GenomDir/EbolaGenom.fa");
		
		b.run();
		*/
		System.out.print("done!                                \r");
		
	}

}
