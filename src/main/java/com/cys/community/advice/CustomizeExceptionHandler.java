package com.cys.community.advice;

import com.alibaba.fastjson.JSON;
import com.cys.community.dto.ResultDTO;
import com.cys.community.exception.CustomizeErrorCode;
import com.cys.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author sam
 * @since  2019-08-07-11:20 AM
 * @apiNote :
 **/

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response) {
        String contentType = request.getContentType();

        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO ; //封装错误信息
            //返回 json
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(200);
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        } else {
            //错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
