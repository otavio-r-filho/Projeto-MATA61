package parser.definitions.nodes;

public class ElseNode extends CommandNode{
	private CommandNode command;
	
	public ElseNode() {
		command = null;
		nodeType = "ELSE";
	}
	
	public ElseNode(ASTNode fatherNode) {
		this();
		this.fatherNode = fatherNode;
	}
	
	public void setCommand(CommandNode command) {
		this.command = command;
	}
}
