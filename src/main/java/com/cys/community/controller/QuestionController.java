package com.cys.community.controller;

import com.cys.community.dto.CommentDTO;
import com.cys.community.dto.QuestionDTO;
import com.cys.community.enums.CommentTypeEnum;
import com.cys.community.service.CommentService;
import com.cys.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author sam
 * @since  2019-08-06-10:57 PM
 * @apiNote :
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        //累加阅读数
        questionService.increaseView(id);
        model.addAttribute("comments", comments);
        model.addAttribute("question", questionDTO);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
