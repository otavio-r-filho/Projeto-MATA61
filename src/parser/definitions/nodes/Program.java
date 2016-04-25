package parser.definitions.nodes;

import java.util.ArrayList;

public class Program {
	ArrayList<VariableNode> globalVariables;
	ArrayList<FunctionNode> functions;
	
	public Program() {
		globalVariables = new ArrayList<VariableNode>();
		functions = new ArrayList<FunctionNode>();
	}
	
	public void addVariable(VariableNode variable) {
		globalVariables.add(variable);
	}
	
	public void addFunction(FunctionNode function) {
		functions.add(function);
	}
	
}
