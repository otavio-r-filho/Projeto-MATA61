package parser.definitions.nodes;

import lexer.definitions.tToken;

public class UnaryExpression extends ExpressionNode{
	private ExpressionNode operand;
	
	public UnaryExpression() {
		operand = null;
	}
	
	public UnaryExpression(tToken expressioType, ASTNode fatherNode) {
		this();
		this.fatherNode = fatherNode;
	}
	
	public void setOperand(ExpressionNode operand) {
		this.operand = operand;
	}
	
	public ExpressionNode getOperand() {
		return operand;
	}
}
