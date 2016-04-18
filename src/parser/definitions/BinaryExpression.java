package parser.definitions;

public class BinaryExpression extends ExpressionNode{
	private ExpressionNode lhsExpression;
	private ExpressionNode rhsExpression;
	
	public BinaryExpression() {
		lhsExpression = null;
		rhsExpression = null;
	}
	
	public void setLhsExpression(ExpressionNode lhsExpression) {
		this.lhsExpression = lhsExpression;
	}
	
	public void setRhsExpression(ExpressionNode rhsExpression) {
		this.rhsExpression = rhsExpression;
	}
}
