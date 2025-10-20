package miscTools;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class PlaceWriter {
	
	public static void writeLong(List<Long> places) {
		
		System.out.println("writing...");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Number\n");
		
		for(Long i: places) {
			sb.append(Long.toUnsignedString(i) + "\n");
		}
		
		try(FileChannel channel = FileChannel.open(Paths.get("src/testFiles/PlacesAnalyse.csv"),
													StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)){
			
			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();
			
		}catch(IOException e) {
			System.out.println("IO-Error");
		}
		
	}
	
	public static void writeInt(ArrayList<Integer> places) {
		
		System.out.println("writing...");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Number\n");
		
		for(int i: places) {
			sb.append(i + "\n");
		}
		
		try(FileChannel channel = FileChannel.open(Paths.get("src/testFiles/PlacesAnalyse.csv"),
													StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)){
			
			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();
			
		}catch(IOException e) {
			System.out.println("IO-Error");
		}
		
	}
	
	public static void writeInt(int[] places) {
		
		System.out.println("writing...");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Number\n");
		
		for(int i: places) {
			sb.append(i + "\n");
		}
		
		try(FileChannel channel = FileChannel.open(Paths.get("src/testFiles/PlacesAnalyse.csv"),
													StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)){
			
			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();
			
		}catch(IOException e) {
			System.out.println("IO-Error");
		}
		
	}

}
