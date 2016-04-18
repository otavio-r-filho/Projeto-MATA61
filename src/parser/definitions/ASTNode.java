package parser.definitions;

import java.util.ArrayList;

public abstract class ASTNode {
	
	private long nodeID;
	private int nodeTypeID;
	private String nodeType;
	private ASTNode fatherNode;
	
	public long getNodeID() {
		return nodeID;
	}
	
	public int getNodeTypeID() {
		return nodeTypeID;
	}
	
	public String getNodeType() {
		return nodeType;
	}
	
	public ASTNode getFatherNode() {
		return fatherNode;
	}
	
	public void setNodeID(long nodeID) {
		this.nodeID = nodeID;
	}
	
	public void setNodeTypeID(int nodeTypeID) {
		this.nodeTypeID = nodeTypeID;
	}
	
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	
	public void setFatherNode(ASTNode fatherNode) {
		this.fatherNode = fatherNode;
	}
	
	public boolean isNull(ArrayList<ASTNode> someList) {
		if(someList.isEmpty()) 
			return true;
		else 
			return false;
	}
	
	public boolean isNull(ASTNode someNode) {
		if(someNode == null) 
			return true;
		else 
			return false;
	}
	
}
