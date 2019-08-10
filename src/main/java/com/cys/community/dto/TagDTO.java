package com.cys.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author : sam
 * @since  2019-08-08-1:36 PM
 * @apiNote
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
