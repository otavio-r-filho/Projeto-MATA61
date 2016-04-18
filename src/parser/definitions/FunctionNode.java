package parser.definitions;

import java.util.ArrayList;

public class FunctionNode extends ASTNode{
	private String returnType;
	private ArrayList<VariableNode> functionParameters;
	private BlockNode block;
	
	public FunctionNode() {
		functionParameters = new ArrayList<VariableNode>();
		block = null;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public void setBlock(BlockNode block) {
		this.block = block;
	}
}
