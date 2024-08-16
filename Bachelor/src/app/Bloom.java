package app;

import java.util.LinkedList;
import java.util.List;

public class Bloom {
	
	String pathGenom;
	String pathGenes;
	
	public Bloom(String pathGenom, String pathGenes) {
		this.pathGenes = pathGenes;
		this.pathGenom = pathGenom;
	}
	
	public void run() {
		
		
		Parser<StringBuilder> genomParser = new Parser<>(pathGenom);
		genomParser.setParser(new GenomFastaParser());
		StringBuilder genom = genomParser.output();
		
		Parser<List<String>> genParser = new Parser<>(pathGenes);
		genParser.setParser(new GenFastaParser());
		List<String> genes = genParser.output();
		
		LinkedList<Double> places = new LinkedList<>();
		int kmerSize = 66;
		
		for(int x = 0; x < genom.length() - kmerSize; x++) {
			
			HashFunction<Double> hash = new HashFunction<Double>(genom.substring(x, x + kmerSize));
			hash.setHashFunction(new DummyHashFunction());
			places.add(hash.output());
		}
		
		PlaceWriter.write(places);
		
		
	}

	

}
