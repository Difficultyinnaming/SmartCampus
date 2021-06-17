package com.hss.campus.service.dynamic;

import com.hss.campus.entity.dynamic.Comment;

import java.util.List;

public interface CommentService {
    int addComment(Comment comment);
    List<Comment> queryAll(Integer id, Integer state);
    int modifyState(Integer id);
}
