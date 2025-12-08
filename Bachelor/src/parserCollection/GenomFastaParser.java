package parserCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import app.records.Alphabet;
import app.records.Genom;

public class GenomFastaParser implements IParser<Genom>{
	
	// Einlesen einer FASTA-Datei, welche das Genom enthaelt
		public Genom readFile(String path,Alphabet alphabet) {
			
			StringBuilder builder = new StringBuilder();
			String name = "PH";
			
			try {
				
				File f = new File(path);
				Scanner myScanner = new Scanner(f);
				
				// Erste Zeile name
				name = myScanner.nextLine();
				
				// Zeile fuer Zeile einlesen
				while (myScanner.hasNext()) {
					String curr = myScanner.nextLine();
					builder.append(curr);
				}

				myScanner.close();

			} catch (FileNotFoundException e) {
				System.out.println("GenomDatei nicht gefunden: " + path);
			}

			return new Genom(name,builder.toString().toCharArray(),alphabet);
		}

}
