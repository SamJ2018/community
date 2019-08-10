package com.cys.community.controller;

import com.cys.community.model.User;
import com.cys.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author sam
 * @apiNote
 * @since 2019-8-10
 */

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/afterRegister")
    public String after(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpServletResponse response) {

        User user = new User();
        user.setToken(password);
        user.setName(username);
        user.setAccountId(UUID.randomUUID().toString());
        user.setAvatarUrl("/images/avatar.png");
        userService.createOrUpdate(user);
        response.addCookie(new Cookie("token", password));
        return "redirect:/";
    }
}
