package com.cys.community.controller;

import com.cys.community.dto.PaginationDTO;
import com.cys.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: sam
 * @create 2019-08-06-12:05 AM
 * @Description:
 **/
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}