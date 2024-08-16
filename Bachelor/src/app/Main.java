package app;

public class Main {

	public static void main(String[] args) {
		
		String genom = "ACGTAACGGT";
		
		Bloom b = new Bloom(genom);
		
		b.magic();
	}

}
