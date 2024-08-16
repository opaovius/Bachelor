package app;

public class Bloom {
	
	String genom;
	
	public Bloom(String genom) {
		this.genom = genom;
	}
	
	public void magic() {
		HashFunction<Integer> hash = new HashFunction<Integer>(genom);
		hash.setHashFunction(new DummyHashFunction());
		System.out.println(hash.output());
	}

}
