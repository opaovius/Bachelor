package hashFunctionCollection;

import java.util.BitSet;

import app.Gen;

public interface IHashFunction {
	
	public BitSet toHashGenom(String s);
	
	public int toHashGen(Gen g);
}
