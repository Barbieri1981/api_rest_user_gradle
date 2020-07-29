package com.api.rest.exception;


import com.api.rest.util.ErrorType;

public class UserException extends RuntimeException {

    protected final ErrorType error;
    protected final int httpStatus;

    public UserException(final String message, final ErrorType error, final int httpStatus) {
        super(message);
        this.error = error;
        this.httpStatus = httpStatus;
    }


    public ErrorType getError() {
        return this.error;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

}
