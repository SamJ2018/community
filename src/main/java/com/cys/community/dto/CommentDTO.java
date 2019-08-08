package com.cys.community.dto;

/**
 * @Author: sam
 * @create 2019-08-07-10:08 PM
 * @Description:
 **/

import com.cys.community.model.User;
import lombok.Data;

/**
 * 页面接受到的DTO
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
