package hashFunctionCollection.methods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import app.records.Alphabet;

public class NtHash2LookupTable {

	/**
	 * Erzeugt eine Lookup Table fuer NtHash2
	 * 
	 * @param kmerSize : Groesse der Kmere
	 */

	public static void create(int kmerSize, Alphabet alphabet) {

		HashMap<Character, Long> seed = SeedTable.load(alphabet);
		StringBuilder sb = new StringBuilder();

		ArrayList<String> letters = getChars(alphabet);

		// Jeden Buchstaben durchgehen
		for (String s : letters) {

			long curr = seed.get(s.charAt(0));

			// k mal rollen um die einzelnen Long Werte zu erzeugen
			for (int x = 0; x < kmerSize; x++) {

				switch (alphabet) {

				case Alphabet.TALES -> {
					curr = srol(curr);
					sb.append(s + "\t" + x + "\t" + curr + "\n");
				}

				default -> {
					curr = srol(curr);
					sb.append(s + "," + x + "," + curr + "\n");
				}

				}

			}
		}

		// Speichern in Datei
		write(sb, alphabet);

	}

	/**
	 * Laed die Lookup Table
	 * 
	 * @return long[][]
	 */

	public static HashMap<Character, HashMap<Integer, Long>> load(Alphabet alphabet, int kmerSize) {

		HashMap<Character, HashMap<Integer, Long>> look = new HashMap<>();
		HashMap<Integer, Long> temp = new HashMap<>();

		try {
			
			String path = "";
			
			switch(alphabet) {
			
			case Alphabet.TALES -> path = "src/testFiles/LookUpTableDir/LookupTable" + alphabet.toString() + ".tsv";
			
			default -> path = "src/testFiles/LookUpTableDir/LookupTable" + alphabet.toString() + ".csv";
			}
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String curr = br.readLine();
			
			while(curr != null) {
				
				switch(alphabet) {
				
				case Alphabet.TALES -> {
					String[] arr = curr.split("\t");
					Character Zeichen = arr[0].charAt(0);
					int Pos = Integer.parseInt(arr[1]);
					long Zahl = Long.parseLong(arr[2]);
					
					if (Pos == 29) {
						temp.put(Pos, Zahl);
						look.put(Zeichen, temp);
						temp = new HashMap<>();
					} else {
						temp.put(Pos, Zahl);
					}
				}
				
				default -> {
					String[] arr = curr.split(",");
					Character Zeichen = arr[0].charAt(0);
					int Pos = Integer.parseInt(arr[1]);
					long Zahl = Long.parseLong(arr[2]);
					
					if (Pos == 29) {
						temp.put(Pos, Zahl);
						look.put(Zeichen, temp);
						temp = new HashMap<>();
					} else {
						temp.put(Pos, Zahl);
					}
				}
				
				}
			
				
				curr = br.readLine();
			}
			
			br.close();
		} catch (IOException e) {
			IO.println("LookupTable konnte nicht geladen werden: NtHash2LookUp.load()");
		}

		return look;
	}

	private static long srol(long curr) {

		// Bit 0 mit Bit 32 und Bit 33 mit Bit 63 tauschen und dann shiften
		long m = ((curr & 0x8000000000000000L) >>> 30) | ((curr & 0x100000000L) >>> 32);

		return ((curr << 1) & 0xFFFFFFFDFFFFFFFFL) | m;
	}

	private static void write(StringBuilder sb, Alphabet alphabet) {

		try {
			
			String path = "";
			
			switch(alphabet) {
			
			case Alphabet.TALES -> path = "src/testFiles/LookUpTableDir/LookupTable" + alphabet.toString() + ".tsv";
			
			default -> path = "src/testFiles/LookUpTableDir/LookupTable" + alphabet.toString() + ".csv";
			
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));

			bw.write(sb.toString());
			
			bw.close();
		} catch (IOException e) {
			System.out.println("IO-Error: NtHash2LookUpTable.write()");
		}

	}

	private static ArrayList<String> getChars(Alphabet alphabet) {

		ArrayList<String> letters = new ArrayList<>();
		String path = "";

		switch (alphabet) {

		case Alphabet.TALES -> path = "src/testFiles/SeedTableDir/SeedTable" + alphabet.toString() + ".tsv";

		default -> path = "src/testFiles/SeedTableDir/SeedTable" + alphabet.toString() + ".csv";

		}

		try {

			BufferedReader br = new BufferedReader(new FileReader(path));

			String curr = br.readLine();

			while (curr != null) {

				switch (alphabet) {
				case Alphabet.TALES -> {
					String[] arr = curr.split("\t");
					letters.add(arr[0]);
				}
				default -> {
					String[] arr = curr.split(",");
					letters.add(arr[0]);
				}
				}
				curr = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			IO.println("SeedTable konnt nicht geladen werden: NtHash2LookUpTable.getChars()");
		}

		if (letters.size() < 1)
			IO.println("ERROR: NtHash2LookUpTable.java -> getChars()");

		return letters;
	}

}
