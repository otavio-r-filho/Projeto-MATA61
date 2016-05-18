package parser.definitions.nodes;

import java.util.ArrayList;

import lexer.definitions.tToken;

public class FunctionNode extends ASTNode{
	private tToken returnType;
	private tToken functionID;
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
	
	public void setReturnType(tToken returnType) {
		this.returnType = returnType;
	}
	
	public tToken getReturnType() {
		return returnType;
	}
	
	public void setBlock(BlockNode block) {
		this.block = block;
	}
	
	public void setFunctionID(tToken functionID) {
		this.functionID = functionID;
	}
	
	public tToken getFunctioID() {
		return functionID;
	}
	
	public void addParameter(VariableNode parameter) {
		functionParameters.add(parameter);
	}

	public ArrayList<VariableNode> getFunctionParameters() {
		return this.functionParameters;
	}
}
