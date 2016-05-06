package parser.definitions.nodes;

import lexer.definitions.tToken;

public abstract class ExpressionNode extends CommandNode{
	protected tToken expressionType;
	protected int expressionPrecedence;
	
	public tToken getExpressionType() {
		return expressionType;
	}
	public void setExpressionType(tToken expressionType) {
		this.expressionType = expressionType;
	}
	public int getExpressionPrecedence() {
		return expressionPrecedence;
	}
	public void setExpressionPrecedence(int expressionPrecedence) {
		this.expressionPrecedence = expressionPrecedence;
	}
}
