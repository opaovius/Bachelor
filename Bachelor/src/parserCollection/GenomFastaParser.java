package parserCollection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import app.records.Alphabet;
import app.records.Genom;
import hashFunctionCollection.methods.NtHash2LookupTable;
import miscTools.TestMethods.ScanTales;
import miscTools.TestMethods.TalesToCharConverter;

public class GenomFastaParser implements IParser<ArrayList<Genom>>{
	
	// Einlesen einer FASTA-Datei, welche das Genom enthaelt
		public ArrayList<Genom> readFile(String path,Alphabet alphabet) {
			
			return switch(alphabet) {
			
			case Alphabet.TALES -> readTales(path,alphabet);
			
			default -> {
				ArrayList<Genom> genomes = new ArrayList<>();
				genomes.add(readFasta(path,alphabet));
				yield genomes;
				}
			};	
		}
		
		private ArrayList<Genom> readTales(String path, Alphabet alphabet) {
			
			ArrayList<Genom> genomes = new ArrayList<>();
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				
				//skip header
				String curr = br.readLine();
				curr = br.readLine();
				
				while(curr != null) {
					
					TalesToCharConverter TC = new TalesToCharConverter();
					String[] arr = curr.split("\t");
					String[] seq = arr[arr.length-1].split(" ");
					
					
					char[] conv = TC.convertArray(seq);
					
					genomes.add(new Genom(arr[0], conv, alphabet));
					
					curr = br.readLine();
				}
				br.close();
			}
			catch(IOException e) {
				IO.println("IOException: GenomFastaParser.readTales()");
			}
			return genomes;
		}
		
		private Genom readFasta(String path,Alphabet alphabet) {
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
