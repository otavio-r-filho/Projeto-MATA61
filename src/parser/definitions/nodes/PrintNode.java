package parser.definitions.nodes;

public class PrintNode extends CommandNode{
    ExpressionNode expression;

    public PrintNode(ASTNode fatherNode) {
        this.fatherNode = fatherNode;
        this.nodeType = "PRINT";
    }

    public PrintNode(ASTNode fatherNode, ExpressionNode expression) {
        this(fatherNode);
        this.expression = expression;
    }

    public void setExpression(ExpressionNode expression) {
        this.expression = expression;
    }

    public ExpressionNode getExpression() {
        return expression;
    }
}
