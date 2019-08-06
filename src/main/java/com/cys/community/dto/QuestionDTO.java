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
    private long id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private long creator;
    private long commentCount;
    private long viewCount;
    private long likeCount;
    private String tag;
    private User user; //*
}
