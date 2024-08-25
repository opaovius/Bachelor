package app;

public class Main {

	public static void main(String[] args) {
		
		Bloom b = new Bloom("src/GenomRandom.fa","src/GenTest.fa");
		
		b.run();
	}

}
