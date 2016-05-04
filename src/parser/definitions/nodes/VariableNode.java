package parser.definitions.nodes;

public class VariableNode extends ExpressionNode{
	private String variableType;
	private String variableID;
	private String variableValue;
	
	public VariableNode(String variableType, String variableValue) {
		this.variableType = variableType;
		this.variableValue = variableValue;
	}
	
	public VariableNode(String variableType, String variableID, String variableValue) {
		this(variableType, variableValue);
		this.variableID = variableID;
	}
	
	public VariableNode(String variableType, String variableID, String variableValue,  ASTNode fatherNode) {
		this(variableType, variableID, variableValue);
		this.fatherNode = fatherNode;
	}
	
	public String getVariableType() {
		return variableType;
	}
	
	public String getVariableID() {
		return variableID;
	}
	
	public String getVariableValue() {
		return variableValue;
	}
	
	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}
	
	public void setVariableID(String variableID) {
		this.variableID = variableID;
	}
	
	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}
}
