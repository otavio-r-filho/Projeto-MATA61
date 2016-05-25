package generator;

import parser.definitions.nodes.*;

import java.util.ArrayList;

public class Generator {

//    private String asmCode;
    private ArrayList<String> asmCode;

    public Generator() {
        asmCode = new ArrayList<String>();
    }

    public ArrayList<String> cgen(ASTNode node) {

        switch(node.getNodeType()) {
            case "PROGRAM":
                cgenProgram((Program) node);
                break;
            case "FUNCTION":
                cgenFunction((FunctionNode) node);
                break;
            case "BLOCK":
                cgenBlock((BlockNode) node);
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
                //TODO add cgen for float and integer attribution
                break;
            case "CALL":
                break;
            case "RETURN":
                break;
            case "BINARYEXPRESSION":
                //TODO add cgen for float and integer binary expression
                break;
            case "UNARYEXPRESSION":
                //TODO add cgen for float and integer unary expression
                break;
            case "CONSTANT":
                break;
        }

        return asmCode;
    }

    private void cgenProgram(Program program) {
        asmCode.add(".data");
        //Adding global variables
        for(VariableNode variableNode: program.getGlobalVariables()) {
            if(variableNode.getVariableType().getTokenType().equals("INTEGER")) {
                asmCode.add("_" + variableNode.getVariableID().getTokenValue() + ": .word 0");
            } else {
                asmCode.add("_" + variableNode.getVariableID().getTokenValue() + ": .float 0");
            }
        }

        asmCode.add(".text");
        int size = asmCode.size();
        for(FunctionNode functionNode: program.getFunctions()) {
            if(functionNode.getFunctioID().getTokenValue().equals("main")) {
                asmCode.add(size - 1, ".globl main");
            } else {
                asmCode.add(".globl " + functionNode.getFunctioID().getTokenValue());
            }
        }

        for(FunctionNode functionNode: program.getFunctions()) {
            cgen(functionNode);
        }
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
