package com.cys.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
