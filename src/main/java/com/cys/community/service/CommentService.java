package com.cys.community.service;

import com.cys.community.dto.CommentDTO;
import com.cys.community.enums.CommentTypeEnum;
import com.cys.community.exception.CustomizeErrorCode;
import com.cys.community.exception.CustomizeException;
import com.cys.community.mapper.CommentMapper;
import com.cys.community.mapper.QuestionExtMapper;
import com.cys.community.mapper.QuestionMapper;
import com.cys.community.mapper.UserMapper;
import com.cys.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: sam
 * @create 2019-08-07-4:50 PM
 * @Description:
 **/

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional   //配置事务
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);  //rollback
        }
    }

    public List<CommentDTO> listByQuestionId(Long id) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("gmt_create desc");
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(CommentTypeEnum.QUESTION.getType());

        List<Comment> comments = commentMapper.selectByExample(commentExample);
        System.out.println(comments);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //使用lamdar获取评论人
        Set<Long> commentator = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());//查询所有评论人
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentator);

        //获取评论人并换位Map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);//获取所有user
        List<User> users = userMapper.selectByExample(userExample);

        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //转换comment为commentDTOS
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
