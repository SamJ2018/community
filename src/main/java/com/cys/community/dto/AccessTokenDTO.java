package com.cys.community.dto;

import lombok.Data;

/**
 * @Author: sam
 * @create 2019-08-06-7:37 AM
 * @Description:
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
