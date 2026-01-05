package app;

import java.util.ArrayList;

import app.DefaultBFDir.DefaultBF;
import app.HIBFDir.HIBF;
import app.IBFDir.IBF;
import app.helper.LoadGenes;
import app.helper.LoadGenom;
import app.records.Alphabet;
import app.records.Gen;
import app.records.Genom;

public class Bloom {

	ArrayList<ArrayList<Genom>> genom;
	ArrayList<Gen> genes;
	String hashOptionOne;
	String hashOptionTwo;
	Alphabet alphabet;

	/**
	 * Konstruktor fuer den Bloomfilter. Laedt das Genom und die Gene ein
	 * 
	 * @param pathGenom : Pfad zur Genomdatei
	 * @param pathGenes : Pfad zur Gendatei
	 */

	public Bloom(String pathGenom, String pathGenes, Alphabet alphabet, String hashOptionOne, String hashOptionTwo) {
		this.genom = LoadGenom.load(pathGenom, alphabet);
		this.genes = LoadGenes.load(pathGenes, alphabet);
		this.hashOptionOne = hashOptionOne;
		this.hashOptionTwo = hashOptionTwo;
		this.alphabet = alphabet;
	}

	/**
	 * Erstellt BitSet und preuft, ob die Gene enthalten sind.
	 */

	public void run() {

		boolean IBFBool = false;
		boolean HIBFBool = true;
		
		int kmerSize = switchKmerSize();

		if (IBFBool) {
			IBF.filter(genom, genes, hashOptionOne, hashOptionTwo, alphabet, kmerSize);
		} 
		else if(HIBFBool) {
			HIBF.filter(genom, genes, hashOptionOne, hashOptionTwo, alphabet, kmerSize);
		}
		else {
			DefaultBF.filter(genom, genes, hashOptionOne, hashOptionTwo, alphabet, kmerSize);
		}

	}

	/**
	 * Methode zum aendern des Genoms
	 * 
	 * @param pathGenom : Pfad zur Genomdatei
	 */

	public void setGenom(String pathGenom) {
		this.genom = LoadGenom.load(pathGenom, alphabet);
	}

	/**
	 * Methode zum aendern der Gene
	 * 
	 * @param pathGenes : Pfad zur Gendatei
	 */

	public void setGenes(String pathGenes) {
		this.genes = LoadGenes.load(pathGenes, alphabet);
	}

	public void setHashOptionOne(String hashOptionOne) {
		this.hashOptionOne = hashOptionOne;
	}

	public void setHashOptionTwo(String hashOptionTwo) {
		this.hashOptionTwo = hashOptionTwo;
	}

	public void setAlphabet(Alphabet alphabet) {
		this.alphabet = alphabet;
	}
	
	private int switchKmerSize() {
		
		return switch(alphabet) {
		
		case Alphabet.DNA -> 30;
		
		default -> 5;
		};
	}
}
