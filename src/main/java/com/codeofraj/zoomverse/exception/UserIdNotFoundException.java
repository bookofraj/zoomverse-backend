package com.codeofraj.zoomverse.exception;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException(String userId) {
        super("User with id '" + userId + "' not found.");
    }
}
