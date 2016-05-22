package parser.definitions.nodes;

import java.util.ArrayList;

public class ForNode extends CommandNode{
	private ArrayList<ExpressionNode> initialExpressionList;
	private ArrayList<ExpressionNode> stopExpressionList;
	private ArrayList<ExpressionNode> incrementExpressionList;
	
	private int actualExpressionList;
	
	private CommandNode command;
	
	public ForNode() {
		initialExpressionList = new ArrayList<ExpressionNode>();
		stopExpressionList = new ArrayList<ExpressionNode>();
		incrementExpressionList = new ArrayList<ExpressionNode>();
		command = null;
		nodeType = "FOR";
		this.actualExpressionList = 1;
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
	
	public void addExpression(ExpressionNode expression) {
		switch(actualExpressionList) {
		case 1:
			initialExpressionList.add(expression);
			break;
		case 2:
			stopExpressionList.add(expression);
			break;
		case 3:
			incrementExpressionList.add(expression);
			break;
		}
	}
	
	public ArrayList<ExpressionNode> getExpressionList(){
		switch(actualExpressionList) {
		case 1:
			return initialExpressionList;
		case 2:
			return stopExpressionList;
		case 3:
			return incrementExpressionList;
		}
		return null;
	}
	
	public void setCommand(CommandNode command) {
		this.command = command;
	}
	
	public void nextExpressionList() {
		if(actualExpressionList < 3){
			this.actualExpressionList++;
		}
	}
	
	public int getActualExpressionList(){
		return actualExpressionList;
	}

	public ArrayList<ExpressionNode> getInitialExpressionList() {
		return initialExpressionList;
	}

	public ArrayList<ExpressionNode> getStopExpressionList() {
		return stopExpressionList;
	}

	public ArrayList<ExpressionNode> getIncrementExpressionList() {
		return incrementExpressionList;
	}

	public CommandNode getCommand() {
		return command;
	}
}