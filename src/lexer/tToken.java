package lexer;

public class tToken {
	public String tokenClass;
	public String tokenValue;
	
	public tToken(String tokenClass, String tokenValue) {
		this.tokenClass = tokenClass;
		this.tokenValue = tokenValue;
	}
	
	public String getToken() {
		return ("<" + this.tokenClass + ", " + this.tokenValue + ">");
	}
}
