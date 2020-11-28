package com.mec.spring.core;

public class NoSuchBeanException extends RuntimeException{
    private static final long serialVersionUID = -4596875435004595009L;

    public NoSuchBeanException() {
        super();
    }

    public NoSuchBeanException(String message) {
        super(message);
    }

    public NoSuchBeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchBeanException(Throwable cause) {
        super(cause);
    }

    protected NoSuchBeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
