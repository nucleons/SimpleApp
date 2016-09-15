package com.example.aditya.simpleapp.network;

/**
 * Created by aditya on 13/9/16.
 *
 * Custom exception class for notifying of the outcome
 */
public class SimpleAppException extends Exception{

    public SimpleAppException(String detailMessage) {
        super(detailMessage);
    }

    public SimpleAppException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SimpleAppException(Throwable throwable) {
        super(throwable);
    }
}
