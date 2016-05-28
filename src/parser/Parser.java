package parser;

import parser.definitions.nodes.*;
import parser.tools.*;

import java.util.ArrayList;
import java.util.HashMap;

import lexer.definitions.*;

public class Parser {
	private Program programNode;
	private ASTNode actualNode;

	private String errorDescription;
	
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
				errorDescription = "Erro sintatico. Esperado " + treeStack.checkTop() + ", mas encontrado " + tokenList.get(tokenPosition).getTokenValue() + ". Linha: " + tokenList.get(tokenPosition).getLine() + ". Coluna: " + tokenList.get(tokenPosition).getCollumn();
			}
		}
		
		return false;
	}
	
	public boolean checkSyntax(ArrayList<tToken> tokenList) {
		tokenPosition = 0;
		if(checkSyntaxRecursive(tokenList)){
			eliminateParentheses(programNode);
			return true;
		} else { return false; }
	}
	
	private boolean itMatches(tToken token) {
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
					while(!actualNode.getNodeType().equals("PROGRAM") && !actualNode.getNodeType().equals("FUNCTION") &&
						  !actualNode.getNodeType().equals("IF") && !actualNode.getNodeType().equals("WHILE") &&
						  !actualNode.getNodeType().equals("FOR") && !actualNode.getNodeType().equals("BLOCK")) {
						actualNode = actualNode.getFatherNode();
					}
					break;
				}
				break;
			case "CKEYBRACKET":
				tokenStack.clearStack();
				if(actualNode.getFatherNode().equals("ELSE")) {
					actualNode = actualNode.getFatherNode();
				}
				actualNode = actualNode.getFatherNode();
				actualNode = actualNode.getFatherNode();
				break;
			case "CPARENTHESES":  //Must stop until a command or a parentheses expression is found
				tokenStack.clearStack();
				if(actualNode.getNodeType().equals("BINARYEXPRESSION") || actualNode.getNodeType().equals("CONSTANT") ||
				   actualNode.getNodeType().equals("UNARYEXPRESSION") || actualNode.getNodeType().equals("CALL")) {
					while(!actualNode.getNodeType().equals("IF") && !actualNode.getNodeType().equals("WHILE") &&
						  !actualNode.getNodeType().equals("FOR") && !actualNode.getNodeType().equals("RETURN") &&
						  !actualNode.getNodeType().equals("BLOCK") && !actualNode.getNodeType().equals("CALL") &&
						  !actualNode.getNodeType().equals("ATTRIBUTION") && !actualNode.getNodeType().equals("PRINT")) {
						ExpressionNode expression = (ExpressionNode) actualNode;
						if(expression.getExpressionType().getTokenType().equals("OPARENTHESES")) {
							return true;
						}
						actualNode = expression.getFatherNode();
					}
				} else if(!actualNode.getNodeType().equals("FUNCTION")){
					actualNode = actualNode.getFatherNode();
				}
				break;
			case "COMMA":
				if(actualNode.getNodeType().equals("PARAMETER") || actualNode.getFatherNode().getNodeType().equals("CALL")) {
					tokenStack.clearStack();
					actualNode = actualNode.getFatherNode();
				}
				break;
			}
			return true;
		}
		return false;
	}
	
	private void addType() {
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
	
	private void addID() {
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
	
	private void addValue() {
		VariableNode variable;
		
		switch(actualNode.getNodeType()){
		case "VARIABLE":
			variable = (VariableNode)actualNode;
			variable.setVariableValue(tokenStack.pop());
			break;
		}
	}

	public Program getASTTree() {
		return programNode;
	}

	private void eliminateParentheses(ASTNode node) {

		switch (node.getNodeType()) {
			case "PROGRAM":
				for(FunctionNode functionNode: ((Program) node).getFunctions()) {
					eliminateParentheses(functionNode);
				}
				break;
			case "FUNCTION":
				eliminateParentheses(((FunctionNode) node).getBlock());
				break;
			case "BLOCK":
				for(CommandNode commandNode: ((BlockNode) node).getCommands()) {
					eliminateParentheses(commandNode);
				}
				break;
			case "IF":
				eliminateParentheses(((IfNode) node).getConditionExpression());
				eliminateParentheses(((IfNode) node).getCommand());
				if(((IfNode) node).getElseCommand() != null) {
					eliminateParentheses(((IfNode) node).getElseCommand());
				}
				break;
			case "ELSE":
				eliminateParentheses(((ElseNode) node).getCommand());
				break;
			case "WHILE":
				eliminateParentheses(((WhileNode) node).getConditionExpression());
				eliminateParentheses(((WhileNode) node).getCommand());
				break;
			case "FOR":
				for(ExpressionNode expressionNode: ((ForNode) node).getInitialExpressionList()) {
					eliminateParentheses(expressionNode);
				}
				for(ExpressionNode expressionNode: ((ForNode) node).getStopExpressionList()) {
					eliminateParentheses(expressionNode);
				}
				for(ExpressionNode expressionNode: ((ForNode) node).getIncrementExpressionList()) {
					eliminateParentheses(expressionNode);
				}
				eliminateParentheses(((ForNode) node).getCommand());
				break;
			case "RETURN":
				if(((ReturnNode) node).getReturnExpression() != null) {
					eliminateParentheses(((ReturnNode) node).getReturnExpression());
				}
				break;
			case "CALL":
				for(ExpressionNode expressionNode: ((CallExpression) node).getExpressionList()) {
					eliminateParentheses(expressionNode);
				}
				break;
			case "PRINT":
				eliminateParentheses(((PrintNode) node).getExpression());
				break;
			case "UNARYEXPRESSION":
				if(((UnaryExpression) node).getExpressionType().getTokenType().equals("OPARENTHESES")) {
					ASTNode fatherNode = node.getFatherNode();
					switch(fatherNode.getNodeType()) {
						case "IF":
							//Change the expression of the if to the expression inside the parentheses;
							((IfNode) fatherNode).setConditionExpression(((UnaryExpression) node).getExpression());
							//Set the if as the father node of the expression inside the parentheses
							((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
							break;
						case "WHILE":
							//Change the expression of the while to the expression inside the parentheses;
							((WhileNode) fatherNode).setConditionExpression(((UnaryExpression) node).getExpression());
							//Set the if as the father node of the expression inside the parentheses
							((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
							break;
						case "FOR":
							if(((ForNode) fatherNode).getInitialExpressionList().contains(node)) {
								((ForNode) fatherNode).getInitialExpressionList().add(((ForNode) fatherNode).getInitialExpressionList().indexOf(node), ((UnaryExpression) node).getExpression());
								((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
								((ForNode) fatherNode).getInitialExpressionList().remove(node);
							} else if(((ForNode) fatherNode).getStopExpressionList().contains(node)) {
								((ForNode) fatherNode).getStopExpressionList().add(((ForNode) fatherNode).getStopExpressionList().indexOf(node), ((UnaryExpression) node).getExpression());
								((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
								((ForNode) fatherNode).getStopExpressionList().remove(node);
							} else if(((ForNode) fatherNode).getIncrementExpressionList().contains(node)) {
								((ForNode) fatherNode).getIncrementExpressionList().add(((ForNode) fatherNode).getStopExpressionList().indexOf(node), ((UnaryExpression) node).getExpression());
								((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
								((ForNode) fatherNode).getIncrementExpressionList().remove(node);
							}
							break;
						case "RETURN":
							((ReturnNode) fatherNode).setReturnExpression(((UnaryExpression) node).getExpression());
							((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
							break;
						case "CALL":
							((CallExpression) fatherNode).getExpressionList().add(((CallExpression) fatherNode).getExpressionList().indexOf(node), ((UnaryExpression) node).getExpression());
							((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
							break;
						case "PRINT":
							((PrintNode) fatherNode).setExpression(((UnaryExpression) node).getExpression());
							((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
							break;
						case "ATTRIBUTION":
							((AttributionNode) fatherNode).setExpression(((UnaryExpression) node).getExpression());
							((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
							break;
						case "UNARYEXPRESSION":
							((UnaryExpression) fatherNode).setExpression(((UnaryExpression) node).getExpression());
							((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
							break;
						case "BINARYEXPRESSION":
							if(((BinaryExpression) fatherNode).getLhsExpression() == node) {
								((BinaryExpression) fatherNode).setLhsExpression(((UnaryExpression) node).getExpression());
							} else {
								((BinaryExpression) fatherNode).setLhsExpression(((UnaryExpression) node).getExpression());
							}
							((UnaryExpression) node).getExpression().setFatherNode(fatherNode);
							break;
					}
				}
				eliminateParentheses(((UnaryExpression)node).getExpression());
				break;
			case "BINARYEXPRESSION":
				eliminateParentheses(((BinaryExpression) node).getLhsExpression());
				eliminateParentheses(((BinaryExpression) node).getRhsExpression());
				break;

		}
	}

	public String getErrorDescription() {
		return errorDescription;
	}
}
