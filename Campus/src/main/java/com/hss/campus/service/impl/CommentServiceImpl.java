package com.hss.campus.service.impl;

import com.hss.campus.dao.CommentDao;
import com.hss.campus.entity.dynamic.Comment;
import com.hss.campus.service.dynamic.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public List<Comment> queryAll(Integer id,Integer state) {
        return commentDao.queryAll(id,state);
    }

    @Override
    public int modifyState(Integer id) {
        return commentDao.modifyState(id);
    }

    @Override
    public Comment queryOne(Integer id) {
        return commentDao.queryOne(id);
    }
}
