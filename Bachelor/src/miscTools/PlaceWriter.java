package miscTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class PlaceWriter {
	
	public static void write(List<BigInteger> places) {
		
		try {
			 BufferedWriter writer = new BufferedWriter(new FileWriter("src/PlacesAnalyse.csv"));
			 
			 writer.write("Index,Number");
			 writer.write("\n");
			 
			 int count = 1;
			 
			 for(int x = 0; x < places.size(); x++) {
				 //String i = String.valueOf(places.get(x).intValue());
				 
				 writer.write(count + "," + places.get(x).intValue());
				 writer.write("\n");
				 count++;
			 }
			 
			 writer.close();
		}catch(IOException e) {
			System.out.println("IO-Error");
		}
		
	}

}
