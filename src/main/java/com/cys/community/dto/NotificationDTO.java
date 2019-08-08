package com.cys.community.dto;

import lombok.Data;

/**
 * @Author: sam
 * @create 2019-08-08-5:35 PM
 * @Description:
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
