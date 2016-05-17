package semantic.semantic;

/*
    Semantic Analyzer:

    This will receive the program node and process the tree based on the grammar
    This is part of the subject MATA61 - Compilers of the Federal Univery of Bahia
 */

import parser.definitions.nodes.ASTNode;
import parser.definitions.nodes.FunctionNode;
import parser.definitions.nodes.Program;
import parser.definitions.nodes.VariableNode;
import semantic.semantic.definitions.Symbol;

import java.util.ArrayList;

public class Analyzer {

    private ArrayList<Symbol> symbolTable;

    public Analyzer() {
        symbolTable = new ArrayList<Symbol>();
    }

    public boolean analyzeTree(Program program) {

        return false;
    }

    public void addSymbol(ASTNode node) {
        switch(node.getNodeType()) {
            case "PROGRAM":
                Program programNode = (Program) node;
                ArrayList<VariableNode> variableNodes = programNode.getGlobalVariables();
                for(VariableNode variableNode : variableNodes) {
                    Symbol newSymbol = new Symbol(variableNode.getVariableID().getTokenValue(), variableNode.getVariableType().getTokenType(), false);
                    symbolTable.add(newSymbol);
                }
                ArrayList<FunctionNode> functionNodes = programNode.getFunctions();
                for(FunctionNode functionNode : functionNodes) {
                    Symbol newSymbol = new Symbol(functionNode.getFunctioID().getTokenValue(), functionNode.getReturnType().getTokenType(), false)
                }
                break;
        }
    }
}
