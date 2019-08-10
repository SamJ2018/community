package com.cys.community.dto;

import lombok.Data;

/**
 * @author : sam
 * @since -08-09-7:42 AM
 * @apiNote
 **/
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
