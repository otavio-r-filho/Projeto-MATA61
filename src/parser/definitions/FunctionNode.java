package parser.definitions;

import java.util.ArrayList;

public class FunctionNode extends ASTNode{
	private String returnType;
	private ArrayList<VariableNode> functionParameters;
	private BlockNode block;
}
