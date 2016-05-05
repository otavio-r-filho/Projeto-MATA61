package parser.definitions.nodes;

import lexer.definitions.tToken;

public class VariableNode extends ExpressionNode{
	private tToken variableType;
	private tToken variableID;
	private tToken variableValue;
	
	public VariableNode(tToken variableType, tToken variableValue) {
		this.variableType = variableType;
		this.variableValue = variableValue;
	}
	
	public VariableNode(tToken variableType, tToken variableID, tToken variableValue) {
		this(variableType, variableValue);
		this.variableID = variableID;
	}
	
	public VariableNode(tToken variableType, tToken variableID, tToken variableValue,  ASTNode fatherNode) {
		this(variableType, variableID, variableValue);
		this.fatherNode = fatherNode;
	}
	
	public tToken getVariableType() {
		return variableType;
	}
	
	public tToken getVariableID() {
		return variableID;
	}
	
	public tToken getVariableValue() {
		return variableValue;
	}
	
	public void setVariableType(tToken variableType) {
		this.variableType = variableType;
	}
	
	public void setVariableID(tToken variableID) {
		this.variableID = variableID;
	}
	
	public void setVariableValue(tToken variableValue) {
		this.variableValue = variableValue;
	}
}
