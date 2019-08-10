package com.cys.community.dto;

import com.cys.community.exception.CustomizeErrorCode;
import com.cys.community.exception.CustomizeException;
import lombok.Data;

/**
 * @author sam
 * @since 2019-08-07-4:42 PM
 * @apiNote 封装页面返回的结果（响应码+相应的消息）
 **/

@Data
public class ResultDTO<T> {
    private Integer code;   //响应码
    private String message;  //响应的消息
    private T data;

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static <T> ResultDTO okOf(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }

    /**
     * 通用方法
     * @param code
     * @param message
     * @return
     */
    public static ResultDTO errOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    /**
     * 根据状态码（信息）封装结果集
     *
     * @param errorCode
     * @return
     */
    public static ResultDTO errOf(CustomizeErrorCode errorCode) {
        return errOf(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 根据异常类型封装结果集
     *
     * @param e
     * @return
     */
    public static ResultDTO errOf(CustomizeException e) {
        return errOf(e.getCode(), e.getMessage());
    }
}
