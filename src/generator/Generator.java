package generator;

import parser.definitions.nodes.*;
import semantic.Analyzer;
import semantic.definitions.Symbol;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Generator {

//    private String asmCode;
    private ArrayList<String> asmCode;
//    private ArrayList<Symbol> symbolTable;
    private Analyzer analyzer;

    public Generator() {
        analyzer = new Analyzer();
        asmCode = new ArrayList<>();
//        symbolTable = new ArrayList<>();
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
                cgenAttribution((AttributionNode) node);
                break;
            case "CALL":
                break;
            case "RETURN":
                cgenReturn((ReturnNode) node);
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
        cgen(attributionNode.getExpression());
        
    }

    private void cgenCall(CallExpression callExpression) {

    }

    private void cgenReturn(ReturnNode returnNode) {
        if(returnNode.getReturnExpression() != null ) {
            cgen(returnNode.getReturnExpression());
        }
        //TODO add part to pop the stack
        asmCode.add("j $ra");

    }

    private void cgenUnaryExpression(UnaryExpression unaryExpression) {
        switch (unaryExpression.getExpressionType().getTokenType()) {
            case "EXCLAMATION":
                cgen(unaryExpression.getExpression());
                asmCode.add("not $a0, $a0");
                break;
            case "MINUS":
                cgen(unaryExpression.getExpression());
                asmCode.add("neg $a0, $a0");
                break;
        }
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
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("c.eq.s $f1, $f0");
                    asmCode.add("lwc1 $f3, _false");
                    asmCode.add("lwc1 $f4, _true");
                    asmCode.add("movt.s $f3, f4");
                    asmCode.add("mfc1 $a0, $f3");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("seq $a0, $t0, $a0");
                }
                break;
            case "DIFFERENT":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("c.eq.s $f1, $f0");
                    asmCode.add("lwc1 $f3, _false");
                    asmCode.add("lwc1 $f4, _true");
                    asmCode.add("movf.s $f3, f4");
                    asmCode.add("mfc1 $a0, $f3");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("sne $a0, $t0, $a0");
                }
                break;
            case "GREATER":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("c.lt.s $f0, $f1");
                    asmCode.add("lwc1 $f3, _false");
                    asmCode.add("lwc1 $f4, _true");
                    asmCode.add("movt.s $f3, f4");
                    asmCode.add("mfc1 $a0, $f3");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("sgt $a0, $t0, $a0");
                }
                break;
            case "SMALLER":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("c.lt.s $f1, $f0");
                    asmCode.add("lwc1 $f3, _false");
                    asmCode.add("lwc1 $f4, _true");
                    asmCode.add("movt.s $f3, f4");
                    asmCode.add("mfc1 $a0, $f3");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("slt $a0, $t0, $a0");
                }
                break;
            case "GEQUAL":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("c.le.s $f0, $f1");
                    asmCode.add("lwc1 $f3, _false");
                    asmCode.add("lwc1 $f4, _true");
                    asmCode.add("movt.s $f3, f4");
                    asmCode.add("mfc1 $a0, $f3");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("sge $a0, $t0, $a0");
                }
                break;
                break;
            case "SEQUAL":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("c.le.s $f1, $f0");
                    asmCode.add("lwc1 $f3, _false");
                    asmCode.add("lwc1 $f4, _true");
                    asmCode.add("movt.s $f3, f4");
                    asmCode.add("mfc1 $a0, $f3");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("sle $a0, $t0, $a0");
                }
                break;
            case "PLUS":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("add.s $f0, $f1, $f0");
                    asmCode.add("mfc1 $a0, $f0");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("add $a0, $t0, $a0");
                }
                break;
            case "MINUS":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("sub.s $f0, $f1, $f0");
                    asmCode.add("mfc1 $a0, $f0");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("sub $a0, $t0, $a0");
                }
                break;
            case "ASTERISK":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("mul.s $f0, $f1, $f0");
                    asmCode.add("mfc1 $a0, $f0");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mul $a0, $t0, $a0");
                }
                break;
            case "SLASH":
                if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("FLOAT") || analyzer.checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) {
                    cgen(binaryExpression.getLhsExpression());
                    if(analyzer.checkType(binaryExpression.getLhsExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("mtc1 $a0, $f0");
                    if(analyzer.checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) {
                        asmCode.add("cvt.s.w $f0, $f0");
                    }
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("mtc1 $t0, $f1");
                    asmCode.add("div.s $f0, $f1, $f0");
                    asmCode.add("mfc1 $a0, $f0");
                } else {
                    cgen(binaryExpression.getLhsExpression());
                    asmCode.add("sw $a0, 0($sp)");
                    asmCode.add("subu $sp, $sp, 4");
                    cgen(binaryExpression.getRhsExpression());
                    asmCode.add("lw $t0, 4($sp)");
                    asmCode.add("addiu $sp, $sp, 4");
                    asmCode.add("div $a0, $t0, $a0");
                }
                break;
        }
    }

    private void cgenConstant(VariableNode constant) {
        switch(constant.getVariableType().getTokenType()) {
            case "ID":
                for(int i = analyzer.getSymbolTable().size() - 1 ; i >= 0 ; i--) {
                    if(analyzer.getSymbolTable().get(i).getSymbolID().equals(constant.getVariableID().getTokenValue())) {
                        if(!analyzer.getSymbolTable().get(i).isGlobal()) {
                            if (analyzer.getSymbolTable().get(i).getSymbolType().equals("INTEGER")) {
                                asmCode.add("lw $a0, " + (analyzer.getSymbolTable().size() - i) + "($sp)");
                            } else {
                                asmCode.add("lwc1.s $f0, " + (analyzer.getSymbolTable().size() - i) + "($sp)");
                                asmCode.add("mfc1 $a0, $f0");
                            }
                        } else {
                            if (analyzer.getSymbolTable().get(i).getSymbolType().equals("INTEGER")) {
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
