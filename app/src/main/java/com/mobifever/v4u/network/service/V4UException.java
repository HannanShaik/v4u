package com.mobifever.v4u.network.service;

public class V4UException extends Exception {

    public V4UException() {
        super();
    }

    public V4UException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public V4UException(String detailMessage) {
        super(detailMessage);
    }

    public V4UException(Throwable throwable) {
        super(throwable);
    }
}
