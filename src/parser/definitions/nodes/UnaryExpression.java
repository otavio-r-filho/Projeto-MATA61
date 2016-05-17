package parser.definitions.nodes;

import lexer.definitions.tToken;

public class UnaryExpression extends ExpressionNode{
	private ExpressionNode expression;
	
	public UnaryExpression() {
		expression = null;
		this.nodeType = "UNARYEXPRESSION";
		this.expressionPrecedence = 0;
	}
	
	public UnaryExpression(tToken expressioType, ASTNode fatherNode) {
		this();
		this.fatherNode = fatherNode;
		this.expressionType = expressioType;
	}
	
	public void setExpression(ExpressionNode expression) {
		this.expression = expression;
	}
	
	public ExpressionNode getExpression() {
		return expression;
	}
}
