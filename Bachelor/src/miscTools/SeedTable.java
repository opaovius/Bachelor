package miscTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.BitSet;
import java.util.Random;
import java.io.IOException;

public class SeedTable {
	
	public static void create() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/testFiles/SeedTable.csv"));
			StringBuilder sb = new StringBuilder();
			
			for (char x = 'A'; x <= 'Z'; x++) {
				sb.append(x + "," + random() + "\n");
			}
			
			writer.write(sb.toString());
			writer.close();
			
		} catch(IOException e) {
			IO.println("SeedTable konnte nicht erstellt werden");
		}
	}

	private static long random() {
		
		BitSet random = new BitSet();
		Random coin = new Random();
		
		for(int counter = 0; counter < 64; counter++) {
			if(coin.nextBoolean()) {
				random.set(counter);
			}
		}
		
		long[] arr = random.toLongArray();
		
		return arr[0];
	}
	
}
