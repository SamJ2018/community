package com.cys.community.exception;

/**
 * @Author: sam
 * @create 2019-08-07-12:14 PM
 * @Description:
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在了，要不换个试试？");
    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
