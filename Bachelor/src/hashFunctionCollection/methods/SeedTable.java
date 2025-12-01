package hashFunctionCollection.methods;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import app.records.Alphabet;

public class SeedTable {

	/**
	 * Erstellt Seed Table ACHTUNG!!! Beeinflusst direkt den HashWert! Genom und
	 * Gene muessen mit der selben SeedTable gehasht werden!
	 */

	public static void create() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/testFiles/SeedTable.csv"));
			StringBuilder sb = new StringBuilder();
			HashSet<Long> duplicate = new HashSet<>();

			// Erstellen der SeedTable fuer ein Alphabet von A bis Z
			for (char x = 'A'; x <= 'Z'; x++) {

				long tempNumber = random();

				// If-Else, damit zwei verschiedene Buchstaben nicht den selben Long Wert haben
				if (duplicate.isEmpty() || !duplicate.contains(tempNumber)) {
					duplicate.add(tempNumber);
					sb.append(x + "," + tempNumber + "\n");

				} else {
					while (duplicate.contains(tempNumber)) {
						tempNumber = random();
					}
					duplicate.add(tempNumber);
					sb.append(x + "," + tempNumber + "\n");
				}
			}

			writer.write(sb.toString());
			writer.close();

		} catch (IOException e) {
			IO.println("SeedTable konnte nicht erstellt werden");
		}
	}

	/**
	 * Laedt die seedTable.csv und packt sie in eine HashMap
	 * 
	 * @return HashMap
	 */

	public static HashMap<Character, Long> load(Alphabet alphabet) {

		HashMap<Character, Long> seedTable = new HashMap<>();

		seedTable = switch (alphabet) {

		case Alphabet.DNA -> getFile("src/testFiles/SeedTableDir/SeedTableDNA.csv");

		case Alphabet.PROTEIN -> getFile("src/testFiles/SeedTableDir/SeedTableProtein.csv");
		
		case Alphabet.LATIN -> getFile("src/testFiles/SeedTableDir/SeedTableLatin.csv");
		
		default -> new HashMap<>();
		};

		return seedTable;
	}

	private static HashMap<Character, Long> getFile(String pathToFile) {
		HashMap<Character, Long> seedTable = new HashMap<>();

		try {
			File f = new File(pathToFile);
			Scanner sc = new Scanner(f);

			while (sc.hasNext()) {

				StringTokenizer token = new StringTokenizer(sc.next(), ",");
				String Zeichen = token.nextToken();
				String Zahl = token.nextToken();
				seedTable.put(Zeichen.charAt(0), Long.parseLong(Zahl));
			}

			sc.close();
		} catch (IOException e) {
			IO.println("SeedTable konnte nicht geladen werden");
		}

		return seedTable;
	}

	// Erstellt zufaelligen 64-Bit Wert
	private static long random() {

		BitSet random = new BitSet();
		Random coin = new Random();

		for (int counter = 0; counter < 64; counter++) {
			if (coin.nextBoolean()) {
				random.set(counter);
			}
		}

		long[] arr = random.toLongArray();

		return arr[0];
	}

}
