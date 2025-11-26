package parserCollection;

import app.records.Alphabet;

public class Parser<T> {
	
	String path;
	IParser<T> parser = null;
	Alphabet alphabet;
	
	public Parser(String path, Alphabet alphabet) {
		this.path = path;
		this.alphabet = alphabet;
	}
	
	public void setParser(IParser<T> parser) {
		this.parser = parser;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public T output() {
		
		T out = parser.readFile(path,alphabet);
		
		return out;
	}

}
