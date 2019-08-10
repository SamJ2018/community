package com.cys.community.dto;

import lombok.Data;

/**
 * @author sam
 * @since  2019-08-06-7:54 AM
 * @apiNote
 **/

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
