package parser;

import java.util.HashMap;

import parser.definitions.nodes.*;
import parser.tools.Stack;
import parser.tools.TokenFIFOStack;

public class Production2 {
	
	private HashMap<String, Integer> nonTerminalCoding;
	private HashMap<String, Integer> terminalCoding;
	
	public Production2() {
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
		nonTerminalCoding.put("opt-parametro", 	9);
		nonTerminalCoding.put("bloco", 			10);
		nonTerminalCoding.put("dec-variavel", 	11);
		nonTerminalCoding.put("comandos", 		12);
		nonTerminalCoding.put("comando", 		13);
		nonTerminalCoding.put("retorno", 		14);
		nonTerminalCoding.put("parte-else", 	15);
		nonTerminalCoding.put("chamada-atr",	16);
		nonTerminalCoding.put("chamada", 		17);
		nonTerminalCoding.put("atribuicao", 	18);
		nonTerminalCoding.put("exp", 			19);
		nonTerminalCoding.put("exp2", 			20);
		nonTerminalCoding.put("exp3", 			21);
		nonTerminalCoding.put("exp4", 			22);
		nonTerminalCoding.put("exp5", 			23);
		nonTerminalCoding.put("exp6", 			24);
		nonTerminalCoding.put("exp7", 			25);
		nonTerminalCoding.put("exp-p1", 		26);
		nonTerminalCoding.put("exp-p2", 		27);
		nonTerminalCoding.put("exp-p3", 		28);
		nonTerminalCoding.put("exp-p4", 		29);
		nonTerminalCoding.put("exp-p5", 		30);
		nonTerminalCoding.put("exp-p6", 		31);
		nonTerminalCoding.put("lista-exp", 		32);
		
		
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
	
	public void produce(int productionID, Stack productionStack, ASTNode actualNode, TokenFIFOStack tokenStack) {
		switch (productionID) {
		case 0:
			//programa -> declaracoes
			productionStack.pop();
			productionStack.push("declaracoes");
			break;
		case 1:
			//declaracoes -> declaracao declaracoes
			productionStack.pop();
			productionStack.push("declaracoes");
			productionStack.push("declaracao");
			break;
		case 2:
			//declaracao -> tipo-base ID dec-fim
			productionStack.pop();
			productionStack.push("dec-fim");
			productionStack.push("ID");
			productionStack.push("tipo-base");			
			break;
		case 3:
			//declaracao - >void ID dec-funcao
			productionStack.pop();
			productionStack.push("dec-funcao");
			productionStack.push("ID");
			productionStack.push("VOID");
			break;
		case 4:
			//dec-fim -> lista-nomes ;
			productionStack.pop();
			productionStack.push("SEMICOLON");
			productionStack.push("lista-nomes");
			addGlobalVariable((Program) actualNode, tokenStack);
			break;
		case 5:
			//dec-fim -> dec-funcao
			productionStack.pop();
			productionStack.push("dec-funcao");
			break;
		case 6:
			//lista-nomes -> , ID lista-nomes
			productionStack.pop();
			productionStack.push("lista-nomes");
			productionStack.push("ID");
			productionStack.push("COMMA");
			break;
		case 7:
			//tipo-base -> int
			productionStack.pop();
			productionStack.push("INTEGER");
			break;
		case 8:
			//tipo-base -> float
			productionStack.pop();
			productionStack.push("FLOAT");
			break;
		case 9:
			//dec-funcao -> ( parametros ) bloco
			productionStack.pop();
			productionStack.push("bloco");
			productionStack.push("CPARENTHESES");
			productionStack.push("parametros");
			productionStack.push("OPARENTHESES");
			break;
		case 10:
			//parametros -> parametro opt-parametro
			productionStack.pop();
			productionStack.push("opt-parametro");
			productionStack.push("parametro");
			break;
		case 11:
			//parametro -> tipo-base ID
			productionStack.pop();
			productionStack.push("ID");
			productionStack.push("tipo-base");
			break;
		case 12:
			//opt-parametro -> , parametro opt-parametro
			productionStack.pop();
			productionStack.push("opt-parametro");
			productionStack.push("parametro");
			productionStack.push("COMMA");
			break;
		case 13:
			//bloco -> { dec-variavel comandos }
			productionStack.pop();
			productionStack.push("CKEYBRACKET");
			productionStack.push("comandos");
			productionStack.push("dec-variavel");
			productionStack.push("OKEYBRACKET");
			break;
		case 14:
			//dec-variavel -> tipo-base ID lista-nomes ; dec-variavel
			productionStack.pop();
			productionStack.push("dec-variavel");
			productionStack.push("SEMICOLON");
			productionStack.push("lista-nomes");
			productionStack.push("ID");
			productionStack.push("tipo-base");
			break;
		case 15:
			//comandos -> comando comandos
			productionStack.pop();
			productionStack.push("comandos");
			productionStack.push("comando");
			break;
		case 16:
			//comando -> if ( exp ) comando parte-else
			productionStack.pop();
			productionStack.push("parte-else");
			productionStack.push("comando");
			productionStack.push("CPARENTHESES");
			productionStack.push("exp");
			productionStack.push("OPARENTHESES");
			productionStack.push("IF");
			break;
		case 17:
			//comando -> while ( exp ) comando
			productionStack.pop();
			productionStack.push("comando");
			productionStack.push("CPARENTHESES");
			productionStack.push("exp");
			productionStack.push("OPARENTHESES");
			productionStack.push("while");
			break;
		case 18:
			//comando -> for ( lista-exp ; lista-exp ; lista-exp ) comando
			productionStack.pop();
			productionStack.push("comando");
			productionStack.push("CPARENTHESES");
			productionStack.push("lista-exp");
			productionStack.push("SEMICOLON");
			productionStack.push("lista-exp");
			productionStack.push("SEMICOLON");
			productionStack.push("lista-exp");
			productionStack.push("OPARENTHESES");
			productionStack.push("FOR");
			break;
		case 19:
			//comando -> ID chamada-atr ;
			productionStack.pop();
			productionStack.push("SEMICOLON");
			productionStack.push("chamada-atr");
			productionStack.push("ID");
			break;
		case 20:
			//comando -> return retorno ;
			productionStack.pop();
			productionStack.push("SEMICOLON");
			productionStack.push("retorno");
			productionStack.push("RETURN");
			break;
		case 21:
			//comando -> bloco
			productionStack.pop();
			productionStack.push("bloco");
			break;
		case 22:
			//retorno -> exp
			productionStack.pop();
			productionStack.push("exp");
			break;
		case 23:
			//parte-else -> else comando
			productionStack.pop();
			productionStack.push("comando");
			productionStack.push("ELSE");
			break;
		case 24:
			//chamada-atr -> chamada
			productionStack.pop();
			productionStack.push("chamada");
			break;
		case 25:
			//chamada-atr -> atribuicao
			productionStack.pop();
			productionStack.push("atribuicao");
			break;
		case 26:
			//chamada -> ( lista-exp )
			productionStack.pop();
			productionStack.push("CPARENTHESES");
			productionStack.push("lista-exp");
			productionStack.push("OPARENTHESES");
			break;
		case 27:
			//atribuicao -> = exp
			productionStack.pop();
			productionStack.push("exp");
			productionStack.push("ATTRIBUTION");
			break;
		case 28:
			//exp -> exp2 exp-p1
			productionStack.pop();
			productionStack.push("exp-p1");
			productionStack.push("exp2");
			break;
		case 29:
			//exp-p1 -> || exp
			productionStack.pop();
			productionStack.push("exp");
			productionStack.push("OR");
			break;
		case 30:
			//exp2 -> exp3 exp-p2
			productionStack.pop();
			productionStack.push("exp-p2");
			productionStack.push("exp3");
			break;
		case 31:
			//exp-p2 -> && exp2
			productionStack.pop();
			productionStack.push("exp2");
			productionStack.push("AND");
			break;
		case 32:
			//exp3 -> exp4 exp-p3
			productionStack.pop();
			productionStack.push("exp-p3");
			productionStack.push("exp4");
			break;
		case 33:
			//exp-p3 ->  == exp3
			productionStack.pop();
			productionStack.push("exp3");
			productionStack.push("COMPARISSON");
			break;
		case 34:
			//exp-p3 -> != exp3
			productionStack.pop();
			productionStack.push("exp3");
			productionStack.push("DIFFERENT");
			break;
		case 35:
			//exp4 -> exp5 exp-p4
			productionStack.pop();
			productionStack.push("exp-p4");
			productionStack.push("exp5");
			break;
		case 36:
			//exp-p4 -> < exp4
			productionStack.pop();
			productionStack.push("exp4");
			productionStack.push("SMALLER");
			break;
		case 37:
			//exp-p4 -> > exp4
			productionStack.pop();
			productionStack.push("exp4");
			productionStack.push("GREATER");
			break;
		case 38:
			//exp-p4 -> <= exp4
			productionStack.pop();
			productionStack.push("exp4");
			productionStack.push("SEQUAL");
			break;
		case 39:
			//exp-p4 -> >= exp4
			productionStack.pop();
			productionStack.push("exp4");
			productionStack.push("GEQUAL");
			break;
		case 40:
			//exp5 -> exp6 exp-p5
			productionStack.pop();
			productionStack.push("exp-p5");
			productionStack.push("exp6");
			break;
		case 41:
			//exp-p5 -> - exp-p5
			productionStack.pop();
			productionStack.push("exp5");
			productionStack.push("MINUS");
			break;
		case 42:
			//exp-p5 -> + exp-p5
			productionStack.pop();
			productionStack.push("exp5");
			productionStack.push("PLUS");
			break;
		case 43:
			//exp6 -> NUM exp-p6
			productionStack.pop();
			productionStack.push("exp-p6");
			productionStack.push("NUM");
			break;
		case 44:
			//exp6 -> REAL exp-p6
			productionStack.pop();
			productionStack.push("exp-p6");
			productionStack.push("REAL");
			break;
		case 45:
			//exp6 -> ( exp ) exp-p6
			productionStack.pop();
			productionStack.push("exp-p6");
			productionStack.push("CPARENTHESES");
			productionStack.push("exp");
			productionStack.push("OPARENTHESES");
			break;
		case 46:
			//exp6 -> ID exp7
			productionStack.pop();
			productionStack.push("exp7");
			productionStack.push("ID");
			break;
		case 47:
			//exp6 -> ! exp 
			productionStack.pop();
			productionStack.push("exp");
			productionStack.push("EXCLAMATION");
			break;
		case 48:
			//exp6 -> - exp
			productionStack.pop();
			productionStack.push("exp");
			productionStack.push("MINUS");
			break;
		case 49:
			//exp-p6 -> / exp6
			productionStack.pop();
			productionStack.push("exp6");
			productionStack.push("SLASH");
			break;
		case 50:
			//exp-p6 -> * exp6
			productionStack.pop();
			productionStack.push("exp6");
			productionStack.push("ASTERISK");
			break;
		case 51:
			//exp7 -> chamada
			productionStack.pop();
			productionStack.push("chamada");
			break;
		case 52:
			//exp7 -> exp-p6
			productionStack.pop();
			productionStack.push("exp-p6");
		case 53:
			//lista-exp -> exp lista-exp
			productionStack.pop();
			productionStack.push("lista-exp");
			productionStack.push("exp");
			break;
		case 54:
			//lista-exp -> , exp lista-exp
			productionStack.pop();
			productionStack.push("lista-exp");
			productionStack.push("exp");
			productionStack.push("COMMA");
			break;
		case 55:
			//EPSILON
			productionStack.pop();
		default:
			break;
		}
	}
	
	private void addGlobalVariable(Program program, TokenFIFOStack tokenStack) {
		String variableID = tokenStack.pop().getTokenValue();
		String variableType = tokenStack.checkTop().getTokenType();
		program.addVariable(new VariableNode(variableType, variableID, null, program));
	}
	
	public void addFunction(ASTNode actualNode, TokenFIFOStack tokenStack) {
		Program program = (Program) actualNode;
		FunctionNode function = new FunctionNode(program);
		String functionID = tokenStack.pop().getTokenValue();
		String returnType = tokenStack.pop().getTokenValue();
		function.setReturnType(returnType);
		function.setFunctioID(functionID);
		program.addFunction(function);
//		actualNode = 
	}

}
