package parser.definitions.nodes;

public class VariableNode extends ExpressionNode{
	private String variableType;
	private String variableID;
	private String variableValue;
	
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
