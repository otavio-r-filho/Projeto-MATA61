package parser.definitions.nodes;

import lexer.definitions.tToken;

public abstract class CommandNode extends ASTNode{
	protected tToken commandType;

	public tToken getCommandType() {
		return commandType;
	}

	public void setCommandType(tToken commandType) {
		this.commandType = commandType;
	}
}
