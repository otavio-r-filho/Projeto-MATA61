package parser.definitions;

public abstract class ASTNode {
	
	private long nodeID;
	private int nodeTypeID;
	private String nodeType;
	private ASTNode fatherNode;
	
	public ASTNode getFatherNode() {
		return fatherNode;
	}
	
	public long getNodeID() {
		return nodeID;
	}
	
	public int getNodeTypeID() {
		return nodeTypeID;
	}
	
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	
	
}
