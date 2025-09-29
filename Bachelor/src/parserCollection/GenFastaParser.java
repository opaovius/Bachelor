package parserCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.Gen;

public class GenFastaParser implements IParser<ArrayList<Gen>>{
	
	// Einlesen einer FASTA-Datei, welche die Gene enthaelt
		public ArrayList<Gen> readFile(String path){
			
			ArrayList<Gen> genList = new ArrayList<>();
			
			try {
				File f = new File(path);
				Scanner myScanner = new Scanner(f);
				
				String currStr = myScanner.nextLine();
				StringBuilder builder = new StringBuilder();
				String genID = currStr.substring(currStr.indexOf("|") + 1, currStr.indexOf(" "));
				ArrayList<String> numberList = new ArrayList<>();
				
				//Erste Zeile erster Durchlauf
				for(int x = 0; x < currStr.length() - 9; x++) {
					
					if(currStr.substring(x, x + 9).equals("location=")) {

						String numberString = currStr.substring(x);
						Pattern numberPattern = Pattern.compile("\\d+");
						Matcher numberMatcher = numberPattern.matcher(numberString);
						
						while(numberMatcher.find()) {
							numberList.add(numberMatcher.group());
						}
						break;
					}
					
				}
				
				// Hinzufuegen der Gensequenzen zur LinkedList. Wenn '>' das erste Zeichen ist,
				// wird der String gespeichert in der Liste und ein neuer Begonnen.
				while(myScanner.hasNext()) {
					currStr = myScanner.nextLine();
					
					if(currStr.charAt(0) == '>') {
						
						//Fuer normales Gen ohne Join
						if(numberList.size() == 2) {
							
							genList.add(new Gen(genID
									,builder.toString()
									,Integer.parseInt(numberList.get(0))
									,Integer.parseInt(numberList.get(1))));
							
						}
						//Fuer Location=Join, also wenn ein Gen zweigeteilt ist
						else if(numberList.size() == 4) {
							
							int seqEndOne = Integer.parseInt(numberList.get(1)) - Integer.parseInt(numberList.get(0));
							
							genList.add(new Gen(genID + "_part_1"
									,builder.toString().substring(0, seqEndOne + 1)
									,Integer.parseInt(numberList.get(0))
									,Integer.parseInt(numberList.get(1))));
							
							genList.add(new Gen(genID + "_part_2"
									,builder.toString().substring(seqEndOne + 1)
									,Integer.parseInt(numberList.get(2))
									,Integer.parseInt(numberList.get(3))));
							
						}
						else {
							if(numberList.size() != 0) {
								IO.println("Fehler bei GenFastaParser: zu viele Int bei einem Gen");
							}
						}
						
						//Naechste GenID
						genID = currStr.substring(currStr.indexOf("|") + 1, currStr.indexOf(" "));
						
						//location= suchen
						for(int x = 0; x < currStr.length() - 9; x++) {
							
							if(currStr.substring(x, x + 9).equals("location=")) {
								
								//Suchen nach Zahlen hinter Location
								String numberString = currStr.substring(x);
								Pattern numberPattern = Pattern.compile("\\d+");
								Matcher numberMatcher = numberPattern.matcher(numberString);
								numberList = new ArrayList<>();
								
								while(numberMatcher.find()) {
									numberList.add(numberMatcher.group());
								}
										
								break;
							}
							
						}
						
						//StringBuilder zuruecksetzen
						builder.setLength(0);
					}
					else {
						builder.append(currStr);
					}
				}
				myScanner.close();
			
			} catch(FileNotFoundException e) {
				System.out.println("File not found!");
			}
			
			return genList;
		}

}
