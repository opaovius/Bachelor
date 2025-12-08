package miscTools.TestMethods;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.BitSet;

import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import hashFunctionCollection.variants.RabinFingerprint;
import hashFunctionCollection.variants.TwoBitHashTest;
import miscTools.FileWriters.PlaceWriter;

public class TestPipeline {
	
	//Test fuer mehrere kombinierte Hashfunktionen
	public static void test(ArrayList<int[]> intList, char[] seq, int kmerSize, ArrayList<HashFunction> hfList, BitSet bitS) {
		
		int[] k1 = intList.get(0);
		
		IO.println(seq.length - kmerSize + 1);
		IO.println(k1.length);
		
		PlaceWriter.writeInt(k1);
		
		int cap = BoundedInteger.calculateCap(seq.length);
		
		IO.println("cap size: " + ((1 << cap) - 1));
		
		IO.println(intList.size());
		IO.println("BitS Size: " + bitS.size());
		IO.println("BitS Card: " + bitS.cardinality());
		IO.println("Rate BitS: " + (Math.round((100.0/bitS.size()*bitS.cardinality())* 100000.0) / 100000.0) + "%");
		
	}
	
	public static void bitTest(BitSet bitS) {
		IO.println("BitS Size: " + bitS.size());
		IO.println("BitS Card: " + bitS.cardinality());
		IO.println("Rate BitS: " + (Math.round((100.0/bitS.size()*bitS.cardinality())* 100000.0) / 100000.0) + "%");
	}
	
	public static void testTwoBit() {
		
		HashFunction testHF = new HashFunction();
		
		testHF.setHashFunction(new TwoBitHashTest(15));
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("src/testFiles/AnalyseDir/AllKmer.fa"));
		

		
		StringBuilder sb = new StringBuilder("");
		sb.append("Number\n");
		
		String gen;
		
		while((gen = br.readLine()) != null) {
					
			if(gen.charAt(0) == '>' || gen.charAt(0) == '<') continue;
			
			int[] i = testHF.output(gen.toCharArray(), 0);
			
			sb.append(i[0] + "\n");
			
			if(i.length > 1) {
				IO.println("falsch");
			}
			
			if(sb.length() > Integer.MAX_VALUE/4) {
				write(sb);
				sb.setLength(0);
			}
		}
		
		br.close();
		
