package com.cys.community.dto;

import com.cys.community.model.User;
import lombok.Data;

/**
 * @Author: sam
 * @create 2019-08-06-1:33 PM
 * @Description:
 **/
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user; //*
}
