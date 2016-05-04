package parser.definitions.nodes;

import java.util.ArrayList;

public class ForNode extends CommandNode{
	private ArrayList<ExpressionNode> initialExpressionList;
	private ArrayList<ExpressionNode> stopExpressionList;
	private ArrayList<ExpressionNode> incrementExpressionList;
	
	private CommandNode command;
	
	public ForNode() {
		initialExpressionList = new ArrayList<ExpressionNode>();
		stopExpressionList = new ArrayList<ExpressionNode>();
		incrementExpressionList = new ArrayList<ExpressionNode>();
		command = null;
		nodeType = "FOR";
	}
	
	public ForNode(ASTNode fatherNode) {
		this();
		this.fatherNode = fatherNode;
	}
	
	public void addInitialExpression(ExpressionNode expression) {
		initialExpressionList.add(expression);
	}
	
	public void addStopExpression(ExpressionNode expression) {
		stopExpressionList.add(expression);
	}
	
	public void addIncrementExpression(ExpressionNode expression) {
		incrementExpressionList.add(expression);
	}
	
	public void setCommand(CommandNode command) {
		this.command = command;
	}
}