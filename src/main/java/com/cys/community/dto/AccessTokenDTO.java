package com.cys.community.dto;

import lombok.Data;

/**
 * @author sam
 * @since  2019-08-06-7:37 AM
 * @apiNote :
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
