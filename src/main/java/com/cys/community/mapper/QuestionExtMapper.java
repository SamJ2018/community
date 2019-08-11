package com.cys.community.mapper;

import com.cys.community.dto.QuestionQueryDTO;
import com.cys.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}