package com.cys.community.dto;
import lombok.Data;

/**
 * @author sam
 * @apiNote
 * @since 2019-08-08-10:32 PM
 **/

@Data
public class FileDTO {
    private int success;
    private String message;
    private String url;
}
