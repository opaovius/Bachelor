package miscTools;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PlaceWriter {
	
	public static void write(List<Long> places) {
		
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
			
		}catch(IOException e) {
			System.out.println("IO-Error");
		}
		
	}

}
