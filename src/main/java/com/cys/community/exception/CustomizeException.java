package com.cys.community.exception;

/**
 * @Author: sam
 * @create 2019-08-07-12:04 PM
 * @Description:
 **/
public class CustomizeException extends RuntimeException {
    private String message;


    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
