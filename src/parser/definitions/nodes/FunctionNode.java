package parser.definitions.nodes;

import java.util.ArrayList;

public class FunctionNode extends ASTNode{
	private String returnType;
	private ArrayList<VariableNode> functionParameters;
	private BlockNode block;
	
	public FunctionNode() {
		functionParameters = new ArrayList<VariableNode>();
		block = null;
		nodeType = "FUNCTION";
	}
	
	public FunctionNode(ASTNode fatherNode) {
		this();
		this.fatherNode = fatherNode;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public void setBlock(BlockNode block) {
		this.block = block;
	}
}
