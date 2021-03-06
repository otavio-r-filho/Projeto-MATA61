package lexer.definitions;

import lexer.definitions.tToken;

public class Tokens {
	//Tokens reservados - comandos
	public static final tToken ELSE = new tToken("ELSE", "else", 1);
	public static final tToken FLOAT = new tToken("FLOAT", "float", 2);
	public static final tToken FOR = new tToken("FOR", "for", 3);
	public static final tToken IF = new tToken("IF", "if", 4);
	public static final tToken INT = new tToken("INTEGER", "int", 5);
	public static final tToken NEW = new tToken("NEW", "new", 6);
	public static final tToken RETURN = new tToken("RETURN", "return", 7);
	public static final tToken STR = new tToken("STRING", "str", 8);
	public static final tToken THEN = new tToken("THEN", "then", 9);
	public static final tToken VOID = new tToken("VOID", "void", 10);
	public static final tToken WHILE = new tToken("WHILE", "while", 11);
	
	
	//Tokens reservados - caracteres
	public static final tToken PLUS = new tToken("PLUS", "+", 12);
	public static final tToken MINUS = new tToken("MINUS", "-", 13);
	public static final tToken ASTERISK = new tToken("ASTERISK", "*", 14);
	public static final tToken SLASH = new tToken("SLASH", "/", 15);
	public static final tToken SMALLER = new tToken("SMALLER", "<", 16);
	public static final tToken GREATER = new tToken("GREATER", ">", 17);
	public static final tToken ATTRIBUTION = new tToken("ATTRIBUTION", "=", 18);
	public static final tToken EXCLAMATION = new tToken("EXCLAMATION", "!", 19);
	public static final tToken SEQUAL = new tToken("SEQUAL", "<=", 20);
	public static final tToken GEQUAL = new tToken("GEQUAL", ">=", 21);
	public static final tToken COMPARISSON = new tToken("COMPARISSON", "==", 22);
	public static final tToken DIFFERENT = new tToken("DIFFERENT", "!=", 23);
	public static final tToken SEMICOLON = new tToken("SEMICOLON", ";", 24);
	public static final tToken COMMA = new tToken("COMMA", ",", 25);
	public static final tToken OPARENTHESES = new tToken("OPARENTHESES", "(", 26);
	public static final tToken CPARENTHESES = new tToken("CPARENTHESES", ")", 27);
	public static final tToken OBRACKET = new tToken("OBRACKET", "[", 28);
	public static final tToken CBRACKET = new tToken("CBRACKET", "]", 29);
	public static final tToken OKEYBRACKET = new tToken("OKEYBRACKET", "{", 30);
	public static final tToken CKEYBRACKET = new tToken("CKEYBRACKET", "}", 31);
	public static final tToken COMERCIALE = new tToken("COMMERCIALE", "&", 32);
	public static final tToken PIPE = new tToken("PIPE", "|", 33);
	public static final tToken POINT = new tToken("POINT", ".", 34);
	public static final tToken AND = new tToken("AND", "&&", 35);
	public static final tToken OR = new tToken("OR", "||", 36);
	
	
	//Tokens "abertos"
//	public static tToken ID = new tToken("ID", "", 37);
//	public static tToken NUM = new tToken("NUM", "", 38);
//	public static tToken REAL = new tToken("REAL", "", 39);
//	
//	public static final tToken ERROR = new tToken("ERROR", "", 38);
}
