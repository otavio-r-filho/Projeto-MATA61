package semantic.definitions;

/**
 * Created by otavio on 23/05/16.
 */
public class Error {

    private String errorMessage, errorLine, errorCollumn;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorLine() {
        return errorLine;
    }

    public void setErrorLine(String errorLine) {
        this.errorLine = errorLine;
    }

    public String getErrorCollumn() {
        return errorCollumn;
    }

    public void setErrorCollumn(String errorCollumn) {
        this.errorCollumn = errorCollumn;
    }
}
