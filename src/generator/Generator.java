package generator;

import parser.definitions.nodes.*;
import semantic.Analyzer;

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
                cgenIf((IfNode) node);
                break;
            case "ELSE":
                cgenElse((ElseNode) node);
                break;
            case "WHILE":
                cgenWhile((WhileNode) node);
                break;
            case "FOR":
                cgenFor((ForNode) node);
                break;
            case "ATTRIBUTION":
                cgenAttribution((AttributionNode) node);
                break;
            case "CALL":
                cgenCall((CallExpression) node);
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
        for(int i = analyzer.getSymbolTable().size() - 1 ; i >= 0 ; i--) {
            if(analyzer.getSymbolTable().get(i).getSymbolID().equals(attributionNode.getCommandType().getToken())) {
                if(analyzer.getSymbolTable().get(i).getSymbolType().equals("INTEGER")) {
                    if (analyzer.checkType(attributionNode.getExpression()).equals("FLOAT")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.w.s $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                } else {
                    if (analyzer.checkType(attributionNode.getExpression()).equals("INTEGER")) {
                        asmCode.add("mtc1 $a0, $f0");
                        asmCode.add("cvt.s.w $f0, $f0");
                        asmCode.add("mfc1 $a0, $f0");
                    }
                }
                if(analyzer.getSymbolTable().get(i).isGlobal()) {
                    asmCode.add("sw $a0, _" + analyzer.getSymbolTable().get(i).getSymbolID());
                } else {
                    asmCode.add("sw $a0, " + (analyzer.getSymbolTable().size() - i) + "($sp)");
                }
            }
        }
    }

    private void cgenCall(CallExpression callExpression) {
        asmCode.add("sw $fp, 0($sp)");
        asmCode.add("subu $sp, $sp, 4");
        for(ExpressionNode expressionNode: callExpression.getExpressionList()) {
            cgen(expressionNode);
            asmCode.add("sw $a0, 0($sp)");
            asmCode.add("subu $sp, $sp, 4");
        }
        asmCode.add("jal " + callExpression.getFunctionID() + "_entry:");
    }

    private void cgenReturn(ReturnNode returnNode) {
        if(returnNode.getReturnExpression() != null ) {
            cgen(returnNode.getReturnExpression());
        }
        ASTNode node = returnNode;
        int variableCount = 0;
        while(!node.getNodeType().equals("FUNCTION")) {
            if(node.getNodeType().equals("BLOCK")) {
                variableCount = variableCount + ((BlockNode) node).getBlockVariables().size();
            }
            node = node.getFatherNode();
        }
        asmCode.add("addiu $sp, $sp, " + (variableCount * 4)); //popping function variables
        variableCount = ((FunctionNode) node).getFunctionParameters().size();
        asmCode.add("lw $ra, 4($ra)");
        asmCode.add("addiu $sp, $sp, " + ((variableCount * 4) + 8)); //popping the AR
        asmCode.add("lw $fp, 0($sp)");
        asmCode.add("jr $ra");

    }

    private void cgenUnaryExpression(UnaryExpression unaryExpression) {
        switch (unaryExpression.getExpressionType().getTokenType()) {
            case "EXCLAMATION":
                cgen(unaryExpression.getExpression());
                asmCode.add("not $a0, $a0");
                break;
            case "MINUS":
                cgen(unaryExpression.getExpression());
                if(analyzer.checkType(unaryExpression.getExpression()).equals("INTEGER")) {
                    asmCode.add("neg $a0, $a0");
                } else {
                    asmCode.add("mtc1 $a0, $f0");
                    asmCode.add("neg.s $f0, $f0");
                    asmCode.add("mfc1 $a0, $f0");
                }
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
                                break;
                            } else {
                                asmCode.add("lwc1.s $f0, " + (analyzer.getSymbolTable().size() - i) + "($sp)");
                                asmCode.add("mfc1 $a0, $f0");
                                break;
                            }
                        } else {
                            if (analyzer.getSymbolTable().get(i).getSymbolType().equals("INTEGER")) {
                                asmCode.add("lw $a0, " + constant.getVariableID().getTokenValue());
                                break;
                            } else {
                                asmCode.add("lwc1 $f0, " + constant.getVariableID().getTokenValue());
                                asmCode.add("mfc1 $a0, $f0");
                                break;
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
