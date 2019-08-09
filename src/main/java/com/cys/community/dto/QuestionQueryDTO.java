package com.cys.community.dto;

import lombok.Data;

/**
 * @Author: sam
 * @create 2019-08-09-7:42 AM
 * @Description:
 **/
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
