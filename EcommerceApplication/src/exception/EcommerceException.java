package exception;

import model.ErrorCode;

public class EcommerceException extends RuntimeException {

    ErrorCode errorCode;
    String errorMessage;

    public EcommerceException(ErrorCode errorCode,String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
