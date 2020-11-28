package com.mec.spring.core;

public class CircleGetBeanException extends RuntimeException{
    private static final long serialVersionUID = 1953235172058097843L;

    public CircleGetBeanException() {
        super();
    }

    public CircleGetBeanException(String message) {
        super(message);
    }

    public CircleGetBeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircleGetBeanException(Throwable cause) {
        super(cause);
    }

    protected CircleGetBeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
