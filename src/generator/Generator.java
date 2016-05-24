package generator;

import parser.definitions.nodes.*;

public class Generator {

    private String asmCode;

    public Generator() {
        this.asmCode = null;
    }

    public String cgen(ASTNode node) {

        switch(node.getNodeType()) {
            case "PROGRAM":
                break;
            case "FUNCTION":
                break;
            case "BLOCK":
                break;
            case "IF":
                break;
            case "ELSE":
                break;
            case "WHILE":
                break;
            case "FOR":
                break;
            case "ATTRIBUTION":
                break;
            case "CALL":
                break;
            case "RETURN":
                break;
            case "BINARYEXPRESSION":
                break;
            case "UNARYEXPRESSION":
                break;
            case "CONSTANT":
                break;
        }

        return asmCode;
    }

    private void cgenProgram(Program program) {

    }

    private void cgenGlobalVariable(VariableNode variableNode) {

    }

    private void cgenFunction(FunctionNode functionNode) {

    }

    private void cgenBlock(BlockNode blockNode) {

    }

    private void cgenIf(IfNode ifNode) {

    }

    private void cgenElse(ElseNode elseNode) {

    }

    private void cgenWhile(WhileNode whileNode) {

    }

    private void cgenFor(ForNode forNode) {

    }

    private void cgenAttribution(AttributionNode attributionNode) {

    }

    private void cgenCall(CallExpression callExpression) {

    }

    private void cgenReturn(ReturnNode returnNode) {

    }

    private void cgenUnaryExpression(UnaryExpression unaryExpression) {

    }

    private void cgenBinaryExpression(BinaryExpression binaryExpression) {

    }

    private void cgenConstant(VariableNode constant) {
        
    }
}
