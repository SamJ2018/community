package com.cys.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Integer creator;
    private long gmtCreate;
    private long gmtModified;
    private long commentCount;
    private long viewCount;
    private long likeCount;
    private String tag;
}
