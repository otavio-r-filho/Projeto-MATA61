package parser.definitions.nodes;

public class NumberNode extends ExpressionNode{
	private String numberValue;
	
	public NumberNode(String number, String numberType) {
		this.numberValue = number;
		nodeType = numberType;
	}
	
	public NumberNode(String number, String numberType, ASTNode fatherNode) {
		this(number, numberType);
		this.fatherNode = fatherNode;
	}
	
	public void setNumberValue(String numberValue) {
		this.numberValue = numberValue;
	}
	
	public String getNumberValue() {
		return numberValue;
	}
}
