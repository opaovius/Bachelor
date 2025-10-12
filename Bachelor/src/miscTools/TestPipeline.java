package miscTools;

public class TestPipeline {
	
	public static void test(int[] kmers) {
		
		//PlaceWriter.writeInt(kmers);
		
		// Anaylse
		int notUnique = kmers.length - PlaceCounter.countInt(kmers);
		IO.println("Stellen mehrfach: " + notUnique);
		IO.println("Rate: " + (Math.round((100.0 / kmers.length * notUnique) * 100.0) / 100.0) + "%");
		System.out.println("Place done!");
		IO.println("-----------------------------");
		
	}

}
