package com.cys.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author sam
 * @apiNote
 * @since 2019-8-10
 */

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }

}
