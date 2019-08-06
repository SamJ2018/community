package com.cys.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: sam
 * @create 2019-08-06-12:05 AM
 * @Description:
 **/
@Controller
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
