package com.cys.community.dto;

import com.cys.community.exception.CustomizeErrorCode;
import com.cys.community.exception.CustomizeException;
import lombok.Data;

/**
 * @Author: sam
 * @create 2019-08-07-4:42 PM
 * @Description:
 **/
@Data
public class ResultDTO {
    private Integer code;   //响应码
    private String message;  //响应的消息

    public static ResultDTO errOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errOf(CustomizeErrorCode errorCode) {
        return errOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO errOf(CustomizeException e) {
        return errOf(e.getCode(),e.getMessage());
    }
}
