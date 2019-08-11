package com.cys.community.dto;

import lombok.Data;

/**
 * @author : sam
 * @since -08-09-7:42 AM
 * @apiNote
 **/
@Data
public class QuestionQueryDTO {
    private String search ; //条件
    private Integer page; //页数
    private Integer size; //
}
