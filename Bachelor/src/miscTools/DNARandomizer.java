package miscTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DNARandomizer {
	
	public static void createDNA(int size) {

		StringBuilder DNA = new StringBuilder();
		Random random = new Random();
		int length = 71;
		
		for(int x = 0; x < size; x++) {
			int curr = random.nextInt(4);
			
			switch(curr) {
			case 0:
				DNA.append('A');
				break;
			case 1:
				DNA.append('C');
				break;
			case 2:
				DNA.append('G');
				break;
			case 3:
				DNA.append('T');
				break;
			default:
				DNA.append('X');
			}
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/GenomRandom.fa"));
			
			writer.write(">Genomius Testus\n");
			
			for(int x = 0; x < size - length; x = x + length) {
				writer.write(DNA.substring(x, x+length) + "\n");
			}
			
			if(size % length != 0) {
				writer.write(DNA.substring(size-(size % length), size));	
			}
			
			writer.close();
			
		}
		catch(IOException e) {
			System.out.println("IO-Exception: DNA-Randomizer");
		}
		
		System.out.println("DNA-Datei geschrieben!");
		
	}

}
