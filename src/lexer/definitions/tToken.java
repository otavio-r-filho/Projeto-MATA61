package lexer.definitions;

public class tToken {
	private String tokenType;
	private String tokenValue;
	private int tokenTypeIndex; 
	private int line;
	private int collumn;
	
	public tToken() {
		
	}
	
	public tToken(String tokenType){
		this.tokenType = tokenType;
		this.line = 0;
		this.collumn = 0;
	}
	
	public tToken(String tokenType, String tokenValue) {
		//this.tokenType = tokenType;
		this(tokenType);
		this.tokenValue = tokenValue;
	}
	
	public tToken(String tokenType, String tokenValue, int tokenTypeIndex) {
		this(tokenType, tokenValue);
		this.tokenTypeIndex = tokenTypeIndex;
	}
	
	public tToken(String tokenType, String tokenValue, int tokenTypeIndex, int tokenLine, int tokenCollumn) {
		this(tokenType, tokenValue, tokenTypeIndex);
		this.line = tokenLine;
		this.collumn = tokenCollumn;
	}
	
	public String getToken() {
		return ("<" + tokenType + ", " + tokenValue + ">");
	}
	
	public int gettokenTypeIndex(){
		return tokenTypeIndex;
	}
	
	public void setTokenValue(String tokenValue){
		this.tokenValue = tokenValue;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getCollumn() {
		return collumn;
	}
	
	public void setLine(int line) {
		this.line = line;
	}
	
	public void setCollumn(int collumn) {
		this.collumn = collumn;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	
	public String getTokenValue() {
		return tokenValue;
	}
}
