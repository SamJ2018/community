package com.cys.community.dto;

import lombok.Data;

/**
 * @Author: sam
 * @create 2019-08-06-7:54 AM
 * @Description:
 **/

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
