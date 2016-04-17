package parser.definitions;

import java.util.ArrayList;

public class BlockNode extends CommandNode{
	private ArrayList<VariableNode> blockVariables;
	private ArrayList<CommandNode> commands;
}
