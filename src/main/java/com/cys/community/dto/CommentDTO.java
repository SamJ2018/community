package com.cys.community.dto;

/**
 * @author sam
 * @create 2019-08-07-10:08 PM
 * @apiNote  页面接受到的DTO
 **/

import com.cys.community.model.User;
import lombok.Data;

/**
 *
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}
