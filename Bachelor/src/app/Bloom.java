package app;

import java.util.ArrayList;

import app.helper.CheckGenes;
import app.helper.CreateBloom;
import app.helper.LoadGenes;
import app.helper.LoadGenom;
import app.records.BitSetObject;
import app.records.Gen;
import app.records.Genom;

public class Bloom {
	
	Genom genom;
	ArrayList<Gen> genes;

	
	public Bloom(String pathGenom, String pathGenes) {
		this.genom = LoadGenom.load(pathGenom);
		this.genes = LoadGenes.load(pathGenes);
	}
	
	public void run() {
		
		int kmerSize = 30;
		
		//Erstellen einer Liste aus BitSetObjekten
		BitSetObject BSO = CreateBloom.create(genom,kmerSize);
		
		//Nachpruefen, ob Gene enthalten sind
		//CheckGenes.check(BSO,genes,kmerSize);
		
		//GenPermutator.permutate(genes);
	}
	
	
	public void setGenom(String pathGenom) {
		this.genom = LoadGenom.load(pathGenom);
	}
	
	public void setGenes(String pathGenes) {
		this.genes = LoadGenes.load(pathGenes);
	}

}
