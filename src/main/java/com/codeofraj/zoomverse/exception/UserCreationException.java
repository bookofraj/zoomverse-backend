package com.codeofraj.zoomverse.exception;

public class UserCreationException extends RuntimeException{
    public UserCreationException(String message) {
        super("User creation failed: " + message);
    }
}
