package com.cys.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: sam
 * @create 2019-08-08-1:36 PM
 * @Description:
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
