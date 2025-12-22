package app;

import java.util.ArrayList;

import app.helper.CheckGenes;
import app.helper.CreateBloom;
import app.helper.LoadGenes;
import app.helper.LoadGenom;
import app.records.Alphabet;
import app.records.BitSetObject;
import app.records.Gen;
import app.records.Genom;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.CreateHashFunction;
import miscTools.Generator.ProteinGenerator;

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

	
	public Bloom(String pathGenom, String pathGenes,Alphabet alphabet, String hashOptionOne, String hashOptionTwo) {
		this.genom = LoadGenom.load(pathGenom,alphabet);
		this.genes = LoadGenes.load(pathGenes,alphabet);
		this.hashOptionOne = hashOptionOne;
		this.hashOptionTwo = hashOptionTwo;
		this.alphabet = alphabet;
	}
	
	/**
	 * Erstellt BitSet und preuft, ob die Gene enthalten sind.
	 */
	
	public void run() {
		
		int kmerSize = switchKmerSize(alphabet);
		
		ArrayList<BitSetObject> BSOList = new ArrayList<>();
		
		//Erstellen der HashFunktionen
		ArrayList<HashFunction> hfList = CreateHashFunction.create(kmerSize,hashOptionOne,hashOptionTwo,alphabet);
		
		//Erstellen einer Liste aus BitSetObjekten
		for(ArrayList<Genom> g : genom) {
			BSOList.add(CreateBloom.create(g,kmerSize,hfList,alphabet));
		}
		//Nachpruefen, ob Gene enthalten sind
		CheckGenes.check(BSOList,genes,kmerSize,hfList,alphabet);
		
		//GenPermutator.permutate(genes);
		//ProteinGenerator.generate(genom.getFirst().getFirst());
	}
	
	/**
	 * Methode zum aendern des Genoms
	 * 
	 * @param pathGenom : Pfad zur Genomdatei
	 */
	
	public void setGenom(String pathGenom) {
		this.genom = LoadGenom.load(pathGenom,alphabet);
	}
	
	/**
	 * Methode zum aendern der Gene
	 * 
	 * @param pathGenes : Pfad zur Gendatei
	 */
	
	public void setGenes(String pathGenes) {
		this.genes = LoadGenes.load(pathGenes,alphabet);
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
	
	private int switchKmerSize(Alphabet alphabet) {
		
		return switch(alphabet) {
		
		case Alphabet.DNA -> 30;
		
		default -> 5;
		};
	}

}
