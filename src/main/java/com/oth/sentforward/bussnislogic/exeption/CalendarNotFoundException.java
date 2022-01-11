package com.oth.sentforward.bussnislogic.exeption;

public class CalendarNotFoundException extends RuntimeException{

    private String message;

    public CalendarNotFoundException() {
    }

    public CalendarNotFoundException(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
