package com.hss.campus.dao;

import com.hss.campus.entity.dynamic.Images;

import java.util.List;

public interface ImagesDao {
    int add(String url, String mark);
    //查找
    List<Images> queryUrl(String mark);
    //更新
    int modifyStatus(Integer status);
}
