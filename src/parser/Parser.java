package parser;

import parser.definitions.nodes.*;
import parser.tools.*;

import java.util.ArrayList;

import lexer.definitions.*;

public class Parser {
	private Program programNode;
	private ASTNode actualNode;
	
	private Stack treeStack;
	
	private int tokenPosition;
	
	private Production2 production;
	private ParsingTable2 parsingTable;
	
	public Parser() {
		treeStack = new Stack();
		treeStack.push("$");
		treeStack.push("programa");
		
		tokenPosition = 0;
		
		production = new Production2();
		parsingTable = new ParsingTable2();
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
					production.produce(productionID, treeStack);
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
			return true;
		}
		return false;
	}
}
