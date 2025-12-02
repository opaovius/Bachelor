package hashFunctionCollection.methods;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
				curr = srol(curr);
				sb.append(s + "," + x + "," + curr + "\n");
			}
		}

		// Speichern in Datei
		write(sb,alphabet);

	}

	/**
	 * Laed die Lookup Table
	 * 
	 * @return long[][]
	 */

	public static HashMap<String,HashMap<Integer,Long>> load(Alphabet alphabet,int kmerSize) {
		
		HashMap<String,HashMap<Integer,Long>> look = new HashMap<>();
		HashMap<Integer,Long> temp = new HashMap<>();
		
		try {
			File f = new File("src/testFiles/LookUpTableDir/LookupTable" + alphabet.toString() + ".csv");
			Scanner sc = new Scanner(f);

			while (sc.hasNext()) {

				StringTokenizer token = new StringTokenizer(sc.next(), ",");
				String Zeichen = token.nextToken();
				int Pos = Integer.parseInt(token.nextToken());
				long Zahl = Long.parseLong(token.nextToken());
				
				if(Pos == 29) {
					temp.put(Pos,Zahl);
					look.put(Zeichen, temp);
					temp = new HashMap<>();
				}
				else {
					temp.put(Pos,Zahl);
				}
			}

			sc.close();
		} catch (IOException e) {
			IO.println("LookupTable konnte nicht geladen werden");
		}

		return look;
	}

	private static long srol(long curr) {

		// Bit 0 mit Bit 32 und Bit 33 mit Bit 63 tauschen und dann shiften
		long m = ((curr & 0x8000000000000000L) >>> 30) | ((curr & 0x100000000L) >>> 32);

		return ((curr << 1) & 0xFFFFFFFDFFFFFFFFL) | m;
	}

	private static void write(StringBuilder sb, Alphabet alphabet) {

		try (FileChannel channel = FileChannel.open(Paths.get("src/testFiles/LookUpTableDir/LookupTable" + alphabet.toString() +".csv"),
				StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {

			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();

		} catch (IOException e) {
			System.out.println("IO-Error");
		}

	}

	private static ArrayList<String> getChars(Alphabet alphabet) {
		
		ArrayList<String> letters = new ArrayList<>();

		String path = "src/testFiles/SeedTableDir/SeedTable" + alphabet.toString() + ".csv";
		
		try {
			File f = new File(path);
			Scanner sc = new Scanner(f);

			while (sc.hasNext()) {
				StringTokenizer token = new StringTokenizer(sc.next(), ",");
				String Zeichen = token.nextToken();
				letters.add(Zeichen);
			}

			sc.close();
		} catch (IOException e) {
			IO.println("SeedTable konnte nicht geladen werden");
		}

		if (letters.size() < 1)
			IO.println("ERROR: NtHash2LookUpTable.java -> getChars()");

		return letters;
	}

}
