package app;

import java.util.ArrayList;

import app.helper.CheckGenes;
import app.helper.CreateBloom;
import app.helper.LoadGenes;
import app.records.BitSetObject;
import app.records.Gen;
import hashFunctionCollection.methods.NtHash2LookupTable;

public class Bloom {
	
	private String pathGenom;
	private String pathGenes;

	
	public Bloom(String pathGenom, String pathGenes) {
		this.pathGenes = pathGenes;
		this.pathGenom = pathGenom;
	}
	
	public void run() {
		
		int kmerSize = 30;
		
		//Erstellen einer Liste aus BitSetObjekten
		BitSetObject BSO = CreateBloom.create(pathGenom,kmerSize);
		
		//Laden der Gene als Liste
		ArrayList<Gen> genList = LoadGenes.load(pathGenes);
		
		//Nachpruefen, ob Gene enthalten sind
		//CheckGenes.check(BSO,genList,kmerSize);
		
	}
	
	
	public void setPathGenom(String pathGenom) {
		this.pathGenom = pathGenom;
	}
	
	public void setPathGenes(String pathGenes) {
		this.pathGenes = pathGenes;
	}

}
