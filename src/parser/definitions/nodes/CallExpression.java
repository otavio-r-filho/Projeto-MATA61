package parser.definitions.nodes;

import java.util.ArrayList;

import lexer.definitions.tToken;

public class CallExpression extends ExpressionNode {
	tToken functionID;
	ArrayList<ExpressionNode> expressionList;

	public CallExpression() {
		expressionList = new ArrayList<ExpressionNode>();
		functionID = null;
	}
	
	public CallExpression(ASTNode fatherNode) {
		this();
		this.fatherNode = fatherNode;
	}
	
	public CallExpression(ASTNode fatherNode, tToken functionID) {
		this(fatherNode);
		this.functionID = functionID;
	}
	
	public tToken getFunctionID() {
		return functionID;
	}
	
	public void setFunctionID(tToken functionID) {
		this.functionID = functionID;
	}
	
	public void addExpression(ExpressionNode expression){
		expressionList.add(expression);
	}
	
	public ArrayList<ExpressionNode> getExpressionList() {
		return expressionList;
	}
}
