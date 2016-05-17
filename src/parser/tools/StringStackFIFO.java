package parser.tools;

import java.util.ArrayList;

public class StringStackFIFO {
	
	private ArrayList<String> stringList;
	private int operationMode;
	
	public StringStackFIFO(int operationMode) {
		this.operationMode = operationMode;
		this.stringList = new ArrayList<String>();
	}
	
	public void push(String word) {
		stringList.add(word);
	}
	
	public String pop() {
		if(!stringList.isEmpty()) {
			if(operationMode == 1) {
				return popStack();
			}
			return popFIFO();
		}
		return null;
	}
	
	private String popStack() {
		String popResult = stringList.get(stringList.size() - 1);
		stringList.remove(stringList.size() - 1);
		return popResult;
	}
	
	private String popFIFO() {
		String popResult = stringList.get(0);
		stringList.remove(0);
		return popResult;
	}
	
	public String checkTop() {
		if(!stringList.isEmpty()) {
			if(operationMode == 1) {
				return checkTopStack();
			}
			return checkTopFIFO();
		}
		return null;
	}
	
	private String checkTopStack() {
		return stringList.get(stringList.size() - 1);
	}
	
	private String checkTopFIFO() {
		return stringList.get(0);
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
	
	public void clearStack() {
		this.stringList.clear();
	}
}
