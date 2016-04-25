package parser;

import parser.definitions.nodes.*;
import parser.tools.*;

public class Parser {
	Program programNode;
	ASTNode actualNode;
	
	Stack treeStack;
	
	public Parser() {
		treeStack = new Stack();
		treeStack.push("$");
		treeStack.push("0");
	}
}
