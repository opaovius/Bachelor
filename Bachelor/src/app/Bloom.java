package app;

import java.util.ArrayList;

import app.helper.CheckGenes;
import app.helper.CreateBloom;
import app.helper.LoadGenes;
import app.helper.LoadGenom;
import app.records.BitSetObject;
import app.records.Gen;
import app.records.Genom;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.CreateHashFunction;
import miscTools.TestMethods.TestPipeline;

public class Bloom {
	
	Genom genom;
	ArrayList<Gen> genes;
	
	/**
	 * Konstruktor fuer den Bloomfilter. Laedt das Genom und die Gene ein
	 * 
	 * @param pathGenom : Pfad zur Genomdatei
	 * @param pathGenes : Pfad zur Gendatei
	 */

	
	public Bloom(String pathGenom, String pathGenes) {
		this.genom = LoadGenom.load(pathGenom);
		this.genes = LoadGenes.load(pathGenes);
	}
	
	/**
	 * Erstellt BitSet und preuft, ob die Gene enthalten sind.
	 */
	
	public void run() {
		
		int kmerSize = 30;
		
		//TestPipeline.bugTest();
		
		//Erstellen der HashFunktionen
		ArrayList<HashFunction> hfList = CreateHashFunction.create(kmerSize);
		
		//Erstellen einer Liste aus BitSetObjekten
		BitSetObject BSO = CreateBloom.create(genom,kmerSize,hfList);
		
		//Nachpruefen, ob Gene enthalten sind
		CheckGenes.check(BSO,genes,kmerSize,hfList);
		
		//GenPermutator.permutate(genes);
	}
	
	/**
	 * Methode zum aendern des Genoms
	 * 
	 * @param pathGenom : Pfad zur Genomdatei
	 */
	
	public void setGenom(String pathGenom) {
		this.genom = LoadGenom.load(pathGenom);
	}
	
	/**
	 * Methode zum aendern der Gene
	 * 
	 * @param pathGenes : Pfad zur Gendatei
	 */
	
	public void setGenes(String pathGenes) {
		this.genes = LoadGenes.load(pathGenes);
	}

}
