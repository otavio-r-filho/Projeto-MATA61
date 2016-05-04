package parser.definitions.nodes;

import java.util.ArrayList;

public class BlockNode extends CommandNode{
	private ArrayList<VariableNode> blockVariables;
	private ArrayList<CommandNode> commands;
	
	public BlockNode() {
		blockVariables = new ArrayList<VariableNode>();
		commands = new ArrayList<CommandNode>();
		nodeType = "BLOCK";
	}
	
	public BlockNode(ASTNode fatherNode) {
		this();
		this.fatherNode = fatherNode;
	}
	
	public void addVariable(VariableNode variable) {
		blockVariables.add(variable);
	}
	
	public void addCommand(CommandNode command) {
		commands.add(command);
	}
}
