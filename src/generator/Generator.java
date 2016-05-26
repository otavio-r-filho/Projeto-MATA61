package generator;

import parser.definitions.nodes.*;
import semantic.Analyzer;
import semantic.definitions.Symbol;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Generator {

//    private String asmCode;
    private ArrayList<String> asmCode;
    private ArrayList<Symbol> symbolTable;
    private Analyzer analyzer;

    public Generator() {
        analyzer = new Analyzer();
        asmCode = new ArrayList<>();
        symbolTable = new ArrayList<>();
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
                cgenBinaryExpression((BinaryExpression) node);
                break;
            case "UNARYEXPRESSION":
                cgenUnaryExpression((UnaryExpression) node);
                break;
            case "CONSTANT":
                cgenConstant((VariableNode) node);
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
        switch(binaryExpression.getExpressionType().getTokenType()) {
            case "OR:":
                cgen(binaryExpression.getLhsExpression());
                asmCode.add("sw $a0, 0($sp)");
                asmCode.add("subu $sp, $sp, 4");
                cgen(binaryExpression.getRhsExpression());
                asmCode.add("lw $t0, 4($sp)");
                asmCode.add("addiu $sp, $sp, 4");
                asmCode.add("or $a0, $t0, $a0");
                break;
            case "AND":
                cgen(binaryExpression.getLhsExpression());
                asmCode.add("sw $a0, 0($sp)");
                asmCode.add("subu $sp, $sp, 4");
                cgen(binaryExpression.getRhsExpression());
                asmCode.add("lw $t0, 4($sp)");
                asmCode.add("addiu $sp, $sp, 4");
                asmCode.add("or $a0, $t0, $a0");
                break;
            case "COMPARISSON":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        //TODO add conversion to float here;
                    }
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                }
                break;
            case "DIFFERENT":
                break;
            case "GREATER":
                break;
            case "SMALLER":
                break;
            case "GEQUAL":
                break;
            case "SEQUAL":
                break;
            case "PLUS":
                break;
            case "MINUS":
                break;
            case "ASTERISK":
                break;
            case "SLASH":
                break;
        }
    }

    private void cgenConstant(VariableNode constant) {
        switch(constant.getVariableType().getTokenType()) {
            case "ID":
                for(int i = symbolTable.size() - 1 ; i >= 0 ; i--) {
                    if(symbolTable.get(i).getSymbolID().equals(constant.getVariableID().getTokenValue())) {
                        if(!symbolTable.get(i).isGlobal()) {
                            if (symbolTable.get(i).getSymbolType().equals("INTEGER")) {
                                asmCode.add("lw $a0, " + ((i * 4) + 4) + "($sp)");
                            } else {
                                asmCode.add("lwc1.s $f0, " + ((i * 4) + 4) + "($sp)");
                                asmCode.add("mfc1 $a0, $f0");
                            }
                        } else {
                            if (symbolTable.get(i).getSymbolType().equals("INTEGER")) {
                                asmCode.add("lw $a0, " + constant.getVariableID().getTokenValue());
                            } else {
                                asmCode.add("lwc1 $f0, " + constant.getVariableID().getTokenValue());
                                asmCode.add("mfc1 $a0, $f0");
                            }
                        }
                    }
                }
                break;
            case "REAL":
                asmCode.add("li.s $f0, " + constant.getVariableValue().getTokenValue());
                asmCode.add("mfc1 $a0, $f0");
                break;
            case "NUM":
                asmCode.add("li $a0, " + constant.getVariableValue().getTokenValue());
                break;
        }
    }

    private void cgenPrint(ASTNode node) {

    }
}
