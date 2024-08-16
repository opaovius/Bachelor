package app;

import java.util.List;

public class Bloom {
	
	String genom;
	
	public Bloom(String genom) {
		this.genom = genom;
	}
	
	public void magic() {
		
		
		Parser<StringBuilder> genomParser = new Parser<>("src/Genom.fa");
		genomParser.setParser(new GenomFastaParser());
		StringBuilder builder = genomParser.output();
		
		Parser<List<String>> genParser = new Parser<>("src/Gen.fa");
		genParser.setParser(new GenFastaParser());
		List<String> genes = genParser.output();
		
		HashFunction<Integer> hash = new HashFunction<Integer>(genom);
		hash.setHashFunction(new DummyHashFunction());
		System.out.println(hash.output());
	}

	

}