		if(sb.length() != 0) {
			write(sb);
		}
		} catch (IOException e) {
			IO.println("IOError: testPipeline");
		}
	}
	
	private static void write(StringBuilder sb) {

		try (FileChannel channel = FileChannel.open(Paths.get("src/testFiles/AnalyseDir/Places.csv"),
				StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {

			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();

		} catch (IOException e) {
			System.out.println("IO-Error");
		}

	}
	
	public static void bugTest() {
		
		HashFunction hf = new HashFunction();
		
		hf.setHashFunction(new RabinFingerprint(30));
		
		int cap = BoundedInteger.calculateCap(getTestGenom().length);
		
		int[] intGenom = hf.output(getTestGenom(), cap);
		
		BitSet bs = new BitSet();
		
		for(int i : intGenom) {
			bs.set(i);
		}
		
		int[] intGen = hf.output(getTestGen(), cap);
		
		boolean inside = true;
		
		for(int i : intGen) {
			if(bs.get(i) == false) {
				inside = false;
				break;
			}
		}
		PlaceWriter.writeInt(intGen);
		
		IO.println(getTestGenom().length-30+1);
		IO.println(intGenom.length);
		IO.println("Inside: " + inside);
		
	}
	
	
	//Test fuer einzelne Hashfunktion
	public static void testOld(int[] kmersInt, char[] seq , int kmerSize, HashFunction hf, BitSet bitS) {
		
		String genom = new String(seq);
		
		//PlaceWriter.writeInt(kmersInt);
		
		
		// Anaylse
		int kmerCount = genom.length() - kmerSize + 1;
		int kmerOverlap = kmerCount - PlaceCounter.countSameKmers(genom,kmerSize);
		ArrayList<String> UniqueKmers = PlaceCounter.getUniqueStrings(genom, kmerSize);
		int[] ints = {};
		ArrayList<Integer> intArr = new ArrayList<>();
		int cap = BoundedInteger.calculateCap(genom.length());
		
		
		
		for(String s : UniqueKmers) {
			ints = hf.output(s.toCharArray(),cap);
			intArr.add(ints[0]);
		}
		
		
		int intArrUnique = PlaceCounter.countInt(intArr);
		
		BitSet bit = new BitSet((1 << cap) - 1);
		
		for(int i : intArr) {
			bit.set(i);
		}
		
		PlaceWriter.writeInt(intArr);
		
		
		IO.println("Unique kmers: " + UniqueKmers.size());
		IO.println("Gesamt Test: " + (UniqueKmers.size() + kmerOverlap));
		IO.println("Differenz Int: " + (kmersInt.length - UniqueKmers.size()));
		IO.println("Anzahl Kmere Soll: " + kmerCount);
		IO.println("Anzahl Kmere Ist: " + kmersInt.length);
		IO.println("Anzahl Ueberschneidungen Kmere im Genom: " + kmerOverlap);
		IO.println("Kmere ohne Overlap: " + (kmerCount - kmerOverlap));
		IO.println("ints size: " + intArr.size());
		IO.println("int Overlap: " + (intArr.size() - intArrUnique));
		IO.println("Rate Int: " + (Math.round((100.0 / intArr.size() * (intArr.size() - intArrUnique)) * 100000.0) / 100000.0) + "%");
		IO.println("BitSet Size: " + bit.size());
		IO.println("BitSet Vergleich: " + bitS.size());
		IO.println("BitSet card: " + bit.cardinality());
		IO.println("Card Vergleich: " + bitS.cardinality());
		IO.println("Rate BitS: " + Math.round((100.0/bitS.size()*bitS.cardinality())* 100000.0 / 100000.0) + "%");
		
		System.out.println("Place done!");
		IO.println("-----------------------------");
		
	}
	
	private static char[] getTestGen() {
		
		String temp = "CGGTAATGAAAAAGGCGAACTGGTGGTACTTGGACGCAACGGTTCCGACTACTCCGCGGCGGTGC";
		
		return temp.toCharArray();
	}
	
	private  static char[] getTestGenom() {
		
		String temp = "AGCTTTTCATTCTGACTGCAATGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTCTCTGACAGCAGC"
				+ "TTCTGAACTGGTTACCTGCCGTGAGTAAATTAAAATTTTATTGACTTAGGTCACTAAATACTTTAACCAA"
				+ "TATAGGCATAGCGCACAGACAGATAAAAATTACAGAGTACACAACATCCATGAAACGCATTAGCACCACC"
				+ "ATTACCACCACCATCACCATTACCACAGGTAACGGTGCGGGCTGACGCGTACAGGAAACACAGAAAAAAG"
				+ "CCCGCACCTGACAGTGCGGGCTTTTTTTTTCGACCAAAGGTAACGAGGTAACAACCATGCGAGTGTTGAA"
				+ "GTTCGGCGGTACATCAGTGGCAAATGCAGAACGTTTTCTGCGGGTTGCCGATATTCTGGAAAGCAATGCC"
				+ "AGGCAGGGGCAGGTGGCCACCGTCCTCTCTGCCCCCGCCAAAATCACCAACCACCTGGTGGCGATGATTG"
				+ "AAAAAACCATTAGCGGCCAGGATGCTTTACCCAATATCAGCGATGCCGAACGTATTTTTGCCGAACTTTT"
				+ "GACGGGACTCGCCGCCGCCCAGCCGGGATTCCCGCTGGCGCAATTGAAAACTTTCGTCGACCAGGAATTT"
				+ "GCCCAAATAAAACATGTCCTGCATGGCATTAGTTTGTTAGGGCAGTGCCCGGATAGCATTAACGCTGCGC"
				+ "TGATTTGCCGTGGCGAGAAAATGTCGATCGCCATTATGGCCGGCGTGTTAGAAGCGCGTGGTCACAACGT"
				+ "TACCGTTATCGATCCGGTCGAAAAACTACTGGCAGTGGGGCATTACCTCGAATCTACCGTCGATATTGCT"
				+ "GAGTCCACCCGCCGTATTGCGGCAAGTCGTATTCCGGCTGATCACATGGTGCTGATGGCAGGCTTCACCG"
				+ "CCGGTAATGAAAAAGGCGAACTGGTGGTACTTGGACGCAACGGTTCCGACTACTCCGCGGCGGTGCTGGC"
				+ "TGCCTGTTTACGCGCCGATTGTTGCGAGATTTGGACGGACGTTGACGGGGTCTATACCTGCGACCCGCGT"
				+ "CAGGTGCCCGATGCGAGGTTGTTGAAGTCGATGTCCTACCAGGAAGCGATGGAGCTTTCCTACTTCGGCG"
				+ "CTAAAGTTCTTCACCCCCGCACCATTACCCCCATCGCCCAGTTCCAGATCCCTTGCCTGATTAAAAATAC"
				+ "CGGAAATCCTCAAGCACCAGGTACGCTCATTGGTGCCAGTCGTGATGAAGACGAATTACCGGTCAAGGGC"
				+ "ATTTCCAATCTGAATAACATGGCAATGTTCAGCGTTTCCGGCCCGGGGATGAAAGGAATGGTCGGCATGG"
				+ "CGGCGCGCGTCTTTGCCGCGATGTCACGCGCCCGTATTTCCGTGGTGCTGATTACGCAATCATCTTCCGA"
				+ "ATACAGTATCAGTTTCTGCGTTCCGCAAAGCGACTGTGTGCGGGCTGAACGGGCAATGCAGGAAGAGTTC"
				+ "TACCTGGAACTGAAAGAAGGCTTACTGGAGCCGCTGGCGGTGACGGAACGGCTGGCCATTATCTCGGTGG"
				+ "TAGGTGATGGTATGCGTACCTTGCGTGGGATCTCGGCGAAATTCTTTGCCGCGCTGGCCCGCGCCAATAT"
				+ "CAACATTGTCGCTATTGCTCAGGGATCTTCTGAACGCTCAATCTCTGTCGTGGTAAATAACGATGATGCG"
				+ "ACCACTGGCGTGCGCGTTACTCATCAGATGCTGTTCAATACCGATCAGGTTATCGAAGTGTTTGTGATTG"
				+ "GCGTCGGTGGCGTTGGCGGTGCGCTGCTGGAGCAACTGAAGCGTCAACAAAGCTGGCTGAAGAATAAACA"
				+ "TATCGACTTACGTGTCTGCGGTGTTGCCAACTCGAAGGCACTGCTCACCAATGTGCATGGCCTAAATCTG"
				+ "GAAAACTGGCAGGAAGAACTGGCGCAAGCCAAAGAGCCGTTTAATCTCGGGCGCTTAATTCGCCTCGTGA"
				+ "AAGAATATCATCTGCTGAACCCGGTCATTGTTGACTGCACCTCCAGCCAGGCAGTGGCGGATCAATATGC"
				+ "CGACTTCCTGCGCGAAGGTTTCCACGTTGTCACGCCGAACAAAAAGGCCAACACCTCGTCGATGGATTAC"
				+ "TACCATCTGTTGCGTCATGCGGCGGAAAAATCGCGGCGGAAATTCCTCTATGACACCAACGTTGGGGCTG"
				+ "GATTACCGGTTATTGAAAACCTGCAAAATCTGCTCAATGCTGGTGATGAATTGATGAAGTTCTCCGGCAT"
				+ "TCTTTCAGGATCGCTTTCTTATATCTTCGGCAAGTTAGACGAAGGCATGAGTTTCTCCGAGGCGACCACG"
				+ "CTGGCACGGGAAATGGGTTATACCGAACCGGATCCGCGAGATGATCTTTCTGGTATGGATGTGGCGCGTA"
				+ "AACTATTGATTCTCGCCCGTGAAACGGGACGTGAACTGGAGCTGGCGGATATTGAAATTGAACCTGTGCT";
		
		return temp.toCharArray();
	}

}
