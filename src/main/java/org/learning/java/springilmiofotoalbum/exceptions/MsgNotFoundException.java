package org.learning.java.springilmiofotoalbum.exceptions;

public class MsgNotFoundException extends RuntimeException {

    public MsgNotFoundException(String message) {
        super(message);
    }
}
