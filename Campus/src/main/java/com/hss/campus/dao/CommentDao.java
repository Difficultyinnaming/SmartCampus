package com.hss.campus.dao;

import com.hss.campus.entity.dynamic.Comment;

import java.util.List;

public interface CommentDao {
    int addComment(Comment comment);
    List<Comment> queryAll(Integer id, Integer state);
    int modifyState(Integer id);
    Comment queryOne(Integer id);
}
