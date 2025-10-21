package hashFunctionCollection.methods;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class NtHash2LookupTable {
	
	//Erezeugen einer LookupTable
	public static void create(int kmerSize) {
		
		HashMap<Character,Long> seed = SeedTable.load();
		StringBuilder sb = new StringBuilder();
		char[] chars = {'A','C','G','T','N'};
		
		//Jeden Buchstaben durchgehen
		for(char c: chars) {
			
			long curr = seed.get(c);
			
			//k mal rollen um die einzelnen Long Werte zu erzeugen
			for(int x = 0; x < kmerSize; x++) {
				curr = srol(curr);
				sb.append(c + "," + x + "," + curr + "\n");
			}
		}
		
		//Speichern in Datei
		write(sb);
		
	}
	
	//Laden der LookupTable
	public static long[][] load(){
		
		//[5] ,da A,C,G,T,N und [30] ,da 30 mal srol(), basierend auf kmerSize
		long[][] lookup = new long[5][30];
		
		try {
			File f = new File("src/testFiles/LookupTable.csv");
			Scanner sc = new Scanner(f);
			
			while(sc.hasNext()) {
				
				StringTokenizer token = new StringTokenizer(sc.next(),",");
				String Zeichen = token.nextToken();
				String Pos = token.nextToken();
				String Zahl = token.nextToken();
				
				switch (Zeichen) {
				case "A":
					lookup[0][Integer.parseInt(Pos)] = Long.parseLong(Zahl);
					break;
				case "C":
					lookup[1][Integer.parseInt(Pos)] = Long.parseLong(Zahl);
					break;
				case "G":
					lookup[2][Integer.parseInt(Pos)] = Long.parseLong(Zahl);
					break;
				case "T":
					lookup[3][Integer.parseInt(Pos)] = Long.parseLong(Zahl);
					break;
				case "N":
					lookup[4][Integer.parseInt(Pos)] = Long.parseLong(Zahl);
					break;
				default:
					IO.println("Fehler beim Laden der LookupTable: Zeichen nicht gefunden");
					break;
				}
			}
			
			sc.close();
		}catch(IOException e) {
			IO.println("LookupTable konnte nicht geladen werden");
		}
		
		return lookup;
	}
	
	
	private static long srol(long curr) {
		
		//Bit 0 mit Bit 32 und Bit 33 mit Bit 63 tauschen und dann shiften
		long m = ((curr & 0x8000000000000000L) >>> 30) | ((curr & 0x100000000L) >>> 32) ;
		
		return ((curr << 1) & 0xFFFFFFFDFFFFFFFFL) | m;
	}

	private static void write(StringBuilder sb) {

		try (FileChannel channel = FileChannel.open(Paths.get("src/testFiles/LookupTable.csv"),
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
