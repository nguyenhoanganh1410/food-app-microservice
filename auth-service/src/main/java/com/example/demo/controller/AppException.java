package com.example.demo.controller;


public class AppException extends RuntimeException {
    private int code;
    private String message;

    public AppException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);

    }


    public AppException(Throwable cause, int code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AppException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
