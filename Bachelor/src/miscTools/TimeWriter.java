package miscTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TimeWriter {
	
	public static void writeTime(List<List<Long>> time, int testCount) {
		
		try {
			BufferedWriter writer = new BufferedWriter( new FileWriter("src/testFiles/time.csv"));
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

}
