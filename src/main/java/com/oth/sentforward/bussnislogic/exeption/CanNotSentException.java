package com.oth.sentforward.bussnislogic.exeption;

import org.aspectj.bridge.IMessage;

public class CanNotSentException extends RuntimeException {

    public CanNotSentException(String message) {
        super(message);
    }

    public CanNotSentException() {
    }
}
