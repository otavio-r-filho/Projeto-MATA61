package parser.definitions.nodes;

public class IfNode extends CommandNode{
	private ExpressionNode conditionExpression;
	private CommandNode command;
	private ElseNode elseCommand;
	
	public IfNode() {
		conditionExpression = null;
		command = null;
		elseCommand = null;
	}
	
	public void setConditionExpression(ExpressionNode conditionExpression) {
		this.conditionExpression = conditionExpression;
	}
	
	public void setElseCommand(ElseNode elseCommand) {
		this.elseCommand = elseCommand;
	}
	
	public void setCommand(CommandNode command) {
		this.command = command;
	}
}
