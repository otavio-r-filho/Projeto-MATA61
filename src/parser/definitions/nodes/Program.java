package parser.definitions.nodes;

import java.util.ArrayList;

public class Program extends ASTNode{
	private ArrayList<VariableNode> globalVariables;
	private ArrayList<FunctionNode> functions;
	
	public Program() {
		globalVariables = new ArrayList<VariableNode>();
		functions = new ArrayList<FunctionNode>();
		fatherNode = null;
		nodeType = "Program";
	}
	
	public void addVariable(VariableNode variable) {
		globalVariables.add(variable);
	}
	
	public void addFunction(FunctionNode function) {
		functions.add(function);
	}
	
}
