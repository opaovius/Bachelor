package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenomFastaParser implements IParser<StringBuilder>{
	
	// Einlesen einer FASTA-Datei, welche das Genom enthaelt
		public StringBuilder readFile(String path) {
			
			StringBuilder builder = new StringBuilder();
			
			try {
				
				File f = new File(path);
				Scanner myScanner = new Scanner(f);
				
				// Erste Zeile ignorieren
				myScanner.nextLine();
				
				// Zeile fuer Zeile einlesen
				while (myScanner.hasNext()) {
					String curr = myScanner.nextLine();
					builder.append(curr);
				}

				myScanner.close();

			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}

			return builder;
		}

}
