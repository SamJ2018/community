package com.cys.community.enums;

/**
 * @author : sam
 * @since  2019-08-08-5:17 PM
 * @apiNote :
 **/
public enum NotificationStatusEnum {
    UNREAD(0),READ(1)
    ;
    private int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
