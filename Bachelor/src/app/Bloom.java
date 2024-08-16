package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bloom {
	
	String genom;
	
	public Bloom(String genom) {
		this.genom = genom;
	}
	
	public void magic() {
		
		StringBuilder builder = readFileGenom("src/Genom.fa");
		
		List<String> genes = readFileGenes("src/Gen.fa");
		
		
		HashFunction<Integer> hash = new HashFunction<Integer>(genom);
		hash.setHashFunction(new DummyHashFunction());
		System.out.println(hash.output());
	}
	
	// Einlesen einer FASTA-Datei, welche das Genom enthaelt
	public StringBuilder readFileGenom(String path) {
		
		StringBuilder s = new StringBuilder();
		
		try {
			
			File f = new File(path);
			Scanner myScanner = new Scanner(f);
			myScanner.nextLine();
			
			while (myScanner.hasNext()) {
				String curr = myScanner.nextLine();
				s.append(curr);
			}

			myScanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}

		return s;
	}

	// Einlesen einer FASTA-Datei, welche die Gene enthaelt
	public List<String> readFileGenes(String path){
		
		ArrayList<String> genes = new ArrayList<>();
		
		try {
			File f = new File(path);
			Scanner myScanner = new Scanner(f);
		
			// erste Zeile skippen
			myScanner.next();
			
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
			
			// Letztes Gen hinzufuegen, da es am Ende der Datei kein '>' gibt.
			builder.append(currStr);
			genes.add(builder.toString());
			
			myScanner.close();
		
		} catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}
		return genes;
	}

}
