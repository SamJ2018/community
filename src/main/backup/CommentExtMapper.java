package com.cys.community.mapper;

import com.cys.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}