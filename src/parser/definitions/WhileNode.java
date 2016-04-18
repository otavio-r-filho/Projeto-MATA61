package parser.definitions;

public class WhileNode extends CommandNode{
	private ExpressionNode conditionExpression;
	private CommandNode command;
	
	public WhileNode() {
		conditionExpression = null;
		command = null;
	}
	
	
	public void setConditionExpression(ExpressionNode conditionExpression) {
		this.conditionExpression = conditionExpression;
	}
	
	public void setCommand(CommandNode command) {
		this.command = command;
	}
}
