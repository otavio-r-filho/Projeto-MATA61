package parser;

import java.util.ArrayList;
import java.util.HashMap;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import jdk.nashorn.internal.ir.BinaryNode;
import jdk.nashorn.internal.ir.CallNode;
import jdk.nashorn.internal.ir.UnaryNode;
import parser.definitions.nodes.*;
import parser.tools.Stack;
import parser.tools.TokenFIFOStack;
import lexer.definitions.*;

public class Production2 {
	
	private HashMap<String, Integer> nonTerminalCoding;
	private HashMap<String, Integer> terminalCoding;
	
	private int nodeID;
	
	public Production2() {
		nonTerminalCoding = new HashMap<String, Integer>();
		terminalCoding = new HashMap<String, Integer>();
		nodeID = 1;
		
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
		case 4: //Creates a variable in the main scope - variableType and variableID already in the stack
			//dec-fim -> lista-nomes ;
			productionStack.pop();
			productionStack.push("SEMICOLON");
			productionStack.push("lista-nomes");
			addVariable(actualNode, tokenStack); //Will return the actualNode
			return actualNode;
		case 5: //Creates a function
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
			return addVariable(actualNode, tokenStack.checkTop(), null, null, "VARIABLE"); //Will return a VariableNode to be completed
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
			return addFunction((Program)actualNode, tokenStack);
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
			return addVariable((FunctionNode) actualNode, null, null, null, "PARAMETER");
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
			return addVariable(actualNode, null, null, null, "VARIABLE");
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
			return addCommand(actualNode, tokenList, tokenPosition, tokenStack);
		case 17:
			//comando -> while ( exp ) comando
			productionStack.pop();
			productionStack.push("comando");
			productionStack.push("CPARENTHESES");
			productionStack.push("exp");
			productionStack.push("OPARENTHESES");
			productionStack.push("WHILE");
			return addCommand(actualNode, tokenList, tokenPosition, tokenStack);
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
			return addCommand(actualNode, tokenList, tokenPosition, tokenStack);
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
			return addCommand(actualNode, tokenList, tokenPosition, tokenStack);
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
			return addCommand(actualNode, tokenList, tokenPosition, tokenStack);
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
			if(tokenStack.checkTop().getTokenValue().equals("println")) {
				productionStack.push("exp");
			} else {
				productionStack.push("lista-exp");
			}
			productionStack.push("OPARENTHESES");
			return addCommand(actualNode, tokenList, tokenPosition, tokenStack);
		case 27:
			//atribuicao -> = exp
			productionStack.pop();
			productionStack.push("exp");
			productionStack.push("ATTRIBUTION");
			return addCommand(actualNode, tokenList, tokenPosition, tokenStack);
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
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
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
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
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
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
		case 34:
			//exp-p3 -> != exp3
			productionStack.pop();
			productionStack.push("exp3");
			productionStack.push("DIFFERENT");
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
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
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
		case 37:
			//exp-p4 -> > exp4
			productionStack.pop();
			productionStack.push("exp4");
			productionStack.push("GREATER");
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
		case 38:
			//exp-p4 -> <= exp4
			productionStack.pop();
			productionStack.push("exp4");
			productionStack.push("SEQUAL");
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
		case 39:
			//exp-p4 -> >= exp4
			productionStack.pop();
			productionStack.push("exp4");
			productionStack.push("GEQUAL");
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
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
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
		case 42:
			//exp-p5 -> + exp-p5
			productionStack.pop();
			productionStack.push("exp5");
			productionStack.push("PLUS");
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
		case 43:
			//exp6 -> NUM exp-p6
			productionStack.pop();
			productionStack.push("exp-p6");
			productionStack.push("NUM");
			return addConstantExpression(actualNode, tokenList, tokenPosition);
		case 44:
			//exp6 -> REAL exp-p6
			productionStack.pop();
			productionStack.push("exp-p6");
			productionStack.push("REAL");
			return addConstantExpression(actualNode, tokenList, tokenPosition);
		case 45:
			//exp6 -> ( exp ) exp-p6
			productionStack.pop();
			productionStack.push("exp-p6");
			productionStack.push("CPARENTHESES");
			productionStack.push("exp");
			productionStack.push("OPARENTHESES");
			return addUnaryExpression(actualNode, tokenList, tokenPosition);
		case 46:
			//exp6 -> ID exp7
			productionStack.pop();
			productionStack.push("exp7");
			productionStack.push("ID");
			if(tokenList.get(tokenPosition + 1).getTokenType().equals("SEMICOLON") || tokenList.get(tokenPosition + 1).getTokenType().equals("CPARENTHESES") ||
			   tokenList.get(tokenPosition + 1).getTokenType().equals("GREATER") || tokenList.get(tokenPosition + 1).getTokenType().equals("SMALLER") ||
			   tokenList.get(tokenPosition + 1).getTokenType().equals("GEQUAL") || tokenList.get(tokenPosition + 1).getTokenType().equals("SEQUAL") ||
			   tokenList.get(tokenPosition + 1).getTokenType().equals("PLUS") || tokenList.get(tokenPosition + 1).getTokenType().equals("MINUS") ||
			   tokenList.get(tokenPosition + 1).getTokenType().equals("COMPARISSON") || tokenList.get(tokenPosition + 1).getTokenType().equals("DIFFERENT")) {
				return addConstantExpression(actualNode, tokenList, tokenPosition);
			}
			break;
		case 47:
			//exp6 -> ! exp 
			productionStack.pop();
			productionStack.push("exp");
			productionStack.push("EXCLAMATION");
			return addUnaryExpression(actualNode, tokenList, tokenPosition);
		case 48:
			//exp6 -> - exp
			productionStack.pop();
			productionStack.push("exp");
			productionStack.push("MINUS");
			return addUnaryExpression(actualNode, tokenList, tokenPosition);
		case 49:
			//exp-p6 -> / exp6
			productionStack.pop();
			productionStack.push("exp6");
			productionStack.push("SLASH");
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
		case 50:
			//exp-p6 -> * exp6
			productionStack.pop();
			productionStack.push("exp6");
			productionStack.push("ASTERISK");
			return addBinaryExpression(actualNode, tokenList, tokenPosition);
		case 51:
			//exp7 -> chamada
			productionStack.pop();
			productionStack.push("chamada");
//			return addCallExpression(actualNode, tokenStack);
			break;
		case 52:
			//exp7 -> exp-p6
			productionStack.pop();
			productionStack.push("exp-p6");
			return addConstantExpression(actualNode, tokenStack.pop());
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
	
	public ASTNode addVariable(ASTNode actualNode, TokenFIFOStack tokenStack) {
		tToken variableID = tokenStack.pop();
		tToken variableType = tokenStack.checkTop();
		addVariable(actualNode, variableType, variableID, null, "VARIABLE"); //This function is used to add the variable to the actualNode
		return actualNode;
	}
	
	public VariableNode addVariable(ASTNode actualNode, tToken variableType, tToken variableID, tToken variableValue, String nodeType) {
		VariableNode variable = new VariableNode(variableType, variableID, variableValue, actualNode);
		variable.setNodeType(nodeType);
		variable.setNodeID(nodeID);
		nodeID++;
		switch(actualNode.getNodeType()) {
		case "PROGRAM":
			Program program = (Program)actualNode;
			program.addVariable(variable);
			break;
		case "BLOCK":
			BlockNode block = (BlockNode)actualNode;
			block.addVariable(variable);
			break;
		case "FUNCTION":
			FunctionNode function = (FunctionNode) actualNode;
			function.addParameter(variable);
			break;
		}
		return variable;		
	}
	
	public FunctionNode addFunction(ASTNode actualNode, TokenFIFOStack tokenStack) {
		Program program = (Program) actualNode;
		FunctionNode function = new FunctionNode(program);
		function.setFunctionID(tokenStack.pop());
		function.setReturnType(tokenStack.pop());
		function.setNodeType("FUNCTION");
		function.setNodeID(nodeID);
		nodeID++;
		program.addFunction(function);
		return function;
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
		block.setNodeID(nodeID);
		nodeID++;
		return block;
	}
	
	public CommandNode addCommand(ASTNode actualNode, ArrayList<tToken> tokenList, int tokenPosition, TokenFIFOStack tokenStack) {
		IfNode ifNode;
		ElseNode elseNode;
		WhileNode whileNode;
		ForNode forNode;
		ReturnNode returnNode;
		CallExpression callNode;
		AttributionNode attributionNode;
		switch(tokenList.get(tokenPosition).getTokenType()) {
		case "IF":
			ifNode = new IfNode();
			ifNode.setNodeID(nodeID);
			nodeID++;
			ifNode.setCommandType(tokenList.get(tokenPosition));
			ifNode.setNodeType("IF");
			setCommand(actualNode, ifNode);
			ifNode.setFatherNode(actualNode);
			return ifNode;
		case "ELSE":
			elseNode = new ElseNode();
			elseNode.setNodeID(nodeID);
			nodeID++;
			elseNode.setCommandType(tokenList.get(tokenPosition));
			elseNode.setNodeType("ELSE");
//			setCommand(actualNode, elseNode);
			ifNode = (IfNode) actualNode;
			ifNode.setElseCommand(elseNode);
			elseNode.setFatherNode(actualNode);
			return elseNode;
		case "WHILE":
			whileNode = new WhileNode();
			whileNode.setNodeID(nodeID);
			nodeID++;
			whileNode.setCommandType(tokenList.get(tokenPosition));
			whileNode.setNodeType("WHILE");
			setCommand(actualNode, whileNode);
			whileNode.setFatherNode(actualNode);
			return whileNode;
		case "FOR":
			forNode = new ForNode();
			forNode.setNodeID(nodeID);
			nodeID++;
			forNode.setCommandType(tokenList.get(tokenPosition));
			forNode.setNodeType("FOR");
			setCommand(actualNode, forNode);
			forNode.setFatherNode(actualNode);
			return forNode;
		case "RETURN":
			returnNode = new ReturnNode();
			returnNode.setNodeID(nodeID);
			nodeID++;
			returnNode.setCommandType(tokenList.get(tokenPosition));
			returnNode.setNodeType("RETURN");
			setCommand(actualNode, returnNode);
			returnNode.setFatherNode(actualNode);
			if(tokenList.get(tokenPosition + 1).getTokenType().equals("SEMICOLON")){
				return (CommandNode)actualNode;
			}
			return returnNode;
		case "OPARENTHESES":
			if(tokenStack.checkTop().getTokenValue().equals("println")) {
				PrintNode printNode = new PrintNode(actualNode);
				tToken printToken = new tToken("PRINT", "println", 0, tokenStack.checkTop().getLine(), tokenStack.checkTop().getCollumn());
				tokenStack.pop();
				printNode.setNodeID(nodeID);
				printNode.setCommandType(printToken);
				setCommand(actualNode, printNode);
				nodeID++;
				return printNode;
			} else {
				callNode = new CallExpression(actualNode, tokenStack.checkTop());
				callNode.setNodeID(nodeID);
				nodeID++;
				callNode.setCommandType(tokenStack.pop());
				callNode.setNodeType("CALL");
				callNode.setExpressionPrecedence(7);
				setExpression(actualNode, callNode);
				callNode.setFatherNode(actualNode);
				return callNode;
			}
		case "ATTRIBUTION":
			attributionNode = new AttributionNode();
			attributionNode.setNodeID(nodeID);
			nodeID++;
			attributionNode.setCommandType(tokenStack.pop());
			attributionNode.setNodeType("ATTRIBUTION");
			setCommand(actualNode, attributionNode);
			attributionNode.setFatherNode(actualNode);
			return attributionNode;			
		}
		return null;
	}
	
	public void setCommand(ASTNode actualNode,CommandNode command) {
		BlockNode blockFather;
		IfNode ifFather;
		ElseNode elseFather;
		WhileNode whileFather;
		ForNode forFather;
		switch(actualNode.getNodeType()) {
		case "BLOCK":
			blockFather = (BlockNode) actualNode;
			blockFather.addCommand(command);
			command.setFatherNode(blockFather);
			break;
		case "IF":
			ifFather = (IfNode) actualNode;
			ifFather.setCommand(command);
			command.setFatherNode(ifFather);
			break;
		case "ELSE":
			elseFather = (ElseNode) actualNode;
			elseFather.setCommand(command);
			command.setFatherNode(elseFather);
			break;
		case "WHILE":
			whileFather = (WhileNode) actualNode;
			whileFather.setCommand(command);
			command.setFatherNode(whileFather);
			break;
		case "FOR":
			forFather = (ForNode) actualNode;
			forFather.setCommand(command);
			command.setFatherNode(forFather);
			break;
		}
	}
	
	public VariableNode addConstantExpression(ASTNode actualNode, ArrayList<tToken> tokenList, int tokenPosition) {
		VariableNode constant = null;
		constant = new VariableNode(tokenList.get(tokenPosition), tokenList.get(tokenPosition), tokenList.get(tokenPosition), actualNode);
		constant.setNodeType("CONSTANT");
		constant.setNodeID(nodeID);
		nodeID++;
		constant.setExpressionType(tokenList.get(tokenPosition));
		constant.setExpressionPrecedence(0);
		return (VariableNode) setExpression(actualNode, constant);
	}

	public VariableNode addConstantExpression(ASTNode actualNode, tToken token) {
		VariableNode constant = null;
		constant = new VariableNode(token, token, token, actualNode);
		constant.setNodeType("CONSTANT");
		constant.setNodeID(nodeID);
		nodeID++;
		constant.setExpressionType(token);
		constant.setExpressionPrecedence(0);
		return (VariableNode) setExpression(actualNode, constant);
	}
	
	public BinaryExpression addBinaryExpression(ASTNode actualNode, ArrayList<tToken> tokenList, int tokenPosition) {
		BinaryExpression binaryExpression = new BinaryExpression(tokenList.get(tokenPosition), actualNode);
		binaryExpression.setNodeType("BINARYEXPRESSION");
		binaryExpression.setNodeID(nodeID);
		nodeID++;
		switch (tokenList.get(tokenPosition).getTokenType()) {
		case "OR":
			binaryExpression.setExpressionPrecedence(1);
			break;
		case "AND":
			binaryExpression.setExpressionPrecedence(2);
			break;
		case "COMPARISSON":
			binaryExpression.setExpressionPrecedence(3);
			break;
		case "DIFFERENT":
			binaryExpression.setExpressionPrecedence(3);
			break;
		case "SMALLER":
			binaryExpression.setExpressionPrecedence(4);
			break;
		case "GREATER":
			binaryExpression.setExpressionPrecedence(4);
			break;
		case "SEQUAL":
			binaryExpression.setExpressionPrecedence(4);
			break;
		case "GEQUAL":
			binaryExpression.setExpressionPrecedence(4);
			break;
		case "PLUS":
			binaryExpression.setExpressionPrecedence(5);
			break;
		case "MINUS":
			binaryExpression.setExpressionPrecedence(5);
			break;
		case "ASTERISK":
			binaryExpression.setExpressionPrecedence(6);
			break;
		case "SLASH":
			binaryExpression.setExpressionPrecedence(6);
			break;

		default:
			break;
		}
		return (BinaryExpression) setExpression(actualNode, binaryExpression);
	}
	
	public UnaryExpression addUnaryExpression(ASTNode actualNode, ArrayList<tToken> tokenList, int tokenPosition) {
		UnaryExpression unaryExpression = new UnaryExpression(tokenList.get(tokenPosition), actualNode);
		unaryExpression.setExpressionPrecedence(7);
		unaryExpression.setNodeType("UNARYEXPRESSION");
		unaryExpression.setNodeID(nodeID);
		nodeID++;
		return (UnaryExpression) setExpression(actualNode, unaryExpression);
	}
	
	public CallExpression addCallExpression(ASTNode actualNode, TokenFIFOStack tokenStack) {
		CallExpression callExpression = new CallExpression(actualNode, tokenStack.pop());
		callExpression.setExpressionPrecedence(7);
		callExpression.setNodeType("CALL");
		callExpression.setCommandType(callExpression.getFunctionID());
		callExpression.setNodeID(nodeID);
		nodeID++;
		return (CallExpression) setExpression(actualNode, callExpression);
	}
	
	public ExpressionNode setExpression(ASTNode actualNode, ExpressionNode expression) {
		IfNode ifNode;
		WhileNode whileNode;
		ForNode forNode;
		CallExpression callExpression;
		AttributionNode attributionNode;
		ReturnNode returnNode;
		BinaryExpression binaryExpression;
		UnaryExpression unaryExpression;

		switch(actualNode.getNodeType()) {
		case "IF":
			ifNode = (IfNode)actualNode;
			ifNode.setConditionExpression(expression);
			break;
		case "WHILE":
			whileNode = (WhileNode)actualNode;
			whileNode.setConditionExpression(expression);
			break;
		case "FOR":
			forNode = (ForNode)actualNode;
			forNode.addExpression(expression);
			break;
		case "CALL":
			callExpression = (CallExpression) actualNode;
			callExpression.addExpression(expression);
			break;
		case "PRINT":
			((PrintNode) actualNode).setExpression(expression);
			break;
		case "ATTRIBUTION":
			attributionNode = (AttributionNode) actualNode;
			attributionNode.setExpression(expression);
			break;
		case "RETURN":
			returnNode = (ReturnNode) actualNode;
			returnNode.setReturnExpression(expression);
			break;
		case "BINARYEXPRESSION":
			binaryExpression = (BinaryExpression) actualNode;
			binaryExpression.setRhsExpression(expression);
			break;
		case "UNARYEXPRESSION":
			unaryExpression = (UnaryExpression) actualNode;
			if(expression.getNodeType().equals("BINARYEXPRESSION")) {
				binaryExpression = (BinaryExpression) expression;
				binaryExpression.setLhsExpression(unaryExpression);
				swapExpression(unaryExpression, binaryExpression);
			} else {
				unaryExpression.setExpression(expression);
			}
			break;
		case "CONSTANT":
			if(!actualNode.getFatherNode().getNodeType().equals("BINARYEXPRESSION")) {
				binaryExpression = (BinaryExpression) expression;
				binaryExpression.setLhsExpression((VariableNode) actualNode);
				swapExpression((ExpressionNode) actualNode, expression);
			} else {
				BinaryExpression actualExpression = (BinaryExpression) actualNode.getFatherNode();
				binaryExpression = (BinaryExpression) expression;
				if(actualExpression.getExpressionPrecedence() < expression.getExpressionPrecedence()) {
					binaryExpression.setLhsExpression(actualExpression.getRhsExpression());
					actualExpression.getRhsExpression().setFatherNode(binaryExpression);
					binaryExpression.setFatherNode(actualExpression);
					actualExpression.setRhsExpression(binaryExpression);
//					swapExpression(actualExpression, binaryExpression);					
				} else {
					ASTNode nextNode = actualExpression.getFatherNode();
					while(nextNode.getNodeType().equals("BINARYEXPRESSION")) {
						actualExpression = (BinaryExpression) nextNode;
						if(actualExpression.getExpressionPrecedence() < binaryExpression.getExpressionPrecedence()) {
							binaryExpression.setLhsExpression(actualExpression.getRhsExpression());
							actualExpression.setRhsExpression(binaryExpression);
							swapExpression(actualExpression, binaryExpression);
							return binaryExpression;
						}
						nextNode = actualExpression.getFatherNode();
					}
					binaryExpression.setLhsExpression(actualExpression);
					swapExpression(actualExpression, binaryExpression);
					return binaryExpression;
				}
			}
			break;
		}
		return expression;
	}
	
	public void swapExpression(ExpressionNode oldExpression, ExpressionNode newExpression) {
		/*
		 * Search inside: IfNode, WhileNode, ForNode, ReturnNode, CallExpression, AttributionNode, UnaryExpression, BinaryExpression
		 */
		
		IfNode ifNode;
		WhileNode whileNode;
		ForNode forNode;
		ReturnNode returnNode;
		CallExpression callExpression;
		AttributionNode attributionNode;
		UnaryExpression unaryExpression;
		BinaryExpression binaryExpression;
		ArrayList<ExpressionNode> expressions;
		
		switch(oldExpression.getFatherNode().getNodeType()) {
		case "IF":
			ifNode = (IfNode) oldExpression.getFatherNode();
			ifNode.setConditionExpression(newExpression);
			break;
		case "WHILE":
			whileNode = (WhileNode) oldExpression.getFatherNode();
			whileNode.setConditionExpression(newExpression);
			break;
		case "FOR":
			forNode = (ForNode) oldExpression.getFatherNode();
			expressions = forNode.getExpressionList();
			expressions.add(expressions.indexOf(oldExpression), newExpression);
			expressions.remove(oldExpression);
			break;
		case "RETURN":
			returnNode = (ReturnNode) oldExpression.getFatherNode();
			returnNode.setReturnExpression(newExpression);
			break;
		case "CALL":
			callExpression = (CallExpression) oldExpression.getFatherNode();
			expressions = callExpression.getExpressionList();
			expressions.add(expressions.indexOf(oldExpression), newExpression);
			expressions.remove(oldExpression);
			break;
		case "PRINT":
			PrintNode printNode = (PrintNode) oldExpression.getFatherNode();
			printNode.setExpression(newExpression);
			break;
		case "ATTRIBUTION":
			attributionNode = (AttributionNode) oldExpression.getFatherNode();
			attributionNode.setExpression(newExpression);
			break;
		case "UNARYEXPRESSION":
			unaryExpression = (UnaryExpression) oldExpression.getFatherNode();
			unaryExpression.setExpression(newExpression);
			break;
		case "BINARYEXPRESSION":
			binaryExpression = (BinaryExpression) oldExpression.getFatherNode();
			binaryExpression.setLhsExpression(newExpression);
			break;
		}
		newExpression.setFatherNode(oldExpression.getFatherNode());
		oldExpression.setFatherNode(newExpression);
	}
}
