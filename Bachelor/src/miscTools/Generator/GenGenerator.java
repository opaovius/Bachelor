package miscTools.Generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.random.RandomGenerator;

import app.records.Alphabet;
import app.records.Gen;
import app.records.Genom;

public class GenGenerator {

	// Permutiert neue Gene aus den alten
	public static void permutate(ArrayList<Gen> oldGenList, Alphabet alphabet) {

		ArrayList<Gen> newGenList = new ArrayList<>();

		for (Gen oldGen : oldGenList) {
			newGenList.add(new Gen(oldGen.name() + " Permutated", mix(oldGen.sequence()), alphabet));
		}

		write(newGenList);

	}

	public static void generateTales(ArrayList<Genom> genoms) {

		IO.println(genoms.size());
		
		genoms = filter(genoms);
		
		IO.println(genoms.size());
		
		HashSet<Integer> rolledGenoms = new HashSet<>();
		int min = 0;
		int max = genoms.size();
		StringBuilder sb = new StringBuilder("");
		
		for(int x = 0; x < 500; x++) {
		
			int randomGenom = RandomGenerator.getDefault().nextInt(min, max);
		
			while(rolledGenoms.contains(randomGenom) || genoms.get(randomGenom).sequence().length < 30) {
				randomGenom = RandomGenerator.getDefault().nextInt(min, max);
			}
			
			rolledGenoms.add(randomGenom);
			
			int start = RandomGenerator.getDefault().nextInt(0, 5);
			
			start = start*3;
			
			while(start + 30 > genoms.get(randomGenom).sequence().length) {
				start = RandomGenerator.getDefault().nextInt(0, 5);
				start = start*3;
			}
			
			char[] newSeq = new char[30];
			char[] oldSeq = genoms.get(randomGenom).sequence();
			
			for(int y = 0; y < 30; y++) {
				newSeq[y] = oldSeq[start+y];
			}
			
			sb.append("TALPH1\t" + (new String(newSeq)) + "\n");
			IO.println(x);
		}
		
		writeTales(sb.toString());
	}
	
	private static void writeTales(String s) {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/testFiles/GenDir/TalesPermutated.tsv"));
			bw.write(s);
			bw.close();
		}
		catch(IOException e) {
			IO.println("IOEXception: GenGenerator.writeTales()");
		}
		
	}

	// Speichern der neuen Gene in einer neuen Datei
	private static void write(ArrayList<Gen> genList) {

		StringBuilder sb = new StringBuilder();

		for (Gen g : genList) {
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

	// Mischt eine neue Sequenze aus der alten zusammen
	private static char[] mix(char[] seq) {

		StringBuilder sbNew = new StringBuilder(seq.length);
		StringBuilder sbOld = new StringBuilder(new String(seq));
		Random rand = new Random();

		while (sbOld.length() > 0) {
			int index = rand.nextInt(sbOld.length());
			char currChar = sbOld.charAt(index);
			sbNew.append(currChar);
			sbOld.deleteCharAt(index);
		}
		return sbNew.toString().toCharArray();
	}

	private static ArrayList<Genom> filter(ArrayList<Genom> genoms) {

		ArrayList<Genom> filteredGenoms = new ArrayList<>();
		ArrayList<Genom> tempList = new ArrayList<>();

		for (int x = 0; x < genoms.size(); x++) {
			Genom currGenom = genoms.get(x);

			if (x + 1 < genoms.size()) {

				Genom tempGenom = genoms.get(x + 1);

				// Checken, ob TALXX == TALXX
				if (currGenom.name().substring(3, 4).equals(tempGenom.name().substring(3, 4))) {
					tempList.add(currGenom);
				} else {
					// Ab 20 werden Genome in die filterListe uebergeben
					if (tempList.size() >= 20) {
						
						for(Genom g : tempList) {
							filteredGenoms.add(g);
						}
						
						tempList = new ArrayList<>();
					} else {
						tempList = new ArrayList<>();
					}
				}
			} else {
				// Ab 20 werden Genome in die filterListe uebergeben
				if (tempList.size() >= 20) {
					for(Genom g : tempList) {
						filteredGenoms.add(g);
					}
					tempList = new ArrayList<>();
				}
			}

		}

		return filteredGenoms;
	}

}
