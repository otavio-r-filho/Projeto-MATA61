package parser;

import java.util.HashMap;

public class ParsingTable {
	                             
	private String parsingTable[][] = { // 0         1        2        3        4        5        6        7        8         9        10       11       12       13       14       15       16       17       18       19       20       21       22       23       24       25       26       27       28       29       30       31       32       33       34       35       36       37       38       39
						 		   /*0*/ {"ERROR",  "0"    , "ERROR", "ERROR", "0"    , "ERROR", "ERROR", "ERROR", "ERROR",  "0"    , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*1*/ {"ERROR",  "1"    , "ERROR", "ERROR", "1"    , "ERROR", "ERROR", "ERROR", "ERROR",  "1"    , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "70"   },
						 		   /*2*/ {"ERROR",  "3"    , "ERROR", "ERROR", "3"    , "ERROR", "ERROR", "ERROR", "ERROR",  "4"    , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*3*/ {"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "5"    , "5"    , "6"    , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*4*/ {"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "70"   , "7"    , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*5*/ {"ERROR",  "10"   , "ERROR", "ERROR", "9"    , "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*6*/ {"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "11"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*7*/ {"ERROR",  "12"   , "ERROR", "ERROR", "12"   , "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "13"   , "ERROR", "70"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*8*/ {"ERROR",  "15"   , "ERROR", "ERROR", "15"   , "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*9*/ {"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "16"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*10*/{"ERROR",  "17"   , "70"   , "70"   , "17"   , "ERROR", "70"   , "ERROR", "ERROR",  "ERROR", "70"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "70"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "70"   , "ERROR", "ERROR", "ERROR"},
						 		   /*11*/{"ERROR",  "ERROR", "19"   , "19"   , "ERROR", "ERROR", "19"   , "ERROR", "ERROR",  "ERROR", "19"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "19"   , "70"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "19"   , "ERROR", "ERROR", "ERROR"},
						 		   /*12*/{"ERROR",  "ERROR", "23"   , "21"   , "ERROR", "ERROR", "25"   , "ERROR", "ERROR",  "ERROR", "22"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "26"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "24"   , "ERROR", "ERROR", "ERROR"},
						 		   /*13*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "27"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "27"   , "ERROR", "ERROR", "ERROR", "ERROR", "70"   , "ERROR", "27"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "27"   , "27"   , "27"   , "ERROR"},
						 		   /*14*/{"29"   ,  "ERROR", "70"   , "70"   , "ERROR", "ERROR", "70"   , "ERROR", "ERROR",  "ERROR", "70"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "70"   , "70"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "70"   , "ERROR", "ERROR", "ERROR"},
						 		   /*15*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "32"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "31"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*16*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "33"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*17*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "34"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
						 		   /*18*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "35"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "35"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "35"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "35"   , "35"   , "35"   , "ERROR"},
						 		   /*19*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "38"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "38"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "38"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "38"   , "38"   , "38"   , "ERROR"},
						 		   /*20*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "41"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "41"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "41"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "41"   , "41"   , "41"   , "ERROR"},
						 		   /*21*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "45"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "45"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "45"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "45"   , "45"   , "45"   , "ERROR"},
						 		   /*22*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "51"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "51"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "51"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "51"   , "51"   , "51"   , "ERROR"},
						 		   /*23*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "60"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "59"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "57"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "58"   , "55"   , "56"   , "ERROR"},
						 		   /*24*/{"70"   ,  "ERROR", "70"   , "70"   , "ERROR", "ERROR", "70"   , "ERROR", "ERROR",  "ERROR", "70"   , "70"   , "70"   , "65"   , "65"   , "70"   , "70"   , "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "64"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "ERROR", "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR"},
						 		   /*25*/{"70"   ,  "ERROR", "70"   , "70"   , "ERROR", "ERROR", "70"   , "ERROR", "ERROR",  "ERROR", "70"   , "70"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "ERROR", "ERROR", "70"   , "36"   , "70"   , "70"   , "70"   , "ERROR"},
						 		   /*26*/{"70"   ,  "ERROR", "70"   , "70"   , "ERROR", "ERROR", "70"   , "ERROR", "ERROR",  "ERROR", "70"   , "70"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "ERROR", "ERROR", "39"   , "70"   , "70"   , "70"   , "70"   , "ERROR"},
						 		   /*27*/{"70"   ,  "ERROR", "70"   , "70"   , "ERROR", "ERROR", "70"   , "ERROR", "ERROR",  "ERROR", "70"   , "70"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "70"   , "70"   , "70"   , "42"   , "43"   , "70"   , "70"   , "70"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "ERROR", "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR"},
						 		   /*28*/{"70"   ,  "ERROR", "70"   , "70"   , "ERROR", "ERROR", "70"   , "ERROR", "ERROR",  "ERROR", "70"   , "70"   , "70"   , "ERROR", "ERROR", "46"   , "47"   , "ERROR", "70"   , "48"   , "49"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "ERROR", "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR"},
						 		   /*29*/{"70"   ,  "ERROR", "70"   , "70"   , "ERROR", "ERROR", "70"   , "ERROR", "ERROR",  "ERROR", "70"   , "53"   , "52"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "ERROR", "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR"},
						 		   /*30*/{"70"   ,  "ERROR", "70"   , "70"   , "ERROR", "ERROR", "70"   , "ERROR", "ERROR",  "ERROR", "70"   , "70"   , "70"   , "62"   , "61"   , "70"   , "70"   , "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR", "ERROR", "70"   , "70"   , "ERROR", "ERROR", "ERROR", "70"   , "70"   , "70"   , "70"   , "70"   , "ERROR"},
						 		   /*31*/{"ERROR",  "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR",  "ERROR", "ERROR", "ERROR", "67"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "67"   , "ERROR", "ERROR", "ERROR", "ERROR", "70"   , "68"   , "67"   , "70"   , "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "67"   , "67"   , "67"   , "ERROR"}
						 			 };
	
	private HashMap<String, Integer> nonTerminalCoding;
	private HashMap<String, Integer> terminalCoding;
	
	public ParsingTable() {
		nonTerminalCoding = new HashMap<String, Integer>();
		terminalCoding = new HashMap<String, Integer>();
		
		nonTerminalCoding.put("programa", 		0);
		nonTerminalCoding.put("declaracoes", 	1);
		nonTerminalCoding.put("declaracao",		2);
		nonTerminalCoding.put("dec-fim", 		3);
		nonTerminalCoding.put("lista-nomes", 	4);
		nonTerminalCoding.put("tipo-base", 		5);
		nonTerminalCoding.put("dec-funcao", 	6);
		nonTerminalCoding.put("parametros", 	7);
		nonTerminalCoding.put("parametro", 		8);
		nonTerminalCoding.put("bloco", 			9);
		nonTerminalCoding.put("dec-variavel", 	10);
		nonTerminalCoding.put("comandos", 		11);
		nonTerminalCoding.put("comando", 		12);
		nonTerminalCoding.put("retorno", 		13);
		nonTerminalCoding.put("parte-else", 	14);
		nonTerminalCoding.put("chamada-atr",	15);
		nonTerminalCoding.put("chamada", 		16);
		nonTerminalCoding.put("atribuicao", 	17);
		nonTerminalCoding.put("exp", 			18);
		nonTerminalCoding.put("exp2", 			19);
		nonTerminalCoding.put("exp3", 			20);
		nonTerminalCoding.put("exp4", 			21);
		nonTerminalCoding.put("exp5", 			22);
		nonTerminalCoding.put("exp6", 			23);
		nonTerminalCoding.put("exp7", 			24);
		nonTerminalCoding.put("exp-p1", 		25);
		nonTerminalCoding.put("exp-p2", 		26);
		nonTerminalCoding.put("exp-p3", 		27);
		nonTerminalCoding.put("exp-p4", 		28);
		nonTerminalCoding.put("exp-p5", 		29);
		nonTerminalCoding.put("exp-p6", 		30);
		nonTerminalCoding.put("lista-exp", 		31);
		
		
		terminalCoding.put("ELSE", 			0);
		terminalCoding.put("FLOAT", 		1);
		terminalCoding.put("FOR", 			2);
		terminalCoding.put("IF", 			3);
		terminalCoding.put("INTEGER", 		4);
		terminalCoding.put("NEW", 			5);
		terminalCoding.put("RETURN", 		6);
		terminalCoding.put("STRING", 		7);
		terminalCoding.put("THEN", 			8);
		terminalCoding.put("VOID", 			9);
		terminalCoding.put("WHILE", 		10);
		terminalCoding.put("PLUS", 			11);
		terminalCoding.put("MINUS", 		12);
		terminalCoding.put("ASTERISK", 		13);
		terminalCoding.put("SLASH", 		14);
		terminalCoding.put("SMALLER", 		15);
		terminalCoding.put("GREATER", 		16);
		terminalCoding.put("ATTRIBUTION", 	17);
		terminalCoding.put("EXCLAMATION", 	18);
		terminalCoding.put("SEQUAL", 		19);
		terminalCoding.put("GEQUAL", 		20);
		terminalCoding.put("COMPARISSON", 	21);
		terminalCoding.put("DIFFERENT", 	22);
		terminalCoding.put("SEMICOLON", 	23);
		terminalCoding.put("COMMA", 		24);
		terminalCoding.put("OPARENTHESES", 	25);
		terminalCoding.put("CPARENTHESES", 	26);
		terminalCoding.put("OBRACKET", 		27);
		terminalCoding.put("CBRACKET", 		28);
		terminalCoding.put("OKEYBRACKET", 	29);
		terminalCoding.put("CKEYBRACKET", 	30);
		terminalCoding.put("COMMERCIALE", 	31);
		terminalCoding.put("PIPE", 			32);
		terminalCoding.put("POINT", 		33);
		terminalCoding.put("AND", 			34);
		terminalCoding.put("OR", 			35);
		terminalCoding.put("ID", 			36);
		terminalCoding.put("NUM", 			37);
		terminalCoding.put("REAL", 			38);
		terminalCoding.put("$", 			39);
	}
	
	public int getProductionInt(int line, int collumn) {
		if(parsingTable[line][collumn].equals("ERROR")) {
			return -1;
		} else {
			return Integer.parseInt(parsingTable[line][collumn]);
		}
	}
	
	public int getProductionInt(String nonTerminal, String terminal) {
		int line = getNonTerminalIndex(nonTerminal);
		int collumn = getTerminalIndex(terminal);
		if(line == -1 || collumn == -1) {
			return -1;
		}
		if(parsingTable[line][collumn].equals("ERROR")) {
			return -1;
		} else {
			return Integer.parseInt(parsingTable[line][collumn]);
		}
	}
	
	public String getProductionString(int line, int collumn) {
		return parsingTable[line][collumn];
	}
	
	public String getProductionString(String nonTerminal, String terminal) {
		int line = getNonTerminalIndex(nonTerminal);
		int collumn = getTerminalIndex(terminal);
		if(line == -1 || collumn == -1){
			return "ERROR";
		}
		return parsingTable[line][collumn];
	}
	
	public int getNonTerminalIndex(String nonTerminal) {
		if(nonTerminalCoding.containsKey(nonTerminal)) {
			return nonTerminalCoding.get(nonTerminal).intValue();
		} else {
			return -1;
		}
	}
	
	public int getTerminalIndex(String terminal) {
		if(terminalCoding.containsKey(terminal)) {
			return terminalCoding.get(terminal).intValue();
		} else {
			return -1;
		}
	}
	
}
	