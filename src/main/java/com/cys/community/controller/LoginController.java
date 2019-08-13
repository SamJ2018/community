package com.cys.community.controller;

import com.cys.community.mapper.UserMapper;
import com.cys.community.model.User;
import com.cys.community.model.UserExample;
import com.cys.community.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("afterLogin")
    public String afterLogin(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password,
                             HttpServletResponse response) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(username).andTokenEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 0) {
            for (User user : users) {
                userService.createOrUpdate(user);
                break;
            }
            response.addCookie(new Cookie("token", password));
        }else{
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
