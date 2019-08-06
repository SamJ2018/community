package com.cys.community.service;

import com.cys.community.dto.PaginationDTO;
import com.cys.community.dto.QuestionDTO;
import com.cys.community.mapper.QuestionMapper;
import com.cys.community.mapper.UserMapper;
import com.cys.community.model.Question;
import com.cys.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sam
 * @create 2019-08-06-1:35 PM
 * @Description:
 **/

/**
 * 中间层，用于组装逻辑
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //获取到所有记录数
        Integer totalCount = questionMapper.count();

        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;

        page = page < 1 ? page = 1 : (page > totalPage ? page = totalPage : page); //robust
        paginationDTO.setPagination(totalPage, page);

        //size 5
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速将question对象中的属性拷贝到questionDTO
            questionDTO.setUser(user);//用于同时获得用户信息
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOS);


        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //获取到所有记录数
        Integer totalCount = questionMapper.countByUserId(userId);

        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;


        page = page < 1 ? page = 1 : (page > totalPage ? page = totalPage : page); //robust
        paginationDTO.setPagination(totalPage, page);

        //size 5
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速将question对象中的属性拷贝到questionDTO
            questionDTO.setUser(user);//用于同时获得用户信息
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOS);


        return paginationDTO;
    }
}
