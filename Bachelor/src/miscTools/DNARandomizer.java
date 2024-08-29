
package miscTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DNARandomizer {
	
	public static void createDNA(int genomSize, int kmerSize, int genCount) {

		StringBuilder DNA = new StringBuilder();
		Random random = new Random();
		int length = 71;
		
		for(int x = 0; x < genomSize; x++) {
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
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/testFiles/GenomRandom" + genomSize + ".fa"));
			
			//GenomDatei
			writer.write(">Genomius Testus\n");
			
			for(int x = 0; x < genomSize - length; x = x + length) {
				writer.write(DNA.substring(x, x+length) + "\n");
			}
			
			if(genomSize % length != 0) {
				writer.write(DNA.substring(genomSize-(genomSize % length), genomSize));	
			}
			
			writer.close();
			
			//GenDatei
			BufferedWriter writer2 = new BufferedWriter(new FileWriter("src/testFiles/GenRandomKS" + kmerSize + "GC" + genCount + ".fa"));
		
			if(kmerSize > genomSize) {
				System.out.println("Genom zu klein fuer kmer");
				writer2.close();
				return;
			}
			
			for(int x = 1; x <= genCount; x++) {
				Random rand = new Random();
				int start = rand.nextInt(genomSize-kmerSize);
				
				writer2.write(">Gen " + x + "\n"
						+ DNA.substring(start, start + kmerSize) + "\n");
			}
			
			writer2.close();
			
		}
		catch(IOException e) {
			System.out.println("IO-Exception: DNA-Randomizer");
		}
		
		System.out.println("DNA-Datei geschrieben!");
		
	}

}
