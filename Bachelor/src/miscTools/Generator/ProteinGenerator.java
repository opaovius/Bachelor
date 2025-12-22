package miscTools.Generator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import app.records.Gen;
import app.records.Genom;

public class ProteinGenerator {

	/**
	 * Wandelt ein DNA-Genom in ein Preotein-Genom um. Dabei enstehen 6 Sequenzen
	 * aus den 3 Leserahmen * 2 Straenge.
	 * 
	 * @param genom : umzuwandelndes Genom
	 * @param start : Index fuer den Start des Leserahmens
	 */

	public static void generate(Genom genom) {

		int seqLen = genom.sequence().length;

		int len = switch (seqLen) {
		case 0 -> 0;
		case 1 -> 0;
		case 2 -> 0;
		case 3 -> 1;
		case 4 -> 2;
		default -> 3;
		};

		// Drei Leserahmen in 5' nach 3' Richtung
		for (int x = 0; x < len; x++) {
			toProtein(genom, x, false);
		}

		// Drei Leserahmen in 3' nach 5' Richtung
		for (int x = 0; x < len; x++) {
			toProtein(genom, x, true);
		}

	}

	public static void generate(ArrayList<Gen> genes) {

		for (Gen g : genes) {
			toProtein(g);
		}

	}

	private static void toProtein(Gen g) {

		char[] seq = g.sequence();
		StringBuilder tripplet = new StringBuilder("");
		StringBuilder protein = new StringBuilder(seq.length / 2);

		protein.append(g.name() + "\n");

		for (int x = 0; x < seq.length - 2; x = x + 3) {
			tripplet.append(seq[x]);
			tripplet.append(seq[x + 1]);
			tripplet.append(seq[x + 2]);

			protein.append(convertTripplet(tripplet.toString()));
			tripplet.setLength(0);
		}

		protein.append("\n");

		write(protein);
	}

	private static void toProtein(Genom genom, int start, boolean complement) {

		char[] seq = genom.sequence();
		StringBuilder tripplet = new StringBuilder("");
		StringBuilder protein = new StringBuilder(seq.length / 2);
		StringBuilder tempID = new StringBuilder("");

		protein.append(genom.name());
		tempID.append("EColi_");

		if (complement) {
			protein.append(" |Complement");
			tempID.append("COMP_");
		}

		protein.append("|Leserahmen: " + start + "\n");
		tempID.append("LR_" + start);

		// Wird genutzt um Dateinamen leichter zu schreiben.
		String id = tempID.toString();

		if (complement) {
			for (int x = seq.length - start - 1; x >= 2; x -= 3) {

				tripplet.append(convertBase(seq[x]));
				tripplet.append(convertBase(seq[x - 1]));
				tripplet.append(convertBase(seq[x - 2]));

				protein.append(convertTripplet(tripplet.toString()));
				tripplet.setLength(0);
			}
			write(protein, id);
		} else {
			for (int x = 0 + start; x < seq.length - 2; x = x + 3) {
				tripplet.append(seq[x]);
				tripplet.append(seq[x + 1]);
				tripplet.append(seq[x + 2]);

				protein.append(convertTripplet(tripplet.toString()));
				tripplet.setLength(0);
			}
			write(protein, id);
		}
	}

	private static String convertTripplet(String trip) {

		StringBuilder protein = new StringBuilder(3);

		// klassische codesonne als switch
		switch (trip) {

		case "AAA", "AAG" -> protein.append('K');

		case "AAC", "AAT" -> protein.append('N');

		case "ACG", "ACA", "ACT", "ACC" -> protein.append('T');

		case "ATG" -> protein.append('M');

		case "ATA", "ATC", "ATT" -> protein.append('I');

		case "CGA", "CGC", "CGG", "CGT", "AGG", "AGA" -> protein.append('R');

		case "CAA", "CAG" -> protein.append('Q');

		case "CAC", "CAT" -> protein.append('H');

		case "CCA", "CCC", "CCG", "CCT" -> protein.append('P');

		case "CTA", "CTC", "CTG", "CTT", "TTA", "TTG" -> protein.append('L');

		case "TGG" -> protein.append('W');

		case "TGA", "TAG", "TAA" -> protein.append('*');

		case "TGC", "TGT" -> protein.append('C');

		case "TAC", "TAT" -> protein.append('Y');

		case "TCA", "TCC", "TCG", "TCT", "AGC", "AGT" -> protein.append('S');

		case "TTT", "TTC" -> protein.append('F');

		case "GGA", "GGC", "GGG", "GGT" -> protein.append('G');

		case "GAA", "GAG" -> protein.append('E');

		case "GAC", "GAT" -> protein.append('D');

		case "GCA", "GCC", "GCG", "GCT" -> protein.append('A');

		case "GTA", "GTC", "GTG", "GTT" -> protein.append('V');

		default -> protein.append('X');

		}
		return protein.toString();
	}

	private static void write(StringBuilder sb, String id) {

		try (FileChannel channel = FileChannel.open(Paths.get("src/testFiles/GenomDir/" + id + ".fa"),
				StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {

			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();

		} catch (IOException e) {
			System.out.println("IO-Error");
		}

	}

	private static void write(StringBuilder sb) {

		try (FileChannel channel = FileChannel.open(Paths.get("src/testFiles/GenDir/EColiGenePROTEIN.fa"),
				StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {

			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();

		} catch (IOException e) {
			System.out.println("IO-Error");
		}

	}

	private static char convertBase(char c) {
		return switch (c) {
		case 'A' -> 'T';
		case 'T' -> 'A';
		case 'C' -> 'G';
		case 'G' -> 'C';
		default -> 'N';
		};
	}

}