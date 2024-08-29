package parserCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenFastaParser implements IParser<List<String>>{
	
	// Einlesen einer FASTA-Datei, welche die Gene enthaelt
		public List<String> readFile(String path){
			
			ArrayList<String> genes = new ArrayList<>();
			
			try {
				File f = new File(path);
				Scanner myScanner = new Scanner(f);
			
				// erste Zeile skippen
				myScanner.nextLine();
				
				String currStr = "";
				StringBuilder builder = new StringBuilder();
				
				// Hinzufuegen der Gensequenzen zur LinkedList. Wenn '>' das erste Zeichen ist,
				// wird der String gespeichert in der Liste und ein neuer Begonnen.
				while(myScanner.hasNext()) {
					currStr = myScanner.nextLine();
					
					if(currStr.charAt(0) == '>') {
						genes.add(builder.toString());
						builder.setLength(0);
					}
					else {
						builder.append(currStr);
					}
				}
				genes.add(builder.toString());
				myScanner.close();
			
			} catch(FileNotFoundException e) {
				System.out.println("File not found!");
			}
			return genes;
		}

}
