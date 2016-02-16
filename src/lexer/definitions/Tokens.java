package lexer.definitions;

import lexer.definitions.tToken;

public class Tokens {
	//Lexemas reservados - comandos
	public static tToken ELSE = new tToken("ELSE", "else", 1);
	public static tToken FLOAT = new tToken("FLOAT", "float", 2);
	public static tToken FOR = new tToken("FOR", "for", 3);
	public static tToken IF = new tToken("IF", "if", 4);
	public static tToken INT = new tToken("INTEGER", "int", 5);
	public static tToken NEW = new tToken("NEW", "new", 6);
	public static tToken RETURN = new tToken("RETURN", "return", 7);
	public static tToken STR = new tToken("STRING", "str", 8);
	public static tToken THEN = new tToken("THEN", "then", 9);
	public static tToken VOID = new tToken("VOID", "void", 10);
	public static tToken WHILE = new tToken("WHILE", "while", 11);
	
	
	//Lexemas reservados - caracteres
	public static tToken PLUS = new tToken("PLUS", "+", 12);
	public static tToken MINUS = new tToken("MINUS", "-", 13);
	public static tToken ASTERISK = new tToken("ASTERISK", "*", 14);
	public static tToken SLASH = new tToken("SLASH", "/", 15);
	public static tToken SMALLER = new tToken("SMALLER", "<", 16);
	public static tToken GRATER = new tToken("GREATER", ">", 17);
	public static tToken ATTRIBUTION = new tToken("ATTRIBUTION", "=", 18);
	public static tToken SEQUAL = new tToken("SQUAL", "<=", 19);
	public static tToken GEQUAL = new tToken("GEQUAL", ">=", 20);
	public static tToken COMPARISSON = new tToken("COMPARISSON", "==", 21);
	public static tToken DIFFERENT = new tToken("DIFFERENT", "!=", 22);
	public static tToken SEMICOLON = new tToken("SEMICOLON", ";", 23);
	public static tToken COMMA = new tToken("COMMA", ",", 24);
	public static tToken OPARENTHESES = new tToken("OPARENTHESES", "(", 25);
	public static tToken CPARENTHESES = new tToken("CPARENTHESES", ")", 26);
	public static tToken OBRACKET = new tToken("OBRACKET", "[", 27);
	public static tToken CBRACKET = new tToken("CBRACKET", "]", 28);
	public static tToken OKEYBRACKET = new tToken("OKEYBRACKET", "{", 29);
	public static tToken CKEYBRACKET = new tToken("CKEYBRACKET", "}", 30);
	public static tToken COMERCIALE = new tToken("COMERCIALE", "&", 31);
	public static tToken PIPE = new tToken("PIPE", "|", 32);
	
	//Lexemas "abertos"
	public static tToken ID = new tToken("ID", "", 33);
	public static tToken NUM = new tToken("NUM", "", 34);
	public static tToken REAL = new tToken("REAL", "", 35);
}
