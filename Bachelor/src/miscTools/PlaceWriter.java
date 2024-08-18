package miscTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PlaceWriter {
	
	public static void write(List<Double> places) {
		
		try {
			 BufferedWriter writer = new BufferedWriter(new FileWriter("PlacesAnalyse.csv"));
			 System.out.println("hi");
			 writer.write("Index,Number");
			 writer.write("\n");
			 
			 int count = 1;
			 
			 for(int x = 0; x < places.size(); x++) {
				 writer.write(count + "," + places.get(x).intValue());
				 writer.write("\n");
			 }
			 
			 writer.close();
		}catch(IOException e) {
			System.out.println("IO-Error");
		}
		
	}

}
