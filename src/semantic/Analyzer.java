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
import semantic.definitions.Symbol;

import java.util.ArrayList;

public class Analyzer {

    private ArrayList<Symbol> symbolTable;
    private String errorDescription;
    private tToken errorToken;

    public Analyzer() {
        symbolTable = new ArrayList<Symbol>();
    }


    //This method is responsible for arrangin the appropriate checks for each type of node
    public boolean analyzeTree(Program program) {

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
                variableNodes = programNode.getGlobalVariables();
                for(VariableNode variableNode : variableNodes) {
                    Symbol newSymbol = new Symbol(variableNode.getVariableID().getTokenValue(), variableNode.getVariableType().getTokenType(), false, false);
                    symbolTable.add(newSymbol);
                }
                //Inserting the function symbols
                ArrayList<FunctionNode> functionNodes = programNode.getFunctions();
                for(FunctionNode function: functionNodes) {
                    symbolTable.add(new Symbol(function.getFunctioID().getTokenValue(), function.getReturnType().getTokenType(), false, true));
                    break;
                }
            case "FUNCTION":
                functionNode = (FunctionNode)node;
                variableNodes = functionNode.getFunctionParameters();
                for(VariableNode variableNode : variableNodes) {
                    symbolTable.add(new Symbol(variableNode.getVariableID().getTokenValue(), variableNode.getVariableType().getTokenType(), false, false));
                }
                break;
            case "BLOCK":
                blockNode = (BlockNode) node;
                variableNodes = blockNode.getBlockVariables();
                for(VariableNode variableNode : variableNodes) {
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

        switch (node.getNodeType()) {
            case "PROGRAM":
                programNode = (Program) node;
                variableNodes = programNode.getGlobalVariables();
                for(VariableNode variableNode : variableNodes) {
                    Symbol symbol = new Symbol(variableNode.getVariableID().getTokenValue(), variableNode.getVariableType().getTokenType(), false, false);
                    if(symbolTable.contains(symbol)) { symbolTable.remove(symbolTable.lastIndexOf(symbol)); } //As the symbol table works as a stack, this will remove the last ocurrency of the symbol
                    else { System.out.println("REMOVE ERROR! No symbol was found"); }  //If no symbol is found this means an error, a strange symbol will stay at the stack
                }
                //Removing function IDs
                for(FunctionNode function: programNode.getFunctions()) {
                    Symbol symbol = new Symbol(function.getFunctioID().getTokenValue(), function.getReturnType().getTokenType(), false, true);
                    if(symbolTable.contains(symbol)) { symbolTable.remove(symbolTable.lastIndexOf(symbol)); }
                    else { System.out.println("REMOVE ERROR! No symbol was found"); }
                }
                break;
            case "FUNCTION":
                functionNode = (FunctionNode) node;
                for(VariableNode variableNode: functionNode.getFunctionParameters()) {
                    Symbol symbol = new Symbol(variableNode.getVariableID().getTokenValue(), variableNode.getVariableType().getTokenType(), false, false);
                    if(symbolTable.contains(symbol)) { symbolTable.remove(symbolTable.lastIndexOf(symbol)); }
                    else { System.out.println("REMOVE ERROR! No symbol was found"); }
                }
                break;
            case "BLOCK":
                blockNode = (BlockNode) node;
                for(VariableNode variableNode: blockNode.getBlockVariables()) {
                    Symbol symbol = new Symbol(variableNode.getVariableID().getTokenValue(), variableNode.getVariableType().getTokenType(), false, false);
                    if(symbolTable.contains(symbol)) { symbolTable.remove(symbolTable.lastIndexOf(symbol)); }
                    else { System.out.println("REMOVE ERROR! No symbol was found"); }
                }
                break;
        }
    }

    public boolean checkMultiDeclaration(ASTNode node) {
        int symbolCounter = 0; //this will count the number of equal symbols ocurrencies;

        Program program;
        BlockNode blockNode;
        ArrayList<FunctionNode> functionNodes;
        ArrayList<VariableNode> variableNodes;

        //Take each variable from the node and compare with the list
        //TODO make it multiple thread
        //TODO add error treatment
        switch (node.getNodeType()) {
            case "PROGRAM":
                program = (Program) node;
                functionNodes = program.getFunctions();
                for(FunctionNode functionNode: functionNodes) {
                    for(Symbol symbol: symbolTable)
                        if(functionNode.getFunctioID().getTokenValue().equals(symbol.getSymbolID()) && symbol.isFunction()) {
                            symbolCounter++;
                    }
                }
                variableNodes = program.getGlobalVariables();
                for(VariableNode variableNode: variableNodes) {
                    for(Symbol symbol: symbolTable)
                        if(variableNode.getVariableID().getTokenValue().equals(symbol.getSymbolID()) && !symbol.isFunction()) {
                            symbolCounter++;
                        }
                }
                break;
            case "BLOCK":
                blockNode = (BlockNode) node;
                variableNodes = blockNode.getBlockVariables();
                for(VariableNode variableNode: variableNodes) {
                    for(Symbol symbol: symbolTable) {
                        if(variableNode.getVariableID().getTokenValue().equals(symbol.getSymbolID()) && !symbol.isFunction()) {
                            symbolCounter++;
                        }
                    }
                }
                break;
        }

        if(symbolCounter < 2) { return true; }
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
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return "BOOLEAN"; }
                        break;
                    case "DIFFERENT":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return "BOOLEAN"; }
                        break;
                    case "SMALLER":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return "BOOLEAN"; }
                        break;
                    case "GREATER":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return "BOOLEAN"; }
                        break;
                    case "GEQUAL":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return "BOOLEAN"; }
                        break;
                    case "SEQUAL":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return "BOOLEAN"; }
                        break;
                    case "PLUS":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return checkType(binaryExpression.getRhsExpression()); }
                        break;
                    case "MINUS":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return checkType(binaryExpression.getRhsExpression()); }
                        break;
                    case "ASTERISK":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return checkType(binaryExpression.getRhsExpression()); }
                        break;
                    case "SLASH":
                        if((checkType(binaryExpression.getLhsExpression()).equals("INTEGER") && checkType(binaryExpression.getRhsExpression()).equals("INTEGER")) ||
                                checkType(binaryExpression.getLhsExpression()).equals("FLOAT") && checkType(binaryExpression.getRhsExpression()).equals("FLOAT")) { return checkType(binaryExpression.getRhsExpression()); }
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
            case "ATTRIBUTION":
                AttributionNode attributionNode = (AttributionNode) node;
                //The attribution returns boolean type true
                //This check assumes that the declaration check has already been done
                String variableType = null;
                for(int i = symbolTable.size() - 1; i >= 0; i--) {
                    if(symbolTable.get(i).getSymbolID().equals(attributionNode.getVariableID().getTokenValue()) && !symbolTable.get(i).isFunction()) {
                        variableType = symbolTable.get(i).getSymbolType();
                        break;
                    }
                }
                if(checkType(attributionNode.getExpression()).equals(variableType)) { return "BOOLEAN"; }
                break;
            case "CONSTANT":
                VariableNode constantNode = (VariableNode) node;
                switch(constantNode.getVariableType().getTokenType()) {
                    case "NUM":
                        return "INTEGER";
                    case "REAL":
                        return "FLOAT";
                    case "ID":
                        int i = symbolTable.size() - 1;
                        while(i >= 0 || (!symbolTable.get(i).getSymbolID().equals(constantNode.getVariableID().getTokenValue()) && !symbolTable.get(i).isFunction())) { i++; }
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
                for(Symbol symbol: symbolTable) {
                    if(symbol.getSymbolID().equals(callExpression.getFunctionID().getTokenValue()) && symbol.isFunction()) { return true; }
                }
                break;
            case "ATTRIBUTION":
                attributionNode = (AttributionNode) node;
                for (Symbol symbol: symbolTable) {
                    if (symbol.getSymbolID().equals(attributionNode.getVariableID().getTokenValue()) && !symbol.isFunction()) { return true; }
                }
                break;
        }
        return false;
    }
}