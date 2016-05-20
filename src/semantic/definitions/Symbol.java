package semantic.definitions;

import lexer.definitions.tToken;

/**
 * Created by Otávio on 17/05/2016.
 */
public class Symbol {

    private String symbolID;
    private String symbolType;
    private boolean isGlobal;
    private boolean isFunction;

    public String getSymbolID() {
        return symbolID;
    }

    public void setSymbolID(String symbolID) {
        this.symbolID = symbolID;
    }

    public String getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(String symbolType) {
        this.symbolType = symbolType;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public boolean isFunction() {
        return isFunction;
    }

    public Symbol() {

    }

    public Symbol(String symbolID) {
        this.symbolID = symbolID;
    }

    public Symbol(String symbolID, String symbolType) {
        this(symbolID);
        this.symbolType = symbolType;
    }

    public Symbol(String symbolID, String symbolType, boolean isGlobal) {
        this(symbolID, symbolType);
        this.isGlobal = isGlobal;
    }

    public Symbol(String symbolID, String symbolType, boolean isGlobal, boolean isFunction) {
        this(symbolID, symbolType, isGlobal);
        this.isFunction = isFunction;
    }
}
