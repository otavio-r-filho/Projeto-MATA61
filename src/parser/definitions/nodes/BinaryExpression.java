package parser.definitions.nodes;

import lexer.definitions.tToken;

public class BinaryExpression extends ExpressionNode{
	private ExpressionNode lhsExpression;
	private ExpressionNode rhsExpression;
	
	public BinaryExpression(tToken expressionType) {
		lhsExpression = null;
		rhsExpression = null;
		this.expressionType = expressionType;
	}
	
	public BinaryExpression(tToken expressionType, ASTNode fatherNode) {
		this(expressionType);
		this.fatherNode = fatherNode;
		nodeType = "BINARYEXPRESSION";
	}
	
	public void setLhsExpression(ExpressionNode lhsExpression) {
		this.lhsExpression = lhsExpression;
	}
	
	public void setRhsExpression(ExpressionNode rhsExpression) {
		this.rhsExpression = rhsExpression;
	}
}
