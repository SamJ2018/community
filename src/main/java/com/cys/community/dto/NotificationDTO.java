package com.cys.community.dto;

import lombok.Data;

/**
 * @author : sam
 * @since  2019-08-08-5:35 PM
 * @apiNote
 **/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;  //通知的人
    private Long outerid;
    private String notifierName;  //链接的标题
    private String outerTitle;  //链接的标题
    private String typeName;
    private Integer type;
}
