package lexer.definitions;

import lexer.definitions.Tokens;

public class State {
	private boolean finalState;
	private int stateIndex;
	private String stateName;
	private tToken returnToken;
	
	
	public State(int stateIndex, String stateName, boolean finalState, tToken returnToken) {
		this.stateIndex = stateIndex;
		this.stateName = stateName;
		this.finalState = finalState;
		this.returnToken = returnToken;		
	}
	
}
