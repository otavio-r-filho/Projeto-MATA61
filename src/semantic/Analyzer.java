package semantic;

/*
    Semantic Analyzer:

    This will receive the program node and process the tree based on the grammar
    This is part of the subject MATA61 - Compilers of the Federal Univery of Bahia

    Types of analyses:
    1 - Check declaration
    2 - Check types
    3 - Check parameters

    The idea behind this code:
    1 - Fill up the symbol table with the symbols of the actual node;
    2 -
 */

/**
 * Checks
 * PROGRAM: double function declaration
 * CALL: function declaration, number of function parameters and type of function parameters
 * ATTRIBUTION: variable declaration, function declaration and function return type
 * CONSTANT(with no type or with ID): declaration
 * BINARYEXPRESSION(OR and AND): check for boolean types
 * BINARYEXPRESSION(rest): check for INTEGER or FLOATS
 *
 * Symbol fill:
 * Entries of the following nodes:
 * PROGRAM, FUNCTION, BLOCK
 *
 * Symbol remove:
 * Return of the following nodes:
 * PROGRAM, FUNCTION, BLOCK
 * @param node
 */

import lexer.definitions.tToken;
import parser.definitions.nodes.*;
import semantic.definitions.Error;
import semantic.definitions.Symbol;

import java.util.ArrayList;

public class Analyzer {

    private Program program;
    private ArrayList<Symbol> symbolTable;
    private String errorDescription;
    private tToken errorToken;
    private Error error;

    public Analyzer() {
        symbolTable = new ArrayList<Symbol>();
        error = new Error();
    }

    public Analyzer(Program program) {
        this();
        this.program = program;
    }


    //This method is responsible for arrangin the appropriate checks for each type of node
    public boolean analyzeTree(ASTNode node) {
        switch (node.getNodeType()) {
            case "PROGRAM":
                //First check for multiple declarations, then we can add the symbols to the symbol table
                if(checkMultiDeclaration(node)) {
                    fillSymbols(node);
                    Program program = (Program) node;
                    for(FunctionNode functionNode: program.getFunctions()) {
                        if(!analyzeTree(functionNode)) {
                            return false;
                        }
                    }
                    removeSymbols(node);
                    return true;
                }
                break;
            case "FUNCTION":
                if(checkMultiDeclaration(node)) {
                    FunctionNode functionNode = (FunctionNode) node;
                    if(!functionNode.getReturnType().getTokenType().equals("VOID")) {
                        if(checkReturn(functionNode.getBlock())) {
                            fillSymbols(node);
                            if(analyzeTree(functionNode.getBlock())) {
                                removeSymbols(node);
                                return true;
                            }
                        }
                    } else {
                        fillSymbols(node);
                        if (analyzeTree(functionNode.getBlock())) {
                            removeSymbols(node);
                            return true;
                        }
                    }
                }
                break;
            case "BLOCK":
                if(checkMultiDeclaration(node)) {
                    fillSymbols(node);
                    BlockNode blockNode = (BlockNode) node;
                    for (CommandNode commandNode : blockNode.getCommands()) {
                        if (!analyzeTree(commandNode)) {
                            return false;
                        }
                    }
                    removeSymbols(node);
                    return true;
                }
                break;
            case "IF":
                //Condition expressions should return only boolean
                IfNode ifNode = (IfNode) node;
                if(!ifNode.getConditionExpression().getNodeType().equals("ATTRIBUTION") && checkType(ifNode.getConditionExpression()).equals("BOOLEAN")) {
                    return analyzeTree(ifNode.getCommand()) && analyzeTree(ifNode.getElseCommand());
                } //TODO add error treatment here
                break;
            case "WHILE":
                //Condition expressions should return only boolean
                WhileNode whileNode = (WhileNode) node;
                if(!whileNode.getConditionExpression().getNodeType().equals("ATTRIBUTION") && checkType(whileNode.getConditionExpression()).equals("BOOLEAN")) {
                    return analyzeTree(whileNode.getCommand());
                } //TODO add error treatment here
                break;
            case "FOR":
                ForNode forNode = (ForNode) node;
                //Checking if the number of expressions are the same;
                if(forNode.getInitialExpressionList().size() != forNode.getStopExpressionList().size() || forNode.getInitialExpressionList().size() != forNode.getIncrementExpressionList().size() || forNode.getInitialExpressionList().size() == 0) {
                    return false; //TODO add error treatment here
                }

                for(int i = 0; i < forNode.getInitialExpressionList().size(); i++) {
                    //Checking for expression with different IDs
                    //TODO fix this check
                    if(forNode.getInitialExpressionList().get(i).getNodeType().equals("ATTRIBUTION") && checkType(forNode.getStopExpressionList().get(i)).equals("BOOLEAN") && forNode.getIncrementExpressionList().get(i).getNodeType().equals("ATTRIBUTION"));
                }
                break;
            case "ELSE":
                ElseNode elseNode = (ElseNode) node;
                return analyzeTree(elseNode.getCommand());
            case "RETURN":
                ReturnNode returnNode = (ReturnNode) node;
                //Getting the function so we can compare the return;
                while(!node.getNodeType().equals("FUNCTION")) {
                    node = node.getFatherNode();
                }
                FunctionNode functionNode = (FunctionNode) node;
                if(functionNode.getReturnType().getTokenType().equals("VOID")) {
                    if(returnNode.getReturnExpression() == null) { return true; }
                } else {
                    if(returnNode.getReturnExpression() != null && checkType(returnNode.getReturnExpression()).equals(functionNode.getReturnType().getTokenType())) { return true; }
                }
                break;
            case "ATTRIBUTION":
                AttributionNode attributionNode = (AttributionNode) node;
                if(checkDeclaration(node) && checkType(node).equals("BOOLEAN")) { return true; }
                break;
            case "CALL":
                if(checkDeclaration(node) && checkParams(node)) { return true; }
                break;
            case "BINARYEXPRESSION":
                BinaryExpression binaryExpression = (BinaryExpression) node;
                return analyzeTree(binaryExpression);
            case "UNARYEXPRESSION":
                UnaryExpression unaryExpression = (UnaryExpression) node;
                return analyzeTree(unaryExpression.getExpression());
            case "CONSTANT":
                return true;
        }
        return false;
    }

