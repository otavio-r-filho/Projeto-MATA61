package parser.definitions.nodes;

public class ElseNode extends CommandNode{
	private CommandNode command;
	
	public ElseNode() {
		command = null;
	}
	
	public void setCommand(CommandNode command) {
		this.command = command;
	}
}