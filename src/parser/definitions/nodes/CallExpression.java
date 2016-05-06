package parser.definitions.nodes;

import java.util.ArrayList;

import lexer.definitions.tToken;

public class CallExpression extends ExpressionNode {
	tToken functionID;
	ArrayList<ExpressionNode> parameterList;
	
	public CallExpression() {
		parameterList = new ArrayList<ExpressionNode>();
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
	
	public void addParameter(ExpressionNode expression){
		parameterList.add(expression);
	}
}
