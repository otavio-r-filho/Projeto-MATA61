package lexer.definitions;

public class Lexeme {
	
	private int lexemeIndex;
	private String lexemeValue;
	
	public Lexeme(int lIndex, String lValue) {
		this.lexemeIndex = lIndex;
		this.lexemeValue = lValue;
	}
	
	public int getIndex() {
		return lexemeIndex;
	}
	
	public String getValue() {
		return lexemeValue;
	}

}
