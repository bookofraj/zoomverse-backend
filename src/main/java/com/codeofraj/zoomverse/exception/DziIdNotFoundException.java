package com.codeofraj.zoomverse.exception;

public class DziIdNotFoundException extends RuntimeException {
    public DziIdNotFoundException(String dziId) {
        super("DZI with id '" + dziId + "' do not exists.");
    }
}
