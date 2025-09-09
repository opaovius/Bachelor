package app;

import java.util.BitSet;

public class BitSetObject {
	
	String name;
	BitSet vector;

	public BitSetObject(String name, BitSet vector){
		this.name = name;
		this.vector = vector;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setVector(BitSet vector) {
		this.vector = vector;
	}
	
	public String getName() {
		return name;
	}
	
	public BitSet getVector() {
		return vector;
	}
}
