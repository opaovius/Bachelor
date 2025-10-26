package miscTools.FileWriters;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class GenCheckWriter {

	public static void write(StringBuilder sb) {

		try (FileChannel channel = FileChannel.open(Paths.get("src/testFiles/AnalyseDir/GenCheck.csv"),
				StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {

			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();

		} catch (IOException e) {
			System.out.println("IO-Error");
		}

	}

}
