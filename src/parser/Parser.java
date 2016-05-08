package parser;

import parser.definitions.nodes.*;
import parser.tools.*;

import java.util.ArrayList;
import java.util.HashMap;

import lexer.definitions.*;

public class Parser {
	private Program programNode;
	private ASTNode actualNode;
	
	private Stack treeStack;
	
	private int tokenPosition;
	
	private Production2 production;
	private ParsingTable2 parsingTable;
	
	private HashMap<String, Integer> meaningfulTerminals;
	
	private TokenFIFOStack tokenStack;
	
	public Parser() {
		treeStack = new Stack();
		treeStack.push("$");
		treeStack.push("programa");
		
		tokenPosition = 0;
		
		production = new Production2();
		parsingTable = new ParsingTable2();
		
		//Initialize the AST with a program node;
		programNode = new Program();
		programNode.setNodeID(0);
		
		actualNode = programNode;
		
		meaningfulTerminals = new HashMap<String, Integer>();
		meaningfulTerminals.put("INTEGER", 0);				//Keys with value 0 are stackable
		meaningfulTerminals.put("FLOAT", 0);
		meaningfulTerminals.put("VOID", 0);
		meaningfulTerminals.put("ID", 0);
		meaningfulTerminals.put("NUM", 0);
		meaningfulTerminals.put("REAL", 0);
		meaningfulTerminals.put("IF", 1);					//Keys with value 1 are for imediate instantiation
		meaningfulTerminals.put("ELSE", 1);
		meaningfulTerminals.put("WHILE", 1);
		meaningfulTerminals.put("FOR", 1);
		meaningfulTerminals.put("RETURN", 1);
		meaningfulTerminals.put("OKEYBRACKET", 2);			//Keys with value 2 are for instatiation evaluation
		meaningfulTerminals.put("OPARENTHESES", 2);
		meaningfulTerminals.put("CPARENTHESES", 2);
		meaningfulTerminals.put("COMMA", 2);
		meaningfulTerminals.put("SEMICOLON", 2);
		meaningfulTerminals.put("SEMICOLON", 2);
		
		tokenStack = new TokenFIFOStack(1);
	}
	
	private boolean checkSyntaxRecursive(ArrayList<tToken> tokenList) {
		
		//Stop condition for the recursion
//		if(tokenList.get(position).getTokenType().equals("$") && itMatches(tokenList.get(position))) {
//			return true;
//		}		
		if(treeStack.isEmpty() && (tokenPosition == (tokenList.size() - 1))) {
			return true;
		} else {
			if(itMatches(tokenList.get(tokenPosition))) {
				if(tokenPosition < (tokenList.size() -1)) {
					tokenPosition++;
				}
				
				if(!treeStack.isEmpty()) {
					treeStack.pop();
				}
				
				return checkSyntaxRecursive(tokenList);
			} else {
				int productionID = parsingTable.getProductionInt(treeStack.checkTop(), tokenList.get(tokenPosition).getTokenType());
				if(productionID != -1) {
					actualNode = production.produce(productionID, treeStack, actualNode, tokenStack, tokenList, tokenPosition);
 					return checkSyntaxRecursive(tokenList);
				}
			}
		}
		
		return false;
	}
	
	public boolean checkSyntax(ArrayList<tToken> tokenList) {
		tokenPosition = 0;
		return checkSyntaxRecursive(tokenList);
	}
	
	public boolean itMatches(tToken token) {
		ForNode forNode;
		if(token.getTokenType().equals(treeStack.checkTop())) {
			switch(token.getTokenType()) {
			case "INTEGER":
				tokenStack.push(token);
				addType();
				break;
			case "FLOAT":
				tokenStack.push(token);
				addType();
				break;
			case "VOID":
				tokenStack.push(token);
				addType();
				break;
			case "NUM":
				tokenStack.push(token);
				addValue();
				break;
			case "REAL":
				tokenStack.push(token);
				addValue();
				break;
			case "ID":
				tokenStack.push(token);
				addID();
				break;
			case "SEMICOLON":
				tokenStack.clearStack();
				switch(actualNode.getNodeType()) {
				case "FOR":
					forNode = (ForNode) actualNode;
					forNode.nextExpressionList();
					break;
				default:
					actualNode = actualNode.getFatherNode();
					break;
				}
				break;
			case "CKEYBRACKET":
				tokenStack.clearStack();
				actualNode = actualNode.getFatherNode();
				break;
			case "CPARENTHESES":  //Must stop until a command or a parentheses expression is found
				tokenStack.clearStack();
				if(actualNode.getNodeType().equals("BINARYEXPRESSION") || actualNode.getNodeType().equals("CONSTANT") ||
				   actualNode.getNodeType().equals("UNARYEXPRESSION") || actualNode.getNodeType().equals("CALL")) {
					while(!actualNode.getNodeType().equals("IF") && !actualNode.getNodeType().equals("WHILE") &&
						  !actualNode.getNodeType().equals("FOR") && !actualNode.getNodeType().equals("RETURN") &&
						  !actualNode.getNodeType().equals("BLOCK") && !actualNode.getNodeType().equals("CALL") &&
						  !actualNode.getNodeType().equals("ATTRIBUTION")) {
						ExpressionNode expression = (ExpressionNode) actualNode;
						if(expression.getExpressionType().getTokenType().equals("OPARENTHESES")) {
							return true;
						}
						actualNode = expression.getFatherNode();
					}
				}
				break;
			case "COMMA":
				if(actualNode.getNodeType().equals("PARAMETER")) {
					tokenStack.clearStack();
					actualNode = actualNode.getFatherNode();
				}
				break;
			}
			return true;
		}
		return false;
	}
	
	public void addType() {
		FunctionNode function;
		VariableNode variable;
		switch(actualNode.getNodeType()){
		case "FUNCTION":
			function = (FunctionNode)actualNode;
			function.setReturnType(tokenStack.pop());
			break;
		case "VARIABLE":
			variable = (VariableNode)actualNode;
			variable.setVariableType(tokenStack.checkTop());
			break;
		case "PARAMETER":
			variable = (VariableNode)actualNode;
			variable.setVariableType(tokenStack.pop());
			break;
		}
	}
	
	public void addID() {
		FunctionNode function;
		VariableNode variable;
		
		switch(actualNode.getNodeType()) {
		case "FUNCTION":
			function = (FunctionNode)actualNode;
			function.setFunctionID(tokenStack.pop());
			break;
		case "VARIABLE":
			variable = (VariableNode)actualNode;
			variable.setVariableID(tokenStack.pop());
			break;
		case "PARAMETER":
			variable = (VariableNode)actualNode;
			variable.setVariableID(tokenStack.pop());
			break;
		}
	}
	
	public void addValue() {
		VariableNode variable;
		
		switch(actualNode.getNodeType()){
		case "VARIABLE":
			variable = (VariableNode)actualNode;
			variable.setVariableValue(tokenStack.pop());
			break;
		}
	}
}
