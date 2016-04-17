package parser.definitions;

public abstract class ExpressionNode extends ASTNode{
	private String expressioType;
	private ExpressionNode leftHandSide;
	private ExpressionNode rightHandSide;
}
