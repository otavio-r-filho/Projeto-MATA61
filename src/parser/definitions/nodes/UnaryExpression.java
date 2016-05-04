package parser.definitions.nodes;

public class UnaryExpression extends ExpressionNode{
	private ExpressionNode operand;
	private String operation;
	
	public UnaryExpression(String operation) {
		operand = null;
		this.operation = operation;
	}
	
	public UnaryExpression(String operation, ASTNode fatherNode) {
		this(operation);
		this.fatherNode = fatherNode;
	}
	
	public void setOperand(ExpressionNode operand) {
		this.operand = operand;
	}
	
	public ExpressionNode getOperand() {
		return operand;
	}
}
