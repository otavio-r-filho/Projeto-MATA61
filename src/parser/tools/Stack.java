package parser.tools;

import java.util.ArrayList;

public class Stack {
	private ArrayList<String> stackList;
	
	public Stack() {
		stackList = new ArrayList<String>();
	}
	
	public void push(String element) {
		stackList.add(element);
	}
	
	public String pop() {
		if(!stackList.isEmpty()) {
			String popResult = stackList.get(stackList.size() - 1);
			stackList.remove(stackList.size() - 1);
			return popResult;
		} else {
			System.out.println("A lista está vazia!");
			return null;
		}		 
	}
	
	public String checkTop() {
		if(stackList.size() > 0) {
			return stackList.get(stackList.size() - 1);
		} else {
			return null;
		}
	}
	
	public boolean isEmpty() {
		return stackList.isEmpty();
	}
}
