package com.slt.documentmanagment.exceptions;

public class EmailSendException extends Exception {
    public EmailSendException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}