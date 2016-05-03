package parser.tools;

import java.util.ArrayList;

import lexer.definitions.tToken;

public class TokenFIFOStack {
	
	private ArrayList<tToken> tokenList;
	private int operationMode; //1 for stack, 2 for fifo;
	
	public TokenFIFOStack(int operationMode) {
		this.operationMode = operationMode;
		this.tokenList = new ArrayList<tToken>();
	}
	
	public void push(tToken token) {
		tokenList.add(token);
	}
	
	public tToken pop() {
		if(!tokenList.isEmpty()) {
			if(operationMode == 1) {
				return popStack();
			}
			return popFIFO();
		}
		return null;
	}
	
	private tToken popStack() {
		tToken popResult = tokenList.get(tokenList.size() - 1);
		tokenList.remove(tokenList.size() - 1);
		return popResult;
	}
	
	private tToken popFIFO() {
		tToken popResult = tokenList.get(0);
		tokenList.remove(0);
		return popResult;
	}
	
	public tToken checkTop() {
		if(!tokenList.isEmpty()) {
			if(operationMode == 1) {
				return checkTopStack();
			}
			return checkTopFIFO();
		}
		return null;
	}
	
	private tToken checkTopStack() {
		return tokenList.get(tokenList.size() - 1);
	}
	
	private tToken checkTopFIFO() {
		return tokenList.get(0);
	}
	
	public String operationMode() {
		if(operationMode == 1) {
			return "Stack";
		}
		
		return "FIFO";
	}
	
	public void setOperationMode(String operationMode) {
		if(operationMode.equals("Stack")) {
			this.operationMode = 1;
			return;
		}
		this.operationMode = 2;
	}
	
	public void setOperationMode(int operationMode) {
		if(operationMode == 1) {
			this.operationMode = 1;
			return;
		}
		this.operationMode = 2;
	}
}
