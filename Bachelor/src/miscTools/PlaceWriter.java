package miscTools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PlaceWriter {
	
	public static void write(List<Integer> places) {
		
		System.out.println("writing...");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Number\n");
		
		for(Integer i: places) {
			sb.append(i + "\n");
		}
		
		long start = System.currentTimeMillis();
		/*
		try {
			 BufferedWriter writer = new BufferedWriter(new FileWriter("src/testFiles/PlacesAnalyse.csv"));
			 
			 writer.write(sb.toString());
			 
			 writer.close();
		}catch(IOException e) {
			System.out.println("IO-Error");
		}
		
		System.out.println("normal: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		*/
		try(FileChannel channel = FileChannel.open(Paths.get("src/testFiles/PlacesAnalyse.csv"),
													StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)){
			
			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			
		}catch(IOException e) {
			System.out.println("IO-Error");
		}
		
		System.out.println("FileChannel: " + (System.currentTimeMillis() - start));
		
	}

}
