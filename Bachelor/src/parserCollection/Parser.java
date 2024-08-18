package parserCollection;

public class Parser<T> {
	
	String path;
	IParser<T> parser = null;
	
	public Parser(String path) {
		this.path = path;
	}
	
	public void setParser(IParser<T> parser) {
		this.parser = parser;
	}
	
	public T output() {
		
		T out = parser.readFile(path);
		
		return out;
	}

}
