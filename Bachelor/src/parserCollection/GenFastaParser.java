package parserCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import app.records.Alphabet;
import app.records.Gen;

public class GenFastaParser implements IParser<ArrayList<Gen>>{

	// Einlesen einer FASTA-Datei, welche die Gene enthaelt
	public ArrayList<Gen> readFile(String path,Alphabet alphabet) {

		ArrayList<Gen> genList = new ArrayList<>();

		try {
			File f = new File(path);
			Scanner myScanner = new Scanner(f);

			StringBuilder sb = new StringBuilder();
			String genName = "";

			while (myScanner.hasNext()) {

				String currStr = myScanner.nextLine();

				if (currStr.charAt(0) == '>') {
					
					//Altes Gen abspeichern
					if(genName.length() > 0) {
						genList.add(new Gen(genName,sb.toString().toCharArray(),alphabet));
						sb.setLength(0);
					}
					
					//Neues Gen beginnen
					genName = currStr.substring(1, currStr.indexOf(" "));
				}
				else {
					//Sequenz in StringBuilder schreiben
					sb.append(currStr);
				}

			}

			myScanner.close();
			
			//Letztes Gen sichern
			genList.add(new Gen(genName,sb.toString().toCharArray()));

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}

		return genList;
	}

}
