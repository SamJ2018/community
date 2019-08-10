package com.cys.community.dto;

import lombok.Data;

/**
 * @author sam
 * @since  2019-08-07-4:19 PM
 * @apiNote :
 **/

@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content; //评论的内容
    private Integer type; //评论的类型
}
