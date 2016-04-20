package lexer.definitions;

import java.util.HashMap;

public class Lexeme {
	
	private int lexemeIndex;
	private String lexemeValue;
	
	private static HashMap<String, Integer> lexemes;
	
	static{
		lexemes = new HashMap<String, Integer>();
		for(Integer i = 0; i < 10; i++)
		{
			lexemes.put(i.toString(), i);
		}
		for(int i = 0; i < 26; i++)
		{
			char ch = (char)(i + 65);
			lexemes.put(String.valueOf(ch), Integer.valueOf(i+10));
		}
		for(int i = 0; i < 26; i++)
		{
			char ch = (char)(i + 97);
			lexemes.put(String.valueOf(ch), Integer.valueOf(i + 10 + 26));
		}
		lexemes.put("+", 62);
		lexemes.put("-", 63);
		lexemes.put("*", 64);
		lexemes.put("/", 65);
		lexemes.put("<", 66);
		lexemes.put(">", 67);
		lexemes.put("=", 68);
		lexemes.put("!", 69);
		lexemes.put(";", 70);
		lexemes.put(",", 71);
		lexemes.put(".", 72);
		lexemes.put("(", 73);
		lexemes.put(")", 74);
		lexemes.put("[", 75);
		lexemes.put("]", 76);
		lexemes.put("{", 77);
		lexemes.put("}", 78);
		lexemes.put("&", 79);
		lexemes.put("|", 80);
		lexemes.put("\n", 81);
		lexemes.put("\r", 81);
		lexemes.put(" ", 81);
	}
	
	public static int getLexemeIndex(String lexeme)
	{
		if(lexeme.contains(lexeme))
		{
			return lexemes.get(lexeme);
		}
		return -1;
	}
	
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
