package com.hss.campus.service.impl;

import com.hss.campus.dao.DynamicDao;
import com.hss.campus.entity.dynamic.Dynamic;
import com.hss.campus.service.dynamic.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private DynamicDao dynamicDao;

    @Override
    public int add(Dynamic dynamic) {
        return dynamicDao.add(dynamic);
    }

    @Override
    public Dynamic queryDynamic(Integer id) {
        return dynamicDao.queryDynamic(id);
    }

    @Override
    public List<Dynamic> queryAll(Integer id,Integer status) {
        return dynamicDao.queryAll(id,status);
    }

    @Override
    public List<Dynamic> queryEveryone(Integer status) {
        return dynamicDao.queryEveryone(status);
    }

    @Override
    public int modifyStatus(Integer id) {
        return dynamicDao.modifyStatus(id);
    }

    @Override
    public int modifyNum(Integer num, Integer id) {
        return dynamicDao.modifyNum(num,id);
    }

    @Override
    public Long queryGreatNUm(Integer id) {
        return dynamicDao.queryGreatNUm(id);
    }

    @Override
    public boolean changeCommentCountById(Integer count, Integer id) {
        return dynamicDao.changeCommentCountById(count, id)!=-1;
    }
}
