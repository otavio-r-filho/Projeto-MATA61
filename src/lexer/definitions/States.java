package lexer.definitions;

import lexer.definitions.*;

public class States {
	
	private Lexemes lexemes;
	private Tokens tokens;
	
	public static final State ENTRY 		= new State(1,"ENTRY", false);
	public static final State ID0 			= new State(2, "ID0", true);
	public static final State IF 			= new State(3, "IF", true, Tokens.IF);
	public static final State ID1 			= new State(4, "ID1", true);
	public static final State INT 			= new State(5, "INT", true, Tokens.INT);
	public static final State ID2 			= new State(6, "ID2", true);
	public static final State NUM 			= new State(7, "NUM", true);
	public static final State REAL0 		= new State(8, "REAL0", false);
	public static final State REAL 			= new State(9, "REAL", true);
	public static final State ID3			= new State(10, "ID3", true);
	public static final State ID4			= new State(11, "ID4", true);
	public static final State FOR			= new State(12, "FOR", true, Tokens.FOR);
	public static final State ID5			= new State(13, "ID5", true);
	public static final State ID6			= new State(14, "ID6", true);
	public static final State ID7			= new State(15, "ID7", true);
	public static final State FLOAT			= new State(16, "FLOAT", true, Tokens.FLOAT);
	public static final State ID8			= new State(17, "ID8", true);
	public static final State ID9 			= new State(18, "ID9", true);
	public static final State ID10			= new State(19, "ID10", true);
	public static final State ELSE			= new State(20, "ELSE", true, Tokens.ELSE);
	public static final State ID11			= new State(21, "ID11", true);
	public static final State ID12			= new State(22, "ID12", true);
	public static final State NEW			= new State(23, "NEW", true, Tokens.NEW);
	public static final State ID13			= new State(24, "ID13", true);
	public static final State ID14			= new State(25, "ID14", true);
	public static final State ID15			= new State(26, "ID15", true);
	public static final State ID16			= new State(27, "ID16", true);
	public static final State ID17			= new State(28, "ID17", true);
	public static final State RETURN		= new State(29, "RETURN", true, Tokens.RETURN);
	public static final State ID18 			= new State(30, "ID18", true);
	public static final State ID19			= new State(31, "ID19", true);
	public static final State STR			= new State(32, "STR", true, Tokens.STR);
	public static final State ID20			= new State(33, "ID20", true);
	public static final State ID21			= new State(34, "ID21", true);
	public static final State ID22			= new State(35, "ID22", true);
	public static final State THEN			= new State(36, "ID23", true);
	public static final State ID24			= new State(37, "ID24", true);
	public static final State ID25			= new State(38, "ID25", true);
	public static final State ID26			= new State(39, "ID26", true);
	public static final State VOID			= new State(40, "VOID", true, Tokens.VOID);
	public static final State ID27			= new State(41, "ID27", true);
	public static final State ID28			= new State(42, "ID28", true);
	public static final State ID29			= new State(43, "ID29", true);
	public static final State ID30			= new State(44, "ID30", true);
	public static final State WHILE			= new State(45, "WHILE", true, Tokens.WHILE);
	public static final State PLUS			= new State(46, "PLUS", true, Tokens.PLUS);
	public static final State MINUS			= new State(47, "MINUS", true, Tokens.MINUS);
	public static final State ASTERISK		= new State(48, "ASTERISK", true, Tokens.ASTERISK);
	public static final State SLASH			= new State(49, "SLASH", true, Tokens.SLASH);
	public static final State SMALLER		= new State(50, "SMALLER", true);
	public static final State SEQUAL		= new State(51, "SEQUAL", true);
	public static final State GREATER		= new State(52, "GREATER", true);
	public static final State GEQUAL		= new State(53, "GEQUAL", true);
	public static final State ATTRIBUTION	= new State(54, "ATTIBUTION", true);
	public static final State COMPARISSON	= new State(55, "COMPARISSON", true);
	public static final State EXCLAMATION	= new State(56, "EXCLAMATION", true);
	public static final State DIFFERENT		= new State(57, "DIFFERENT", true);
	public static final State SEMICOLON		= new State(58, "SEMICOLON", true);
	public static final State COMMA			= new State(59, "COMMA", true);
	public static final State OPARENTHESES	= new State(60, "OPARENTHESES", true);
	public static final State CPARENTHESES	= new State(61, "CPARENTHESES", true);
	public static final State OBRACKET		= new State(62, "OBRACKET", true);
	public static final State CBRACKET		= new State(63, "CBRACKET", true);
	public static final State OKEYBRACKET	= new State(64, "OKEYBRACKET", true);
	public static final State CKEYBRACKET	= new State(65, "CKEYBRACKET", true);
	
}
