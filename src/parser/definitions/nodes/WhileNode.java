package parser.definitions.nodes;

public class WhileNode extends CommandNode{
	private ExpressionNode conditionExpression;
	private CommandNode command;
	
	public WhileNode() {
		conditionExpression = null;
		command = null;
		nodeType = "WHILE";
	}
	
	public WhileNode(ASTNode fatherNode) {
		this();
		this.fatherNode = fatherNode;
	}
	
	
	public void setConditionExpression(ExpressionNode conditionExpression) {
		this.conditionExpression = conditionExpression;
	}
	
	public void setCommand(CommandNode command) {
		this.command = command;
	}
}
