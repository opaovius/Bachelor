package miscTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TimeWriter {
	
	public static void writeTime(ArrayList<ArrayList<Long>> time, int testCount) {
		
		try {
			BufferedWriter writer = new BufferedWriter( new FileWriter("src/testFiles/AnalyseDir/time.csv"));
			StringBuilder builder = new StringBuilder();
			
			writer.write("Versuch,Ebola\n");
			//writer.write("Versuch,10_000,100_000,1_000_000\n");
			for(int x = 0; x < testCount; x++) {
				
				builder.append((x+1) + ",");
				
				for(int test = 0; test < time.size(); test++) {
					builder.append(time.get(test).get(x));
					
					if(test < time.size() - 1) {
						builder.append(",");
					}
					
				}
				builder.append("\n");
				writer.write(builder.toString());
				builder.setLength(0);
			}
			
			writer.close();
			System.out.println("Time-Datei erstellt!");
		} catch(IOException e) {
			System.out.println("TimeWriter IO-Fehler");
		}
		
	}
	
	public static void writeTime(long[] time, int testCount) {
		
		try {
			BufferedWriter writer = new BufferedWriter( new FileWriter("src/testFiles/AnalyseDir/time.csv"));
			StringBuilder builder = new StringBuilder();
			
			writer.write("Versuch,Ecoli\n");
			//writer.write("Versuch,10_000,100_000,1_000_000\n");
			for(int x = 0; x < testCount; x++) {
				builder.append((x+1) + "," + time[x] + "\n");	
			}
			
			writer.write(builder.toString());
			writer.close();
			System.out.println("Time-Datei erstellt!");
		} catch(IOException e) {
			System.out.println("TimeWriter IO-Fehler");
		}
		
	}

}
