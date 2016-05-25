package generator;

import parser.definitions.nodes.*;

import java.util.ArrayList;

public class Generator {

//    private String asmCode;
    private ArrayList<String> asmCode;

    public Generator() {
        asmCode = new ArrayList<String>();
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
        asmCode.add(".globl main");

        //Adding the other functions to global scope
        for(FunctionNode functionNode: program.getFunctions()) {
            asmCode.add(".globl " + functionNode.getFunctioID().getTokenValue());
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
