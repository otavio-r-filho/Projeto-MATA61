package parser.definitions.nodes;

import lexer.definitions.tToken;

public class AttributionNode extends CommandNode{
	private tToken variableID;
	private ExpressionNode expression;
	
	public AttributionNode() {
		this.fatherNode = fatherNode;
	}
	
	public AttributionNode(ASTNode fatherNode) {
		this.fatherNode = fatherNode;
	}
	
	public AttributionNode(ASTNode fatherNode, tToken variableID) {
		this(fatherNode);
		this.variableID = variableID;		
	}
	public tToken getVariableID() {
		return variableID;
	}

	public void setVariableID(tToken variableID) {
		this.variableID = variableID;
	}

	public ExpressionNode getExpression() {
		return expression;
	}

	public void setExpression(ExpressionNode expression) {
		this.expression = expression;
	}	
}
