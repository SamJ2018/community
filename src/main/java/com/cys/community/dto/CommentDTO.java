package com.cys.community.dto;

import lombok.Data;

/**
 * @Author: sam
 * @create 2019-08-07-4:19 PM
 * @Description:
 **/
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
