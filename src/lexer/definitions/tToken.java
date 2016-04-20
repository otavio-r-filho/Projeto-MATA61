package lexer.definitions;

public class tToken {
	private String tokenClass;
	private String tokenValue;
	private int tokenClassIndex; 
	
	public tToken() {
		
	}
	
	public tToken(String tokenClass){
		this.tokenClass = tokenClass;
	}
	
	public tToken(String tokenClass, String tokenValue) {
		//this.tokenClass = tokenClass;
		this(tokenClass);
		this.tokenValue = tokenValue;
	}
	
	public tToken(String tokenClass, String tokenValue, int tokenClassIndex) {
		this(tokenClass, tokenValue);
		this.tokenClassIndex = tokenClassIndex;
	}
	
	public String getToken() {
		return ("<" + tokenClass + ", " + tokenValue + ">");
	}
	
	public int getTokenClassIndex(){
		return tokenClassIndex;
	}
	
	public void setTokenValue(String tokenValue){
		this.tokenValue = tokenValue;
	}
}
