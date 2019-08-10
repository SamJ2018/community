package com.cys.community.enums;

/**
 * @author : sam
 * @since  2019-08-08-5:11 PM
 * @apiNote :
 **/
public enum NotificationEnum {
    REPLY_QUESTION(1, "回复了问题"),
    REPLY_COMMENT(2, "回复了评论");
    private int type; //
    private String name;

    NotificationEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String nameOfType(int type) {
        for (NotificationEnum notificationEnum : NotificationEnum.values()) {
            if (notificationEnum.getType() == type) {
                return notificationEnum.getName();
            }
        }
        return "";
    }
}
