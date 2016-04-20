package lexer.definitions;

import lexer.definitions.tToken;

public class State {
	private boolean finalState;
	private int stateIndex;
	private String stateName;
	private tToken returnToken;
	//private String returnValue;
	
	
	public State(int stateIndex, String stateName) {
		this.stateIndex = stateIndex;
		this.stateName = stateName;
	}
	
	public State(int stateIndex, String stateName, boolean finalState) {
		this(stateIndex, stateName);
		this.finalState = finalState;
	}
	
	public State(int stateIndex, String stateName, boolean finalState, tToken returnToken) {
		this(stateIndex, stateName, finalState);
		this.returnToken = returnToken;		
	}
	
	public boolean isFinalState() {
		return this.finalState;
	}
	
	public String getToken() {
		return returnToken.getToken();
	}
	
	public int getStateIndex() {
		return this.stateIndex;
	}
	
	public String getStateName() {
		return this.stateName;
	}
	
}
