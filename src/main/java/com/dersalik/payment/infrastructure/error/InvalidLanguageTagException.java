package com.dersalik.payment.infrastructure.error;

public class InvalidLanguageTagException extends IllegalArgumentException {

    public InvalidLanguageTagException(String message) {
        super(message);
    }

}
