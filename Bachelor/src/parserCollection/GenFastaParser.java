package parserCollection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import app.records.Alphabet;
import app.records.Gen;
import miscTools.TestMethods.TalesToCharConverter;

public class GenFastaParser implements IParser<ArrayList<Gen>>{

	// Einlesen einer FASTA-Datei, welche die Gene enthaelt
	public ArrayList<Gen> readFile(String path,Alphabet alphabet) {

		return switch(alphabet) {
		
		case Alphabet.TALES -> readTales(path,alphabet);
		
		default -> readFasta(path,alphabet);
		};
	}
	
	private static ArrayList<Gen> readTales(String path,Alphabet alphabet){
		
		ArrayList<Gen> genList = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String curr = br.readLine();
		
			while(curr != null) {
				
				TalesToCharConverter TC = new TalesToCharConverter();
				String[] arr = curr.split("\t");
				String[] seq = arr[arr.length-1].split(" ");
				char[] conv = TC.convertArray(seq);
				
				genList.add(new Gen(arr[0],conv,alphabet));
				
				
				curr = br.readLine();
			}
			
			br.close();
		}
		catch(IOException e) {
			IO.println("IOException: GenFastaParser.readTales()");
		}
		
		return genList;
	}
	
	private static ArrayList<Gen> readFasta(String path, Alphabet alphabet){
		
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
			genList.add(new Gen(genName,sb.toString().toCharArray(),alphabet));

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}

		return genList;
	}

}