    //Fill the symbol table according to the type of node
    public void fillSymbols(ASTNode node) {
        Program programNode = null;
        FunctionNode functionNode = null;
        BlockNode blockNode = null;
        ArrayList<VariableNode> variableNodes;

        switch(node.getNodeType()) {
            case "PROGRAM":
                //Inserting the global variables symbols
                programNode = (Program) node;
                for(VariableNode variableNode : programNode.getGlobalVariables()) {
                    Symbol newSymbol = new Symbol(variableNode.getVariableID().getTokenValue(), variableNode.getVariableType().getTokenType(), true, false);
                    symbolTable.add(newSymbol);
                }
                //Inserting the function symbols
                for(FunctionNode function: programNode.getFunctions()) {
                    symbolTable.add(new Symbol(function.getFunctioID().getTokenValue(), function.getReturnType().getTokenType(), false, true));
                }
                break;
            case "FUNCTION":
                functionNode = (FunctionNode)node;
                for(VariableNode variableNode : functionNode.getFunctionParameters()) {
                    symbolTable.add(new Symbol(variableNode.getVariableID().getTokenValue(), variableNode.getVariableType().getTokenType(), false, false));
                }
                break;
            case "BLOCK":
                blockNode = (BlockNode) node;
                for(VariableNode variableNode : blockNode.getBlockVariables()) {
                    symbolTable.add(new Symbol(variableNode.getVariableID().getTokenValue(), variableNode.getVariableType().getTokenType(), false, false));
                }
                break;
        }
    }

    public void removeSymbols(ASTNode node) {
        Program programNode;
        FunctionNode functionNode;
        BlockNode blockNode;
        ArrayList<VariableNode> variableNodes;

        int stop;

        switch (node.getNodeType()) {
            case "PROGRAM":
                programNode = (Program) node;
                //Removing function IDs
                stop = (symbolTable.size() - 1) - programNode.getFunctions().size();
                for(int i = symbolTable.size() - 1; i > stop ; i--) {
                    symbolTable.remove(i);
                }
                //Removing global variables
                stop = (symbolTable.size() - 1) - programNode.getGlobalVariables().size();
                for(int i = symbolTable.size() - 1; i > stop; i--) {
                    symbolTable.remove(i);
                }
                break;
            case "FUNCTION":
                functionNode = (FunctionNode) node;
                stop = (symbolTable.size() - 1) - functionNode.getFunctionParameters().size();
                for(int i = symbolTable.size() - 1; i > stop ; i--) {
                    symbolTable.remove(i);
                }
                break;
            case "BLOCK":
                blockNode = (BlockNode) node;
                stop = (symbolTable.size() - 1) - blockNode.getBlockVariables().size();
                for(int i = symbolTable.size() - 1 ; i > stop ; i--) {
                    symbolTable.remove(i);
                }
                break;
        }
    }

