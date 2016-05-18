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

import jdk.nashorn.internal.ir.Block;
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

    //TODO Add removeSymbol(ASTNode node) method
    public void removeSymbols(ASTNode node) {
        Program programNode = null;
        FunctionNode functionNode = null;
        BlockNode blockNode = null;
        ArrayList<VariableNode> variableNodes = null;

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
}