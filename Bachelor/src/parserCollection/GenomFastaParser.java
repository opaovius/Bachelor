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
import miscTools.Generator.GenGenerator;
import miscTools.TestMethods.TalesToCharConverter;

public class GenomFastaParser implements IParser<ArrayList<ArrayList<Genom>>>{
	
	// Einlesen einer FASTA-Datei, welche das Genom enthaelt
		public ArrayList<ArrayList<Genom>> readFile(String path,Alphabet alphabet) {
			
			return switch(alphabet) {
			
			case Alphabet.TALES -> readTales(path,alphabet);
			
			default -> {
				ArrayList<ArrayList<Genom>> superGenoms = new ArrayList<>();
				ArrayList<Genom> genomes = new ArrayList<>();
				genomes.add(readFasta(path,alphabet));
				superGenoms.add(genomes);
				yield superGenoms;
				}
			};	
		}
		
		private ArrayList<ArrayList<Genom>> readTales(String path, Alphabet alphabet) {
			
			ArrayList<Genom> genomes = new ArrayList<>();
			ArrayList<Genom> toGenGenoms = new ArrayList<>();
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				
				//skip header
				String curr = br.readLine();
				curr = br.readLine();
				
				while(curr != null) {
					
					TalesToCharConverter TC = new TalesToCharConverter();
					String[] arr = curr.split("\t");
					String[] seq = arr[arr.length-1].split(" ");
					String[] name = arr[0].split(" ");
					
					
					char[] conv = TC.convertArray(seq);
					
					toGenGenoms.add(new Genom(name[0],arr[arr.length-1].toCharArray(),alphabet));
					genomes.add(new Genom(name[0], conv, alphabet));
					
					curr = br.readLine();
				}
				br.close();
			}
			catch(IOException e) {
				IO.println("IOException: GenomFastaParser.readTales()");
			}
			return filter(genomes);
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
		
		private static ArrayList<ArrayList<Genom>> filter(ArrayList<Genom> genoms){
			
			ArrayList<ArrayList<Genom>> filteredGenoms = new ArrayList<>();
			ArrayList<Genom> tempList = new ArrayList<>();
			
			
			for(int x = 0; x < genoms.size(); x++) {
				Genom currGenom = genoms.get(x);
				
				if(x+1 < genoms.size()) {
					
					Genom tempGenom = genoms.get(x+1);
					
					//Checken, ob TALXX == TALXX
					if(currGenom.name().substring(3, 4).equals(tempGenom.name().substring(3,4))) {
						tempList.add(currGenom);
					}
					else {
						//Ab 20 werden Genome in die filterListe uebergeben
						if(tempList.size() >= 20) {
							filteredGenoms.add(tempList);
							tempList = new ArrayList<>();
						}
						else {
							tempList = new ArrayList<>();
						}
					}
				}
				else {
					//Ab 20 werden Genome in die filterListe uebergeben
					if(tempList.size() >= 20) {
						filteredGenoms.add(tempList);
						tempList = new ArrayList<>();
					}
				}
				
			}
			
			return filteredGenoms;
		}

}
