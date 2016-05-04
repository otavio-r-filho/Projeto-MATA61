package parser.definitions.nodes;

public class BinaryExpression extends ExpressionNode{
	private ExpressionNode lhsExpression;
	private ExpressionNode rhsExpression;
	private String operation;
	
	public BinaryExpression(String operation) {
		lhsExpression = null;
		rhsExpression = null;
		this.operation = operation;
	}
	
	public BinaryExpression(String operation, ASTNode fatherNode) {
		this(operation);
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
