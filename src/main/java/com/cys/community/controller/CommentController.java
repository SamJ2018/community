package com.cys.community.controller;

import com.cys.community.dto.CommentDTO;
import com.cys.community.mapper.CommentMapper;
import com.cys.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sam
 * @create 2019-08-07-3:58 PM
 * @Description:
 **/
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody  //自动序列化-->json
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO) {

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(0);

        commentMapper.insert(comment);
        Map<Object, Object> objectObjectMap = new HashMap<>();
        objectObjectMap.put("message", "成功");
        return objectObjectMap;
    }
}