    public boolean checkMultiDeclaration(ASTNode node) {
        int symbolCounter = 0; //this will count the number of equal symbols ocurrencies;

        Program program;
        BlockNode blockNode;
        //Take each variable from the node and compare with the list
        //TODO make it multiple thread
        //TODO add error treatment
        switch (node.getNodeType()) {
            case "PROGRAM":
                //Checking for double function declaration
                program = (Program) node;
                for(FunctionNode functionNode: program.getFunctions()) {
                    symbolCounter = 0;
                    for(FunctionNode functionNode1: program.getFunctions())
                        if(functionNode.getFunctioID().getTokenValue().equals(functionNode1.getFunctioID().getTokenValue())) {
                            symbolCounter++;
                    }
                    if(symbolCounter > 1) { return false;}
                }
                //Checking for double global variable declaration
                for(VariableNode variableNode: program.getGlobalVariables()) {
                    symbolCounter = 0;
                    for (VariableNode variableNode1 : program.getGlobalVariables()){
                        if (variableNode.getVariableID().getTokenValue().equals(variableNode1.getVariableID().getTokenValue())) {
                            symbolCounter++;
                        }
                    }
                    if(symbolCounter > 1) { return false; } //TODO add error treatment here
                }
                return true;
            case "BLOCK":
                //Check variables in the function parameters
                blockNode = (BlockNode) node;
                for(VariableNode variableNode: blockNode.getBlockVariables()) {
                    symbolCounter = 0;
                    for(Symbol symbol: symbolTable) {
                        if(variableNode.getVariableID().getTokenValue().equals(symbol.getSymbolID()) && !symbol.isFunction() && !symbol.isGlobal()) {
                            symbolCounter++;
                        }
                    }
                    if(symbolCounter > 1) { return false; } //TODO add error treatment here
                }
                return true;
            case "FUNCTION":
                FunctionNode functionNode = (FunctionNode) node;
                //Checking for multiple declarations in the parameters
                for(VariableNode variableNode: functionNode.getFunctionParameters()) {
                    symbolCounter = 0;
                    //Checking for multiple declarations in the parameters
                    for(VariableNode variableNode1: functionNode.getFunctionParameters()) {
                        if(variableNode.getVariableID().getTokenValue().equals(variableNode1.getVariableID().getTokenValue())) { symbolCounter++; }
                    }
                    if(symbolCounter > 1) { return false; } //TODO add error treatment here
                    symbolCounter = 0;
                    //Checking for double declarations in the symbol table
                    for(Symbol symbol: symbolTable) {
                        if(variableNode.getVariableID().getTokenValue().equals(symbol.getSymbolID()) && !symbol.isGlobal()) { symbolCounter++; }
                    }
                    if(symbolCounter > 1) { return false; } //TODO add error treatment here
                }
                return true;
        }
        return false;
    }

    //This function checks the number and types of the call parameters
    //It assumes that the function declaration has already been done
    public boolean checkParams(ASTNode node) {
        if(node.getNodeType().equals("CALL")) {
            CallExpression callExpression = (CallExpression) node;
            while(!node.getNodeType().equals("PROGRAM")) { node = node.getFatherNode(); } //Go to the program node to find the definition of the function
            Program program = (Program) node;
            ArrayList<FunctionNode> functionNodes = program.getFunctions();
            for(FunctionNode functionNode: functionNodes) {
                if(functionNode.getFunctioID().getTokenValue().equals(callExpression.getCommandType().getTokenValue())) {
                    if(functionNode.getFunctionParameters().size() == callExpression.getExpressionList().size()) { //Checking if the number of parameters are the same
                        for(int i = 0; i < functionNode.getFunctionParameters().size(); i++) {
                            if(!functionNode.getFunctionParameters().get(i).getVariableType().getTokenType().equals(checkType(callExpression.getExpressionList().get(i)))) { return false; }
                        }
                        return true;
                    }
                    return false; //TODO add error treatment here
                }
            }
        }
        return false;
    }

