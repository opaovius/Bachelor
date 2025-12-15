package hashFunctionCollection.methods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/testFiles/SeedTableDir/SeedTable.csv"));
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

	public static void createTales() {

		ArrayList<String> tokens = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader("src/testFiles/SeedTableDir/TalesCharsConv.tsv"));

			String curr = br.readLine();

			while (curr != null) {
				String[] arr = curr.split("\t");
				tokens.add(arr[1]);
				curr = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			IO.println("IOException: SeedTable.createTales.Reader");
		}

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/testFiles/SeedTableDir/SeedTableTALES.tsv"));
			StringBuilder sb = new StringBuilder("");
			HashSet<Long> numbs = new HashSet<>();

			for (String s : tokens) {

				long temp = random();

				while (numbs.contains(temp)) {
					temp = random();
				}

				numbs.add(temp);

				sb.append(s + "\t" + temp + "\n");
			}

			bw.write(sb.toString());

			bw.close();
		} catch (IOException e) {
			IO.println("IOException: SeedTable.createTable.Writer");
		}
	}

	/**
	 * Laedt die seedTable.csv und packt sie in eine HashMap
	 * 
	 * @return HashMap
	 */

	public static HashMap<Character, Long> load(Alphabet alphabet) {

		HashMap<Character, Long> seedTable = new HashMap<>();

		String pathToFile = "";

		switch (alphabet) {

		case Alphabet.TALES -> pathToFile = "src/testFiles/SeedTableDir/SeedTable" + alphabet.toString() + ".tsv";

		default -> pathToFile = "src/testFiles/SeedTableDir/SeedTable" + alphabet.toString() + ".csv";

		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(pathToFile));
			String curr = br.readLine();
			
			while (curr != null) {

				switch (alphabet) {

				case Alphabet.TALES -> {
					String[] arr = curr.split("\t");
					seedTable.put(arr[0].charAt(0), Long.parseLong(arr[1]));
				}

				default -> {
					String[] arr = curr.split(",");
					seedTable.put(arr[0].charAt(0), Long.parseLong(arr[1]));
				}

				}
				curr = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			IO.println("SeedTable.load() -> SeedTable konnte nicht geladen werden");
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
