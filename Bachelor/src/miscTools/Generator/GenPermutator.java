package miscTools.Generator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;

import app.records.Gen;

public class GenPermutator {
	
	//Permutiert neue Gene aus den alten
	public static void permutate(ArrayList<Gen> oldGenList) {
		
		ArrayList<Gen> newGenList = new ArrayList<>();
		
		for(Gen oldGen: oldGenList) {
			newGenList.add(new Gen(oldGen.name() + " Permutated", mix(oldGen.sequence())));
		}
		
		write(newGenList);
		
	}
	
	//Speichern der neuen Gene in einer neuen Datei
	private static void write(ArrayList<Gen> genList) {
		
		StringBuilder sb = new StringBuilder();
		
		for(Gen g: genList) {
			sb.append(">" + g.name() + "\n" + new String(g.sequence()) + "\n");
		}
		
		try (FileChannel channel = FileChannel.open(Paths.get("src/testFiles/GenDir/EColiGenePermutated.fa"),
				StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {

			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();

		} catch (IOException e) {
			System.out.println("IO-Error");
		}
		
	}
	
	//Mischt eine neue Sequenze aus der alten zusammen
	private static char[] mix(char[] seq) {
		
		StringBuilder sbNew = new StringBuilder(seq.length);
		StringBuilder sbOld = new StringBuilder(new String(seq));
		Random rand = new Random();
		
		while(sbOld.length() > 0) {
			int index = rand.nextInt(sbOld.length());
			char currChar = sbOld.charAt(index);
			sbNew.append(currChar);
			sbOld.deleteCharAt(index);
		}
		return sbNew.toString().toCharArray();
	}

}