    public String checkType(ASTNode node) {
        BinaryExpression binaryExpression;
        UnaryExpression unaryExpression;
        CallExpression callExpression;

        switch (node.getNodeType()) {
            case "BINARYEXPRESSION":
                binaryExpression = (BinaryExpression) node;
                switch (binaryExpression.getExpressionType().getTokenType()) {
                    case "OR":
                        if(checkType(binaryExpression.getLhsExpression()).equals("BOOLEAN") && checkType(binaryExpression.getRhsExpression()).equals("BOOLEAN")) { return "BOOLEAN"; }
                        break;
                    case "AND":
                        if(checkType(binaryExpression.getLhsExpression()).equals("BOOLEAN") && checkType(binaryExpression.getRhsExpression()).equals("BOOLEAN")) { return "BOOLEAN"; }
                        break;
                    case "COMPARISSON":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                            (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "BOOLEAN";
                        }
                        break;
                    case "DIFFERENT":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                                (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "BOOLEAN";
                        }
                        break;
                    case "SMALLER":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                                (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "BOOLEAN";
                        }
                        break;
                    case "GREATER":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                                (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "BOOLEAN";
                        }
                        break;
                    case "GEQUAL":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                                (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "BOOLEAN";
                        }
                        break;
                    case "SEQUAL":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                                (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "BOOLEAN";
                        }
                        break;
                    case "PLUS":
                        if(checkType(binaryExpression.getLhsExpression()).equals(checkType(binaryExpression.getRhsExpression())) && (checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT"))) {
                            return checkType(binaryExpression.getLhsExpression());
                        } else if(!checkType(binaryExpression.getLhsExpression()).equals(checkType(binaryExpression.getRhsExpression())) &&
                                (checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                                (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "FLOAT";
                        }
                        break;
                    case "MINUS":
                        if(checkType(binaryExpression.getLhsExpression()).equals(checkType(binaryExpression.getRhsExpression())) && (checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT"))) {
                            return checkType(binaryExpression.getLhsExpression());
                        } else if(!checkType(binaryExpression.getLhsExpression()).equals(checkType(binaryExpression.getRhsExpression())) &&
                                (checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                                (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "FLOAT";
                        }
                        break;
                    case "ASTERISK":
                        if(checkType(binaryExpression.getLhsExpression()).equals(checkType(binaryExpression.getRhsExpression())) && (checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT"))) {
                            return checkType(binaryExpression.getLhsExpression());
                        } else if(!checkType(binaryExpression.getLhsExpression()).equals(checkType(binaryExpression.getRhsExpression())) &&
                                (checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                                (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "FLOAT";
                        }
                        break;
                    case "SLASH":
                        if(checkType(binaryExpression.getLhsExpression()).equals(checkType(binaryExpression.getRhsExpression())) && (checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT"))) {
                            return checkType(binaryExpression.getLhsExpression());
                        } else if(!checkType(binaryExpression.getLhsExpression()).equals(checkType(binaryExpression.getRhsExpression())) &&
                                (checkType(binaryExpression.getLhsExpression()).equals("INTEGER") || checkType(binaryExpression.getLhsExpression()).equals("FLOAT")) &&
                                (checkType(binaryExpression.getRhsExpression()).equals("INTEGER") || checkType(binaryExpression.getRhsExpression()).equals("FLOAT"))) {
                            return "FLOAT";
                        }
                        break;
                }
                break;
            case "UNARYEXPRESSION":
                unaryExpression = (UnaryExpression) node;
                switch (unaryExpression.getExpressionType().getTokenType()) {
                    case "MINUS":
                        if(checkType(unaryExpression.getExpression()).equals("INTEGER") || checkType(unaryExpression.getExpression()).equals("FLOAT")) { return checkType(unaryExpression.getExpression()); }
                        break;
                    case "EXCLAMATION":
                        if(checkType(unaryExpression.getExpression()).equals("BOOLEAN")) { return checkType(unaryExpression.getExpression()); }
                        break;
                    case "OPARENTHESES":
                        return checkType(unaryExpression.getExpression());
                }
                break;
            case "CALL":
                //Go to the program node, and check the type of the function
                callExpression = (CallExpression) node;
                while(!node.getNodeType().equals("PROGRAM")) { node = node.getFatherNode(); }
                Program program = (Program) node;
                ArrayList<FunctionNode> functionNodes = program.getFunctions();
                for(FunctionNode functionNode: functionNodes) {
                    if(functionNode.getFunctioID().getTokenValue().equals(callExpression.getFunctionID().getTokenValue())) {
                        return functionNode.getReturnType().getTokenValue();
                    }
                }
                break;
//            case "ATTRIBUTION":
//                AttributionNode attributionNode = (AttributionNode) node;
//                //The attribution returns boolean type true
//                //This check assumes that the declaration check has already been done
//                String variableType = null;
//                for(int i = symbolTable.size() - 1; i >= 0; i--) {
//                    if(symbolTable.get(i).getSymbolID().equals(attributionNode.getVariableID().getTokenValue()) && !symbolTable.get(i).isFunction()) {
//                        variableType = symbolTable.get(i).getSymbolType();
//                        break;
//                    }
//                }
//                if(checkType(attributionNode.getExpression()).equals(variableType)) { return "BOOLEAN"; }
//                break;
            case "CONSTANT":
                VariableNode constantNode = (VariableNode) node;
                switch(constantNode.getVariableType().getTokenType()) {
                    case "NUM":
                        return "INTEGER";
                    case "REAL":
                        return "FLOAT";
                    case "ID":
                        int i = symbolTable.size() - 1;
                        while(i >= 0 && (!symbolTable.get(i).getSymbolID().equals(constantNode.getVariableID().getTokenValue()) && !symbolTable.get(i).isFunction())) { i--; }
                        return symbolTable.get(i).getSymbolType();
                }
                break;
        }
        return "ERROR";
    }

    public boolean checkDeclaration(ASTNode node) {
        CallExpression callExpression;
        AttributionNode attributionNode;
        switch(node.getNodeType()) {
            case "CALL":
                callExpression = (CallExpression) node;
                //Make it search from the last symbols towards the first
                for(int i = symbolTable.size() - 1 ; i >= 0 ; i--) {
                    if(symbolTable.get(i).getSymbolID().equals(callExpression.getFunctionID().getTokenValue()) && symbolTable.get(i).isFunction()) { return true; }
                } //TODO add error treatment here
                this.errorDescription = "Funcao nao declarada. Linha: " + callExpression.getFunctionID().getLine() + ". Coluna: " + callExpression.getFunctionID().getCollumn() +".\n";
                break;
            case "ATTRIBUTION":
                attributionNode = (AttributionNode) node;
                for(int i = symbolTable.size() - 1 ; i >= 0 ; i--) {
                    if (symbolTable.get(i).getSymbolID().equals(attributionNode.getCommandType().getTokenValue()) && !symbolTable.get(i).isFunction()) { return true; }
                } //TODO add error treatment here
                this.errorDescription = "Variavel não declarada. Linha: " + attributionNode.getCommandType().getLine() + ". Coluna: " + attributionNode.getCommandType().getCollumn() + ".\n";
                break;
        }
        return false;
    }

    public boolean checkReturn(ASTNode node) {
        switch (node.getNodeType()) {
            case "BLOCK":
                BlockNode blockNode = (BlockNode) node;
                for(CommandNode commandNode: blockNode.getCommands()) {
                    if(commandNode.getNodeType().equals("RETURN")) { return true; }
                }

                for(CommandNode commandNode: blockNode.getCommands()) {
                    if(commandNode.getCommandType().equals("IF") || commandNode.getCommandType().equals("WHILE") || commandNode.getCommandType().equals("FOR")) {
                        if(checkReturn(commandNode)) { return true; }
                    }
                }
                return false;
            case "IF":
                IfNode ifNode = (IfNode) node;
                switch(ifNode.getCommand().getNodeType()) {
                    case "RETURN":
                        return true;
                    case "BLOCK":
                        return checkReturn(ifNode.getCommand());
                }
                break;
            case "WHILE":
                WhileNode whileNode = (WhileNode) node;
                switch(whileNode.getCommand().getNodeType()) {
                    case "RETURN":
                        return true;
                    case "BLOCK":
                        return checkReturn(whileNode.getCommand());
                }
                break;
            case "FOR":
                ForNode forNode = (ForNode) node;
                switch(forNode.getCommand().getNodeType()) {
                    case "RETURN":
                        return true;
                    case "BLOCK":
                        return checkReturn(forNode.getCommand());
                }
                break;
        }
        return false;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}