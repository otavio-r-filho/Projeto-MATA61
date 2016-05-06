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
		if(token.getTokenType().equals(treeStack.checkTop())) {
			switch(token.getTokenType()) {
			case "INTEGER":
				tokenStack.push(token);
				addType(token);
				break;
			case "FLOAT":
				tokenStack.push(token);
				addType(token);
				break;
			case "VOID":
				tokenStack.push(token);
				addType(token);
				break;
			case "NUM":
				tokenStack.push(token);
				addValue(token);
				break;
			case "REAL":
				tokenStack.push(token);
				addValue(token);
				break;
			case "ID":
				tokenStack.push(token);
				addID(token);
				break;
			case "SEMICOLON":
				tokenStack.clearStack();
				break;
			case "CKEYBRACKET":
				tokenStack.clearStack();
				break;
			}
			return true;
		}
		return false;
	}
	
	public void addType(tToken token) {
		FunctionNode function;
		VariableNode variable;
		switch(actualNode.getNodeType()){
		case "FUNCTION":
			function = (FunctionNode)actualNode;
			function.setReturnType(token);
			break;
		case "VARIABLE":
			variable = (VariableNode)actualNode;
			variable.setVariableType(token);
			break;
		case "PARAMETER":
			variable = (VariableNode)actualNode;
			variable.setVariableType(token);
			break;
		}
	}
	
	public void addID(tToken token) {
		FunctionNode function;
		VariableNode variable;
		
		switch(actualNode.getNodeType()) {
		case "FUNCTION":
			function = (FunctionNode)actualNode;
			function.setFunctionID(token);
			break;
		case "VARIABLE":
			variable = (VariableNode)actualNode;
			variable.setVariableID(token);
			break;
		case "PARAMETER":
			variable = (VariableNode)actualNode;
			variable.setVariableID(token);
			break;
		}
	}
	
	public void addValue(tToken token) {
		VariableNode variable;
		
		switch(actualNode.getNodeType()){
		case "VARIABLE":
			variable = (VariableNode)actualNode;
			variable.setVariableValue(token);
			break;
		}
	}
}
