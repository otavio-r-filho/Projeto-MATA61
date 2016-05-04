package parser;

import java.util.ArrayList;
import java.util.HashMap;

import parser.definitions.nodes.*;
import parser.tools.Stack;
import parser.tools.TokenFIFOStack;
import lexer.definitions.*;

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
	
	public ASTNode produce(int productionID, Stack productionStack, ASTNode actualNode, TokenFIFOStack tokenStack, ArrayList<tToken> tokenList, int tokenPosition) {
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
		case 4: //Creates a variable in the general scope
			//dec-fim -> lista-nomes ;
			productionStack.pop();
			productionStack.push("SEMICOLON");
			productionStack.push("lista-nomes");
			return addVariable(actualNode, tokenStack);
		case 5: //Creates a function
			//dec-fim -> dec-funcao
			productionStack.pop();
			productionStack.push("dec-funcao");
			return addFunction((Program)actualNode, tokenStack);
		case 6:
			//lista-nomes -> , ID lista-nomes
			productionStack.pop();
			productionStack.push("lista-nomes");
			productionStack.push("ID");
			productionStack.push("COMMA");
			return addVariable(actualNode, tokenStack);
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
			addParameter((FunctionNode) actualNode, tokenStack, tokenList, tokenPosition);
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
			return addBlock(actualNode);
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
			return addCommand(actualNode, tokenList, tokenPosition);
		case 17:
			//comando -> while ( exp ) comando
			productionStack.pop();
			productionStack.push("comando");
			productionStack.push("CPARENTHESES");
			productionStack.push("exp");
			productionStack.push("OPARENTHESES");
			productionStack.push("while");
			return addCommand(actualNode, tokenList, tokenPosition);
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
			return addCommand(actualNode, tokenList, tokenPosition);
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
			return addReturn(actualNode, tokenList, tokenPosition);
		case 21:
			//comando -> bloco
			productionStack.pop();
			productionStack.push("bloco");
			return addBlock(actualNode);
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
			return addCommand(actualNode, tokenList, tokenPosition);
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
		return actualNode;
	}
	
	private ASTNode addVariable(ASTNode actualNode, TokenFIFOStack tokenStack) {
		String variableID = tokenStack.pop().getTokenValue();
		String variableType = tokenStack.checkTop().getTokenType();
		if(actualNode.getNodeType().equals("PROGRAM")) {
			Program program = (Program)actualNode;
			program.addVariable(new VariableNode(variableType, variableID, null, program));
			return program;
		}
		BlockNode block = (BlockNode)actualNode;
		block.addVariable(new VariableNode(variableType, variableID, null, block));
		return block;
	}
	
	public FunctionNode addFunction(ASTNode actualNode, TokenFIFOStack tokenStack) {
		Program program = (Program) actualNode;
		FunctionNode function = new FunctionNode(program);
		String functionID = tokenStack.pop().getTokenValue();
		String returnType = tokenStack.pop().getTokenValue();
		function.setReturnType(returnType);
		function.setFunctioID(functionID);
		program.addFunction(function);
		return function;
	}
	
	public void addParameter(FunctionNode function, TokenFIFOStack tokenStack, ArrayList<tToken> tokenList, int tokenPosition) {
		function.addParameter(new VariableNode(tokenStack.pop().getTokenType(), tokenList.get(tokenPosition + 1).getTokenValue(), null, function));
	}
	
	public BlockNode addBlock(ASTNode actualNode) {
		BlockNode block = null;
		switch(actualNode.getNodeType()) {
		case "FUNCTION":
			FunctionNode function = (FunctionNode) actualNode;
			block = new BlockNode(function);
			function.setBlock(block);
			break;
		case "IF":
			IfNode ifNode = (IfNode) actualNode;
			block = new BlockNode(actualNode);
			ifNode.setCommand(block);
			break;
		case "ELSE":
			ElseNode elseNode = (ElseNode) actualNode;
			block = new BlockNode(elseNode);
			elseNode.setCommand(block);
			break;
		case "WHILE":
			WhileNode whileNode = (WhileNode) actualNode;
			block = new BlockNode(whileNode);
			whileNode.setCommand(block);
			break;
		case "FOR":
			ForNode forNode = (ForNode) actualNode;
			block = new BlockNode(forNode);
			forNode.setCommand(block);
			break;
		}
		return block;
	}
	
	public CommandNode addCommand(ASTNode actualNode, ArrayList<tToken> tokenList, int tokenPosition) {
		switch(tokenList.get(tokenPosition).getTokenType()) {
		case "IF":
			IfNode ifNode = new IfNode();
			switch(actualNode.getNodeType()) {
			case "BLOCK":
				BlockNode blockFather = (BlockNode) actualNode;
				blockFather.addCommand(ifNode);
				ifNode.setFatherNode(blockFather);
				break;
			case "IF":
				IfNode ifFather = (IfNode) actualNode;
				ifFather.setCommand(ifNode);
				ifNode.setFatherNode(ifFather);
				break;
			case "ELSE":
				ElseNode elseFather = (ElseNode) actualNode;
				elseFather.setCommand(ifNode);
				ifNode.setFatherNode(elseFather);
				break;
			case "WHILE":
				WhileNode whileFather = (WhileNode) actualNode;
				whileFather.setCommand(ifNode);
				ifNode.setFatherNode(whileFather);
				break;
			case "FOR":
				ForNode forFather = (ForNode) actualNode;
				forFather.setCommand(ifNode);
				ifNode.setFatherNode(forFather);
				break;
			}
			return ifNode;
		case "ELSE":
			ElseNode elseNode = new ElseNode();
			switch(actualNode.getNodeType()) {
			case "BLOCK":
				BlockNode blockFather = (BlockNode) actualNode;
				blockFather.addCommand(elseNode);
				elseNode.setFatherNode(blockFather);
				break;
			case "IF":
				IfNode ifFather = (IfNode) actualNode;
				ifFather.setCommand(elseNode);
				elseNode.setFatherNode(ifFather);
				break;
			case "ELSE":
				ElseNode elseFather = (ElseNode) actualNode;
				elseFather.setCommand(elseNode);
				elseNode.setFatherNode(elseFather);
				break;
			case "WHILE":
				WhileNode whileFather = (WhileNode) actualNode;
				whileFather.setCommand(elseNode);
				elseNode.setFatherNode(whileFather);
				break;
			case "FOR":
				ForNode forFather = (ForNode) actualNode;
				forFather.setCommand(elseNode);
				elseNode.setFatherNode(forFather);
				break;
			}
			return elseNode;
		case "WHILE":
			WhileNode whileNode = new WhileNode();
			switch(actualNode.getNodeType()) {
			case "BLOCK":
				BlockNode blockFather = (BlockNode) actualNode;
				blockFather.addCommand(whileNode);
				whileNode.setFatherNode(blockFather);
				break;
			case "IF":
				IfNode ifFather = (IfNode) actualNode;
				ifFather.setCommand(whileNode);
				whileNode.setFatherNode(ifFather);
				break;
			case "ELSE":
				ElseNode elseFather = (ElseNode) actualNode;
				elseFather.setCommand(whileNode);
				whileNode.setFatherNode(elseFather);
				break;
			case "WHILE":
				WhileNode whileFather = (WhileNode) actualNode;
				whileFather.setCommand(whileNode);
				whileNode.setFatherNode(whileFather);
				break;
			case "FOR":
				ForNode forFather = (ForNode) actualNode;
				forFather.setCommand(whileNode);
				whileNode.setFatherNode(forFather);
				break;
			}
			return whileNode;
		case "FOR":
			ForNode forNode = new ForNode();
			switch(actualNode.getNodeType()) {
			case "BLOCK":
				BlockNode blockFather = (BlockNode) actualNode;
				blockFather.addCommand(forNode);
				forNode.setFatherNode(blockFather);
				break;
			case "IF":
				IfNode ifFather = (IfNode) actualNode;
				ifFather.setCommand(forNode);
				forNode.setFatherNode(ifFather);
				break;
			case "ELSE":
				ElseNode elseFather = (ElseNode) actualNode;
				elseFather.setCommand(forNode);
				forNode.setFatherNode(elseFather);
				break;
			case "WHILE":
				WhileNode whileFather = (WhileNode) actualNode;
				whileFather.setCommand(forNode);
				forNode.setFatherNode(whileFather);
				break;
			case "FOR":
				ForNode forFather = (ForNode) actualNode;
				forFather.setCommand(forNode);
				forNode.setFatherNode(forFather);
				break;
			}
			return forNode;
		}
		return null;
	}

	public ASTNode addReturn(ASTNode actualNode, ArrayList<tToken> tokenList, int tokenPosition) {
		ReturnNode returnNode = new ReturnNode();
		switch(actualNode.getNodeType()) {
		case "BLOCK":
			BlockNode block = (BlockNode)actualNode;
			block.addCommand(returnNode);
			if(tokenList.get(tokenPosition + 1).getTokenType().equals("SEMICOLON")){
				return block;
			}
			return returnNode;
		case "IF":
			IfNode ifNode = (IfNode)actualNode;
			ifNode.setCommand(returnNode);
			if(tokenList.get(tokenPosition + 1).getTokenType().equals("SEMICOLON")){
				return ifNode;
			}
			return returnNode;
		case "ELSE":
			ElseNode elseNode = (ElseNode)actualNode;
			elseNode.setCommand(returnNode);
			if(tokenList.get(tokenPosition + 1).getTokenType().equals("SEMICOLON")){
				return elseNode;
			}
			return returnNode;
		case "WHILE":
			WhileNode whileNode = (WhileNode)actualNode;
			whileNode.setCommand(returnNode);
			if(tokenList.get(tokenPosition + 1).getTokenType().equals("SEMICOLON")){
				return whileNode;
			}
			return returnNode;
		case "FOR":
			ForNode forNode = (ForNode)actualNode;
			forNode.setCommand(returnNode);
			if(tokenList.get(tokenPosition + 1).getTokenType().equals("SEMICOLON")){
				return forNode;
			}
			return returnNode;
		}
		return null;
	}
}
