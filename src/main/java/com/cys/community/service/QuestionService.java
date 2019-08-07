package com.cys.community.service;

import com.cys.community.dto.PaginationDTO;
import com.cys.community.dto.QuestionDTO;
import com.cys.community.exception.CustomizeErrorCode;
import com.cys.community.exception.CustomizeException;
import com.cys.community.mapper.QuestionExtMapper;
import com.cys.community.mapper.QuestionMapper;
import com.cys.community.mapper.UserMapper;
import com.cys.community.model.Question;
import com.cys.community.model.QuestionExample;
import com.cys.community.model.User;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //获取到所有记录数
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());

        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;

        page = page < 1 ? page = 1 : (page > totalPage ? page = totalPage : page); //robust
        paginationDTO.setPagination(totalPage, page);

        //size 5
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速将question对象中的属性拷贝到questionDTO
            questionDTO.setUser(user);//用于同时获得用户信息
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOS);


        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //获取到所有记录数
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(example);


        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;


        page = page < 1 ? page = 1 : (page > totalPage ? page = totalPage : page); //robust
        paginationDTO.setPagination(totalPage, page);

        //size 5
        Integer offset = size * (page - 1);
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速将question对象中的属性拷贝到questionDTO
            questionDTO.setUser(user);//用于同时获得用户信息
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOS);


        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        } else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());

            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());

            int update = questionMapper.updateByExampleSelective(updateQuestion, questionExample);

            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

        }
    }

    public void increaseView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}

