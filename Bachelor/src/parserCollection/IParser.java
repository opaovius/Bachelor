package parserCollection;

import app.records.Alphabet;

public interface IParser<T> {

	public T readFile(String path,Alphabet alphabet);
	
}
