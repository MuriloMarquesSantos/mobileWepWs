package com.appsdeveloperblog.app.ws.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 7159797720879793339L;

    public UserServiceException(String message) {
        super(message);
    }
}
