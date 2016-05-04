package parser.definitions.nodes;

import java.util.ArrayList;

public class FunctionNode extends ASTNode{
	private String returnType;
	private String functionID;
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
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public void setBlock(BlockNode block) {
		this.block = block;
	}
	
	public void setFunctioID(String functionID) {
		this.functionID = functionID;
	}
	
	public String getFunctioID() {
		return functionID;
	}
	
	public void addParameter(VariableNode parameter) {
		functionParameters.add(parameter);
	}
}
