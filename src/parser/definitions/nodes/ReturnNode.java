package parser.definitions.nodes;

public class ReturnNode extends CommandNode{
	ExpressionNode returnExpression;
	
	public ReturnNode() {
		returnExpression = null;
		this.nodeType = "RETURN";
	}
	
	public ReturnNode(IfNode fatherNode) {
		this();
		this.fatherNode = fatherNode;
	}
	
	public ExpressionNode getReturnExpression() {
		return returnExpression;
	}

	public void setReturnExpression(ExpressionNode returnExpression) {
		this.returnExpression = returnExpression;
	}	
	
}
